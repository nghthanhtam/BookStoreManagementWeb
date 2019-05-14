/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.SachModel;
import Model.SachModelWithTheLoaiAndTrangThai;
import Model.TheLoaiModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@MultipartConfig(maxFileSize = 100000000)

@WebServlet(name = "AddSachServlet", urlPatterns = {"/admin/sach/add"})
public class AddSachServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false; // request thất bại

        String noiDungThongBao = "";
        String button = req.getParameter("submit");

        if (button != null && button.equals("them")) {

            try {
                String tenSach = (String) req.getParameter("tensach");
                int maTheLoai = Integer.parseInt(req.getParameter("theloai"));
                String nhaXuatBan = (String) req.getParameter("nhaxuatban");
                int namXuatBan = Integer.parseInt(req.getParameter("namxuatban"));
                Double giaBan = Double.parseDouble(req.getParameter("giaban"));
                String moTa = (String) req.getParameter("mota");
                String dirImage = MyUtils.uploadFile(req, "anhdaidien");
                String tenTacGia = (String) req.getParameter("tentacgia");
                int trangThai = Integer.parseInt(req.getParameter("trangthai"));

                Double phanTramGiamGia = null;
                java.sql.Date ngayBatDauGiamGia = null;
                java.sql.Date ngayKetThucGiamGia = null;

                if (req.getParameter("phantramgiamgia") != null
                        && (!req.getParameter("phantramgiamgia").equals("")
                        && Double.parseDouble(req.getParameter("phantramgiamgia")) > 0 // phần trăm giảm giá lớn hơn 0                        
                        )) {
                    phanTramGiamGia = Double.parseDouble(req.getParameter("phantramgiamgia"));
                    String khoangThoiGianGiamGia = (String) req.getParameter("khoangthoigiangiamgia");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    ngayBatDauGiamGia = new java.sql.Date(dateFormat.parse(khoangThoiGianGiamGia.substring(0, 10)).getTime());
                    ngayKetThucGiamGia = new java.sql.Date(dateFormat.parse(khoangThoiGianGiamGia.substring(13)).getTime());
                }

                boolean isOk = SachModel.InsertNewSach(conn, new SachModel(0, maTheLoai, tenSach,
                        nhaXuatBan, namXuatBan, giaBan, moTa, dirImage, 0, tenTacGia,
                        phanTramGiamGia, ngayBatDauGiamGia, ngayKetThucGiamGia, trangThai));

                if (isOk) {
                    isFailed = false;
                    noiDungThongBao = "Đã thêm sách mới!";
                } else {
                    throw new Exception("Yêu cầu của bạn không thể xử lý!");
                }
            } catch (Exception ex) {
                Logger.getLogger(AddSachServlet.class.getName()).log(Level.SEVERE, null, ex);
                isFailed = true;
                noiDungThongBao = ex.getMessage();
            }
        }

        if (isFailed) // nếu có lỗi thì hiện thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "sách");
        List<SachModelWithTheLoaiAndTrangThai> listAllSach = SachModelWithTheLoaiAndTrangThai.getAllSachWithTheLoaiAndTraangThai(conn);
        req.setAttribute("listAllSach", listAllSach);

        req.getRequestDispatcher("/admin/list-sach.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
        req.setAttribute("listAllTheLoai", listAllTheLoai);
        req.setAttribute("txtTitle", "Thêm sách");
        req.getRequestDispatcher("/admin/sach.jsp").forward(req, resp);
    }

}
