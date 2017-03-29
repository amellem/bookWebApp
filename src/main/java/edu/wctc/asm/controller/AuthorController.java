/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.controller;

import edu.wctc.asm.model.AbstractFacade;
import edu.wctc.asm.model.Author;
import edu.wctc.asm.model.AuthorFacade;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
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

    @EJB
    private AuthorFacade authorFacade;

    @EJB
    private AuthorFacade authorService;
    private String jndiName;

    private String resultPage;
    private static final String HOME_PAGE = "index.html";
    private String authorId;

    private void refreshList(AuthorFacade as, HttpServletRequest request)
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
        String action = request.getParameter("action");
        authorId = request.getParameter("selectedAuthor");

        try {

            switch (action) {
                case "authorList":
                    resultPage = "authorList.jsp";
                    refreshList(authorService, request);
                    break;

                case "authorAdd":
                    resultPage = "authorAdd.jsp";
                    refreshList(authorService, request);
                    break;

                case "confirmAdd":
                    resultPage = "authorList.jsp";
                    if (!request.getParameter("authorName").isEmpty()) {
                        authorService.addNew(request.getParameter("authorName"));
                    }
                    refreshList(authorService, request);
                    break;

                case "authorUpdate":
                    resultPage = "authorUpdate.jsp";
                    refreshList(authorService, request);
                    break;

                case "authorToUpdate":
                    resultPage = "confirmUpdate.jsp";
                    if (authorId != null) {
                        List<Author> authors = authorService.findAll();
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
                    authorService.update(request.getParameter("authorId"), request.getParameter("authorName"));
                    refreshList(authorService, request);
                    break;

                case "authorDelete":
                    resultPage = "authorDelete.jsp";
                    refreshList(authorService, request);
                    break;

                case "authorToDelete":
                    resultPage = "authorList.jsp";
                    authorService.deleteById(request.getParameter("authorId"));
                    refreshList(authorService, request);
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
    }

}
