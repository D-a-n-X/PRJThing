/**
 *
 * @DanX
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

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
    
    public ArrayList<Student> getStdCode(int id) {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.sid, s.scode, s.sname, s.img, s.mail, s.contact, s.gender, s.dob, s.accid, a.accname\n"
                    + "FROM Student s INNER JOIN Account a\n"
                    + "ON s.accid = a.accid\n"
                    + "WHERE a.accid = ?\n";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
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
                a.setAccname(rs.getString("accname"));
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
    
    public Student getTimeTable(int sid, Date from, Date to) {
        Student student = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.sid,s.scode,s.sname,ses.sessionid,ses.date,ses.status\n"
                    + ",g.gid,g.gname,r.rid,r.rname,l.lid,l.lcode,l.lname,t.tid,t.description,c.cid,c.ccode,c.cname, att.aid, att.astatus\n"
                    + "FROM Student s\n"
                    + "INNER JOIN [StudentGroup] sg ON s.sid = sg.sid\n"
                    + "INNER JOIN [Group] g ON g.gid = sg.gid\n"
                    + "INNER JOIN [Session] ses ON g.gid = ses.gid\n"
                    + "LEFT JOIN [Attendance] att ON ses.sessionid = att.sessionid AND s.sid = att.sid\n"
                    + "INNER JOIN [TimeSlot] t ON t.tid = ses.tid\n"
                    + "INNER JOIN [Room] r ON r.rid = ses.rid\n"
                    + "INNER JOIN [Lecturer] l ON l.lid = ses.lid\n"
                    + "INNER JOIN [Course] c ON c.cid = ses.cid\n"
                    + "WHERE s.sid = ? AND ses.date >= ? AND ses.date <= ? ORDER BY s.sid,g.gid";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            Group currentGroup = new Group();
            currentGroup.setId(-1);
            while (rs.next()) {
                if (student == null) {
                    student = new Student();
                    student.setId(rs.getInt("sid"));
                    student.setCode(rs.getString("scode"));
                    student.setName(rs.getString("sname"));
                }
                int gid = rs.getInt("gid");
                if (gid != currentGroup.getId()) {
                    currentGroup = new Group();
                    currentGroup.setId(rs.getInt("gid"));
                    currentGroup.setName(rs.getString("gname"));
                    student.getGroups().add(currentGroup);
                }
                Session ses = new Session();
                ses.setId(rs.getInt("sessionid"));
                ses.setDate(rs.getDate("date"));
                ses.setStatus(rs.getBoolean("status"));
                ses.setGroup(currentGroup);

                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setCode(rs.getString("ccode"));
                c.setName(rs.getString("cname"));
                ses.setCourse(c);
                
                Attendance att = new Attendance();
                att.setId(rs.getInt("aid"));
                att.setStatus(rs.getBoolean("astatus"));
                ses.setAttendance(att);
                
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                l.setCode(rs.getString("lcode"));
                ses.setLecturer(l);

                Room r = new Room();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setName(rs.getString("description"));
                ses.setSlot(t);

                currentGroup.getSessions().add(ses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return student;
    }
    public ArrayList<Student> searchBySes(int session) {
          ArrayList<Student> students = new ArrayList<>();
          PreparedStatement stm = null;
          ResultSet rs = null;
          try {
               String sql = "SELECT s.[sid], s.scode, s.sname, s.img, s.mail, s.contact, s.gender, s.dob, s.accid\n"
                       + "FROM Student s INNER JOIN StudentGroup sg\n"
                       + "ON s.[sid] = sg.[sid]\n"
                       + "INNER JOIN [Group] g ON g.gid = sg.gid\n"
                       + "INNER JOIN [Session] ses ON ses.gid = g.gid\n"
                       + "WHERE ses.sessionid = ?\n";
               stm = connection.prepareStatement(sql);
               stm.setInt(1, session);
               rs = stm.executeQuery();

               while (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getInt("sid"));
                    s.setCode(rs.getString("scode"));
                    s.setName(rs.getString("sname"));
                    s.setImg(rs.getString("img"));
                    s.setMail(rs.getString("mail"));
                    s.setContact(rs.getInt("contact"));
                    s.setGender(rs.getBoolean("gender"));
                    s.setDob(rs.getDate("dob"));

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
