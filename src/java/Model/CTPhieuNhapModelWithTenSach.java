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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CTPhieuNhapModelWithTenSach {
    private int maCTPhieuNhap;
    private int maPhieuNhap;
    private int maSach;
    private String tenSach;
    private Double donGia;
    private int soLuongNhap;

    public CTPhieuNhapModelWithTenSach(int maCTPhieuNhap, int maPhieuNhap, int maSach, String tenSach, Double donGia, int soLuongNhap) {
        this.maCTPhieuNhap = maCTPhieuNhap;
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.donGia = donGia;
        this.soLuongNhap = soLuongNhap;
    }

    
    
    
    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

 

    public int getMaCTPhieuNhap() {
        return maCTPhieuNhap;
    }

    public void setMaCTPhieuNhap(int maCTPhieuNhap) {
        this.maCTPhieuNhap = maCTPhieuNhap;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }
     public static List<CTPhieuNhapModelWithTenSach> FindAllByMaPhieuNhap(Connection conn, int maphieunhap) throws SQLException {

        List<CTPhieuNhapModelWithTenSach> list = new ArrayList<CTPhieuNhapModelWithTenSach>();

        String sql = "SELECT mactphieunhap, maphieunhap, ctphieunhap.masach, sach.tensach, dongia, soluongnhap"
                + "  FROM ctphieunhap,sach WHERE maphieunhap = ? AND sach.masach=ctphieunhap.masach";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, maphieunhap);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                CTPhieuNhapModelWithTenSach ctPhieuNhap = new CTPhieuNhapModelWithTenSach(
                        rs.getInt("mactphieunhap"),
                        rs.getInt("maphieunhap"),
                        rs.getInt("masach"),
                        rs.getString("tensach"),
                        rs.getDouble("dongia"),
                        rs.getInt("soluongnhap"));

                list.add(ctPhieuNhap);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;

    }

}
