package edu.wctc.asm.controller;

import edu.wctc.asm.model.Author;
import edu.wctc.asm.model.AuthorFacade;
import edu.wctc.asm.model.Book;
import edu.wctc.asm.model.BookFacade;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CloudAerius
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    @EJB
    private BookFacade bookService;
    
    @EJB
    private AuthorFacade authorService;
    
    private String resultPage;
    
    private void refreshList(BookFacade bs, HttpServletRequest request)
            throws ClassNotFoundException, SQLException {
        List<Book> b = bs.findAll();
        request.setAttribute("books", b);
    }
    
    private void authorList(AuthorFacade as, HttpServletRequest request)
            throws ClassNotFoundException, SQLException {
        List<Author> a = as.findAll();
        request.setAttribute("authors", a);
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        
        try {
            
            switch(action){
                
                case "bookIndex":
                    resultPage = "bookList.jsp";
                    refreshList(bookService, request);
                    break;
                    
                case "bookAdd":
                    resultPage = "bookAdd.jsp";
                    authorList(authorService, request);
                    break;
                    
                case "bookToAdd":
                    resultPage = "bookToAdd.jsp";
                    refreshList(bookService, request);
                    break;
                    
                default :
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }
        
        RequestDispatcher view
                = request.getRequestDispatcher(response.encodeRedirectURL(resultPage));

        view.forward(request, response);
    }

    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
