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
import java.util.List;
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

    private static final String RESULT_PAGE = "authorList.jsp";
    private static final String HOME_PAGE = "index.html";

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

        try {
            AuthorService as = new AuthorService(
//                    new AuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin"));
                    new AuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/bookDb", "root", "admin"));

            if (action.equals("authorList")) {
                List<Author> a = as.getAuthors("author", 50);
                request.setAttribute("authors", a);
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e.getMessage());
        }

        RequestDispatcher view
                = request.getRequestDispatcher(RESULT_PAGE);
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
