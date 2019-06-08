/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.ConnectionUtils;
import Model.BaoCaoDoanhThuNgayModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/"})
public class AdminServlet extends HttpServlet {

    private void LoadThongKe(HttpServletRequest req, HttpServletResponse resp) {
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        Date now = new Date(cal.getTime().getTime());

        SimpleDateFormat ngay = new SimpleDateFormat("dd");
        SimpleDateFormat thang = new SimpleDateFormat("MM");
        SimpleDateFormat nam = new SimpleDateFormat("yyyy");

        Date ngayBatDau = new java.sql.Date(now.getTime() - (1000 * 60 * 60 * 24) * 7);
        Date ngayKetThuc = now;

        Connection conn = MyUtils.getStoredConnection(req);

        List<BaoCaoDoanhThuNgayModel> listDoanhThuNgayDonHangChuaHoanTat = null;
        List<BaoCaoDoanhThuNgayModel> listDoanhThuNgayDonHangHoanTat = null;
        try {
            listDoanhThuNgayDonHangChuaHoanTat = BaoCaoDoanhThuNgayModel.getThongKeDoanhThuDonHangChuaHoanTat(conn, ngayBatDau, ngayKetThuc);
            listDoanhThuNgayDonHangHoanTat = BaoCaoDoanhThuNgayModel.getThongKeDoanhThuDonHangHoanTat(conn, ngayBatDau, ngayKetThuc);
        } catch (Exception ex) {
        }

        String chartDataDonHangChuaHoanTat = "";

        for (int i = 0; i < listDoanhThuNgayDonHangChuaHoanTat.size(); i++) {
            BaoCaoDoanhThuNgayModel obj = listDoanhThuNgayDonHangChuaHoanTat.get(i);
            chartDataDonHangChuaHoanTat += "[gd(" + nam.format(obj.getNgay()) + ", " + thang.format(obj.getNgay()) + ", " + ngay.format(obj.getNgay()) + "), " + Math.round(obj.getDoanhThu()) + "]";
            if (i < listDoanhThuNgayDonHangChuaHoanTat.size() - 1) {
                chartDataDonHangChuaHoanTat += ",";
            }
        }

        String chartDataDonHangHoanTat = "";
        for (int i = 0; i < listDoanhThuNgayDonHangHoanTat.size(); i++) {
            BaoCaoDoanhThuNgayModel obj = listDoanhThuNgayDonHangHoanTat.get(i);
            chartDataDonHangHoanTat += "[gd(" + nam.format(obj.getNgay()) + ", " + thang.format(obj.getNgay()) + ", " + ngay.format(obj.getNgay()) + "), " + Math.round(obj.getDoanhThu()) + "]";
            if (i < listDoanhThuNgayDonHangHoanTat.size() - 1) {
                chartDataDonHangHoanTat += ",";
            }

        }

        req.setAttribute("chartDataDonHangChuaHoanTat", chartDataDonHangChuaHoanTat);
        req.setAttribute("chartDataDonHangHoanTat", chartDataDonHangHoanTat);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {
            req.getRequestDispatcher("/admin/admin-login.jsp").forward(req, resp);
            return;
        }

        LoadThongKe(req, resp);
        req.getRequestDispatcher("/admin/admin-home.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tendangnhap = req.getParameter("tendangnhap");
        String matkhau = req.getParameter("matkhau");

        ThanhVienModel thanhvien = null;

        if (tendangnhap == null || matkhau == null || tendangnhap.length() == 0 || matkhau.length() == 0) {
            /*hasError = true;
            errorString = "Required username and password!";*/

        } else {
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                thanhvien = ThanhVienModel.FindByTenDangNhap(conn, tendangnhap);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (thanhvien != null && matkhau.equals(thanhvien.getMatKhau())) {
                MyUtils.storeLoginedThanhVien(req.getSession(), thanhvien); // Lưu user vào session

                //String referrer = req.getHeader("referer");
                HttpSession session = ((HttpServletRequest) req).getSession();
                String autoRedirect = (String) session.getAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT);
                if (autoRedirect != null) {
                    session.removeAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT);
                    resp.sendRedirect(autoRedirect);
                } else {
                    
                    
                            LoadThongKe(req, resp);

                    
                    
                    req.getRequestDispatcher("/admin/admin-home.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("txtThongBao", "Đăng nhập thất bại!");
                req.getRequestDispatcher("/admin/admin-login.jsp").forward(req, resp);
            }
        }
    }
}
