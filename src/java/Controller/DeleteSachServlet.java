/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel; 
import Model.SachModel;
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
            sach.setTrangThai(2);
            boolean isOk = SachModel.UpdateSach(conn, sach);
            if (isOk) {
                noiDungThongBao = "Xóa sách thành công!";
                isFailed = false;
            } else {
                throw new Exception("Yêu cầu của bạn không thể xử lý!");
            }
        } catch (Exception ex) {
            isFailed = false;
            noiDungThongBao = ex.getMessage();
            ex.printStackTrace();
        }

        if (isFailed == true) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Sách");
        List<SachModel> listAllSach = SachModel.getAllSach(conn);
        req.setAttribute("listAllSach", listAllSach);
        req.getRequestDispatcher("/admin/sach.jsp").forward(req, resp);

    }
}
