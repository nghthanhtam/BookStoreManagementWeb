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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author TamTorres
 */
public class PhieuChiModel {

    public PhieuChiModel() {
    }

    public PhieuChiModel(int maPhieuChi, int maNhaCungCap, int maThanhVien, double tongTien, Date ngayLapPhieu, String ghiChu) {
        this.maPhieuChi = maPhieuChi;
        this.maNhaCungCap = maNhaCungCap;
        this.maThanhVien = maThanhVien;
        this.tongTien = tongTien;
        this.ngayLapPhieu = ngayLapPhieu;
        this.ghiChu = ghiChu;
    }

      
    public int getMaPhieuChi() {
        return maPhieuChi;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public double getTongTien() {
        return tongTien;
    }

    public Date getNgayLapPhieu() {
        return ngayLapPhieu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setMaPhieuChi(int maPhieuChi) {
        this.maPhieuChi = maPhieuChi;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setNgayLapPhieu(Date ngayLapPhieu) {
        this.ngayLapPhieu = ngayLapPhieu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    private int maPhieuChi;
    private int maNhaCungCap;
    private int maThanhVien;
    private double tongTien;
    private Date ngayLapPhieu;
    private String ghiChu;

    public static boolean InsertNewPhieuChi(Connection conn, PhieuChiModel phieuChi)
            throws SQLException {
        int count = 0;
        try {
            String sql = "INSERT INTO phieuchi (manhacungcap, mathanhvien, tongtien, ngaylapphieu, ghichu) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, phieuChi.getMaNhaCungCap());
            pstm.setInt(2, phieuChi.getMaThanhVien());
            pstm.setDouble(3, phieuChi.getTongTien());
            pstm.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstm.setString(5, phieuChi.getGhiChu());

            count = pstm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            count = 0;
        }

        return count > 0;
    }

    public static List<PhieuChiModel> getAllPhieuChi(Connection conn) {
        List<PhieuChiModel> listPhieuChi = new ArrayList<PhieuChiModel>();

        String sql = "SELECT * FROM phieuchi";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PhieuChiModel phieuChiModel = new PhieuChiModel(
                        Integer.parseInt(rs.getString("maphieuchi")),
                        Integer.parseInt(rs.getString("manhacungcap")),
                        Integer.parseInt(rs.getString("mathanhvien")),
                        Double.parseDouble(rs.getString("tongtien")),
                        rs.getDate("ngaylapphieu"),
                        rs.getString("ghichu"));

                listPhieuChi.add(phieuChiModel);
            }

        } catch (SQLException e) {

        }
        return listPhieuChi;
    }

    public static boolean DeleteByMaPhieuChi(Connection conn, int maPhieuChi)
            throws SQLException {
        int count = 0;
        String sql = "DELETE FROM phieuchi WHERE maPhieuChi = ?";

        try {
            PreparedStatement pstm = conn.prepareCall(sql);
            pstm.setInt(1, maPhieuChi);
            count = pstm.executeUpdate();

        } catch (SQLException Ex) {

        }
        return count > 0;
    }

    public static boolean UpdatePhieuChi(Connection conn, PhieuChiModel phieuChi)
            throws SQLException {
        int count = 0;
        try {
            String sql = "UPDATE phieuchi SET manhacungcap = ?, mathanhvien = ?, tongtien = ?, ngaylapphieu = ?, ghichu = ? WHERE maphieuchi = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, phieuChi.getMaNhaCungCap());
            pstm.setInt(2, phieuChi.getMaThanhVien());
            pstm.setDouble(3, phieuChi.getTongTien());
            pstm.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstm.setString(5, phieuChi.getGhiChu());
            pstm.setInt(6, phieuChi.getMaPhieuChi());

            count = pstm.executeUpdate();

        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }

        return count > 0;
    }

    public static PhieuChiModel FindByMaPhieuChi(Connection conn, int maphieuchi) throws SQLException {
        String sql = "SELECT * FROM phieuchi WHERE maphieuchi = ?";
        PreparedStatement pstm = conn.prepareStatement(sql); //cau lenh sql duoc compile va save trong 1 
        //doi tuong PreparedStatement                    
        
        pstm.setInt(1, maphieuchi);
   
        ResultSet rs = pstm.executeQuery(); //thuc hien truy van

        if (rs.next()) {
            PhieuChiModel phieuChiModel = new PhieuChiModel(
                    Integer.parseInt(rs.getString("maphieuchi")),
                    Integer.parseInt(rs.getString("manhacungcap")),
                    Integer.parseInt(rs.getString("mathanhvien")),
                    Double.parseDouble(rs.getString("tongtien")),
                    rs.getDate("ngaylapphieu"),
                    rs.getString("ghichu"));
            return phieuChiModel;
        }
  
        return null;
    }

   
   
}
