/**
 *
 * @DanX
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import model.Account;
import model.Campus;

public class UserDBContext extends DBContext<Account>{

    public Account get(String username, String password, int campus) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT [accid],[username],[role] FROM [dbo].[Account]\n" +
            "WHERE [username] = ? AND [password] = ? AND [campid] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setInt(3, campus);
            rs = stm.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setAccid(rs.getInt("accid"));
                a.setUsername(username);
                a.setRole(rs.getBoolean("role"));
                Campus c = new Campus();
                c.setId(campus);
                a.setCampus(c);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public Account getUser(int accid){
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT [accid],[username],[role] FROM [dbo].[Account]\n" +
            "WHERE [accid] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, accid);
            rs = stm.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setAccid(rs.getInt("accid"));
                a.setUsername(rs.getString("username"));
                a.setRole(rs.getBoolean("role"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public void update(int id, int count) {
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE [dbo].[Account]\n"
                    + " SET [sessionacc] = ?\n"
                    + " WHERE [accid] = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, count);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void insert(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ArrayList<Account> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
