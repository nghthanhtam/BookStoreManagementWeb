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
 * @author MITICC06
 */
public class BaoCaoDoanhThuNgayModel {

    public BaoCaoDoanhThuNgayModel(Date ngay, double doanhThu) {
        this.ngay = ngay;
        this.doanhThu = doanhThu;
    }
    private Date ngay;

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }
    private double doanhThu;

    public static List<BaoCaoDoanhThuNgayModel> getThongKeDoanhThuDonHangChuaHoanTat(Connection conn, Date ngayBD, Date ngayKT) { // đơn hàng chưa được giao, đang tiếp nhận, vận chuyển,....
        List<BaoCaoDoanhThuNgayModel> list = new ArrayList<BaoCaoDoanhThuNgayModel>();

        String sql = "SELECT tb.selected_date ngaylap, SUM(donhang.tongtien) tongtien "
                + "FROM "
                + "( SELECT * "
                + "FROM donhang "
                + "WHERE (trangthai <>3) and (trangthai<>0) AND ( DATE(donhang.ngaylap) between ? and ?) "
                + ") donhang "
                + "RIGHT JOIN "
                + "(select * from (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v where selected_date between ? and ?) tb "
                + "ON DATE(donhang.ngaylap) = tb.selected_date  "
                + "GROUP BY tb.selected_date "
                + "ORDER BY tb.selected_date";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            // pstm.setInt(1, DonHangModel.TRANGTHAI_DA_HOAN_TAT);
            pstm.setDate(1, ngayBD);
            pstm.setDate(2, ngayKT);
            pstm.setDate(3, ngayBD);
            pstm.setDate(4, ngayKT);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                BaoCaoDoanhThuNgayModel obj = new BaoCaoDoanhThuNgayModel(rs.getDate("ngaylap"), rs.getDouble("tongtien"));
                list.add(obj);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return list;
    }

    public static List<BaoCaoDoanhThuNgayModel> getThongKeDoanhThuDonHangHoanTat(Connection conn, Date ngayBD, Date ngayKT) { // đơn hàng đã hoàn tất giao dịch
        List<BaoCaoDoanhThuNgayModel> list = new ArrayList<BaoCaoDoanhThuNgayModel>();

        String sql = "SELECT tb.selected_date ngaylap, SUM(donhang.tongtien) tongtien "
                + "FROM "
                + "( SELECT * "
                + "FROM donhang "
                + "WHERE (trangthai = 3) AND ( DATE(donhang.ngaylap) between ? and ?) "
                + ") donhang "
                + "RIGHT JOIN "
                + "(select * from (select adddate('1970-01-01',t4.i*10000 + t3.i*1000 + t2.i*100 + t1.i*10 + t0.i) selected_date from (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t0, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, (select 0 i union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v where selected_date between ? and ?) tb "
                + "ON DATE(donhang.ngaylap) = tb.selected_date  "
                + "GROUP BY tb.selected_date "
                + "ORDER BY tb.selected_date";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            // pstm.setInt(1, DonHangModel.TRANGTHAI_DA_HOAN_TAT);
            pstm.setDate(1, ngayBD);
            pstm.setDate(2, ngayKT);
            pstm.setDate(3, ngayBD);
            pstm.setDate(4, ngayKT);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                BaoCaoDoanhThuNgayModel obj = new BaoCaoDoanhThuNgayModel(rs.getDate("ngaylap"), rs.getDouble("tongtien"));
                list.add(obj);
            }

        } catch (SQLException e) {
            e.toString();
        }

        return list;
    }
}
