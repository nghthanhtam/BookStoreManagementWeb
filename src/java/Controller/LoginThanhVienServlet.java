/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "LoginThanhVienServlet", urlPatterns = {"/login"})
public class LoginThanhVienServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ThanhVienModel thanhVien = MyUtils.getLoginedThanhVien(req.getSession());
        if (thanhVien != null) {
            req.setAttribute("status", true);
            req.setAttribute("noiDungThongBao", "Bạn đã đăng nhập với tên <b>" + thanhVien.getTenDangNhap() + "!</b>");
        }
        req.getRequestDispatcher("/dangnhap.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tendangnhap = req.getParameter("tendangnhap");
        String matkhau = req.getParameter("matkhau");
        Connection conn = MyUtils.getStoredConnection(req);
        ThanhVienModel thanhvien = null;
        HttpSession session = ((HttpServletRequest) req).getSession();

        boolean isOk = true;
        String noiDungThongBao = "";
        try {

            String xxxx = (String) session.getAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT_HOME);
            if (session.getAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT_HOME) == null) {
                String queryString = "?" + ((HttpServletRequest) req).getQueryString();
                if (queryString.equals("?null")) {
                    queryString = "";
                }
                String uri = ((HttpServletRequest) req).getRequestURI();
                if (!req.getHeader("referer").equals(uri + queryString)) {
                    session.setAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT_HOME, req.getHeader("referer"));
                }
            }

            if (tendangnhap == null || matkhau == null || tendangnhap.length() == 0 || matkhau.length() == 0) {
                throw new Exception("Thông tin đăng nhập không được để trống!");
            }
            thanhvien = ThanhVienModel.FindByTenDangNhap(conn, tendangnhap);
            if (thanhvien != null && matkhau.equals(thanhvien.getMatKhau())) {
                MyUtils.storeLoginedThanhVien(req.getSession(), thanhvien); // Lưu user vào session
            } else {
                throw new Exception("Tên đăng nhập hoặc mật khẩu không chính xác!");
            }

            noiDungThongBao = "Bạn đã đăng nhập thành công với tên: <b>" + thanhvien.getTenDangNhap() + "!</b>";

//            String uri = ((HttpServletRequest) req).getRequestURI();
        } catch (Exception ex) {
            noiDungThongBao = ex.getMessage();
            isOk = false;
        }

        if (isOk) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
            req.setAttribute("status", true);
            req.setAttribute("noiDungThongBao", "Trang sẽ tự động chuyển hướng trong vài giây!");

            if (session.getAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT_HOME) != null) {
                String urlReturn = (String) session.getAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT_HOME);
                req.setAttribute("urlReturn", urlReturn);
                session.removeAttribute(MyUtils.ATT_NAME_AUTO_REDIRECT_HOME);
            }

        } else {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        }

        req.getRequestDispatcher("/dangnhap.jsp").forward(req, resp);

    }
}
