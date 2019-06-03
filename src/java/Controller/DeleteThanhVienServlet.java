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
@WebServlet(name = "DeleteThanhVienServlet", urlPatterns = {"/admin/thanhvien/delete"})
public class DeleteThanhVienServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        boolean isOk = true;
        String noiDungThongBao = "";
        int maThanhVien = 0;

        try {

            try {
                maThanhVien = Integer.parseInt((String) req.getParameter("id"));
            } catch (Exception ex) {
                throw new Exception("Mã thành viên không hợp lệ!");
            }

            if (ThanhVienModel.DeleteThanhVienById(conn, maThanhVien) == false) {
                throw new Exception("Yêu cầu không được thực hiện!");
            }
            noiDungThongBao = "Đã xóa thành công!";
        } catch (Exception ex) {
            isOk = false;
            noiDungThongBao = ex.getMessage();
        }

        if (isOk == true) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        }

        /* Hiển thị view */
        req.setAttribute("txtTitle", "Thành viên");
        List<PhanQuyenModel> listAllPhanQuyen = PhanQuyenModel.getAllPhanQuyen(conn);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);

        List<ThanhVienModelWithTenQuyen> listAllThanhVienWithModel = ThanhVienModelWithTenQuyen.getAllThanhVienWithTenQuyen(conn);
        req.setAttribute("listAllThanhVienWithModel", listAllThanhVienWithModel);

        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);

    }

}
