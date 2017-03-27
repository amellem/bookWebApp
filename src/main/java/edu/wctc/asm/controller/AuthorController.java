/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.controller;

import edu.wctc.asm.model.Author;
import edu.wctc.asm.model.AuthorDao;
import edu.wctc.asm.model.AuthorService;
import edu.wctc.asm.model.DbAccessor;
import edu.wctc.asm.model.IAuthorDao;
import edu.wctc.asm.model.MySqlDbAccessor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author CloudAerius
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})

public class AuthorController extends HttpServlet {

    private String dbStrategyClassName;
    private String daoClassName;
    private String jndiName;
    // Get init params from web.xml
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    private String resultPage;
    private static final String HOME_PAGE = "index.html";
    private String authorId;

    private void refreshList(AuthorService as, HttpServletRequest request)
            throws ClassNotFoundException, SQLException {
        List<Author> a = as.getAuthors("author", 50);
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
        String action = request.getParameter("action");
        authorId = request.getParameter("selectedAuthor");

        try {
            AuthorService as = injectDependenciesAndGetAuthorService();

            switch (action) {
                case "authorList":
                    resultPage = "authorList.jsp";
                    refreshList(as, request);
                    break;
                    
                case "authorAdd":
                    resultPage = "authorAdd.jsp";
                    refreshList(as, request);
                    break;
                    
                case "confirmAdd":
                    resultPage = "authorList.jsp";
                    if (!request.getParameter("authorName").isEmpty()) {
                        Date date = new Date();
                        List<String> colNames = new ArrayList<>();
                        colNames.add("author_name");
                        colNames.add("date_added");
                        List colValues = new ArrayList<>();
                        colValues.add(request.getParameter("authorName"));
                        colValues.add(date);
                        as.addAuthor("author", colNames, colValues);
                    }
                    refreshList(as, request);
                    break;
                    
                case "authorUpdate":
                    resultPage = "authorUpdate.jsp";
                    refreshList(as, request);
                    break;
                    
                case "authorToUpdate":
                    resultPage = "confirmUpdate.jsp";

                    if (authorId != null) {
                        List<Author> authors = as.getAuthors("author", 50);
                        for (Author a : authors) {
                            if (Objects.equals(a.getAuthorId(), Integer.valueOf(authorId))) {
                                request.setAttribute("authorId", a.getAuthorId());
                                request.setAttribute("authorName", a.getAuthorName());
                                request.setAttribute("dateAdded", a.getDateAdded());
                            }
                        }
                    }
                    break;
                    
                case "updateConfirmed":
                    resultPage = "authorList.jsp";
                    List<String> colNames = new ArrayList<>();
                    colNames.add("author_name");
                    List colValues = new ArrayList<>();
                    colValues.add(request.getParameter("authorName"));

                    as.updateAuthor("author", colNames, colValues, "author_id", request.getParameter("authorId"));

                    refreshList(as, request);
                    break;
                    
                case "authorDelete":
                    resultPage = "authorDelete.jsp";
                    refreshList(as, request);
                    break;
                    
                case "authorToDelete":
                    resultPage = "authorList.jsp";
                    as.deleteAuthor("author", "author_id", request.getParameter("authorId"));
                    refreshList(as, request);
                    break;
                    
                default:
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(response.encodeRedirectURL(resultPage));

        view.forward(request, response);

    }

    private AuthorService injectDependenciesAndGetAuthorService() 
            throws Exception {
        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DBStrategy based on the class name retrieved
        // from web.xml
        Class dbClass = Class.forName(dbStrategyClassName);
        // Use Java reflection to instanntiate the DBStrategy object
        // Note that DBStrategy classes have no constructor params
        DbAccessor db = (DbAccessor) dbClass.newInstance();

        // Use Liskov Substitution Principle and Java Reflection to
        // instantiate the chosen DAO based on the class name retrieved above.
        // This one is trickier because the available DAO classes have
        // different constructor params
        IAuthorDao authorDao = null;
        Class daoClass = Class.forName(daoClassName);
        Constructor constructor = null;
        
        // This will only work for the non-pooled AuthorDao
        try {
            constructor = daoClass.getConstructor(new Class[]{
                DbAccessor.class, String.class, String.class, String.class, String.class
            });
        } catch(NoSuchMethodException nsme) {
            // do nothing, the exception means that there is no such constructor,
            // so code will continue executing below
        }

        // constructor will be null if using connectin pool dao because the
        // constructor has a different number and type of arguments
        
        if (constructor != null) {
            // conn pool NOT used so constructor has these arguments
            Object[] constructorArgs = new Object[]{
                db, driverClass, url, userName, password
            };
            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);

        } else {
            /*
             Here's what the connection pool version looks like. First
             we lookup the JNDI name of the Glassfish connection pool
             and then we use Java Refletion to create the needed
             objects based on the servlet init params
             */
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(jndiName);
            constructor = daoClass.getConstructor(new Class[]{
                DataSource.class, DbAccessor.class
            });
            Object[] constructorArgs = new Object[]{
                ds, db
            };

            authorDao = (IAuthorDao) constructor
                    .newInstance(constructorArgs);
        }
        
        return new AuthorService(authorDao);
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     *
     * @throws javax.servlet.ServletException
     */
    @Override
    public void init()
            throws ServletException {
        // Get init params from web.xml
        driverClass = getServletContext().getInitParameter("driverClass");
        url = getServletContext().getInitParameter("url");
        userName = getServletContext().getInitParameter("username");
        password = getServletContext().getInitParameter("password");
        dbStrategyClassName = getServletContext().getInitParameter("dbStrategy");
        daoClassName = getServletContext().getInitParameter("authorDao");
        jndiName = getServletContext().getInitParameter("connPoolName");
    }

}
