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
public class DonHangModelWithTenThanhVienAndTenDangNhap {
    
      public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
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
    
    private int maDonHang;
    private  String tenThanhVien;
    private Date ngayLap;
    private double tongTien;
    private int trangThai;
    private String diaChiGiaoHang;
    private int maPhiShip;
    private String soDienThoai;
    private String ghiChu;
    private String tenDangNhap;

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    
    public DonHangModelWithTenThanhVienAndTenDangNhap(int maDonHang, String tenThanhVien, Date ngayLap, double tongTien,
            int trangThai, String diaChiGiaoHang, int maPhiShip, String soDienThoai, String ghiChu,String tenDangNhap) {
        this.maDonHang = maDonHang;
        this.tenThanhVien = tenThanhVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.diaChiGiaoHang = diaChiGiaoHang;
        this.maPhiShip = maPhiShip;
        this.soDienThoai = soDienThoai;
        this.ghiChu = ghiChu;
        this.tenDangNhap= tenDangNhap;
    }
    
    public static List<DonHangModelWithTenThanhVienAndTenDangNhap> getAllDonHang(Connection conn,Integer page, Integer numOfBookInOnePage) {
        List<DonHangModelWithTenThanhVienAndTenDangNhap> list = new ArrayList<DonHangModelWithTenThanhVienAndTenDangNhap>();

        String sql = "SELECT madonhang, hoten,tendangnhap, ngaylap,tongtien,trangthai,diachigiaohang, maphiship,donhang.sodienthoai,ghichu "
                + "FROM donhang, thanhvien WHERE thanhvien.mathanhvien = donhang.mathanhvien LIMIT ?,?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, (page-1)*numOfBookInOnePage);
            pstm.setInt(2, numOfBookInOnePage);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                DonHangModelWithTenThanhVienAndTenDangNhap obj = new DonHangModelWithTenThanhVienAndTenDangNhap(
                        rs.getInt("madonhang"),
                        rs.getString("hoten"),
                        rs.getDate("ngaylap"),
                        rs.getDouble("tongtien"),
                        rs.getInt("trangthai"),
                        rs.getString("diachigiaohang"),
                        rs.getInt("maphiship"),
                        rs.getString("sodienthoai"),
                        rs.getString("ghichu"),
                        rs.getString("tendangnhap"));
                list.add(obj);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
    public static int CountAllDonHang(Connection conn) throws SQLException {

        int counter=0;
        int x=0;
            
            String sql ="SELECT COUNT(*) AS TOTAL FROM (SELECT madonhang, hoten,tendangnhap, ngaylap,tongtien,trangthai,diachigiaohang, maphiship,donhang.sodienthoai,ghichu" +
"                               FROM donhang, thanhvien WHERE thanhvien.mathanhvien = donhang.mathanhvien) AS T";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                counter=rs.getInt("TOTAL");
            

        } catch (SQLException e) {
            System.out.println("Exception: ");
            System.out.println(e.toString());
        }

        return counter;

    }
  
    
    
     public static DonHangModelWithTenThanhVienAndTenDangNhap FindByMaDonHang(Connection conn, int madonhang) throws SQLException {

        String sql = "SELECT madonhang, hoten,tendangnhap, ngaylap,tongtien,trangthai,diachigiaohang, maphiship,donhang.sodienthoai,ghichu "
                + " FROM donhang, thanhvien WHERE thanhvien.mathanhvien = donhang.mathanhvien AND madonhang = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, madonhang);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            DonHangModelWithTenThanhVienAndTenDangNhap donhang = new DonHangModelWithTenThanhVienAndTenDangNhap(
                     rs.getInt("madonhang"),
                        rs.getString("hoten"),
                        rs.getDate("ngaylap"),
                        rs.getDouble("tongtien"),
                        rs.getInt("trangthai"),
                        rs.getString("diachigiaohang"),
                        rs.getInt("maphiship"),
                        rs.getString("sodienthoai"),
                        rs.getString("ghichu"),
                        rs.getString("tendangnhap"));
            return donhang;
        }
        return null;

    }

}
