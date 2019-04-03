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
public class ThanhVienModelWithTenQuyen {
    private int maThanhVien;
    private String tenDangNhap;
    private String matKhau; 
    private String hoTen;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private String tenPhanQuyen;
    
  

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTenPhanQuyen() {
        return tenPhanQuyen;
    }

    public void setTenPhanQuyen(String tenPhanQuyen) {
        this.tenPhanQuyen = tenPhanQuyen;
    }

    public ThanhVienModelWithTenQuyen(int maThanhVien, String tenDangNhap, String matKhau, String hoTen, String diaChi, String soDienThoai, String email, String tenPhanQuyen) {
        this.maThanhVien = maThanhVien;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.tenPhanQuyen = tenPhanQuyen;
    }
   

  
    
     public static List<ThanhVienModelWithTenQuyen> getAllThanhVienWithTenQuyen(Connection conn) {
        List<ThanhVienModelWithTenQuyen> listThanhVienWithTenQuyen = new ArrayList<ThanhVienModelWithTenQuyen>();

        String sql = "SELECT mathanhvien, tendangnhap, matkhau, hoten, diachi, sodienthoai, email, phanquyen.tenquyen "
                + "FROM thanhvien,phanquyen WHERE phanquyen.maphanquyen=thanhvien.maphanquyen";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                ThanhVienModelWithTenQuyen thanhVienModelWithTenQuyen = new ThanhVienModelWithTenQuyen(
                        Integer.parseInt(rs.getString("mathanhvien")),
                        rs.getString("tendangnhap"),
                        rs.getString("matkhau"),
                        rs.getString("hoten"),
                        rs.getString("diachi"),
                        rs.getString("sodienthoai"),
                        rs.getString("email"),
                        rs.getString("phanquyen.tenquyen"));

                listThanhVienWithTenQuyen.add(thanhVienModelWithTenQuyen);
            }

        } catch (SQLException e) {

        }

        return listThanhVienWithTenQuyen;
    }

}
