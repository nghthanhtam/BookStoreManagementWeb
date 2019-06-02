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

            Connection conn = MyUtils.getStoredConnection(req);

            try {
                //Check username gồm 6-14 kí tự từ a-z 0-9 và "_" "-"
                if (Pattern.compile("^[a-zA-Z0-9_\\-]{0,25}$").matcher(tenDangNhap).matches() == false) {
                    throw new Exception("Tên đăng nhập không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9 và _ !");
                }

                if (Pattern.compile("^[a-zA-Z0-9]{1,30}$").matcher(matKhau).matches() == false) {
                    throw new Exception("Mật khẩu không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9. Từ 1-30 ký tự!");
                }

                if (Pattern.compile("^[a-zA-Z0-9]{1,30}$").matcher(lapLaiMatKhau).matches() == false) {
                    throw new Exception("Lập lại mật khẩu không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9. Từ 1-30 ký tự!");
                }

                if (Pattern.compile("(\\+84|0)\\d{9,11}").matcher(soDienThoai).matches() == false) {
                    throw new Exception("Số điện thoại di động không hợp lệ! Phải bắt đầu bằng +84 hoặc 0 và gồm 9-11 ký tự!");
                }

                if (Pattern.compile("^[a-zA-Z0-9._]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$").matcher(email).matches() == false) {
                    throw new Exception("Email không hợp lệ!");
                }

                ThanhVienModel thanhVienTemp = null;
                thanhVienTemp = ThanhVienModel.FindByTenDangNhap(conn, tenDangNhap);

                if (thanhVienTemp != null) {
                    throw new Exception("Tên đăng nhập đã được sử dụng. Vui lòng nhập tên đăng nhập khác!");
                }

                thanhVienTemp = null;
                thanhVienTemp = ThanhVienModel.FindByEmail(conn, email);

                if (thanhVienTemp != null) {
                    throw new Exception("Email đã được sử dụng. Vui lòng nhập email khác!");
                }

                if (Objects.equals(matKhau, lapLaiMatKhau))//check mat khau co khop voi nhau khong
                {
                    boolean isOk = ThanhVienModel.InsertNewThanhVien(conn, new ThanhVienModel(0, tenDangNhap, matKhau, hoTen, diaChi, soDienThoai, email, maPhanQuyen));
                    if (isOk) {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã thêm thành viên mới thành công!";
                    } else {
                        throw new Exception("Yêu cầu của bạn không được xử lý!");
                    }
                } else {
                    throw new Exception("Mật khẩu không khớp! Vui lòng kiểm tra lại mật khẩu.");
                }

            } catch (Exception ex) {
                isFailedRequest = true;
                noiDungThongBao = ex.getMessage();
            }

        } else {
            isFailedRequest = true;
            noiDungThongBao = "Yêu cầu của bạn không thể xử lý!";
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

        /* Conflict*/
        for (int i = 0; i < listAllThanhVienWithModel.size(); i++) {
            System.out.println(listAllThanhVienWithModel.get(i).getTenPhanQuyen());
        }

        req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);

        /*
         req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);
         */
        /* Conflict*/
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);

        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);
    }

}
