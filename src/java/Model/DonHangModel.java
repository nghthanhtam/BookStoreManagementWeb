/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.MySQLConnUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TamTorres
 */
public class DonHangModel {

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getDiaChiGiaoHang() {
        return diaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        this.diaChiGiaoHang = diaChiGiaoHang;
    }

    public int getMaPhiShip() {
        return maPhiShip;
    }

    public void setMaPhiShip(int maPhiShip) {
        this.maPhiShip = maPhiShip;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public double getPhiShip() {
        return phiShip;
    }

    public void setPhiShip(double phiShip) {
        this.phiShip = phiShip;
    }
     
    private String soDienThoai;
    private String ghiChu;
    private int maDonHang;
    private int maThanhVien;
    private Date ngayLap;
    private double tongTien;
    private int trangThai;
    private String diaChiGiaoHang;
    private int maPhiShip;
    private double phiShip;

    
    public DonHangModel(int maDonHang, int maThanhVien, Date ngayLap, double tongTien, int trangThai, String diaChiGiaoHang, int maPhiShip, double phiship, String soDienThoai, String ghiChu) {
        this.maDonHang = maDonHang;
        this.maThanhVien = maThanhVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.diaChiGiaoHang = diaChiGiaoHang;
        this.maPhiShip = maPhiShip;
        this.phiShip = phiship;
        this.soDienThoai = soDienThoai;
        this.ghiChu = ghiChu;
    }

   
    

    public static boolean InsertDonHang(Connection conn, DonHangModel donHang)
            throws SQLException {
        int count = 0;
        try {
            String sql = "INSERT INTO donhang (mathanhvien, ngaylap, tongtien ,trangthai, diachigiaohang, maphiship, phiship, sodienthoai, ghichu) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, donHang.getMaThanhVien());
            pstm.setDate(2, donHang.getNgayLap());
            pstm.setDouble(3, donHang.getTongTien());
            pstm.setInt(4, donHang.getTrangThai());
            pstm.setString(5, donHang.getDiaChiGiaoHang());
            pstm.setInt(6, donHang.getMaPhiShip());
            pstm.setDouble(7, donHang.getPhiShip());
            pstm.setString(8, donHang.getSoDienThoai());
            pstm.setString(9, donHang.getGhiChu());

            count = pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            count = 0;
        }

        return count > 0;
    }
    
    public static int getMaDonHangCurrent(Connection conn) throws SQLException {
        
        String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, MySQLConnUtils.dbName);
        pstm.setString(2, "donhang");

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getInt("AUTO_INCREMENT");
        }
        return 0;
    }
    
    public static List<DonHangModel> getAllDonHang(Connection conn) {
        List<DonHangModel> list = new ArrayList<DonHangModel>();

        String sql = "SELECT * FROM donhang";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                DonHangModel obj = new DonHangModel(
                        rs.getInt("madonhang"),
                        rs.getInt("mathanhvien"),
                        rs.getDate("ngaylap"),
                        rs.getDouble("tongtien"),
                        rs.getInt("trangthai"),
                        rs.getString("diachigiaohang"),
                        rs.getInt("maphiship"),
                        rs.getDouble("phiship"),
                        rs.getString("sodienthoai"),
                        rs.getString("ghichu"));
                list.add(obj);
            }

        } catch (SQLException e) {
            
        }

        return list;
    }
    
     public static List<DonHangModel> getAllDonHangByMaThanhVien(Connection conn, int maThanhVien) {
        List<DonHangModel> list = new ArrayList<DonHangModel>();

        String sql = "SELECT * FROM donhang WHERE mathanhvien = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maThanhVien);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                DonHangModel obj = new DonHangModel(
                        rs.getInt("madonhang"),
                        rs.getInt("mathanhvien"),
                        rs.getDate("ngaylap"),
                        rs.getDouble("tongtien"),
                        rs.getInt("trangthai"),
                        rs.getString("diachigiaohang"),
                        rs.getInt("maphiship"),
                        rs.getDouble("phiship"),
                        rs.getString("sodienthoai"),
                        rs.getString("ghichu"));
                list.add(obj);
            }

        } catch (SQLException e) {
            
        }

        return list;
    }
}
