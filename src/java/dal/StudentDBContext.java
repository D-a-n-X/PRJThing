/**
 *
 * @DanX
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Student;

public class StudentDBContext extends DBContext<Student>{
    
    public ArrayList<Student> all(){
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [sid]\n" +
                        "      ,[scode]\n" +
                        "      ,[sname]\n" +
                        "      ,[img]\n" +
                        "      ,[mail]\n" +
                        "      ,[contact]\n" +
                        "      ,[gender]\n" +
                        "      ,[dob]\n" +
                        "      ,[accid]\n" +
                        "  FROM [dbo].[Student]";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setCode(rs.getString("scode"));
                s.setName(rs.getString("sname"));
                s.setImg(rs.getString("img"));
                s.setMail(rs.getString("mail"));
                s.setContact(rs.getInt("contact"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                Account a = new Account();
                a.setAccid(rs.getInt("accid"));
                s.setAccount(a);
                students.add(s);
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
        return students;
    }
    
    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
