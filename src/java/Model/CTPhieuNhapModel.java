/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MITICC06
 */
public class CTPhieuNhapModel {

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

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
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

    public CTPhieuNhapModel(int maCTPhieuNhap, int maPhieuNhap, int maSach, Double donGia, int soLuongNhap) {
        this.maCTPhieuNhap = maCTPhieuNhap;
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.donGia = donGia;
        this.soLuongNhap = soLuongNhap;
    }

    public CTPhieuNhapModel() {
    }
    private int maCTPhieuNhap;
    private int maPhieuNhap;
    private int maSach;
    private Double donGia;
    private int soLuongNhap;

    public static boolean InsertList(Connection conn, List<CTPhieuNhapModel> listCTPhieuNhap)
            throws SQLException {

        int count = 0;

        try {
            String sql = "INSERT INTO ctphieunhap(maphieunhap, masach, dongia, soluongnhap) VALUES (?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            for (CTPhieuNhapModel obj : listCTPhieuNhap) {
                pstm.setInt(1, obj.getMaPhieuNhap());
                pstm.setInt(2, obj.getMaSach());
                pstm.setDouble(3, obj.getDonGia());
                pstm.setInt(4, obj.getSoLuongNhap());

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

}
