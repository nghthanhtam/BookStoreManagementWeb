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
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ThanhVienServlet", urlPatterns = {"/admin/thanhvien"})
public class ThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        
        
        boolean isFailedRequest = false; // request thất bại
        boolean isWrongPassword = false;
        boolean haveUsedName=false;
        boolean haveUsedEmail = false;
        String noiDungThongBao = "";
        String button = req.getParameter("submit");
        
        if (button !=null && button.equals("them"))
        { 
            String tenDangNhap = (String) req.getParameter("tendangnhap");          
            String matKhau = (String) req.getParameter("matkhau");      
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");
            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai"); 
            String email = (String) req.getParameter("email");   
            int maTenQuyen = Integer.parseInt(req.getParameter("phanquyen"));
             System.out.println(maTenQuyen);
            Connection conn = MyUtils.getStoredConnection(req);
            
             
            
            try {
                List<ThanhVienModel> listAllThanhVien = ThanhVienModel.getAllThanhVien(conn);
                for(int i=0;i<listAllThanhVien.size();i++)
                {
                    if(Objects.equals(tenDangNhap,listAllThanhVien.get(i).getTenDangNhap()))
                    {
                     haveUsedName=true;
                     System.out.println("Trùng tên");
                     break;
                    }
                    if(Objects.equals(email,listAllThanhVien.get(i).getEmail()))
                    {
                        haveUsedEmail=true;
                        System.out.println("Trùng email");
                        break;
                    }
                    
                }
                System.out.println(haveUsedName);
                if(!haveUsedName && !haveUsedEmail)
                {
                        if(Objects.equals(matKhau,lapLaiMatKhau))//check mat khau co khop voi nhau khong
                    {
                        System.out.println("Khớp mật khẩu");
                        isWrongPassword=false;
                    }
                        else
                        {
                            isWrongPassword=true;
                        System.out.println("Không khớp mật khẩu");
                        }
                    if(isWrongPassword==false)
                    {
                       boolean isOk = ThanhVienModel.InsertNewThanhVien(conn, new ThanhVienModel(0, tenDangNhap, matKhau,hoTen,diaChi,soDienThoai,email,maTenQuyen));
                        System.out.println(isOk);
                        if (isOk )
                        {
                            isFailedRequest = false;
                            noiDungThongBao = "Đã thêm thành viên mới!";
                        }
                        else
                        {
                            System.out.println("stage cuoi");
                            isFailedRequest = true;
                        }
                    }
                }
                
                  
            } catch (SQLException ex) { 
                isFailedRequest=true; 
                Logger.getLogger(ThanhVienServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        else
            isFailedRequest = true;
        
        
        if (isFailedRequest || isWrongPassword || haveUsedName || haveUsedEmail) // nếu có lỗi thì hiện thông báo
        { 
            if(isWrongPassword) 
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Mật khẩu không khớp!",MessagesModel.ATT_TYPE_ERROR));         
            if(isFailedRequest) 
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được xử lý!",MessagesModel.ATT_TYPE_ERROR));         
            if(haveUsedName)
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Tên đăng đã có người dùng!",MessagesModel.ATT_TYPE_ERROR));         
            if(haveUsedEmail)
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Email đã có người dùng!",MessagesModel.ATT_TYPE_ERROR));
        }
        else
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!",noiDungThongBao,MessagesModel.ATT_TYPE_SUCCESS));         
        }
        
        
        
        
        Connection conn = MyUtils.getStoredConnection(req);
        req.setAttribute("txtTitle", "Thành viên"); 
        
        List<PhanQuyenModel> listAllPhanQuyen= PhanQuyenModel.getAllPhanQuyen(conn);
        List<ThanhVienModel> listAllThanhVien= ThanhVienModel.getAllThanhVien(conn);
        System.out.println("NÔNNONOO");
        
        req.setAttribute("listAllThanhVien", listAllThanhVien);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp); ;
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Thành viên"); 
        
        Connection conn = MyUtils.getStoredConnection(req);
        List<PhanQuyenModel> listAllPhanQuyen= PhanQuyenModel.getAllPhanQuyen(conn);
        List<ThanhVienModel> listAllThanhVien= ThanhVienModel.getAllThanhVien(conn);
        
        req.setAttribute("listAllThanhVien", listAllThanhVien);
        req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
        
 
        req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp); ;
    }

    
}
