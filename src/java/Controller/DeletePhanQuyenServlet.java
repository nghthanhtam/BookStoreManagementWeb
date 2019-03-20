/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhanQuyenModel;
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
 * @author MITICC06
 */
@WebServlet(name = "DeletePhanQuyenServlet", urlPatterns = {"/admin/phanquyen/delete"})
public class DeletePhanQuyenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        boolean result = false;
        try {
            int maPhanQuyen = Integer.parseInt((String) req.getParameter("id"));
            System.out.println(maPhanQuyen);
            result = PhanQuyenModel.DeleteById(conn, maPhanQuyen);
            
        } catch (Exception ex) {
            result = false;
        }
        
        if (result == true)
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!","Đã xóa thành công!",MessagesModel.ATT_TYPE_SUCCESS));         

        } else {
            
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được thực hiện!",MessagesModel.ATT_TYPE_ERROR));         
 
        
        }
        
        /* Hiển thị view */
        req.setAttribute("txtTitle", "Phân quyền"); 
        
        List<PhanQuyenModel> listAllPhanQuyen= PhanQuyenModel.getAllPhanQuyen(conn);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
        req.getRequestDispatcher("/admin/phanquyen.jsp").forward(req, resp);

    }

    
}
