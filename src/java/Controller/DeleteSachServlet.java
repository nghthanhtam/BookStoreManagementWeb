/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.SachModel;
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
@WebServlet(name = "DeleteSachServlet", urlPatterns = {"/admin/sach/delete"})
public class DeleteSachServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        Connection conn = MyUtils.getStoredConnection(req);
        SachModel SachModel = null;
        boolean result = false;
        try {
            int maSach = Integer.parseInt((String) req.getParameter("id"));
            System.out.println(maSach);
            SachModel sach = SachModel.FindByMaSach(conn, maSach);
            sach.setTrangThai(3);
            boolean x= SachModel.UpdateSach(conn, sach,false);
            if (x) {
                result = true;
            }
            else
                result = false;
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }

        if (result == true) {

                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", "Thay đổi trạng thái của sách thành công!", MessagesModel.ATT_TYPE_SUCCESS));
       
             req.setAttribute("txtTitle", "Sách");
        List<SachModel> listAllSach = SachModel.getAllSach(conn);
        System.out.println(listAllSach.size());
        req.setAttribute("listAllSach", listAllSach);

        req.getRequestDispatcher("/admin/sach.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));

            /* Hiển thị view */
            req.setAttribute("txtTitle", "Sách");

            List<SachModel> listAllSach = SachModel.getAllSach(conn);
            req.setAttribute("listAllSach", listAllSach);

            req.getRequestDispatcher("/admin/sach.jsp").forward(req, resp);
  }

            
    }

   
}
