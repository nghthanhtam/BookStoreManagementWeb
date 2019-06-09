/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTDonHangModel;
import Model.CTDonHangModelWithTenSachAnhDaiDien;
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

        int maDonHang = 0;

        List<PhiShipModel> listPhiShip = null;

        if (button != null && button.equals("sua")) {

            try {
                maDonHang = Integer.parseInt(req.getParameter("madonhang"));

                DonHangModel donHangTemp = DonHangModel.FindByMaDonHang(conn, maDonHang);

                int trangThaiDonHangHienTai = donHangTemp.getTrangThai();

                conn.setAutoCommit(false);

                /*
                if (trangThaiDonHangHienTai == DonHangModel.TRANGTHAI_DANG_GIAO_HANG) {
                    throw new Exception("Không thể cập nhật đơn hàng đang được giao!");
                }
                if (trangThaiDonHangHienTai == DonHangModel.TRANGTHAI_DA_HOAN_TAT) {
                    throw new Exception("Không thể cập nhật đơn hàng đã hoàn tất!");
                }
                if (trangThaiDonHangHienTai == DonHangModel.TRANGTHAI_HUY_DON_HANG) {
                    throw new Exception("Không thể cập nhật đơn hàng đã hủy!");
                }
                 */
                if (!(trangThaiDonHangHienTai == DonHangModel.TRANGTHAI_DANG_GIAO_HANG
                        || trangThaiDonHangHienTai == DonHangModel.TRANGTHAI_DA_HOAN_TAT
                        || trangThaiDonHangHienTai == DonHangModel.TRANGTHAI_HUY_DON_HANG)) {

                    String diaChi = (String) req.getParameter("diachigiaohang");

                    int maPhiShip = Integer.parseInt(req.getParameter("maphiship"));

                    String soDienThoai = (String) req.getParameter("sodienthoai");
                    if (MyUtils.checkSoDienThoai(soDienThoai) == false) {
                        throw new Exception("Số điện thoại không hợp lệ!");
                    }

                    PhiShipModel phiShipCu = PhiShipModel.FindByMaPhiShip(conn, donHangTemp.getMaPhiShip());
                    PhiShipModel phiShipMoi = PhiShipModel.FindByMaPhiShip(conn, maPhiShip);
                    if (phiShipMoi == null) {
                        throw new Exception("Chọn tỉnh thành không hợp lệ!");
                    }

                    donHangTemp.setTongTien(donHangTemp.getTongTien() - phiShipCu.getPhiShip() + phiShipMoi.getPhiShip());
                    donHangTemp.setDiaChiGiaoHang(diaChi);
                    donHangTemp.setMaPhiShip(maPhiShip);
                    donHangTemp.setSoDienThoai(soDienThoai);
                }

                /* Cập nhật ghi chú */
                String ghiChu = (String) req.getParameter("ghichu");
                donHangTemp.setGhiChu(ghiChu);

                /*Cập nhật trạng thái đơn hàng và tồn kho */
                if (req.getParameter("trangthai") != null) { // khi trạng thái khác null mới đổi trạng thái
                    int trangThai = Integer.parseInt(req.getParameter("trangthai"));
                    if (trangThai < DonHangModel.TRANGTHAI_CHO_XAC_NHAN || trangThai > DonHangModel.TRANGTHAI_DA_HOAN_TAT) {
                        throw new Exception("Trạng thái đơn hàng không hợp lệ!");
                    }
                    donHangTemp.setTrangThai(trangThai);
                    if (trangThai == DonHangModel.TRANGTHAI_HUY_DON_HANG) // nếu chọn hủy đơn 
                    {
                        List<CTDonHangModel> listCTDonHang = CTDonHangModel.getAllCTDonHangByMaDonHang(conn, maDonHang);
                        for (CTDonHangModel cTDonHangModel : listCTDonHang) {
                            SachModel sach = SachModel.FindByMaSach(conn, cTDonHangModel.getMaSach());
                            if (sach == null) {
                                throw new Exception("Không thể cập nhật tồn sách!");
                            }
                            sach.setSoLuongTon(sach.getSoLuongTon() + cTDonHangModel.getSoLuong());
                            boolean isOkUpdateSach = SachModel.UpdateSach(conn, sach);
                            if (isOkUpdateSach == false) {
                                throw new Exception("Không thể cập nhật tồn sách!");
                            }
                        }
                    }
                }

                boolean isOk = DonHangModel.UpdateDonHang(conn, donHangTemp);
                if (isOk == false) {
                    throw new Exception("Cập nhật đơn hàng thất bại!");
                }

                isFailed = false;
                noiDungThongBao = "Đã cập đơn hàng thành công!";

                conn.commit();

            } catch (Exception ex) {
                isFailed = true;
                noiDungThongBao = ex.getMessage();
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                }
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {

                }
            }

        }

        if (isFailed) // nếu có lỗi thì hiện thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Sửa đơn hàng");

        DonHangModelWithTenThanhVienAndTenDangNhap donHang = null;
        try {
            donHang = DonHangModelWithTenThanhVienAndTenDangNhap.FindByMaDonHang(conn, maDonHang);
            listPhiShip = PhiShipModel.getAllPhiShip(conn);
        } catch (SQLException ex) {
        }

        req.setAttribute("donHang", donHang);
        List<TheLoaiModel> listAllTheLoai = TheLoaiModel.getAllTheLoai(conn);
        req.setAttribute("listPhiShip", listPhiShip);

        List<CTDonHangModelWithTenSachAnhDaiDien> listCTDonHang = null;
        try {
            donHang = DonHangModelWithTenThanhVienAndTenDangNhap.FindByMaDonHang(conn, maDonHang);
            listCTDonHang = CTDonHangModelWithTenSachAnhDaiDien.FindAllByMaDonHang(conn, maDonHang);
        } catch (Exception ex) {
        }
        req.setAttribute("listCTDonHang", listCTDonHang);
        req.getRequestDispatcher("/admin/donhang.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

        req.setAttribute("txtTitle", "Sửa đơn hàng");

        DonHangModelWithTenThanhVienAndTenDangNhap donHang = null;

        List<CTDonHangModelWithTenSachAnhDaiDien> listCTDonHang = null;
        try {
            int maDonHang = Integer.parseInt((String) req.getParameter("id"));
            donHang = DonHangModelWithTenThanhVienAndTenDangNhap.FindByMaDonHang(conn, maDonHang);
            if (donHang == null)
            {
                resp.sendRedirect("/admin/donhang");
                return;
            }
            listCTDonHang = CTDonHangModelWithTenSachAnhDaiDien.FindAllByMaDonHang(conn, maDonHang);
        } catch (Exception ex) {
        }

        req.setAttribute("donHang", donHang);
        List<PhiShipModel> listPhiShip = PhiShipModel.getAllPhiShip(conn);
        req.setAttribute("listPhiShip", listPhiShip);
        req.setAttribute("listCTDonHang", listCTDonHang);
        req.getRequestDispatcher("/admin/donhang.jsp").forward(req, resp);
    }

}
