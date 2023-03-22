/**
 *
 * @DanX
 */
package dal;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.*;
import model.Course;

public class CourseDBContext extends DBContext{

    @Override
    public void insert(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Course> all() {
ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT cid, ccode, cname FROM Course";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setCode(rs.getString("ccode"));
                c.setName(rs.getString("cname"));

                courses.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;    
    }
    
    public ArrayList<Course> getStdCourse(int sid) {
        ArrayList<Course> courses = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.cid, c.ccode, c.cname FROM Course c\n"
                    + "INNER JOIN GroupCourse gc ON c.cid = gc.cid\n"
                    + "INNER JOIN [Group] g ON g.gid = gc.gid\n"
                    + "INNER JOIN StudentGroup sg ON sg.gid = g.gid\n"
                    + "INNER JOIN Student s ON sg.sid = s.sid\n"
                    + "WHERE s.sid = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setCode(rs.getString("ccode"));
                c.setName(rs.getString("cname"));

                courses.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
}
