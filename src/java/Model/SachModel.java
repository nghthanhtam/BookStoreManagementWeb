/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utility.MyUtils;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MITICC06
 */
public class SachModel {

    private int maSach;
    private int maTheLoai;
    private String tenSach;
    private String nhaXuatBan;
    private int namXuatBan;
    private double giaBan;
    private String moTa;
    private String anhDaiDien;
    private int soLuongTon;
    private String tenTacGia;
    private Double phanTramGiamGia;
    private Date ngayBatDauGiamGia;
    private Date ngayKetThucGiamGia;
    private int trangThai;

    public final static int TRANGTHAI_DANG_BAN = 1;
    public final static int TRANGTHAI_NGUNG_KINH_DOANH = 3;
    public final static int TRANGTHAI_XOA = 2;

    public SachModel() {

    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void settAnhDaiDien(String anhdaidien) {
        this.anhDaiDien = anhdaidien;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public Double getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(Double phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public Date getNgayBatDauGiamGia() {
        return ngayBatDauGiamGia;
    }

    public void setNgayBatDauGiamGia(Date ngayBatDauGiamGia) {
        this.ngayBatDauGiamGia = ngayBatDauGiamGia;
    }

    public Date getNgayKetThucGiamGia() {
        return ngayKetThucGiamGia;
    }

    public void setNgayKetThucGiamGia(Date ngayKetThucGiamGia) {
        this.ngayKetThucGiamGia = ngayKetThucGiamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public SachModel(int masach, int matheloai, String tensach,
            String nhaxuatban, int namxuatban, double giaban,
            String mota, String anhdaidien, int soluongton,
            String tentacgia, Double phantramgiamgia,
            Date ngaybatdaugiamgia, Date ngayketthucgiamgia, int trangthai) {
        this.maSach = masach;
        this.maTheLoai = matheloai;
        this.tenSach = tensach;
        this.nhaXuatBan = nhaxuatban;
        this.namXuatBan = namxuatban;
        this.giaBan = giaban;
        this.moTa = mota;
        this.anhDaiDien = anhdaidien;
        this.soLuongTon = soluongton;
        this.tenTacGia = tentacgia;
        this.phanTramGiamGia = phantramgiamgia;
        this.ngayBatDauGiamGia = ngaybatdaugiamgia;
        this.ngayKetThucGiamGia = ngayketthucgiamgia;
        this.trangThai = trangthai;
    }

    public static SachModel FindByMaSach(Connection conn, int masach) throws SQLException {

        String sql = "SELECT * FROM sach WHERE masach = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setInt(1, masach);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            SachModel sach = new SachModel(
                    rs.getInt("masach"),
                    rs.getInt("matheloai"),
                    rs.getString("tensach"),
                    rs.getString("nhaxuatban"),
                    rs.getInt("namxuatban"),
                    rs.getDouble("giaban"),
                    rs.getString("mota"),
                    rs.getString("anhdaidien"),
                    rs.getInt("soluongton"),
                    rs.getString("tentacgia"),
                    rs.getDouble("phantramgiamgia"),
                    rs.getDate("ngaybatdaugiamgia"),
                    rs.getDate("ngayketthucgiamgia"),
                    rs.getInt("trangthai"));
            return sach;
        }
        return null;

    }

    public static boolean InsertNewSach(Connection conn, SachModel sach)
            throws SQLException {
        int count = 0;
        try {
            String sql = "INSERT INTO sach ( matheloai, tensach, nhaxuatban, namxuatban ,"
                    + " giaban, mota, anhdaidien, soluongton,"
                    + " tentacgia, phantramgiamgia, ngaybatdaugiamgia,"
                    + " ngayketthucgiamgia, trangthai) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, sach.getMaTheLoai());
            pstm.setString(2, sach.getTenSach());
            pstm.setString(3, sach.getNhaXuatBan());
            pstm.setInt(4, sach.getNamXuatBan());
            pstm.setDouble(5, sach.getGiaBan());
            pstm.setString(6, sach.getMoTa());
            pstm.setString(7, sach.getAnhDaiDien());

            pstm.setInt(8, sach.getSoLuongTon());
            pstm.setString(9, sach.getTenTacGia());

            if (sach.getPhanTramGiamGia() == null) {
                pstm.setNull(10, java.sql.Types.DOUBLE);
            } else {
                pstm.setDouble(10, sach.getPhanTramGiamGia());
            }

            if (sach.getNgayBatDauGiamGia() == null) {
                pstm.setNull(11, java.sql.Types.DATE);
            } else {
                pstm.setDate(11, sach.getNgayBatDauGiamGia());
            }

            if (sach.getNgayKetThucGiamGia() == null) {
                pstm.setNull(12, java.sql.Types.DATE);
            } else {
                pstm.setDate(12, sach.getNgayKetThucGiamGia());
            }

            pstm.setInt(13, sach.getTrangThai());

            count = pstm.executeUpdate();
        } catch (SQLException ex) {

            System.out.println(ex);
            count = 0;
        }
        return count > 0;
    }

    public static List<SachModel> getAllSach(Connection conn) {
        List<SachModel> listSach = new ArrayList<SachModel>();

        String sql = "SELECT * FROM sach";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));

                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }

    public static boolean UpdateSach(Connection conn, SachModel sach) throws SQLException {

        int count = 0;
        try {
            String sql = "UPDATE sach SET "
                    + "matheloai = ?,"
                    + "tensach=?,"
                    + "nhaxuatban=?,"
                    + "namxuatban=?, "
                    + "giaban = ?, "
                    + "mota = ?, "
                    + "anhdaidien = ?, "
                    + "soluongton =?,"
                    + "tentacgia = ?, "
                    + "phantramgiamgia =?,"
                    + "ngaybatdaugiamgia = ?, "
                    + "ngayketthucgiamgia =?,"
                    + "trangthai = ? "
                    + "WHERE masach = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, sach.getMaTheLoai());
            pstm.setString(2, sach.getTenSach());
            pstm.setString(3, sach.getNhaXuatBan());
            pstm.setInt(4, sach.getNamXuatBan());
            pstm.setDouble(5, sach.getGiaBan());
            pstm.setString(6, sach.getMoTa());
            pstm.setString(7, sach.getAnhDaiDien());
            pstm.setInt(8, sach.getSoLuongTon());
            pstm.setString(9, sach.getTenTacGia());

            if (sach.getPhanTramGiamGia() == null) {
                pstm.setNull(10, java.sql.Types.DOUBLE);
            } else {
                pstm.setDouble(10, sach.getPhanTramGiamGia());
            }

            if (sach.getNgayBatDauGiamGia() == null) {
                pstm.setNull(11, java.sql.Types.DATE);
            } else {
                pstm.setDate(11, sach.getNgayBatDauGiamGia());
            }

            if (sach.getNgayKetThucGiamGia() == null) {
                pstm.setNull(12, java.sql.Types.DATE);
            } else {
                pstm.setDate(12, sach.getNgayKetThucGiamGia());
            }

            pstm.setInt(13, sach.getTrangThai());
            pstm.setInt(14, sach.getMaSach());

            count = pstm.executeUpdate();

        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }
        return count > 0;
    }

    public static boolean UpdateSoLuongTonSach(Connection conn, int soLuongSach, int maSach) throws SQLException {

        int count = 0;
        try {
            String sql = "UPDATE sach SET "
                    + "soluongton = soluongton + ?"
                    + " WHERE masach = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, soLuongSach);
            pstm.setInt(2, maSach);

            count = pstm.executeUpdate();

        } catch (SQLException ex) {
            count = 0;
            ex.printStackTrace();
        }
        return count > 0;
    }

    public static List<AjaxModel> FindTenTacGia(Connection conn, String tenTacGia) throws SQLException {

        List<AjaxModel> listTenTacGia = new ArrayList<AjaxModel>();

        String sql = "SELECT DISTINCT tentacgia FROM sach WHERE tentacgia LIKE ? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, "%" + tenTacGia + "%"); // tìm bằng tên tác giả

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                AjaxModel ajaxSach = new AjaxModel(0, rs.getString("tentacgia"));

                listTenTacGia.add(ajaxSach);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listTenTacGia;

    }

    public static List<AjaxModel> FindNhaXuatBan(Connection conn, String nhaXuatBan) throws SQLException {

        List<AjaxModel> listTenNhaXuatBan = new ArrayList<AjaxModel>();

        String sql = "SELECT DISTINCT nhaxuatban FROM sach WHERE nhaxuatban LIKE ? ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, "%" + nhaXuatBan + "%"); // tìm bằng tên tác giả

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                AjaxModel ajaxSach = new AjaxModel(0, rs.getString("nhaxuatban"));

                listTenNhaXuatBan.add(ajaxSach);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listTenNhaXuatBan;

    }

    public static List<SachModel> getAllSachByMaTheLoai(Connection conn, int maTheLoai) {
        List<SachModel> listSach = new ArrayList<SachModel>();

        String sql = "SELECT * FROM sach WHERE matheloai = ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maTheLoai);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));
                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }

    public static List<SachModel> getListSachMoiNhatTop7(Connection conn) {
        List<SachModel> listSach = new ArrayList<SachModel>();

        String sql = "SELECT * FROM sach ORDER BY masach DESC LIMIT 7";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));

                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }

    public static List<SachModel> getListSachGiamGiaTop7(Connection conn) { // sách giảm giá gần hết nhất
        List<SachModel> listSachGiamGia = new ArrayList<SachModel>();

        //select top 2 sach dang giam gia (sách có hạn giảm giá gần nhất nằm trên đầu)
        String sql = "SELECT * FROM sach WHERE phantramgiamgia <> 0 AND UNIX_TIMESTAMP(ngaybatdaugiamgia) < UNIX_TIMESTAMP(CURRENT_TIMESTAMP) "
                + "AND UNIX_TIMESTAMP(CURRENT_TIMESTAMP) < UNIX_TIMESTAMP(ngayketthucgiamgia)  ORDER BY (ngayketthucgiamgia - CURRENT_TIMESTAMP) ASC LIMIT 7";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));

                listSachGiamGia.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSachGiamGia;
    }

    public static List<AjaxModel> FindAllByName(Connection conn, String tenSach) throws SQLException {

        List<AjaxModel> listSach = new ArrayList<AjaxModel>();

        String sql = "SELECT * FROM sach WHERE masach = ? OR tensach LIKE ? ";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, tenSach); // tìm bằng Mã sách
            pstm.setString(2, "%" + tenSach + "%"); // tìm bằng tên sách

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                AjaxModel ajaxModel = new AjaxModel(
                        Integer.parseInt(rs.getString("masach")),
                        rs.getString("masach") + " - " + rs.getString("tensach"));

                listSach.add(ajaxModel);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listSach;

    }

    public static List<SachModel> FindAllByTuKhoa(Connection conn, String tuKhoa, Integer maTheLoai, Integer page, Integer numOfBookInOnePage) throws SQLException {

        List<SachModel> listSach = new ArrayList<SachModel>();

        Integer x = 0;
        //  (SELECT  DISTINCT *  FROM sach WHERE ( tensach LIKE ? OR tentacgia LIKE ? OR  mota LIKE ?) AND matheloai LIKE ? AND trangthai <> 2)
        String sql = "SELECT DISTINCT * FROM sach "
                + "WHERE ( tensach LIKE ? OR tentacgia LIKE ? OR  mota LIKE ?) AND matheloai LIKE ? AND trangthai <> 2 LIMIT ? , ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, "%" + tuKhoa + "%"); // tìm từ khóa
            pstm.setString(2, "%" + tuKhoa + "%"); // tìm từ khóa
            pstm.setString(3, "%" + tuKhoa + "%"); // tìm từ khóa
            if (maTheLoai != 0) {
                pstm.setInt(4, maTheLoai); // tìm từ khóa
            } else {
                pstm.setString(4, "%%");
            }

            pstm.setInt(5, (page - 1) * numOfBookInOnePage);
            pstm.setInt(6, numOfBookInOnePage);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                SachModel sach = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));
                listSach.add(sach);
            }
        } catch (SQLException e) {
            System.out.println("Exception: ");
            System.out.println(e.toString());
        }

        return listSach;

    }

    public static int CountAllByTuKhoa(Connection conn, String tuKhoa, Integer maTheLoai) throws SQLException {

        int counter = 0;
        int x = 0;

        String sql = "SELECT COUNT(*) AS TOTAL FROM ((SELECT  DISTINCT *  FROM sach"
                + " WHERE ( tensach LIKE ? OR tentacgia LIKE ? OR  mota LIKE ?) AND matheloai LIKE ? AND trangthai <> 2) AS T)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, "%" + tuKhoa + "%"); // tìm từ khóa
            pstm.setString(2, "%" + tuKhoa + "%"); // tìm từ khóa
            pstm.setString(3, "%" + tuKhoa + "%"); // tìm từ khóa
            if (maTheLoai != 0) {
                pstm.setInt(4, maTheLoai); // tìm từ khóa
            } else {
                pstm.setString(4, "%%");
            }

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                counter = rs.getInt("TOTAL");
            }

        } catch (SQLException e) {
            System.out.println("Exception: ");
            System.out.println(e.toString());
        }

        return counter;

    }

    public static List<AjaxModel> FindAllByTuKhoaAjax(Connection conn, String tuKhoa) throws SQLException {

        List<AjaxModel> listSach = new ArrayList<AjaxModel>();

        String sql = "SELECT DISTINCT * FROM sach WHERE "
                + "( masach= ? OR tensach LIKE ? OR tentacgia LIKE ? OR  mota LIKE ?) AND trangthai <> 2 LIMIT 0,10";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, tuKhoa); // tìm từ khóa
            pstm.setString(2, "%" + tuKhoa + "%"); // tìm từ khóa
            pstm.setString(3, "%" + tuKhoa + "%"); // tìm từ khóa
            pstm.setString(4, "%" + tuKhoa + "%"); // tìm từ khóa

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                AjaxModel mode = new AjaxModel(
                        rs.getInt("masach"),
                        rs.getString("tensach") + " - " + rs.getString("tentacgia"));
                listSach.add(mode);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return listSach;

    }

    public static List<SachModel> GetAllSachByTacGiaPerPage(Connection conn, String tacGia, Integer page, Integer numOfBookInOnePage) throws SQLException {
        List<SachModel> listSach = new ArrayList<SachModel>();

        String sql = "SELECT * FROM sach where tentacgia = ? LIMIT ? , ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tacGia);
            pstm.setInt(2, (page - 1) * numOfBookInOnePage);
            pstm.setInt(3, numOfBookInOnePage);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));

                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }

    public static int CountAllByTacGia(Connection conn, String tacGia) throws SQLException {

        int counter = 0;
        int x = 0;

        String sql = "SELECT COUNT(*) AS TOTAL FROM ((SELECT * FROM sach where tentacgia = ? ) AS T)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, tacGia);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                counter = rs.getInt("TOTAL");
            }

        } catch (SQLException e) {
            System.out.println("Exception: ");
            System.out.println(e.toString());
        }

        return counter;

    }

    public static List<SachModel> getAllSachByTheLoaiPerPage(Connection conn, Integer maTheLoai, Integer page, Integer numOfBookInOnePage) throws SQLException {
        List<SachModel> listSach = new ArrayList<SachModel>();

        String sql = "SELECT * FROM sach where matheloai = ? LIMIT ? , ?";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maTheLoai);
            pstm.setInt(2, (page - 1) * numOfBookInOnePage);
            pstm.setInt(3, numOfBookInOnePage);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));

                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }

    public static int CountAllByTheLoai(Connection conn, Integer maTheLoai) throws SQLException {

        int counter = 0;
        int x = 0;

        String sql = "SELECT COUNT(*) AS TOTAL FROM ((SELECT * FROM sach where matheloai = ? ) AS T)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, maTheLoai);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                counter = rs.getInt("TOTAL");
            }

        } catch (SQLException e) {
            System.out.println("Exception: ");
            System.out.println(e.toString());
        }

        return counter;

    }

    public static List<String> getDistinctAllTacGia(Connection conn) {
        List<String> listTacGia = new ArrayList<String>();

        String sql = "SELECT DISTINCT tentacgia FROM sach ";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                listTacGia.add(rs.getString("tentacgia"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listTacGia;
    }
 
    
    
    public static List<SachModel> getSachByMaTheLoaiTop4(Connection conn, int maTheLoai) {
        List<SachModel> listSach = new ArrayList<SachModel>();

        String sql = "SELECT * FROM sach WHERE matheloai = ? LIMIT 4";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, maTheLoai);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));
                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }
    
    
      public static List<SachModel> getRichOrPoorSachTop4(Connection conn, Boolean rich) {
        List<SachModel> listSach = new ArrayList<SachModel>();
        String sql;
        if(rich ==true)
            sql= "SELECT * FROM sach ORDER BY giaban DESC LIMIT 4";
        else
            sql= "SELECT * FROM sach ORDER BY giaban ASC LIMIT 4";
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));
                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }
    
            
             public static List<SachModel> getOutOfStockSachTop3(Connection conn) {
        List<SachModel> listSach = new ArrayList<SachModel>();
        String sql="SELECT * FROM sach WHERE soluongton <>0 ORDER BY soluongton ASC LIMIT 3";
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                SachModel SachModel = new SachModel(
                        rs.getInt("masach"),
                        rs.getInt("matheloai"),
                        rs.getString("tensach"),
                        rs.getString("nhaxuatban"),
                        rs.getInt("namxuatban"),
                        rs.getDouble("giaban"),
                        rs.getString("mota"),
                        rs.getString("anhdaidien"),
                        rs.getInt("soluongton"),
                        rs.getString("tentacgia"),
                        rs.getDouble("phantramgiamgia"),
                        rs.getDate("ngaybatdaugiamgia"),
                        rs.getDate("ngayketthucgiamgia"),
                        rs.getInt("trangthai"));
                listSach.add(SachModel);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listSach;
    }
}

