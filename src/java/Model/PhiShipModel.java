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
}