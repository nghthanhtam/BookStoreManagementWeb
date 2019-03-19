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
/**
 *
 * @author TamTorres
 */
public class PhiShipModel {

    public int getMaPhiShip() {
        return maphiship;
    }   
    public void setMaPhiShip(int maphiship){
        this.maphiship = maphiship;
    }
    
    
    public String getTenTinh() {
        return tentinh;
    }   
    public void setTenTinh(String tentinh){
        this.tentinh = tentinh;
    }
    
    
        public double getPhiShip() {
        return phiship;
    }   
    public void setPhiShip(double phiship){
        this.phiship = phiship;
    }
    
    private int maphiship;
    private String tentinh;
    double phiship;
             
    
     public static PhiShipModel ThemPhiShip(Connection conn, PhiShipModel phiship) throws SQLException
    {                    
        //String sql = "INSERT * FROM sach WHERE masach = ? ";
        String sql = "INSERT INTO phiship (`maphiship`, `tentinh`, `phiship`) VALUES (?,?,?)";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        //pstm.setInt(1, masach);
        
        ResultSet rs = pstm.executeQuery();
                     

//        if (rs.next()) {  

//            SachModel sach = new SachModel(
//                    Integer.parseInt(rs.getString("masach")), 
//                    rs.getString("tensach") ,
//                    rs.getString("nhaxuatban"),
//                    Integer.parseInt(rs.getString("namxuatban")),
//                    Double.parseDouble(rs.getString("giaban")) , 
//                    rs.getString("mota"),
//                    Integer.parseInt(rs.getString("matheloai")), 
//                    rs.getBlob("hinhanh"),
//                    Integer.parseInt(rs.getString("soluongton")), 
//                    rs.getString("tentacgia"));
//         
//         
//             return sach; 
//        }
        return null;
        
    }
}
