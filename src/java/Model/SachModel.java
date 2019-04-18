/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MITICC06
 */
public class SachModel {

    
    
    
    private int maSach;
    private int maTheLoai;    
    private String tenSach;
    private String nhaXuatBan;
    private int namXuatBan;
    private double giaBan;
    private String moTa; 
    private String anhDaiDien;     
    private int soLuongTon;        
    private String tenTacGia; 
    private int phanTramGiamGia;
    private Date ngayBatDauGiamGia;
    private Date ngayKetThucGiamGia;
    private int trangThai;

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }


    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void settAnhDaiDien(String anhdaidien) {
        this.anhDaiDien = anhdaidien;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(int phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public Date getNgayBatDauGiamGia() {
        return ngayBatDauGiamGia;
    }

    public void setNgayBatDauGiamGia(Date ngayBatDauGiamGia) {
        this.ngayBatDauGiamGia = ngayBatDauGiamGia;
    }

    public Date getNgayKetThucGiamGia() {
        return ngayKetThucGiamGia;
    }

    public void setNgayKetThucGiamGia(Date ngayKetThucGiamGia) {
        this.ngayKetThucGiamGia = ngayKetThucGiamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

        
    public SachModel(int masach, int matheloai, String tensach, String nhaxuatban, int namxuatban, double giaban,
            String mota, String anhDaiDien, int soluongton, String tentacgia) {
        this.maSach = masach;
        this.maTheLoai = matheloai;
        this.tenSach = tensach;
        this.nhaXuatBan = nhaxuatban;
        this.namXuatBan = namxuatban;
        this.giaBan = giaban;
        this.moTa = mota;
        this.anhDaiDien = anhDaiDien;
        this.soLuongTon = soluongton;
        this.tenTacGia = tentacgia;
    }

    
    public SachModel(int masach, int matheloai, String tensach,
            String nhaxuatban, int namxuatban, double giaban,
            String mota, String hinhanh, int soluongton, 
            String tentacgia,int phantramgiamgia, 
            Date ngaybatdaugiamgia, Date ngayketthucgiamgia, int trangthai) {
        this.maSach = masach;
        this.maTheLoai = matheloai;
        this.tenSach = tensach;
        this.nhaXuatBan = nhaxuatban;
        this.namXuatBan = namxuatban;
        this.giaBan = giaban;
        this.moTa = mota;
        this.anhDaiDien = anhDaiDien;
        this.soLuongTon = soluongton;
        this.tenTacGia = tentacgia;
        this.phanTramGiamGia=phantramgiamgia;
        this.ngayBatDauGiamGia = ngaybatdaugiamgia;
        this.ngayKetThucGiamGia = ngayketthucgiamgia;
        this.trangThai= trangthai;
    }

    
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
                    rs.getString("anhdaidien"),
                    Integer.parseInt(rs.getString("soluongton")), 
                    rs.getString("tentacgia"));
         
         
             return sach; 
        }
        return null;
        
    }
    public static SachModel FindFullByMaSach(Connection conn, int masach) throws SQLException
    {
              
        
        String sql = "SELECT * FROM sach WHERE masach = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, masach);
        
        ResultSet rs = pstm.executeQuery();
                     

        if (rs.next()) {  
             SachModel SachModel = new SachModel(
    
                        Integer.parseInt(rs.getString("masach")),
                        Integer.parseInt(rs.getString("matheloai")),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        Integer.parseInt(rs.getString("namxuatban")),
                        Double.parseDouble(rs.getString("giaban")),
                        rs.getString("mota"),
                        
                        rs.getString("anhdaidien"),
                        Integer.parseInt(rs.getString("soluongton")),
                        rs.getString("tentacgia"),
                        Integer.parseInt(rs.getString("phantramgiamgia")),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"), 
                        Integer.parseInt(rs.getString("trangthai")));
         
             return SachModel; 
        }
        return null;
        
    }
    
    
     public static boolean InsertNewSach(Connection conn, SachModel sach )
            throws SQLException {
        int count = 0;
        try {
            String sql = "INSERT INTO sach ( matheloai, tensach, nhaxuatban, namxuatban ,"
                    + " giaban, mota, anhdaidien, soluongton,"
                    + " tentacgia, phantramgiamgia, ngaybatdaugiamgia,"
                    + " ngayketthucgiamgia, trangthai) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sach.getMaTheLoai());
            pstm.setString(2, sach.getTenSach());
            pstm.setString(3, sach.getNhaXuatBan());
            pstm.setInt(4, sach.getNamXuatBan());
            pstm.setDouble(5, sach.getGiaBan());
            pstm.setString(6, sach.getMoTa());
              //if (sach.getHinhanh() != null) {
                //files are treated as BLOB objects in database
                //here we're letting the JDBC driver
                //create a blob object based on the
                //input stream that contains the data of the file
                pstm.setString(7,sach.getAnhDaiDien());
            //}
            
            pstm.setInt(8, sach.getSoLuongTon());
            pstm.setString(9, sach.getTenTacGia());
            pstm.setInt(10, sach.getPhanTramGiamGia());
            pstm.setDate(11, sach.getNgayBatDauGiamGia());
            pstm.setDate(12, sach.getNgayKetThucGiamGia());
            pstm.setInt(13, sach.getTrangThai());

            
            count = pstm.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            count = 0;
        }
        return count > 0;
    }
     
     public static List<SachModel>getAllSach(Connection conn) {
        List<SachModel> listSach = new ArrayList<SachModel>();

        String sql = "SELECT * FROM sach";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                        
                        SachModel SachModel = new SachModel(
    
                        Integer.parseInt(rs.getString("masach")),
                        Integer.parseInt(rs.getString("matheloai")),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        Integer.parseInt(rs.getString("namxuatban")),
                        Double.parseDouble(rs.getString("giaban")),
                        rs.getString("mota"),
                        
                        rs.getString("anhdaidien"),
                        Integer.parseInt(rs.getString("soluongton")),
                        rs.getString("tentacgia"),
                        Integer.parseInt(rs.getString("phantramgiamgia")),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"), 
                        Integer.parseInt(rs.getString("trangthai")));
                      
                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }

     public static boolean UpdateSach(Connection conn,SachModel sach, Boolean updateAnh)throws SQLException {
                        
         int count = 0;
        try {
            String sql;
            if(updateAnh){
            sql = "UPDATE sach SET matheloai = ?,tensach=?,nhaxuatban=?,"
                    + "namxuatban=?, giaban = ?, mota = ?, "
                    + "anhdaidien = ?, soluongton =?,"
                    + "tentacgia = ?, phantramgiamgia =?,"
                    + "ngaybatdaugiamgia = ?, ngayketthucgiamgia =?,"
                    + "trangthai = ? WHERE masach = ?";
            }
            else{
                sql = "UPDATE sach SET matheloai = ?,tensach=?,nhaxuatban=?,"
                    + "namxuatban=?, giaban = ?, mota = ?, "
                    + "soluongton =?,"
                    + "tentacgia = ?, phantramgiamgia =?,"
                    + "ngaybatdaugiamgia = ?, ngayketthucgiamgia =?,"
                    + "trangthai = ? WHERE masach = ?";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, sach.getMaTheLoai());
            pstm.setString(2, sach.getTenSach());
            pstm.setString(3, sach.getNhaXuatBan());
            pstm.setInt(4, sach.getNamXuatBan());
            pstm.setDouble(5, sach.getGiaBan());
            pstm.setString(6, sach.getMoTa());
            if(updateAnh){
                
            pstm.setString(7, sach.getAnhDaiDien());
            pstm.setInt(8, sach.getSoLuongTon());
            pstm.setString(9, sach.getTenTacGia());
            pstm.setInt(10, sach.getPhanTramGiamGia());
            pstm.setDate(11, sach.getNgayBatDauGiamGia());
            pstm.setDate(12, sach.getNgayKetThucGiamGia());
            pstm.setInt(13, sach.getTrangThai());
            pstm.setInt(14, sach.getMaSach());

            }
            else{
                 pstm.setInt(7, sach.getSoLuongTon());
            pstm.setString(8, sach.getTenTacGia());
            pstm.setInt(9, sach.getPhanTramGiamGia());
            pstm.setDate(10, sach.getNgayBatDauGiamGia());
            pstm.setDate(11, sach.getNgayKetThucGiamGia());
            pstm.setInt(12, sach.getTrangThai());
            pstm.setInt(13, sach.getMaSach());
            }
            
                
            count = pstm.executeUpdate();
        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }
        return count > 0;
    
     }

     
     
      public static List<AjaxModel> FindTenTacGia(Connection conn, String tenTacGia) throws SQLException {

        List<AjaxModel> listTenTacGia = new ArrayList<AjaxModel>();

        String sql = "SELECT DISTINCT tentacgia FROM sach WHERE tentacgia LIKE ? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1 , "%"+tenTacGia+"%"); // tìm bằng tên tác giả

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                AjaxModel ajaxSach = new AjaxModel( 0 ,rs.getString("tentacgia") );

                listTenTacGia.add(ajaxSach);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listTenTacGia;

    }
     
      
      public static List<AjaxModel> FindNhaXuatBan(Connection conn, String nhaXuatBan) throws SQLException {

        List<AjaxModel> listTenNhaXuatBan = new ArrayList<AjaxModel>();

        String sql = "SELECT DISTINCT nhaxuatban FROM sach WHERE nhaxuatban LIKE ? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1 , "%"+nhaXuatBan+"%"); // tìm bằng tên tác giả

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                AjaxModel ajaxSach = new AjaxModel( 0 ,rs.getString("nhaxuatban") );

                listTenNhaXuatBan.add(ajaxSach);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listTenNhaXuatBan;

    }
     
}
