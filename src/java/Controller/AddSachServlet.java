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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final String SAVE_DIR = "Upload";
    private static final long serialVersionUID = 1L;

    public AddSachServlet() {
        super();
    }

    
    private String extractFileName(Part part) {
       // form-data; name="file"; filename="C:\file1.zip"
       // form-data; name="file"; filename="C:\Note\file2.zip"
       String contentDisp = part.getHeader("content-disposition");
       String[] items = contentDisp.split(";");
       for (String s : items) {
           if (s.trim().startsWith("filename")) {
               // C:\file1.zip
               // C:\Note\file2.zip
               String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
               clientFileName = clientFileName.replace("\\", "/");
               int i = clientFileName.lastIndexOf('/');
               // file1.zip
               // file2.zip
               return clientFileName.substring(i + 1);
           }
       }
       return null;
   }
 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        InputStream inputStream = null;
        boolean isFailedRequest = false; // request thất bại
  
        InputStream fileContent = null;
        String noiDungThongBao = "";
        String button = req.getParameter("submit");
        String savePath = "";//C:\\Users\\Admin\\Documents\\NetBeansProjects\\NhaSachJava\\web\\admin\\" + File.separator + SAVE_DIR;
     
        String tenSach = (String) req.getParameter("tensach");
        int maTheLoai = Integer.parseInt(req.getParameter("theloai"));
        String nhaXuatBan = (String) req.getParameter("nhaxuatban");
        int namXuatBan = Integer.parseInt(req.getParameter("namxuatban"));
        Double giaBan = Double.parseDouble(req.getParameter("giaban"));
        String moTa = (String) req.getParameter("mota");

        // File image= new File ("C:\\Users\\Admin\\Desktop\\123.jpeg");
        //String description = req.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = req.getPart("anhdaidien"); // Retrieves <input type="file" name="file">
        
       // System.out.println(filePart.getName());
       // System.out.println("XXXXXXXXX");

       // String fileName = returnName(req);
        
       // String result = fileName.substring(1, fileName.length()-1);
        
      //  System.out.println("AAAAAAAAAAA"+result);
        
        
        String appPath = req.getServletContext().getRealPath("");
        appPath = appPath.replace('\\', '/');
      
        // Thư mục để save file tải lên.
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIR;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIR;
        }
        
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        try
        {
             
           // String fileName = extractFileName(filePart);
            String filePath = /*req.getServletContext().getRealPath("") + "\\" + */ "1.png";//fileName.substring(1, fileName.length()-1);
            System.out.println("Write attachment to file: " + filePath);
            File file = new File(req.getServletContext().getRealPath("") + "\\" + "1.png");
            
            filePart.write(filePath);
            
            
        } catch ( Exception ex)
        {
            System.out.println(ex.toString());
            
            
        }
        //String xzz=req.getParameter("anhdaidien");
        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        //if(!filePart.getSubmittedFileName().equals(""))
        //fileContent = filePart.getInputStream();

        //System.out.println("AA");
        //System.out.println(filePart.getSubmittedFileName());
        System.out.println(filePart.getName());
        //System.out.println(image.getName());

        //System.out.println("AA");
        //System.out.println(fileContent);
        //System.out.println("1111XXXXXXXXXXXXXXX");
        //System.out.println(anhDaiDienFile.getAbsolutePath());
        //System.out.println("1111XXXXXXXXXXXXXXX");
        Blob anhDaiDien = null;

        String tenTacGia = (String) req.getParameter("tentacgia");
        int phanTramGiamGia = Integer.parseInt(req.getParameter("phantramgiamgia"));
        String khoangThoiGian = (String) req.getParameter("khoangthoigian");
        String ngayBatDauGiamGia = khoangThoiGian.substring(0, 10);
        String ngayKetThucGiamGia = khoangThoiGian.substring(13);

        if (button != null && button.equals("them")) {

            System.out.println(ngayBatDauGiamGia);
            System.out.println(ngayKetThucGiamGia);

            SimpleDateFormat x = new SimpleDateFormat("dd/MM/yyyy");

            java.util.Date dateBatDau;
            java.sql.Date sqlBatDau = null;
            java.util.Date dateKetThuc;
            java.sql.Date sqlKetThuc = null;
            try {
                System.out.println("123123123");
                dateBatDau = x.parse(ngayBatDauGiamGia);
                sqlBatDau = new java.sql.Date(dateBatDau.getTime());
                dateKetThuc = x.parse(ngayKetThucGiamGia);
                sqlKetThuc = new java.sql.Date(dateKetThuc.getTime());
            } catch (ParseException ex) {

                Logger.getLogger(AddSachServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            int trangThai = Integer.parseInt(req.getParameter("trangthai"));

            try {
                String filePath ="";// savePath + File.separator + fileName;
                boolean isOk = SachModel.InsertNewSach(conn, new SachModel(0, maTheLoai, tenSach,
                        nhaXuatBan, namXuatBan, giaBan, moTa, filePath, 0, tenTacGia,
                        phanTramGiamGia, sqlBatDau, sqlKetThuc, trangThai));
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

        if (isFailedRequest) // nếu có lỗi thì hiện thông báo
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

   

    public String returnName(HttpServletRequest request) throws IOException, ServletException {
        String name = "";
        for (Part part : request.getParts()) {

            if (part.getName().equals("anhdaidien")) {
                Collection<String> headers = part.getHeaders("content-disposition");
                if (headers == null) {
                    continue;
                }
                for (String header : headers) {
                    String example = header;
                    System.out.println(example.substring(example.lastIndexOf("filename=") + 9));
                    name=example.substring(example.lastIndexOf("filename=") + 9);
                }

            }
        }
        return name;
    }
}
