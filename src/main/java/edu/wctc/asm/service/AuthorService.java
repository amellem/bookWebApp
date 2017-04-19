package edu.wctc.asm.service;

import edu.wctc.asm.entity.Author;
import edu.wctc.asm.repository.AuthorRepository;
import edu.wctc.asm.repository.BookRepository;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CloudAerius
 */
@Service
@Transactional(readOnly = true)
public class AuthorService {

    private transient final Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    @Inject
    private AuthorRepository authorRepo;

    @Inject
    private BookRepository bookRepo;

    public AuthorService() {
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }
    
    public List<Author> findAllEagerly() {
        List<Author> authors = authorRepo.findAll();
        for(Author a : authors) {
            a.getBookSet().size();
        }
        
        return authors;
    }
    
    /**
     * This custom method is necessary because we are using Hibernate which
     * does not load lazy relationships (in this case Books).
     * @param id
     * @return 
     */
    public Author findByIdAndFetchBooksEagerly(String id) {
        Integer authorId = new Integer(id);
        Author author = authorRepo.findOne(authorId);

        // Eagerly fetches books within a transaction
        author.getBookSet().size();
        
        return author;
    }

    public Author findById(String id) {
        return authorRepo.findOne(new Integer(id));
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param author 
     */
    @Transactional
    public void remove(Author author) {
        LOG.debug("Deleting author: " + author.getAuthorName());
        authorRepo.delete(author);
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param author 
     * @return  
     */
    @Transactional
    public Author edit(Author author) {
        return authorRepo.saveAndFlush(author);
    }
    
    @Transactional
    public void addNew(String name) {
        Author a = new Author();
        a.setAuthorName(name);
        Date createDate = new Date();
        a.setDateAdded(createDate);
        this.edit(a);
    }
    
    public void update(String id, String name) {
        Author a = this.findById(id);
        a.setAuthorName(name);
        this.edit(a);
    }
}
