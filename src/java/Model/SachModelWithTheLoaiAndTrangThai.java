/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SachModelWithTheLoaiAndTrangThai {
    
    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
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

    private int maSach;
    private String tenTheLoai;    
    private String tenSach;
    private String nhaXuatBan;
    private int namXuatBan;
    private double giaBan;
    private String moTa;
    private String anhDaiDien;
    private int soLuongTon;

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public Double getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(Double phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    private String tenTacGia;
    private Double phanTramGiamGia;
    private Date ngayBatDauGiamGia;
    private Date ngayKetThucGiamGia;
    private int trangThai;

    public SachModelWithTheLoaiAndTrangThai(int maSach, String tenTheLoai, String tenSach, String nhaXuatBan, int namXuatBan, double giaBan, String moTa, String anhDaiDien, int soLuongTon, String tenTacGia, Double phanTramGiamGia, Date ngayBatDauGiamGia, Date ngayKetThucGiamGia, int trangThai) {
        this.maSach = maSach;
        this.tenTheLoai = tenTheLoai;
        this.tenSach = tenSach;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.anhDaiDien = anhDaiDien;
        this.soLuongTon = soLuongTon;
        this.tenTacGia = tenTacGia;
        this.phanTramGiamGia = phanTramGiamGia;
        this.ngayBatDauGiamGia = ngayBatDauGiamGia;
        this.ngayKetThucGiamGia = ngayKetThucGiamGia;
        this.trangThai = trangThai;
    }

    
    
    
     public static List<SachModelWithTheLoaiAndTrangThai> getAllSachWithTheLoaiAndTrangThai(Connection conn) {
        List<SachModelWithTheLoaiAndTrangThai> listSach = new ArrayList<SachModelWithTheLoaiAndTrangThai>();

        String sql = "SELECT masach, tentheloai,tensach, nhaxuatban, namxuatban, giaban, mota, anhdaidien, soluongton, tentacgia,phantramgiamgia,ngaybatdaugiamgia,ngayketthucgiamgia, trangthai "
                + " FROM sach, theloai where sach.matheloai=theloai.matheloai AND trangthai<> ? "
                + "ORDER BY masach ASC";
        
         
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, SachModel.TRANGTHAI_XOA);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                SachModelWithTheLoaiAndTrangThai sachModel = new SachModelWithTheLoaiAndTrangThai(
                    rs.getInt("masach"),
                    rs.getString("tentheloai"),
                    rs.getString("tensach"),
                    rs.getString("nhaxuatban"),
                    rs.getInt("namxuatban"),
                    rs.getDouble("giaban"),
                    rs.getString("mota"),
                    rs.getString("anhdaidien"),
                    rs.getInt("soluongton"),
                    rs.getString("tentacgia"),
                    rs.getDouble("phantramgiamgia"),
                    rs.getDate("ngaybatdaugiamgia"),
                    rs.getDate("ngayketthucgiamgia"),
                    rs.getInt("trangthai"));    

                listSach.add(sachModel);
            }

        } catch (SQLException e) {
       
        }
        return listSach;
    }

}
