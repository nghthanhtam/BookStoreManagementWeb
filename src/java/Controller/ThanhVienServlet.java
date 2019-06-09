/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhanQuyenModel;
import Model.ThanhVienModel;
import Model.ThanhVienModelWithTenQuyen;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ThanhVienServlet", urlPatterns = {"/admin/thanhvien"})
public class ThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isFailedRequest = false; // request thất bại
        boolean isOk = false;

        String noiDungThongBao = "";
        String button = req.getParameter("submit");

        if (button != null && button.equals("them")) {
            String tenDangNhap = (String) req.getParameter("tendangnhap");
            String matKhau = (String) req.getParameter("matkhau");
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");
            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            String email = (String) req.getParameter("email");
            int maPhanQuyen = Integer.parseInt(req.getParameter("phanquyen"));

            ThanhVienModel temp = new ThanhVienModel(-1, tenDangNhap, matKhau, hoTen, diaChi, soDienThoai, email, maPhanQuyen);

            try {

                if (MyUtils.checkUsername(tenDangNhap) == false) {

                    throw new Exception("Tên đăng nhập không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9 và _ !");
                }
                if (MyUtils.checkSoDienThoai(soDienThoai) == false) {
                    throw new Exception("Số điện thoại di động không hợp lệ! Phải bắt đầu bằng +84 hoặc 0 và gồm 9-11 ký tự!");
                }

                if (MyUtils.checkEmail(email) == false) {
                    throw new Exception("Email không hợp lệ!");
                }
                if (Pattern.compile("^[a-zA-Z0-9]{1,30}$").matcher(matKhau).matches() == false) {
                    throw new Exception("Mật khẩu không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9. Từ 1-30 ký tự!");
                }
                Connection conn = MyUtils.getStoredConnection(req);
                ThanhVienModel thanhVienTemp = null;
                thanhVienTemp = ThanhVienModel.FindByTenDangNhapNotSameID(conn, temp);

                if (thanhVienTemp != null) {
                    throw new Exception("Tên đăng nhập đã được sử dụng. Vui lòng nhập tên đăng nhập khác!");
                }

                thanhVienTemp = null;
                thanhVienTemp = ThanhVienModel.FindByEmailNotSameID(conn, temp);

                if (thanhVienTemp != null) {
                    throw new Exception("Email đã được sử dụng. Vui lòng nhập email khác!");
                }

                if (Objects.equals(matKhau, lapLaiMatKhau))//check mat khau co khop voi nhau khong
                {
                    isOk = ThanhVienModel.InsertNewThanhVien(conn, new ThanhVienModel(0, tenDangNhap, matKhau, hoTen, diaChi, soDienThoai, email, maPhanQuyen));
                } else {
                    throw new Exception("Mật khẩu không khớp! Vui lòng kiểm tra lại mật khẩu.");
                }

                if (isOk) {
                    isFailedRequest = false;
                    noiDungThongBao = "Đã cập nhật thông tin thành viên thành công!";
                } else {
                    throw new Exception("Yêu cầu của bạn không được xử lý!");
                }

            } catch (Exception ex) {
                isFailedRequest = true;
                noiDungThongBao = ex.getMessage();

            }

        } else {
            noiDungThongBao = "Yêu cầu của bạn không được xử lý";
            isFailedRequest = true;
        }

        if (isFailedRequest) // nếu có lỗi thì hiện thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));

        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        Connection conn = MyUtils.getStoredConnection(req);
        req.setAttribute("txtTitle", "Thành viên");

        List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
        List<ThanhVienModel> listAllThanhVien = ThanhVienModel.getAllThanhVien(conn);

        req.setAttribute("listAllThanhVien", listAllThanhVien);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Thành viên");

        Connection conn = MyUtils.getStoredConnection(req);
        List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
        List<ThanhVienModelWithTenQuyen> listAllThanhVienWithModel = ThanhVienModelWithTenQuyen.getAllThanhVienWithTenQuyen(conn);
        req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);

        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);
    }

}
