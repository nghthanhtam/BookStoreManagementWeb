/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhanQuyenModel;
import Model.SachModel;
import Model.SachModelWithTheLoaiAndTrangThai;
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
@MultipartConfig(maxFileSize = 100000000)

@WebServlet(name = "EditSachServlet", urlPatterns = {"/admin/sach/edit"})
public class EditSachServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false; // request thất bại

        String noiDungThongBao = "";
        String button = req.getParameter("submit");
        SachModel sach = null;
        if (button != null && button.equals("sua")) {

            try {
                int maSach = Integer.parseInt(req.getParameter("masach"));
                String tenSach = (String) req.getParameter("tensach");
                int maTheLoai = Integer.parseInt(req.getParameter("theloai"));
                String nhaXuatBan = (String) req.getParameter("nhaxuatban");
                int namXuatBan = Integer.parseInt(req.getParameter("namxuatban"));
                Double giaBan = Double.parseDouble(req.getParameter("giaban"));
                String moTa = (String) req.getParameter("mota");

                String dirImage = null;
                Part anhDaiDien = req.getPart("anhdaidien");

                if (anhDaiDien != null && anhDaiDien.getSize() > 0) {
                    dirImage = MyUtils.uploadFile(req, "anhdaidien");
                }

                String tenTacGia = (String) req.getParameter("tentacgia");
                int trangThai = Integer.parseInt(req.getParameter("trangthai"));

                Double phanTramGiamGia = null;
                java.sql.Date ngayBatDauGiamGia = null;
                java.sql.Date ngayKetThucGiamGia = null;

                if (req.getParameter("phantramgiamgia") != null
                        && !req.getParameter("phantramgiamgia").equals("")
                        && Double.parseDouble(req.getParameter("phantramgiamgia")) > 0 // phần trăm giảm giá lớn hơn 0                        
                        ) {
                    phanTramGiamGia = Double.parseDouble(req.getParameter("phantramgiamgia"));
                    String khoangThoiGianGiamGia = (String) req.getParameter("khoangthoigiangiamgia");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    ngayBatDauGiamGia = new java.sql.Date(dateFormat.parse(khoangThoiGianGiamGia.substring(0, 10)).getTime());
                    ngayKetThucGiamGia = new java.sql.Date(dateFormat.parse(khoangThoiGianGiamGia.substring(13)).getTime());
                }

                sach = SachModel.FindByMaSach(conn, maSach);
                sach.setMaTheLoai(maTheLoai);
                sach.setTenSach(tenSach);
                sach.setNhaXuatBan(nhaXuatBan);
                sach.setNamXuatBan(namXuatBan);
                sach.setGiaBan(giaBan);
                sach.setMoTa(moTa);
                if (dirImage != null && !dirImage.equals("")) {
                    sach.settAnhDaiDien(dirImage);
                }
                sach.setTenTacGia(tenTacGia);
                sach.setPhanTramGiamGia(phanTramGiamGia);
                sach.setNgayBatDauGiamGia(ngayBatDauGiamGia);
                sach.setNgayKetThucGiamGia(ngayKetThucGiamGia);
                sach.setTrangThai(trangThai);

                boolean isOk = SachModel.UpdateSach(conn, sach);

                if (isOk) {
                    isFailed = false;
                    noiDungThongBao = "Đã cập nhật sách!";
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

        req.setAttribute("txtTitle", "Sửa sách");

        try {
            sach = SachModel.FindByMaSach(conn, Integer.parseInt(req.getParameter("masach")));
        } catch (SQLException ex) {
            Logger.getLogger(EditSachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("sach", sach);
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
        req.setAttribute("listAllTheLoai", listAllTheLoai);

        req.getRequestDispatcher("/admin/sach.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);

        req.setAttribute("txtTitle", "Sửa sách");
        boolean result = false;
        SachModel sach = null;

        try {
            int maSach = Integer.parseInt((String) req.getParameter("id"));
            sach = SachModel.FindByMaSach(conn, maSach);
            if (sach != null) {
                result = true;
            }
        } catch (Exception ex) {
            result = false;
        }

        if (result == true) {
            req.setAttribute("sach", sach);

            List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
            req.setAttribute("listAllTheLoai", listAllTheLoai);

            req.getRequestDispatcher("/admin/sach.jsp").forward(req, resp);
        } else {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));

            List<SachModelWithTheLoaiAndTrangThai> listAllSach = SachModelWithTheLoaiAndTrangThai.getAllSachWithTheLoaiAndTraangThai(conn);
            req.setAttribute("listAllSach", listAllSach);
            req.getRequestDispatcher("/admin/list-sach.jsp").forward(req, resp);;
        }

    }

}
