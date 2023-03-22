/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.main;

import controller.auth.BaseRequiredAuthenticatedController;
import dal.CampusDBContext;
import dal.CourseDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Campus;
import model.Course;
import model.Session;
import model.Student;

/**
 *
 * @author admin
 */
public class AttendanceCheckController extends BaseRequiredAuthenticatedController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req
     * @param resp
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int a = (int) req.getSession().getAttribute("id");
        StudentDBContext studb = new StudentDBContext();
        ArrayList<Student> stu = studb.getStdCode(a);
        req.setAttribute("stu", stu);
        Student currentStu = stu.get(0);

        CampusDBContext camp = new CampusDBContext();
        ArrayList<Campus> camps = camp.search(a);
        req.setAttribute("camps", camps);

        CourseDBContext cb = new CourseDBContext();
        ArrayList<Course> courses = cb.getStdCourse(currentStu.getId());
        req.setAttribute("courses", courses);
        String raw_course = req.getParameter("course");
        if (raw_course != null) {
            int course = Integer.parseInt(raw_course);
            SessionDBContext sdb = new SessionDBContext();
            ArrayList<Session> ses1 = sdb.checkAtt(course, currentStu.getId());
            req.setAttribute("ses1", ses1);
        }
        req.getRequestDispatcher("view/main/check.jsp").forward(req, resp);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account acc) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
