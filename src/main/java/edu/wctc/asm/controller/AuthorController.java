/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.controller;

import edu.wctc.asm.model.Author;
import edu.wctc.asm.model.AuthorDao;
import edu.wctc.asm.model.AuthorService;
import edu.wctc.asm.model.MySqlDbAccessor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

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
            AuthorService as = new AuthorService(
                    //                    new AuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin"));
                    new AuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/bookdb", "root", "admin"));

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
                default:
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(resultPage);

        view.forward(request, response);

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

}
