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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author TamTorres
 */
public class PhieuChiModelWithTenNhaCungCap {

    public PhieuChiModelWithTenNhaCungCap() {
    }

    public PhieuChiModelWithTenNhaCungCap(int maPhieuChi, String tenNhaCungCap, String hoTen, double tongTien, Timestamp ngayLapPhieu, String ghiChu) {
        this.maPhieuChi = maPhieuChi;
        this.tenNhaCungCap = tenNhaCungCap;
        this.hoTen = hoTen;
        this.tongTien = tongTien;
        this.ngayLapPhieu = ngayLapPhieu;
        this.ghiChu = ghiChu;
    }

      
    public int getMaPhieuChi() {
        return maPhieuChi;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public String getHoTen() {
        return hoTen;
    }

    public double getTongTien() {
        return tongTien;
    }

    public Timestamp getNgayLapPhieu() {
        return ngayLapPhieu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setMaPhieuChi(int maPhieuChi) {
        this.maPhieuChi = maPhieuChi;
    }

    public void setMaNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setNgayLapPhieu(Timestamp ngayLapPhieu) {
        this.ngayLapPhieu = ngayLapPhieu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    private int maPhieuChi;
    private String tenNhaCungCap;
    private String hoTen;
    private double tongTien;
    private Timestamp ngayLapPhieu;
    private String ghiChu;


    public static List<PhieuChiModelWithTenNhaCungCap> getAllPhieuChiWithTenNhaCungCap(Connection conn) {
        List<PhieuChiModelWithTenNhaCungCap> listPhieuChi = new ArrayList<PhieuChiModelWithTenNhaCungCap>();

        String sql = "SELECT maphieuchi, nhacungcap.tennhacungcap, thanhvien.hoten, tongtien, ngaylapphieu, ghichu  "
                + "FROM phieuchi, nhacungcap, thanhvien WHERE nhacungcap.manhacungcap = phieuchi.manhacungcap AND thanhvien.mathanhvien = phieuchi.mathanhvien";
        
        // String sql = "SELECT maphieuchi, nhacungcap.tennhacungcap, thanhvien.hoten, tongtien, ngaylapphieu, ghichu  "
                //+ "FROM phieuchi, nhacungcap, thanhvien WHERE nhacungcap.manhacungcap = phieuchi.manhacungcap AND thanhvien.mathanhvien = phieuchi.mathanhvien"
                //+ "ORDER by maphieuchi asc";
         
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PhieuChiModelWithTenNhaCungCap phieuChiModel = new PhieuChiModelWithTenNhaCungCap(
                        Integer.parseInt(rs.getString("maphieuchi")),
                        rs.getString("nhacungcap.tennhacungcap"),
                        rs.getString("thanhvien.hoten"),
                        Double.parseDouble(rs.getString("tongtien")),
                        rs.getTimestamp("ngaylapphieu"),
                        rs.getString("ghichu"));

                listPhieuChi.add(phieuChiModel);
            }

        } catch (SQLException e) {

        }
        return listPhieuChi;
    }

  public static PhieuChiModelWithTenNhaCungCap findAllByMaPhieuChi(Connection conn, int maPhieuChi) {
 
         String sql = "SELECT maphieuchi, nhacungcap.tennhacungcap, thanhvien.hoten, tongtien, ngaylapphieu, ghichu"
                + "FROM phieuchi, nhacungcap, thanhvien WHERE nhacungcap.manhacungcap = phieuchi.manhacungcap AND thanhvien.mathanhvien = phieuchi.mathanhvien"
                + "AND maphieuchi = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,maPhieuChi);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PhieuChiModelWithTenNhaCungCap phieuChiModel = new PhieuChiModelWithTenNhaCungCap(
                        Integer.parseInt(rs.getString("maphieuchi")),
                        rs.getString("nhacungcap.tennhacungcap"),
                        rs.getString("thanhvien.hoten"),
                        Double.parseDouble(rs.getString("tongtien")),
                        rs.getTimestamp("ngaylapphieu"),
                        rs.getString("ghichu"));

                return phieuChiModel;
            }

        } catch (SQLException e) {

        }
        return null;
    }
  
}    