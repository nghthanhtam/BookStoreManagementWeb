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
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhaCungCapModel {

    public NhaCungCapModel() {
    }
    private int maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChi;
    private String soDienThoai;
    private Double soTienNo;

    public NhaCungCapModel(int maNhaCungCap, String tenNhaCungCap, String diaChi, String soDienThoai, Double soTienNo) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.soTienNo = soTienNo;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
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

    public Double getSoTienNo() {
        return soTienNo;
    }

    public void setSoTienNo(Double soTienNo) {
        this.soTienNo = soTienNo;
    }

    public static boolean InsertNewNhaCungCap(Connection conn, NhaCungCapModel obj)
            throws SQLException {
        int count = 0;
        try {
            String sql = "INSERT INTO nhacungcap (tennhacungcap, diachi, sodienthoai) VALUES (?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, obj.getTenNhaCungCap());
            pstm.setString(2, obj.getDiaChi());
            pstm.setString(3, obj.getSoDienThoai());
            count = pstm.executeUpdate();
        } catch (SQLException ex) {
            count = 0;
        }
        return count > 0;
    }

    public static List<NhaCungCapModel> getAllNhaCungCap(Connection conn) {
        List<NhaCungCapModel> listNhaCungCap = new ArrayList<NhaCungCapModel>();

        String sql = "SELECT * FROM nhacungcap";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                NhaCungCapModel nhaCungCapModel = new NhaCungCapModel(
                        Integer.parseInt(rs.getString("manhacungcap")),
                        rs.getString("tennhacungcap"),
                        rs.getString("diachi"),
                        rs.getString("sodienthoai"),
                        Double.parseDouble(rs.getString("sotienno")));

                listNhaCungCap.add(nhaCungCapModel);
            }

        } catch (SQLException e) {

        }

        return listNhaCungCap;
    }

    public static boolean DeleteNhaCungCapById(Connection conn, int maNhaCungCap) throws SQLException {
        String sql = "DELETE FROM nhacungcap WHERE manhacungcap = ?";
        int count = 0;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maNhaCungCap);
            count = pstm.executeUpdate();

        } catch (SQLException ex) {

        }

        System.out.println("count = " + count);

        return count > 0;
    }

    public static NhaCungCapModel FindByMaNhaCungCap(Connection conn, int maNhaCungCap) throws SQLException {
        String sql = "SELECT * FROM nhacungcap WHERE manhacungcap = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, maNhaCungCap);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            NhaCungCapModel nhaCungCapModel = new NhaCungCapModel(
                    Integer.parseInt(rs.getString("manhacungcap")),
                    rs.getString("tennhacungcap"),
                    rs.getString("diachi"),
                    rs.getString("sodienthoai"),
                    Double.parseDouble(rs.getString("sotienno")));

            return nhaCungCapModel;
        }
        return null;
    }

    public static String FindTenByMaNhaCungCap(Connection conn, int maNhaCungCap) throws SQLException {
        String sql = "SELECT tennhacungcap FROM nhacungcap WHERE manhacungcap = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, maNhaCungCap);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String tenNhaCungCap = rs.getString("manhacungcap");

            return tenNhaCungCap;
        }
        return null;
    }

    public static boolean UpdateNhaCungCap(Connection conn, NhaCungCapModel obj)
            throws SQLException {
        int count = 0;
        try {
            String sql = "UPDATE nhacungcap SET tennhacungcap = ?, diachi = ?, sodienthoai = ?, sotienno = ? WHERE manhacungcap = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, obj.getTenNhaCungCap());
            pstm.setString(2, obj.getDiaChi());
            pstm.setString(3, obj.getSoDienThoai());
            pstm.setDouble(4, obj.getSoTienNo());
            pstm.setDouble(5, obj.getMaNhaCungCap());

            count = pstm.executeUpdate();
        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }
        return count > 0;
    }

  
    public static List<AjaxModel> FindAllByName(Connection conn, String tenNhaCungCap) throws SQLException {

        List<AjaxModel> listNhaCungCap = new ArrayList<AjaxModel>();

        String sql = "SELECT * FROM nhacungcap WHERE manhacungcap = ? OR tennhacungcap LIKE ? ";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, tenNhaCungCap); // tìm bằng Mã nhà cung cấp
            pstm.setString(2, "%" + tenNhaCungCap + "%"); // tìm bằng tên nhà cung cấp

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                AjaxModel ajaxNhaCungCapModel = new AjaxModel(
                        Integer.parseInt(rs.getString("manhacungcap")),
                        rs.getString("manhacungcap") + " - " + rs.getString("tennhacungcap"));

                listNhaCungCap.add(ajaxNhaCungCapModel);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listNhaCungCap;

    }

}
