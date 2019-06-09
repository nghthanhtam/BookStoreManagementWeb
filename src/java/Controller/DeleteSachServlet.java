/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.SachModel;
import Model.SachModelWithTheLoaiAndTrangThai;
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
@WebServlet(name = "DeleteSachServlet", urlPatterns = {"/admin/sach/delete"})
public class DeleteSachServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false;
        String noiDungThongBao = "";

        try {
            int maSach = Integer.parseInt((String) req.getParameter("id"));
            SachModel sach = SachModel.FindByMaSach(conn, maSach);
            if (sach == null) {
                throw new Exception("Không tìm thấy sách!");
            }

            if (sach.getTrangThai() == SachModel.TRANGTHAI_XOA) {
                throw new Exception("Sách đã được xóa trước đó!");
            }

            sach.setTrangThai(SachModel.TRANGTHAI_XOA);

            if (SachModel.UpdateSach(conn, sach) == false) {
                throw new Exception("Yêu cầu của bạn không thể xử lý!");
            }

            noiDungThongBao = "Xóa sách thành công!";
            isFailed = false;

        } catch (Exception ex) {

            isFailed = true;
            noiDungThongBao = ex.getMessage();
            ex.printStackTrace();
        }

        if (isFailed
                == true) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute(
                "txtTitle", "Sách");
        List<SachModelWithTheLoaiAndTrangThai> listAllSach = SachModelWithTheLoaiAndTrangThai.getAllSachWithTheLoaiAndTrangThai(conn);

        req.setAttribute(
                "listAllSach", listAllSach);

        req.getRequestDispatcher(
                "/admin/list-sach.jsp").forward(req, resp);

    }
}
