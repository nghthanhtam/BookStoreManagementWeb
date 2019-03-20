/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package Controller;

import Model.MessagesModel;
import Model.PhanQuyenModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "PhanQuyenServlet", urlPatterns = {"/admin/phanquyen"})
public class PhanQuyenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        /* Set page */
        req.setAttribute("txtTitle", "Phân quyền");
        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";
        
        String submitValue = req.getParameter("submit");
        if (submitValue !=null && submitValue.equals("them"))
        {
            
            String tenPhanQuyen = (String) req.getParameter("tenphanquyen");
            int qlThanhVien = req.getParameter("qlthanhvien") == null ? 0 : 1;
            int qlSach = req.getParameter("qlsach") == null ? 0 : 1;
            int qlTheLoai = req.getParameter("qltheloai") == null ? 0 : 1;
            int qlPhiShip = req.getParameter("qlphiship") == null ? 0 : 1;
            int qlPhanQuyen = req.getParameter("qlphanquyen") == null ? 0 : 1;
            int qlPhieuNhap = req.getParameter("qlphieunhap") == null ? 0 : 1;
            int qlPhieuChi = req.getParameter("qlphieuchi") == null ? 0 : 1;
            int qlNhaCungCap = req.getParameter("qlnhacungcap") == null ? 0 : 1;
            int qlHoaDon = req.getParameter("qlhoadon") == null ? 0 : 1;
            
             
             
            
            Connection conn = MyUtils.getStoredConnection(req);
            try {
                    boolean isOk = PhanQuyenModel.InsertNewPhanQuyen(conn, new PhanQuyenModel(0, tenPhanQuyen, qlThanhVien, qlSach, qlTheLoai, qlPhiShip, qlPhanQuyen, qlPhieuNhap, qlPhieuChi, qlNhaCungCap, qlHoaDon));
                    if (isOk)
                    {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã thêm phân quyền!";
                   }
                    else
                        isFailedRequest = true;
                         
            } catch (SQLException ex) { 
                isFailedRequest=true; 
                Logger.getLogger(PhanQuyenServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        else
            isFailedRequest = true;
        
        
        if (isFailedRequest) // nếu có lỗi thì hiện thông báo
        { 
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được xử lý!",MessagesModel.ATT_TYPE_ERROR));         
        }
        else
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!",noiDungThongBao,MessagesModel.ATT_TYPE_SUCCESS));         
        }
        
        req.getRequestDispatcher("/admin/phanquyen.jsp").forward(req, resp);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         
        req.setAttribute("txtTitle", "Phân quyền"); 
        
        Connection conn = MyUtils.getStoredConnection(req);
        List<PhanQuyenModel> listAllPhanQuyen= PhanQuyenModel.getAllPhanQuyen(conn);
        
        
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
        
        req.getRequestDispatcher("/admin/phanquyen.jsp").forward(req, resp);

    }

    
}
