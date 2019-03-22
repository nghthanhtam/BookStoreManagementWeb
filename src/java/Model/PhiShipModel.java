/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author TamTorres
 */
public class PhiShipModel {

      public PhiShipModel() {  
     
    }
      
    private int maPhiShip;
    private String tenTinh;
    private double phiShip;

    public PhiShipModel(int maPhiShip, String tenTinh, double phiShip) {
        this.maPhiShip = maPhiShip;
        this.tenTinh = tenTinh;
        this.phiShip = phiShip;
    }

    public int getMaPhiShip() {
        return maPhiShip;
    }

    public void setMaPhiShip(int maPhiShip) {
        this.maPhiShip = maPhiShip;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public double getPhiShip() {
        return phiShip;
    }

    public void setPhiShip(double phiShip) {
        this.phiShip = phiShip;
    }
             
    
     public static boolean InsertNewPhiShip(Connection conn, PhiShipModel phiship) 
            throws SQLException
    { 
                   
        int count = 0;
        try
        {
            String sql="INSERT INTO phiship (phiship, tentinh) VALUES (?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setDouble(1, phiship.getPhiShip());
            pstm.setString(2, phiship.getTenTinh());

            count = pstm.executeUpdate(); 
        } catch (SQLException ex) {
            count = 0;
        }
       return count>0;

    }
     
    public static List<PhiShipModel> getAllPhiShip(Connection conn)
    {
        List<PhiShipModel> listPhiShip = new ArrayList<PhiShipModel>();
        
        String sql = "SELECT * FROM phiship";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next())
            {
                PhiShipModel phiShipModel = new PhiShipModel(
                        Integer.parseInt(rs.getString("maphiship")),
                        rs.getString("tentinh"),
                        Double.parseDouble(rs.getString("phiship")));
             
                
                listPhiShip.add(phiShipModel);
            }
            
        } catch (SQLException e) {
           
        } 
        
        return listPhiShip; 
    }
    
    public static boolean DeleteByMaPhiShip(Connection conn, int maPhiShip) throws SQLException
    {
        String sql= "DELETE FROM phiship WHERE maPhiShip = ?";
        
        int count = 0;
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maPhiShip);             
            count = pstm.executeUpdate(); 
            
        } catch (SQLException ex) {
            
        }
            
        System.out.println("count = "+count);
        
        return count>0;
    }
    
    public static boolean UpdatePhiShip(Connection conn, PhiShipModel obj) 
            throws SQLException
    { 
        int count = 0;
        try
        {
            String sql="UPDATE phiship SET tentinh = ?, phiship = ? WHERE maphiship = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, obj.getTenTinh());
            pstm.setDouble(2, obj.getPhiShip());
            pstm.setDouble(3, obj.getMaPhiShip());

            count = pstm.executeUpdate(); 

        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }
        
        return count>0;
    }
    
    public static PhiShipModel FindByMaPhiShip(Connection conn, int maphiship) throws SQLException
    {
        String sql="SELECT * FROM phiship WHERE maphiship = ?";
        PreparedStatement pstm = conn.prepareStatement(sql); //cau lenh sql duoc compile va save trong 1 
                                                             //doi tuong PreparedStatement                    
        pstm.setInt(1, maphiship);
        
        ResultSet rs = pstm.executeQuery(); //thuc hien truy van
                     
        if (rs.next()) {  
            PhiShipModel phiShipModel = new PhiShipModel(
                        Integer.parseInt(rs.getString("maphiship")),
                        rs.getString("tentinh"),
                        Double.parseDouble(rs.getString("phiship")));
         
            return phiShipModel; 
        }
        return null;
        
    }
}