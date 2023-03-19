/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

public abstract class BaseRequiredAuthenticatedController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("acc") != null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            Account a = (Account) request.getSession().getAttribute("acc");
            UserDBContext updb = new UserDBContext();
            int count = a.getSessionCount() + 1;
            updb.update(a.getAccid(), count);
            doGet(request, response, (Account) request.getSession().getAttribute("acc"));
        } else {
            response.sendRedirect("http://localhost:9999/PRJ_Assignment/accessDenied");
//            response.getWriter().println("access denied!");
        }
    }

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, Account acc)
            throws ServletException, IOException;

    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, Account acc)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuthenticated(request)) {
            doPost(request, response, (Account) request.getSession().getAttribute("acc"));
        } else {
            response.sendRedirect("http://localhost:9999/PRJ_Assignment/accessDenied");
//            response.getWriter().println("access denied!");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
