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
import java.sql.Date;

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
    
    
    public Date getNgaybatdaugiamgia() {
        return ngaybatdaugiamgia;
    }

    public void setNgaybatdaugiamgia(Date ngaybatdaugiamgia) {
        this.ngaybatdaugiamgia = ngaybatdaugiamgia;
    }

    public Date getNgayketthucgiamgia() {
        return ngayketthucgiamgia;
    }

    public SachModel(int masach, int matheloai, String tensach, String nhaxuatban, int namxuatban, double giaban, String mota, Blob hinhanh, int soluongton, String tentacgia) {
        this.masach = masach;
        this.matheloai = matheloai;
        this.tensach = tensach;
        this.nhaxuatban = nhaxuatban;
        this.namxuatban = namxuatban;
        this.giaban = giaban;
        this.mota = mota;
        this.hinhanh = hinhanh;
        this.soluongton = soluongton;
        this.tentacgia = tentacgia;
    }

    public void setNgayketthucgiamgia(Date ngayketthucgiamgia) {
        this.ngayketthucgiamgia = ngayketthucgiamgia;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public SachModel(int masach, int matheloai, String tensach, String nhaxuatban, int namxuatban, double giaban, String mota, Blob hinhanh, int soluongton, String tentacgia,int phantramgiamgia, Date ngaybatdaugiamgia, Date ngayketthucgiamgia, int trangthai) {
        this.masach = masach;
        this.matheloai = matheloai;
        this.tensach = tensach;
        this.nhaxuatban = nhaxuatban;
        this.namxuatban = namxuatban;
        this.giaban = giaban;
        this.mota = mota;
        this.hinhanh = hinhanh;
        this.soluongton = soluongton;
        this.tentacgia = tentacgia;
        this.phantramgiamgia=phantramgiamgia;
        this.ngaybatdaugiamgia = ngaybatdaugiamgia;
        this.ngayketthucgiamgia = ngayketthucgiamgia;
        this.trangthai= trangthai;
    }

    
    private int masach;
    private int matheloai;    
    private String tensach;
    private String nhaxuatban;
    private int namxuatban;
    private double giaban;
    private String mota; 
    private Blob hinhanh;     
    private int soluongton;        
    private String tentacgia; 
    private int phantramgiamgia;

    public int getPhantramgiamgia() {
        return phantramgiamgia;
    }

    public void setPhantramgiamgia(int phantramgiamgia) {
        this.phantramgiamgia = phantramgiamgia;
    }
    private Date ngaybatdaugiamgia;
    private Date ngayketthucgiamgia;
    private int trangthai;

    
    
    
    public static SachModel FindByMaSach(Connection conn, int masach) throws SQLException
    {
              
        
        String sql = "SELECT * FROM sach WHERE masach = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, masach);
        
        ResultSet rs = pstm.executeQuery();
                     

        if (rs.next()) {  
            SachModel sach = new SachModel(
                    Integer.parseInt(rs.getString("masach")), 
                    Integer.parseInt(rs.getString("matheloai")), 
                    rs.getString("tensach") ,
                    rs.getString("nhaxuatban"),
                    Integer.parseInt(rs.getString("namxuatban")),
                    Double.parseDouble(rs.getString("giaban")) , 
                    rs.getString("mota"),
                    rs.getBlob("hinhanh"),
                    Integer.parseInt(rs.getString("soluongton")), 
                    rs.getString("tentacgia"));
         
         
             return sach; 
        }
        return null;
        
    }
    
     public static boolean InsertNewSach(Connection conn, SachModel sach)
            throws SQLException {
        int count = 0;
        try {
            String sql = "INSERT INTO sach ( matheloai, tensach, nhaxuatban, namxuatban , giaban, mota, anhdaidien, soluongton,"
                    + " tentacgia, phantramgiamgia, ngaybatdaugiamgia, ngayketthucgiamgia, trangthai) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sach.getMatheloai());
            pstm.setString(2, sach.getTensach());
            pstm.setString(3, sach.getNhaxuatban());
            pstm.setInt(4, sach.getNamxuatban());
            pstm.setDouble(5, sach.getGiaban());
            pstm.setString(6, sach.getMota());
            pstm.setBlob(7, sach.getHinhanh());
            pstm.setInt(8, sach.getSoluongton());
            pstm.setString(9, sach.getTentacgia());
            pstm.setInt(10, sach.getPhantramgiamgia());
            pstm.setDate(11, sach.getNgaybatdaugiamgia());
            pstm.setDate(12, sach.getNgayketthucgiamgia());
            pstm.setInt(13, sach.getTrangthai());

            
            count = pstm.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            count = 0;
        }
        return count > 0;
    }

}
