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
@WebServlet(name = "EditThanhVienServlet", urlPatterns = {"/admin/thanhvien/edit"})
public class EditThanhVienServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Thành viên");
        boolean isFailedRequest = false; // request thất bại
        boolean isWrongPassword = false;
        boolean haveUsedName=false;
        boolean haveUsedEmail = false;
        String noiDungThongBao = "";
        
        Connection conn = MyUtils.getStoredConnection(req);
        
        String submitValue = req.getParameter("submit");
        if (submitValue !=null && submitValue.equals("capnhat"))
        {
             int maThanhVien = Integer.parseInt(req.getParameter("mathanhvien"));
            String tenDangNhap = (String) req.getParameter("tendangnhap");
            String matKhau = (String) req.getParameter("matkhau");
            String hoTen = (String) req.getParameter("hoten");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            String email = (String) req.getParameter("email");
            int maTenQuyen = Integer.parseInt(req.getParameter("phanquyen"));
            String lapLaiMatKhau = (String) req.getParameter("laplaimatkhau");
            
            
            try {
                List<ThanhVienModel> listAllThanhVien = ThanhVienModel.getAllThanhVien(conn);
                for(int j=0;j<listAllThanhVien.size();j++)
                {
                    if(listAllThanhVien.get(j).getMaThanhVien()-maThanhVien==0)
                    {
                        listAllThanhVien.remove(j);
                    }
                }
                System.out.println(listAllThanhVien.size());
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
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
               
                if(!haveUsedName && !haveUsedEmail)
                {
                    if(!lapLaiMatKhau.equals(""))
                    {
                        System.out.println("Đổi mật khẩu");
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
                    }
                        
                        System.out.println(maTenQuyen);
                    if(isWrongPassword==false)
                    {
                        
                        boolean isOk = ThanhVienModel.UpdateThanhVien(conn, new ThanhVienModel(maThanhVien, tenDangNhap, matKhau,hoTen,diaChi,soDienThoai,email,maTenQuyen);
                        System.out.println(isOk);
                        if (isOk)
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
        {
            isFailedRequest = true;
        }
        
            System.out.println("isFailedRequest"+isFailedRequest);
            System.out.println("isWrongPassword"+isWrongPassword);
            System.out.println("haveUsedName"+haveUsedName);
            System.out.println("haveUsedEmail"+haveUsedEmail);
        
        if (isFailedRequest || isWrongPassword || haveUsedName || haveUsedEmail) // nếu có lỗi thì hiện thông báo
        { 
            if(isWrongPassword) 
                { 
                System.out.println("XDXĐXD1");
             
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Mật khẩu không khớp!",MessagesModel.ATT_TYPE_ERROR));         
                }
            if(isFailedRequest) { 
                System.out.println("XDXĐXD2");
             
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được xử lý!",MessagesModel.ATT_TYPE_ERROR));         
            }
            if(haveUsedName)
            { 
                System.out.println("XDXĐXD3");
                 req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Tên đăng đã có người dùng!",MessagesModel.ATT_TYPE_ERROR));         
            }
            if(haveUsedEmail)
            { 
                System.out.println("XDXĐXD4");
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Email đã có người dùng!",MessagesModel.ATT_TYPE_ERROR));
            }
               
        }
        else
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!",noiDungThongBao,MessagesModel.ATT_TYPE_SUCCESS));         
           
        }
        
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
    Connection conn = MyUtils.getStoredConnection(req);
    
        ThanhVienModel thanhVienModel = null;
         PhanQuyenModel phanQuyenModel=null;
               
        boolean result = false;
        boolean result1 = false;
        try {
            int maThanhVien = Integer.parseInt((String) req.getParameter("id"));
            System.out.println(maThanhVien);
            
            thanhVienModel = ThanhVienModel.FindByMaNhaCungCap(conn, maThanhVien);
            if (thanhVienModel != null)
            {
                
                result = true;
                phanQuyenModel = PhanQuyenModel.FindByMaPhanQuyen(conn, thanhVienModel.getMaPhanQuyen());
                
                if (phanQuyenModel != null)
                {
                    
                    result1=true;
                }
            }
                
        } catch (Exception ex) {
            result = false;
        }
        
        if (result == true && result1 ==true)
        {   
            List<PhanQuyenModel> listAllPhanQuyen= PhanQuyenModel.getAllPhanQuyen(conn);
            int thuTu=0;
            for(int i=0;i<listAllPhanQuyen.size();i++)
            {
                thuTu=thuTu+1;
                if(phanQuyenModel.getMaPhanQuyen() == listAllPhanQuyen.get(i).getMaPhanQuyen())
                {
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
            
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!","Yêu cầu của bạn không được thực hiện!",MessagesModel.ATT_TYPE_ERROR));         
            
            /* Hiển thị view */
            req.setAttribute("txtTitle", "Nhà cung cấp"); 
            List<PhanQuyenModel> listAllPhanQuyen= PhanQuyenModel.getAllPhanQuyen(conn);
            List<ThanhVienModel> listAllThanhVien= ThanhVienModel.getAllThanhVien(conn);
        
            req.setAttribute("listAllThanhVien", listAllThanhVien);
            req.setAttribute("listAllPhanQuyen", listAllPhanQuyen);
            req.getRequestDispatcher("/admin/thanhvien.jsp").forward(req, resp);
        }
        
        
         
    }    
}

   


