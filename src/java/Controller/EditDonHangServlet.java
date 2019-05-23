/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTDonHangModelWithTenSach;
import Model.DonHangModel;
import Model.DonHangModelWithTenThanhVienAndTenDangNhap;
import Model.MessagesModel;
import Model.PhiShipModel;
import Model.SachModel;
import Model.TheLoaiModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Dell
 */
@WebServlet(name = "EditDonHangServlet", urlPatterns = {"/admin/donhang/edit"})
public class EditDonHangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //Connection
        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false; // request thất bại
        String noiDungThongBao = "";
        
        //Button
        String button = req.getParameter("submit");
        
        DonHangModelWithTenThanhVienAndTenDangNhap donHang = null;
        
        List<PhiShipModel> listPhiShip = null;
        
        if (button != null && button.equals("sua")) {

            System.out.println("AAAAAAAAAAAAAAAA");
            try {
                
                //Get MaDonHang
                int maDonHang = Integer.parseInt(req.getParameter("madonhang"));
                //Get DonHang
                DonHangModel donHangTemp = DonHangModel.FindByMaDonHang(conn, maDonHang);
                
                
                if(donHangTemp.getTrangThai()==3)
                    throw new Exception("Không thể cập nhật đơn hàng đã xóa!");
                
                
                String diaChi = (String) req.getParameter("diachigiaohang");
                int maPhiShip = Integer.parseInt(req.getParameter("maphiship"));
                int trangthai = Integer.parseInt(req.getParameter("trangthai"));
                System.out.println("XXXXXXXXXXXXXXX");
                System.out.println(trangthai);
                System.out.println("XXXXXXXXXXXXXXX");

                PhiShipModel phiShipCu = PhiShipModel.FindByMaPhiShip(conn, donHangTemp.getMaPhiShip());
                PhiShipModel phiShipMoi = PhiShipModel.FindByMaPhiShip(conn, maPhiShip);

                donHangTemp.setTongTien(donHangTemp.getTongTien() - phiShipCu.getPhiShip() + phiShipMoi.getPhiShip());
                donHangTemp.setDiaChiGiaoHang(diaChi);
                donHangTemp.setTrangThai(trangthai);
                donHangTemp.setMaPhiShip(maPhiShip);

                boolean isOk = DonHangModel.UpdateDonHang(conn, donHangTemp);

                if (isOk) {
                    isFailed = false;
                    noiDungThongBao = "Đã cập đơn hàng!";
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
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Sửa đơn hàng");
        try {
            donHang = DonHangModelWithTenThanhVienAndTenDangNhap.FindByMaDonHang(conn, Integer.parseInt(req.getParameter("madonhang")));
            listPhiShip = PhiShipModel.getAllPhiShip(conn);
        } catch (SQLException ex) {
            Logger.getLogger(EditSachServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("donHang", donHang);
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
        req.setAttribute("listPhiShip", listPhiShip);

        req.getRequestDispatcher("/admin/donhang.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

        req.setAttribute("txtTitle", "Sửa đơn hàng");
        boolean result = false;
        List<CTDonHangModelWithTenSach> listSanPham = null;
        
        DonHangModelWithTenThanhVienAndTenDangNhap donHang = null;

        List<PhiShipModel> listPhiShip = PhiShipModel.getAllPhiShip(conn);

        System.out.println(listPhiShip.size());

        try {
            int maDonHang = Integer.parseInt((String) req.getParameter("id"));
            donHang = DonHangModelWithTenThanhVienAndTenDangNhap.FindByMaDonHang(conn, maDonHang);
            //Get list san pham in don hang
            System.out.println(maDonHang);
            listSanPham = CTDonHangModelWithTenSach.FindAllByMaDonHang(conn, maDonHang);
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println(listSanPham.size());
            if (donHang != null) {
                result = true;
            }
        } catch (Exception ex) {
            result = false;
        }

        if (result == true) {
            req.setAttribute("donHang", donHang);
            req.setAttribute("listPhiShip", listPhiShip);
            req.setAttribute("listSanPham", listSanPham);
            req.getRequestDispatcher("/admin/donhang.jsp").forward(req, resp);
        } else {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));

            List<DonHangModelWithTenThanhVienAndTenDangNhap> listDonHang = DonHangModelWithTenThanhVienAndTenDangNhap.getAllDonHang(conn);
            req.setAttribute("listDonHang", listDonHang);
            req.getRequestDispatcher("/admin/list-donhang.jsp").forward(req, resp);;
        }
    }

}
