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
 * @author TamTorres
 */
public class CTDonHangModel {
    
    private int maCTDonHang;
    private int maDonHang;
    private int maSach;
    private int soLuong;
    private double donGia;
    private double phanTramGiamGia;

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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(double phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public CTDonHangModel(int maCTDonHang, int maDonHang, int maSach, int soLuong, double donGia, double phanTramGiamGia) {
        this.maCTDonHang = maCTDonHang;
        this.maDonHang = maDonHang;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.phanTramGiamGia = phanTramGiamGia;
    }
    
    public static boolean InsertList(Connection conn, List<CTDonHangModel> listCTDonHang)
            throws SQLException {

        int count = 0;

        try {
            String sql = "INSERT INTO ctdonhang(madonhang, masach, soluong, dongia, phantramgiamgia) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            for (CTDonHangModel obj : listCTDonHang) {
                pstm.setInt(1, obj.getMaDonHang());
                pstm.setInt(2, obj.getMaSach());
                pstm.setInt(3, obj.getSoLuong());
                pstm.setDouble(4, obj.getDonGia());
                pstm.setDouble(5, obj.getPhanTramGiamGia());
     
                pstm.addBatch();
            }
            
            pstm.executeBatch();
            count = 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            count = 0;
        }
        return count > 0;
    }
    
    public static List<CTDonHangModel> getAllCTDonHangByMaDonHang(Connection conn, int maDonHang) {
        List<CTDonHangModel> list = new ArrayList<CTDonHangModel>();

        String sql = "SELECT * FROM ctdonhang WHERE madonhang = ?";
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maDonHang);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                CTDonHangModel obj = new CTDonHangModel(
                        rs.getInt("mactdonhang"),
                        rs.getInt("madonhang"),
                        rs.getInt("masach"),
                        rs.getInt("soluong"),
                        rs.getDouble("dongia"),
                        rs.getDouble("phantramgiamgia"));
                list.add(obj);
            }

        } catch (SQLException e) {
            
        }

        return list;
    }
}
