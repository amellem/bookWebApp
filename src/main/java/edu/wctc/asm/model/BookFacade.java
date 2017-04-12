package edu.wctc.asm.model;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author CloudAerius
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @EJB
    private AuthorFacade authorFacade;

    @PersistenceContext(unitName = "edu.wctc.asm_bookWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }

    public int deleteById(String id) {

        Integer iId = Integer.parseInt(id);
        String jpql = "delete from Book b where b.bookId = :id";
        Query q = this.getEntityManager().createQuery(jpql);
        q.setParameter("id", iId);
        return q.executeUpdate();
    }

    public void addNew(String authorId, String title, String isbn) {

        Book b = new Book();
        b.setTitle(title);
        b.setIsbn(isbn);
        Author a = authorFacade.findAuthorById(authorId);
        b.setAuthorEntity(a);
        a.getBookSet().add(b);
    }

    public void update(String id, String title, String isbn) {

        Book b = this.find(Integer.parseInt(id));
        b.setTitle(title);
        b.setIsbn(isbn);
        this.edit(b);
    }

}
