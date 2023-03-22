/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.main;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.*;
import java.util.ArrayList;
import model.*;
/**
 *
 * @author admin
 */
public class AttendanceReportController extends HttpServlet {

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
        int id = (int) req.getSession().getAttribute("id");
          UserDBContext udb = new UserDBContext();
          Account a = udb.getUser(id);
          req.setAttribute("role", a);

          CampusDBContext camp = new CampusDBContext();
          ArrayList<Campus> camps = camp.search(id);
          req.setAttribute("camps", camps);

          LecturerDBContext lecdb = new LecturerDBContext();
          ArrayList<Lecturer> lec = lecdb.getLecCode(id);
          req.setAttribute("lec", lec);

          DBContext<Course> cb = new CourseDBContext();
          ArrayList<Course> courses = cb.all();
          req.setAttribute("courses", courses);

          String raw_session = req.getParameter("session");

          if (a.isRole() == false) {
               if (raw_session != null) {
                    int session = Integer.parseInt(raw_session);
                    StudentDBContext db = new StudentDBContext();
                    ArrayList<Student> students = db.searchBySes(session);
                    req.setAttribute("students", students);

                    SessionDBContext sdb = new SessionDBContext();
                    ArrayList<Session> ss = sdb.search(session);
                    Session s1 = ss.get(0);

                    SessionDBContext ssdb = new SessionDBContext();
                    ArrayList<Session> sss = ssdb.countSes(s1.getCourse().getId(), s1.getGroup().getId());
                    req.setAttribute("ss", sss);

                    AttendanceDBContext adb = new AttendanceDBContext();
                    ArrayList<Attendance> atts = adb.searchBy(s1.getGroup().getId(), s1.getCourse().getId());
                    req.setAttribute("att", atts);
               }
               req.getRequestDispatcher("view/main/report.jsp").forward(req, resp);
          }
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
