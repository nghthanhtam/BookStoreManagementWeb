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
 * @author MITICC06
 */
public class SachModel {

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getNhaxuatban() {
        return nhaxuatban;
    }

    public void setNhaxuatban(String nhaxuatban) {
        this.nhaxuatban = nhaxuatban;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(int matheloai) {
        this.matheloai = matheloai;
    }

    public Blob getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(Blob hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public SachModel(int masach, String tensach, String nhaxuatban, int namxuatban, double giaban, String mota, int matheloai, Blob hinhanh, int soluongton, String tentacgia) {
        this.masach = masach;
        this.tensach = tensach;
        this.nhaxuatban = nhaxuatban;
        this.namxuatban = namxuatban;
        this.giaban = giaban;
        this.mota = mota;
        this.matheloai = matheloai;
        this.hinhanh = hinhanh;
        this.soluongton = soluongton;
        this.tentacgia = tentacgia;
    }
    
    private int masach;
    private String tensach;
    private String nhaxuatban;
    private int namxuatban;
    private double giaban;
    private String mota;
    private int matheloai;     
    private Blob hinhanh;     
    private int soluongton;        
    private String tentacgia;      
             
    
    
    public static SachModel FindByMaSach(Connection conn, int masach) throws SQLException
    {
        
        
        
        String sql = "SELECT * FROM sach WHERE masach = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, masach);
        
        ResultSet rs = pstm.executeQuery();
                     

        if (rs.next()) {  
            SachModel sach = new SachModel(
                    Integer.parseInt(rs.getString("masach")), 
                    rs.getString("tensach") ,
                    rs.getString("nhaxuatban"),
                    Integer.parseInt(rs.getString("namxuatban")),
                    Double.parseDouble(rs.getString("giaban")) , 
                    rs.getString("mota"),
                    Integer.parseInt(rs.getString("matheloai")), 
                    rs.getBlob("hinhanh"),
                    Integer.parseInt(rs.getString("soluongton")), 
                    rs.getString("tentacgia"));
         
         
             return sach; 
        }
        return null;
        
    }
    
            
}
