/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class PhieuNhapModelWithTenNhaCungCapAndTenNhanVien {
     private int maPhieuNhap;
    private Date ngayNhap;
    private String tenNhaCungCap;
    private String tenThanhVien;
    private String ghiChu;

    public PhieuNhapModelWithTenNhaCungCapAndTenNhanVien(int maPhieuNhap, Date ngayNhap, String tenNhaCungCap, String tenThanhVien, String ghiChu) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenThanhVien = tenThanhVien;
        this.ghiChu = ghiChu;
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

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    
    public static List<PhieuNhapModelWithTenNhaCungCapAndTenNhanVien> getAllPhieuNhap(Connection conn) {
        List<PhieuNhapModelWithTenNhaCungCapAndTenNhanVien> list = new ArrayList<PhieuNhapModelWithTenNhaCungCapAndTenNhanVien>();

        String sql = "SELECT maphieunhap, ngaynhap, tennhacungcap, hoten, ghichu FROM phieunhap, thanhvien, nhacungcap where nhacungcap.manhacungcap = phieunhap.manhacungcap AND "
                + "thanhvien.mathanhvien = phieunhap.mathanhvien";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PhieuNhapModelWithTenNhaCungCapAndTenNhanVien obj = new PhieuNhapModelWithTenNhaCungCapAndTenNhanVien(
                        rs.getInt("maphieunhap"),
                        rs.getDate("ngaynhap"),
                        rs.getString("tennhacungcap"),
                        rs.getString("hoten"),
                        rs.getString("ghichu")); 
                list.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static PhieuNhapModelWithTenNhaCungCapAndTenNhanVien FindByMaPhieuNhap(Connection conn, int maphieunhap) throws SQLException {

          try {
              
        String sql = "SELECT maphieunhap, ngaynhap,tennhacungcap,hoten,ghichu "
                + " FROM phieunhap, thanhvien, nhacungcap WHERE thanhvien.mathanhvien = phieunhap.mathanhvien AND nhacungcap.manhacungcap = phieunhap.manhacungcap AND maphieunhap = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, maphieunhap);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            PhieuNhapModelWithTenNhaCungCapAndTenNhanVien phieuNhap = new PhieuNhapModelWithTenNhaCungCapAndTenNhanVien(
                     rs.getInt("maphieunhap"),
                        rs.getDate("ngaynhap"),
                        rs.getString("tennhacungcap"),
                        rs.getString("hoten"),
                        rs.getString("ghichu"));
            return phieuNhap;
        }

          } catch (Exception e) {
              e.printStackTrace();
          }
          
        return null;
    }
}

