/**
 *
 * @DanX
 */
package dal;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;
import model.TimeSlot;

public class SlotDBContext extends DBContext{

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
    public ArrayList<TimeSlot> all() {
        ArrayList<TimeSlot> slots = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [tid]\n"
                    + "      ,[description]\n"
                    + "  FROM [TimeSlot]";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                TimeSlot d = new TimeSlot();
                d.setId(rs.getInt("tid"));
                d.setName(rs.getString("description"));
                slots.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return slots;
    }

}
