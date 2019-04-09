/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhanQuyenModel;
import Model.SachModel;
import Model.ThanhVienModel;
import Model.TheLoaiModel;
import Utility.MyUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Trung
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "AddSachServlet", urlPatterns = {"/admin/sach/add"})
public class AddSachServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileInputStream input= null;
        Connection conn = MyUtils.getStoredConnection(req);
        InputStream inputStream = null;
        boolean isFailedRequest = false; // request thất bại
        boolean isWrongPicture = false;
        boolean isWrongGiaBan = false;
        boolean isWrongSoLuongTon = false;
        boolean isWrongTenTacGia = false;
        boolean isWrongPhanTramGiamGia = false;
        boolean isWrongTrangThai = false;
        String noiDungThongBao = "";
        String button = req.getParameter("submit");

        
             
            String tenSach = (String) req.getParameter("tensach");
            int maTheLoai = Integer.parseInt(req.getParameter("theloai"));
            String nhaXuatBan = (String) req.getParameter("nhaxuatban");
            int namXuatBan = Integer.parseInt(req.getParameter("namxuatban"));
            Double giaBan = Double.parseDouble(req.getParameter("giaban"));
            String moTa = (String) req.getParameter("mota");
            
            File anhDaiDienFile= new File (req.getParameter("anhdaidien"));
            System.out.println("XXXXXXXXXXXXXXX");
            System.out.println(anhDaiDienFile.getAbsolutePath());
            System.out.println("XXXXXXXXXXXXXXX");
            input= new FileInputStream(anhDaiDienFile);
            
            Blob anhDaiDien = null;
            
            String tenTacGia = (String) req.getParameter("tentacgia");
            int phanTramGiamGia = Integer.parseInt(req.getParameter("phantramgiamgia"));
            String khoangThoiGian = (String) req.getParameter("khoangthoigian");
            String ngayBatDauGiamGia=khoangThoiGian.substring(0, 10);
            String ngayKetThucGiamGia=khoangThoiGian.substring(13);
            
     

                                
      
                                        
                                        
        if (button != null && button.equals("them")) {
            
            
//            Part filePart = req.getPart("anhdaidien");  
//            
//              if (filePart != null) {
//            // prints out some information for debugging
//            System.out.println(filePart.getName());
//            System.out.println(filePart.getSize());
//            System.out.println(filePart.getContentType());
//
//            //obtains input stream of the upload file
//            //the InputStream will point to a stream that contains
//            //the contents of the file
//            inputStream = filePart.getInputStream();
//            }
//              else
//                isWrongPicture=true;
//              System.out.println("XXXXXX");
//              System.out.println(isWrongPicture);
//              System.out.println("XXXXXX");
//              
//         
            
            
            System.out.println(ngayBatDauGiamGia);
            System.out.println(ngayKetThucGiamGia);
            
            SimpleDateFormat x = new SimpleDateFormat("dd/MM/yyyy");
            
            java.util.Date dateBatDau;
            java.sql.Date sqlBatDau=null;
            java.util.Date dateKetThuc;
            java.sql.Date sqlKetThuc=null;
            try {
                System.out.println("123123123");
                dateBatDau = x.parse(ngayBatDauGiamGia);
                sqlBatDau= new java.sql.Date(dateBatDau.getTime());
                dateKetThuc = x.parse(ngayKetThucGiamGia);
                sqlKetThuc= new java.sql.Date(dateKetThuc.getTime());
            } catch (ParseException ex) {
                
                Logger.getLogger(AddSachServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            int trangThai = Integer.parseInt(req.getParameter("trangthai"));
           
          
    
            try {
                boolean isOk = SachModel.InsertNewSach(conn, new SachModel(0, maTheLoai, tenSach,
                        nhaXuatBan,namXuatBan, giaBan, moTa, anhDaiDien,0, tenTacGia,
                        phanTramGiamGia,sqlBatDau,sqlKetThuc,trangThai), input);
                                System.out.println(isOk);
                if (isOk) {
                                isFailedRequest = false;
                                noiDungThongBao = "Đã thêm sách mới!";
                            } else {
                                System.out.println("stage cuoi");
                                isFailedRequest = true;
                            }
            } catch (SQLException ex) {
                Logger.getLogger(AddSachServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                              
            
        }
            
        if (isFailedRequest ) // nếu có lỗi thì hiện thông báo
        {
            if (isFailedRequest) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
            }
            
            

        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }
        
        req.setAttribute("txtTitle", "Sách");
         List<SachModel> listAllSach = SachModel.getAllSach(conn);
        System.out.println(listAllSach.size());
        req.setAttribute("listAllSach", listAllSach);

        req.getRequestDispatcher("/admin/sach.jsp").forward(req, resp);;

    
        
        
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(req);
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
        req.setAttribute("listAllTheLoai", listAllTheLoai);
        
      
        //req.getRequestDispatcher("/admin/themsach.jsp").forward(req, resp);
        req.getRequestDispatcher("/admin/themsach.jsp").forward(req, resp);
    }

}
