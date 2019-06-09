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
import java.util.Objects;
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
@WebServlet(name = "EditThanhVienServlet", urlPatterns = {"/admin/thanhvien/edit"})
public class EditThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Thành viên");
        boolean isFailed = false; // request thất bại

        String noiDungThongBao = "";

        Connection conn = MyUtils.getStoredConnection(req);
        String submitValue = req.getParameter("submit");

        try {
            if (!(submitValue != null && submitValue.equals("capnhat"))) {
                throw new Exception("Yêu cầu của bạn không được xử lí!");
            }

            int maThanhVien = Integer.parseInt(req.getParameter("mathanhvien"));
            String matKhau = (String) req.getParameter("matkhau");
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");

            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            String email = (String) req.getParameter("email");
            int maTenQuyen = Integer.parseInt(req.getParameter("phanquyen"));

            
            req.setAttribute("hoten", hoTen);
            req.setAttribute("diachi", diaChi);
            req.setAttribute("sodienthoai", soDienThoai);
            req.setAttribute("email", email);
            
            
            
            if (MyUtils.checkSoDienThoai(soDienThoai) == false) {
                throw new Exception("Số điện thoại di động không hợp lệ! Phải bắt đầu bằng +84 hoặc 0 và gồm 9-11 ký tự!");
            }

            if (MyUtils.checkEmail(email) == false) {
                throw new Exception("Email không hợp lệ!");
            }

            if (ThanhVienModel.isExistEmail(conn, email, maThanhVien) == true) {
                throw new Exception("Email đã được sử dụng. Vui lòng nhập email khác!");
            }

            ThanhVienModel thanhVien = ThanhVienModel.FindByMaThanhVien(conn, maThanhVien);
            thanhVien.setMatKhau(MyUtils.MD5(matKhau));
            thanhVien.setHoTen(hoTen);
            thanhVien.setDiaChi(diaChi);
            thanhVien.setSoDienThoai(soDienThoai);
            thanhVien.setEmail(email);
            thanhVien.setMaPhanQuyen(maTenQuyen);

            boolean isOk;

            if (matKhau.length() > 0 || lapLaiMatKhau.length() > 0) {
                if (Objects.equals(matKhau, lapLaiMatKhau)) {
                    isOk = ThanhVienModel.UpdateThanhVien(conn, thanhVien);
                } else {
                    throw new Exception("Mật khẩu không khớp! Vui lòng kiểm tra lại mật khẩu.");
                }

            } else {
                isOk = ThanhVienModel.UpdateThanhVienWithoutPassword(conn, thanhVien);
            }

            if (isOk == false) {
                throw new Exception("Yêu cầu của bạn không được xử lý!");
            }

            isFailed = false;
            noiDungThongBao = "Đã cập nhật thông tin thành viên thành công!";

        } catch (Exception ex) {
            isFailed = true;
            noiDungThongBao = ex.getMessage();
        }

        if (isFailed) // nếu có lỗi thì hiện thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);

        List<ThanhVienModelWithTenQuyen> listAllThanhVienWithModel = ThanhVienModelWithTenQuyen.getAllThanhVienWithTenQuyen(conn);
        req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);

        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

        ThanhVienModel thanhVienModel = null;
        PhanQuyenModel phanQuyenModel = null;

        String noiDungThongBao = "";
        boolean isOk = true;

        try {
            int maThanhVien = Integer.parseInt((String) req.getParameter("id"));
            thanhVienModel = ThanhVienModel.FindByMaThanhVien(conn, maThanhVien);
            if (thanhVienModel == null) {
                throw new Exception("Không tìm thấy thành viên!");
            }

            phanQuyenModel = PhanQuyenModel.FindByMaPhanQuyen(conn, thanhVienModel.getMaPhanQuyen());
            if (phanQuyenModel == null) {
                throw new Exception("Không tìm thấy phân quyền!");
            }

        } catch (Exception ex) {
            isOk = false;
            noiDungThongBao = ex.getMessage();
        }

        if (isOk == true) {
            List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);

            req.setAttribute("txtTitle", "Sửa thông tin nhà cung cấp");
            req.setAttribute("thanhVienModel", thanhVienModel);
            req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
            req.getRequestDispatcher("/admin/thanhvien-edit.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));

            /* Hiển thị view */
            req.setAttribute("txtTitle", "Nhà cung cấp");
            List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
            req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);

            List<ThanhVienModelWithTenQuyen> listAllThanhVienWithModel = ThanhVienModelWithTenQuyen.getAllThanhVienWithTenQuyen(conn);
            req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);

            req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);
        }

    }
}
