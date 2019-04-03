/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import java.io.IOException;
import javax.servlet.ServletException;

import java.sql.Connection;
import Utility.MyUtils;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteNhaCungCapServlet", urlPatterns = {"/admin/nhacungcap/delete"})
public class DeleteNhaCungCapServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        System.out.println("XA^XAXAXAXAXAX");
        boolean result = false;
        try {
            int maNhaCungCap = Integer.parseInt((String) req.getParameter("id"));
            System.out.println("XA^XAXAXAXAXAX");
            result = NhaCungCapModel.DeleteNhaCungCapById(conn, maNhaCungCap);

        } catch (Exception ex) {
            result = false;
        }

        if (result == true) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", "Đã xóa thành công!", MessagesModel.ATT_TYPE_SUCCESS));

        } else {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));

        }

        /* Hiển thị view */
        req.setAttribute("txtTitle", "Nhà cung cấp");
        System.out.println("XA^xázd");
        List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);
        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);

    }

}
