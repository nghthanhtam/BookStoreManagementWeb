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
public class CTDonHangModelWithTenSachAnhDaiDien {

    private int maCTDonHang;
    private int maDonHang;
    private int maSach;
    private String tenSach;
    private String anhDaiDien;
    private int soLuong;
    private Double donGia;
    private Double phanTramGiamGia;

    public CTDonHangModelWithTenSachAnhDaiDien(int maCTDonHang, int maDonHang, int maSach, String tenSach, String anhDaiDien, int soLuong, Double donGia, Double phanTramGiamGia) {
        this.maCTDonHang = maCTDonHang;
        this.maDonHang = maDonHang;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.anhDaiDien = anhDaiDien;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public int getMaCTDonHang() {
        return maCTDonHang;
    }

    public void setMaCTDonHang(int maCTDonHang) {
        this.maCTDonHang = maCTDonHang;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(Double phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public static List<CTDonHangModelWithTenSachAnhDaiDien> FindAllByMaDonHang(Connection conn, int madonhang) throws SQLException {

        List<CTDonHangModelWithTenSachAnhDaiDien> list = new ArrayList<CTDonHangModelWithTenSachAnhDaiDien>();

        String sql = "SELECT mactdonhang, madonhang, ctdonhang.masach, sach.tensach, sach.anhdaidien, soluong, dongia, ctdonhang.phantramgiamgia  FROM ctdonhang,sach WHERE madonhang = ? AND sach.masach=ctdonhang.masach";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, madonhang);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                CTDonHangModelWithTenSachAnhDaiDien ctdonhang = new CTDonHangModelWithTenSachAnhDaiDien(
                        rs.getInt("mactdonhang"),
                        rs.getInt("madonhang"),
                        rs.getInt("masach"),
                        rs.getString("tensach"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluong"),
                        rs.getDouble("dongia"),
                        rs.getDouble("phantramgiamgia"));

                list.add(ctdonhang);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;

    }
}
