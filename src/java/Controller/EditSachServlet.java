/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.SachModel;
import Model.TheLoaiModel;
import Utility.MyUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "EditSachServlet", urlPatterns = {"/admin/sach/edit"})
public class EditSachServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileInputStream input= null;
        Connection conn = MyUtils.getStoredConnection(req);
        InputStream fileContent = null;
        boolean isFailedRequest = false; // request thất bại
        boolean isWrongPicture = false;
        boolean isWrongGiaBan = false;
        boolean isWrongSoLuongTon = false;
        boolean isWrongTenTacGia = false;
        boolean isWrongPhanTramGiamGia = false;
        boolean isWrongTrangThai = false;
        String noiDungThongBao = "";
        
        String button = req.getParameter("submit");

        
            int maSach = Integer.parseInt(req.getParameter("masach"));
            String tenSach = (String) req.getParameter("tensach");
            int maTheLoai = Integer.parseInt(req.getParameter("theloai"));
            String nhaXuatBan = (String) req.getParameter("nhaxuatban");
            int namXuatBan = Integer.parseInt(req.getParameter("namxuatban"));
            Double giaBan = Double.parseDouble(req.getParameter("giaban"));
            String moTa = (String) req.getParameter("mota");
            int soLuongTon = Integer.parseInt(req.getParameter("soluongton"));
            System.out.println("XXXXXXXXXXXXXXX");
            System.out.println(soLuongTon);
            System.out.println("XXXXXXXXXXXXXXX");
            //File anhDaiDienFile= new File (req.getParameter("anhdaidien"));
            //input= new FileInputStream(anhDaiDienFile);
            
        Part filePart = req.getPart("anhdaidien"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        if(!filePart.getSubmittedFileName().equals(""))
        fileContent = filePart.getInputStream();
            
            
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
                boolean isOk=false;
                if(fileContent==null){
                    String xa="";
                isOk = SachModel.UpdateSach(conn, new SachModel(maSach, maTheLoai, tenSach,
                        nhaXuatBan,namXuatBan, giaBan, moTa, xa,soLuongTon, tenTacGia,
                        phanTramGiamGia,sqlBatDau,sqlKetThuc,trangThai),false);
            }
                else
                {
                    String xz="";
                    isOk = SachModel.UpdateSach(conn, new SachModel(maSach, maTheLoai, tenSach,
                        nhaXuatBan,namXuatBan, giaBan, moTa, xz,soLuongTon, tenTacGia,
                        phanTramGiamGia,sqlBatDau,sqlKetThuc,trangThai),true);
                }
                    
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
        SachModel sachModel = null;
        ServletOutputStream sos=null;
        TheLoaiModel theLoaiModel = null;
        byte[] image=null;
        boolean result = false;
          boolean          result1 = false;
        try {
            int maSach = Integer.parseInt((String) req.getParameter("id"));
            sachModel = SachModel.FindFullByMaSach(conn, maSach);
//            sos=resp.getOutputStream();
//            ByteArrayOutputStreamEx buffer = new ByteArrayOutputStreamEx();
//        int nRead;
//        byte[] data = new byte[16384];
//
//            while ((nRead = sachModel.getHinhAnh().read(data, 0, data.length)) != -1) {
//            buffer.write(data, 0, nRead);
//            }
//
//            sos.write( buffer.toByteArray());
            System.out.println("XXXXXXXXXXXXXXXXXX");
            //System.out.println(sachModel.getNgayBatDauGiamGia());
            //System.out.println(sachModel.getNgayKetThucGiamGia());
            System.out.println("XXXXXXXXXXXXXXXXXX");
            if (sachModel != null) {
                result = true;
                theLoaiModel = TheLoaiModel.FindByMaTheLoai(conn, sachModel.getMaTheLoai());

                if (theLoaiModel != null) {

                    result1 = true;
                }
                else
                    result1=false;
            }
        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }

        if (result == true && result1==true) {

             
            List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
            int thuTu = 0;
            for (int i = 0; i < listAllTheLoai.size(); i++) {
                thuTu = thuTu + 1;
                if (theLoaiModel.getMaTheLoai()== listAllTheLoai.get(i).getMaTheLoai()) {
                    break;
                }
            }
            System.out.println(thuTu);
            req.setAttribute("txtTitle", "Sửa thông tin sách");
            req.setAttribute("sachModel", sachModel);
            req.setAttribute("listAllTheLoai", listAllTheLoai);
            req.setAttribute("theLoaiModel", theLoaiModel);
            req.setAttribute("thuTu", thuTu);
            
            req.getRequestDispatcher("/admin/sach-edit.jsp").forward(req, resp);

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
