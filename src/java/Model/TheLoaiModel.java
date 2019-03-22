/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
public class TheLoaiModel {

    public TheLoaiModel(int maTheLoai, String tenTheLoai) {
        this.maTheLoai=maTheLoai;
        this.tenTheLoai=tenTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }
        
    private int maTheLoai;
    private String tenTheLoai;
    
    public static boolean InsertNewTheLoai(Connection conn, TheLoaiModel theloai) 
            throws SQLException
    {                   
        int count = 0;
        try
        {
            String sql="INSERT INTO theloai (tentheloai) VALUES (?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, theloai.getTenTheLoai());      

            count = pstm.executeUpdate(); 
        } catch (SQLException ex) {
            count = 0;
        }
       return count>0;

    }
     
    public static List<TheLoaiModel> getAllTheLoai(Connection conn)
    {
        List<TheLoaiModel> listTheLoai = new ArrayList<TheLoaiModel>();
        
        String sql = "SELECT * FROM theloai";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next())
            {
                TheLoaiModel theLoaiModel = new TheLoaiModel(
                        Integer.parseInt(rs.getString("matheloai")),
                        rs.getString("tentheloai"));
             
                
                listTheLoai.add(theLoaiModel);
            }
            
        } catch (SQLException e) {
           
        } 
        
        return listTheLoai; 
    }
    
    public static boolean DeleteByMaTheLoai(Connection conn, int maTheLoai) throws SQLException
    {
        String sql= "DELETE FROM theloai WHERE maTheLoai = ?";
        
        int count = 0;
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maTheLoai);             
            count = pstm.executeUpdate(); 
            
        } catch (SQLException ex) {
            
        }
            
        System.out.println("count = "+count);
        
        return count>0;
    }
    
    public static boolean UpdateTheLoai(Connection conn, TheLoaiModel obj) 
            throws SQLException
    { 
        int count = 0;
        try
        {
            String sql="UPDATE theloai SET tentheloai = ? WHERE matheloai = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, obj.getTenTheLoai());
            pstm.setDouble(2, obj.getMaTheLoai());
       

            count = pstm.executeUpdate(); 

        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }
        
        return count>0;
    }
    
    public static TheLoaiModel FindByMaTheLoai(Connection conn, int matheloai) throws SQLException
    {
        String sql="SELECT * FROM theloai WHERE matheloai = ?";
        PreparedStatement pstm = conn.prepareStatement(sql); //cau lenh sql duoc compile va save trong 1 
                                                             //doi tuong PreparedStatement                    
        pstm.setInt(1, matheloai);
        
        ResultSet rs = pstm.executeQuery(); //thuc hien truy van
                     
        if (rs.next()) {  
            TheLoaiModel theLoaiModel = new TheLoaiModel(
                        Integer.parseInt(rs.getString("matheloai")),
                        rs.getString("tentheloai"));
         
            return theLoaiModel; 
        }
        return null;
        
    }
}


