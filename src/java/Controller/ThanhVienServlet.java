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
import java.util.List;
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

        boolean isOk = true;
        Connection conn = MyUtils.getStoredConnection(req);
        String noiDungThongBao = "";
        String button = req.getParameter("submit");

        try {
            if (!(button != null && button.equals("them"))) {
                throw new Exception("Yêu cầu của bạn không được xử lý!");
            }

            String tenDangNhap = (String) req.getParameter("tendangnhap");
            String matKhau = (String) req.getParameter("matkhau");
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");
            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            String email = (String) req.getParameter("email");
            int maPhanQuyen = Integer.parseInt(req.getParameter("phanquyen"));

            ThanhVienModel thanhVienModel = new ThanhVienModel(0, tenDangNhap, MyUtils.MD5(matKhau), hoTen, diaChi, soDienThoai, email, maPhanQuyen);
            req.setAttribute("thanhVienModel", thanhVienModel);
            
            
            if (MyUtils.checkUsername(tenDangNhap) == false) {
                throw new Exception("Tên đăng nhập không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9 và _ !");
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
            if (ThanhVienModel.InsertNewThanhVien(conn, thanhVienModel) == false) {
                throw new Exception("Thêm thành viên mới thất bại!");
            }

            noiDungThongBao = "Thêm thành viên thành công!";
            isOk = true;
        } catch (Exception ex) {
            isOk = false;
            noiDungThongBao = ex.getMessage();

        }
        if (isOk == true) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        }

        req.setAttribute("txtTitle", "Thành viên");

        List<ThanhVienModelWithTenQuyen> listAllThanhVienWithModel = ThanhVienModelWithTenQuyen.getAllThanhVienWithTenQuyen(conn);
        req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);

        List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
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
