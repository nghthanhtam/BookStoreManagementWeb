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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MITICC06
 */
public class PhieuNhapModel {

    public PhieuNhapModel(int maPhieuNhap, Date ngayNhap, int maNhaCungCap, int maThanhVien, String ghiChu) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.maThanhVien = maThanhVien;
        this.ghiChu = ghiChu;
    }

    public PhieuNhapModel() {
    }
    private int maPhieuNhap;
    private Date ngayNhap;
    private int maNhaCungCap;
    private int maThanhVien;
    private String ghiChu;

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public static boolean InsertPhieuNhap(Connection conn, PhieuNhapModel phieuNhap)
            throws SQLException {
        int count = 0;
        try {
            String sql = "INSERT INTO phieunhap (ngaynhap, manhacungcap, mathanhvien ,ghichu) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setDate(1, phieuNhap.getNgayNhap());
            pstm.setInt(2, phieuNhap.getMaNhaCungCap());
            pstm.setInt(3, phieuNhap.getMaThanhVien());
            pstm.setString(4, phieuNhap.getGhiChu());

            count = pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            count = 0;
        }

        return count > 0;
    }

    public static int getMaPhieuNhapCurrent(Connection conn) throws SQLException {
        
        String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, MySQLConnUtils.dbName);
        pstm.setString(2, "phieunhap");

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getInt("AUTO_INCREMENT");
        }
        return 0;
    }

    
    public static List<PhieuNhapModel> getAllPhieuNhap(Connection conn) {
        List<PhieuNhapModel> list = new ArrayList<PhieuNhapModel>();

        String sql = "SELECT * FROM phieunhap ORDER BY maphieunhap ASC";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PhieuNhapModel obj = new PhieuNhapModel(
                        rs.getInt("maphieunhap"),
                        rs.getDate("ngaynhap"),
                        rs.getInt("manhacungcap"),
                        rs.getInt("mathanhvien"),
                        rs.getString("ghichu")); 
                list.add(obj);
            }

        } catch (SQLException e) {
            
        }

        return list;
    }
    
    public static PhieuNhapModel FindByMaPhieuNhap(Connection conn, int maphieunhap) throws SQLException {

        String sql = "SELECT * FROM phieunhap WHERE maphieunhap = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, maphieunhap);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            PhieuNhapModel obj = new PhieuNhapModel(
                        rs.getInt("maphieunhap"),
                        rs.getDate("ngaynhap"),
                        rs.getInt("manhacungcap"),
                        rs.getInt("mathanhvien"),
                        rs.getString("ghichu")); 
            return obj;
        }
        return null;

    }

    
    
    public static boolean UpdatePhieuNhap(Connection conn, PhieuNhapModel phieuNhap) throws SQLException {

        int count = 0;
        try {

            String sql = "UPDATE phieunhap SET "
                    + "ngaynhap=?,"
                    + "manhacungcap=?,"
                    + "mathanhvien=?, "
                    + "ghichu =?"
                    + "WHERE maphieunhap = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setDate(1, phieuNhap.getNgayNhap());
            pstm.setInt(2, phieuNhap.getMaNhaCungCap());
            pstm.setInt(3, phieuNhap.getMaThanhVien());
            pstm.setString(4, phieuNhap.getGhiChu());
            pstm.setInt(5, phieuNhap.getMaPhieuNhap());

            count = pstm.executeUpdate();

        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }
        return count > 0;
    }
}
