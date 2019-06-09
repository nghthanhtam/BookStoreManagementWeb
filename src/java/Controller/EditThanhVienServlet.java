/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhanQuyenModel;
import Model.ThanhVienModel;
import Model.ThanhVienModelWithTenQuyen;
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
 * @author Admin
 */
@WebServlet(name = "EditThanhVienServlet", urlPatterns = {"/admin/thanhvien/edit"})
public class EditThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Thành viên");
        boolean isFailedRequest = false; // request thất bại
        boolean isOk;
        
        String noiDungThongBao = "";

        Connection conn = MyUtils.getStoredConnection(req);
        String submitValue = req.getParameter("submit");

        if (submitValue != null && submitValue.equals("capnhat")) {

            int maThanhVien = Integer.parseInt(req.getParameter("mathanhvien"));
            String tenDangNhap = (String) req.getParameter("tendangnhap");
            String matKhau = (String) req.getParameter("matkhau");
            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            String email = (String) req.getParameter("email");
            int maTenQuyen = Integer.parseInt(req.getParameter("phanquyen"));
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");

            ThanhVienModel temp = new ThanhVienModel(maThanhVien, tenDangNhap, matKhau, hoTen, diaChi, soDienThoai, email, maTenQuyen);

            try {

                if (MyUtils.checkUsername(tenDangNhap)==false) {

                    throw new Exception("Tên đăng nhập không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9 và _ !");
                }
                if (MyUtils.checkSoDienThoai(soDienThoai) == false) {
                    throw new Exception("Số điện thoại di động không hợp lệ! Phải bắt đầu bằng +84 hoặc 0 và gồm 9-11 ký tự!");
                }

                if (MyUtils.checkEmail(email) == false) {
                    throw new Exception("Email không hợp lệ!");
                }

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

                if (!lapLaiMatKhau.equals("") || !matKhau.equals("")) {
                    
                    

                    if (Pattern.compile("^[a-zA-Z0-9]{1,30}$").matcher(matKhau).matches() == false) {
                        throw new Exception("Mật khẩu không hợp lệ! Chỉ bao gồm a-z, A-Z, 0-9. Từ 1-30 ký tự!");
                    }
                    if (Objects.equals(matKhau, lapLaiMatKhau))//check mat khau co khop voi nhau khong
                    {
                        isOk = ThanhVienModel.UpdateThanhVien(conn, new ThanhVienModel(maThanhVien, tenDangNhap, matKhau, hoTen, diaChi, soDienThoai, email, maTenQuyen));
                    } else 
                        throw new Exception("Mật khẩu không khớp! Vui lòng kiểm tra lại mật khẩu.");
                    

                } else {
                    isOk = ThanhVienModel.UpdateThanhVienWithoutPassword(conn, new ThanhVienModel(maThanhVien, tenDangNhap, matKhau, hoTen, diaChi, soDienThoai, email, maTenQuyen));
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

        req.setAttribute("txtTitle", "Thành viên");

        List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);

      
        List<ThanhVienModelWithTenQuyen> listAllThanhVienWithModel = ThanhVienModelWithTenQuyen.getAllThanhVienWithTenQuyen(conn);
        req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);

       
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);

        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

        ThanhVienModel thanhVienModel = null;
        PhanQuyenModel phanQuyenModel = null;

        boolean result = false;
        boolean result1 = false;
        try {
            int maThanhVien = Integer.parseInt((String) req.getParameter("id"));
            System.out.println(maThanhVien);

            thanhVienModel = ThanhVienModel.FindByMaNhaCungCap(conn, maThanhVien);
            if (thanhVienModel != null) {

                result = true;
                phanQuyenModel = PhanQuyenModel.FindByMaPhanQuyen(conn, thanhVienModel.getMaPhanQuyen());

                if (phanQuyenModel != null) {

                    result1 = true;
                }
            }

        } catch (Exception ex) {
            result = false;
        }

        if (result == true && result1 == true) {
            List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
            int thuTu = 0;
            for (int i = 0; i < listAllPhanQuyen.size(); i++) {
                thuTu = thuTu + 1;
                if (phanQuyenModel.getMaPhanQuyen() == listAllPhanQuyen.get(i).getMaPhanQuyen()) {
                    break;
                }
            }
            System.out.println(thuTu);
            req.setAttribute("txtTitle", "Sửa thông tin nhà cung cấp");
            req.setAttribute("thanhVienModel", thanhVienModel);
            req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
            req.setAttribute("phanQuyenModel", phanQuyenModel);
            req.setAttribute("thuTu", thuTu);
            req.getRequestDispatcher("/admin/thanhvien-edit.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));

            /* Hiển thị view */
            req.setAttribute("txtTitle", "Nhà cung cấp");
            List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
            List<ThanhVienModel> listAllThanhVien = ThanhVienModel.getAllThanhVien(conn);

            req.setAttribute("listAllThanhVien", listAllThanhVien);
            req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
            req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);
        }

    }
}
