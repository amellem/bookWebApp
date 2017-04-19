package edu.wctc.asm.service;

import edu.wctc.asm.entity.Author;
import edu.wctc.asm.entity.Book;
import edu.wctc.asm.repository.AuthorRepository;
import edu.wctc.asm.repository.BookRepository;
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
public class BookService {
    private transient final Logger LOG = LoggerFactory.getLogger(BookService.class);
    private AuthorService authorService;
    
    @Inject
    private BookRepository bookRepo;
    
    @Inject
    private AuthorRepository authorRepo;

    public BookService() {
    }
    
    public List<Book> findAll() {
        return bookRepo.findAll();
    }
    
    public Book findById(String id) {
        return bookRepo.findOne(new Integer(id));
    }
    
    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param book 
     */
    @Transactional
    public void remove(Book book) {
        LOG.debug("Deleting book: " + book.getTitle());
        bookRepo.delete(book);
    }

    /**
     * Spring performs a transaction with readonly=false. This
     * guarantees a rollback if something goes wrong.
     * @param book 
     */
    @Transactional
    public Book edit(Book book) {
        return bookRepo.saveAndFlush(book);
    }
    

//    public List<Book> searchByAuthorId(Integer id) {
//        return bookRepo.searchByAuthorId(id);
//    }
//    
//    public List<Book> searchByAuthorIdAndIsbn(Integer id, String isbn) {
//        return bookRepo.searchByAuthorIdAndIsbn(id, isbn);
//    }
//
//    public List<Book> searchByIsbn(String isbn) {
//        return bookRepo.searchByIsbn(isbn);        
//    } 
//
//    public List<Book> searchByAuthorIdAndTitle(Integer id, String title) {
//        return bookRepo.searchByAuthorIdAndTitle(id, title);        
//    }
//
//    public List<Book> searchByTitle(String title) {
//        return bookRepo.searchByTitle(title);        
//    }
//   
//    public List<Book> searchByAuthorIdAndIsbnAndTitle(Integer id, String isbn, String title) {
//        return bookRepo.searchByAuthorIdAndIsbnAndTitle(id, isbn, title);        
//    }
//
//    public List<Book> searchByIsbnAndTitle(String isbn, String title) {
//        return bookRepo.searchByIsbnAndTitle(isbn, title);        
//    }    
    
    @Transactional
    public void addNew(String authorId, String title, String isbn) {

        Book b = new Book();
        b.setTitle(title);
        b.setIsbn(isbn);
        Author a = authorService.findById(authorId);
        b.setAuthorEntity(a);
        a.getBookSet().add(b);
    }
    
    @Transactional
    public void update(String id, String title, String isbn) {

        Book b = this.findById(id);
        b.setTitle(title);
        b.setIsbn(isbn);
        this.edit(b);
    }
    
}
