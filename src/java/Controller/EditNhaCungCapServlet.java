/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditNhaCungCapServlet", urlPatterns = {"/admin/nhacungcap/edit"})
public class EditNhaCungCapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            
         Connection conn = MyUtils.getStoredConnection(req);
        boolean result = false;
        try {
            int maNhaCungCap = Integer.parseInt((String) req.getParameter("id"));
            NhaCungCapModel  nhaCungCap=NhaCungCapModel.FindByMaNhaCungCap(conn, maNhaCungCap);
            req.setAttribute("tenNhaCungCap", nhaCungCap.getTenNhaCungCap());
            req.setAttribute("diaChi", nhaCungCap.getDiaChi());
            req.setAttribute("soDienThoai", nhaCungCap.getSoDienThoai());
            req.setAttribute("soTienNo", nhaCungCap.getSoTienNo());
        
            if(nhaCungCap!=null)
                result = true;
        } catch (Exception ex) {
            result = false;
        }
        
        if (result == true)
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!","Đã tìm thấy nhà cung cấp theo mã thành công!",MessagesModel.ATT_TYPE_SUCCESS));         

        } else {
            
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Không tìm thấy nhà cung cấp theo mã!",MessagesModel.ATT_TYPE_ERROR));         
 
        
        }
        //String ten= req.getParameter("tennhacungcap");
        req.setAttribute("txtTitle", "Nhà cung cấp"); 
        List<NhaCungCapModel> listAllNhaCungCap= NhaCungCapModel.getAllNhaCungCap(conn);
        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);
    }

    
}
