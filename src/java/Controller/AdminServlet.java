/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.ConnectionUtils;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {
            request.getRequestDispatcher("/admin/admin-login.jsp").forward(request, response);
        } else {
            ThanhVienModel thanhvien = MyUtils.getLoginedThanhVien(session);
            request.setAttribute("txtTenDangNhap", thanhvien.getTenDangNhap());
            request.getRequestDispatcher("/admin/admin-home.jsp").forward(request, response);
        }

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
                    req.getRequestDispatcher("/admin/admin-home.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("txtThongBao", "Đăng nhập thất bại!");
                req.getRequestDispatcher("/admin/admin-login.jsp").forward(req, resp);
            }
        }
    }
}
