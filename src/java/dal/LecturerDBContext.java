/**
 *
 * @DanX
 */
package dal;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.*;
import model.*;

public class LecturerDBContext extends DBContext {

    @Override
    public ArrayList<Lecturer> get(int id) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT l.lid, l.lcode, l.lname, l.img, l.mail, l.accid, a.accname\n"
                    + "FROM Lecturer l INNER JOIN Account a\n"
                    + "ON l.accid = a.accid\n"
                    + "WHERE l.lid = ?\n";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                l.setCode(rs.getString("lcode"));
                l.setImg(rs.getString("img"));
                l.setMail(rs.getString("mail"));
                Account a = new Account();
                a.setAccid(rs.getInt("accid"));
                a.setAccname(rs.getString("accname"));
                l.setAccount(a);

                lecturers.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecturers;
    }
    
    public ArrayList<Lecturer> getLecCode(int id) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT l.lid, l.lcode, l.lname, l.img, l.mail, l.accid, a.accname\n"
                    + "FROM Lecturer l INNER JOIN Account a\n"
                    + "ON l.accid = a.accid\n"
                    + "WHERE l.accid = ?\n";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            while (rs.next()) {
                Lecturer l = new Lecturer();
                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                l.setCode(rs.getString("lcode"));
                l.setImg(rs.getString("img"));
                l.setMail(rs.getString("mail"));
                Account a = new Account();
                a.setAccid(rs.getInt("accid"));
                a.setAccname(rs.getString("accname"));
                l.setAccount(a);
                lecturers.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecturers;
    }
    
    public Lecturer getTimeTable(int lid, Date from, Date to) {
        Lecturer lecturer = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ses.sessionid,ses.date,ses.status,g.gid,g.gname,r.rid,r.rname,l.lid,l.lcode,l.lname,t.tid,t.description,c.cid,c.ccode,c.cname\n"
                    + "FROM [Lecturer] l\n"
                    + "INNER JOIN [Session] ses ON ses.lid = l.lid\n"
                    + "INNER JOIN [Group] g ON ses.gid = g.gid\n"
                    + "INNER JOIN [TimeSlot] t ON t.tid = ses.tid\n"
                    + "INNER JOIN [Room] r ON r.rid = ses.rid\n"
                    + "INNER JOIN [Course] c ON c.cid = ses.cid\n"
                    + "WHERE l.lid = ? AND ses.date >= ? AND ses.date <= ? ORDER BY l.lid,g.gid";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (lecturer == null) {
                    lecturer = new Lecturer();
                    lecturer.setId(rs.getInt("lid"));
                    lecturer.setCode(rs.getString("lcode"));
                    lecturer.setName(rs.getString("lname"));
                }
                Group group = new Group();
                group.setId(rs.getInt("gid"));
                group.setName(rs.getString("gname"));

                Session ses = new Session();
                ses.setId(rs.getInt("sessionid"));
                ses.setDate(rs.getDate("date"));
                ses.setStatus(rs.getBoolean("status"));
                ses.setGroup(group);

                Course c = new Course();
                c.setId(rs.getInt("cid"));
                c.setCode(rs.getString("ccode"));
                c.setName(rs.getString("cname"));
                ses.setCourse(c);

                Room r = new Room();
                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                TimeSlot t = new TimeSlot();
                t.setId(rs.getInt("tid"));
                t.setName(rs.getString("description"));
                ses.setSlot(t);
                
                lecturer.getSessions().add(ses);
                
//                currentGroup.getSessions().add(ses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LecturerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecturer;
    }
    
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
    public ArrayList all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
