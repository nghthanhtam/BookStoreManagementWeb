/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhanQuyenModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author MITICC06
 */
@WebServlet(name = "DangKyThanhVienServlet", urlPatterns = {"/dangky"})
public class DangKyThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isFailedRequest = false; // request thất bại 

        String noiDungThongBao = "";
        String button = req.getParameter("submit");
        Connection conn = MyUtils.getStoredConnection(req);

        try {
            if (!(button != null && button.equals("dangky"))) {
                throw new Exception("Yêu cầu của bạn không thể xử lý!");
            }

            String tenDangNhap = (String) req.getParameter("tendangnhap");
            String matKhau = (String) req.getParameter("matkhau");
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");
            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            String email = (String) req.getParameter("email");
            String xacNhanDieuKhoan = (String) req.getParameter("xacnhandieukhoan");

            req.setAttribute("tendangnhap", tenDangNhap);
            req.setAttribute("hoten", hoTen);
            req.setAttribute("diachi", diaChi);
            req.setAttribute("sodienthoai", soDienThoai);
            req.setAttribute("email", email);
            req.setAttribute("xacnhandieukhoan", xacNhanDieuKhoan);

            ThanhVienModel thanhVienModel = new ThanhVienModel(0, tenDangNhap, MyUtils.MD5(matKhau), hoTen, diaChi, soDienThoai, email, PhanQuyenModel.ATT_MAPHANQUYEN_THANHVIEN);

            if (MyUtils.checkUsername(tenDangNhap) == false) // username k hợp lệ //Check username gồm 6-14 kí tự từ a-z 0-9 và "_" "-"
            {
                throw new Exception("Tên đăng nhập không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9 và _");
            }

            if (ThanhVienModel.isExistTenDangNhap(conn, tenDangNhap) == true) {
                throw new Exception("Tên đăng nhập này đã được sử dụng!");
            }

            if (MyUtils.checkSoDienThoai(soDienThoai) == false) {
                throw new Exception("Số điện thoại di động không hợp lệ! Phải bắt đầu bằng +84 hoặc 0 và gồm 9-11 ký tự!");
            }

            if (MyUtils.checkEmail(email) == false) {
                throw new Exception("Email không hợp lệ!");
            }

            if (ThanhVienModel.isExistEmail(conn, email, 0)) {
                throw new Exception("Email đã được sử dụng. Vui lòng nhập email khác!");
            }

            if (matKhau.length() == 0 || lapLaiMatKhau.length() == 0) {
                throw new Exception("Bạn chưa nhập đủ mật khẩu!");
            }

            if (matKhau.equals(lapLaiMatKhau) == false) {
                throw new Exception("Mật khẩu không khớp! Vui lòng kiểm tra lại mật khẩu.");
            }

            if (xacNhanDieuKhoan == null || !xacNhanDieuKhoan.equals("true")) {
                throw new Exception("Bạn chưa đồng ý điều khoản sử dụng!");
            }

            if (ThanhVienModel.InsertNewThanhVien(conn, thanhVienModel) == false) {
                throw new Exception("Thêm thành viên mới thất bại!");
            }

            noiDungThongBao = "Đăng ký thành viên mới hoàn tất!";
            isFailedRequest = false;

        } catch (Exception ex) {
            isFailedRequest = true;
            noiDungThongBao = ex.getMessage();
        }

        if (isFailedRequest == true) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Đăng ký thành viên");

        req.getRequestDispatcher("dangky.jsp").forward(req, resp);;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("dangky.jsp").forward(req, resp);

    }

}
