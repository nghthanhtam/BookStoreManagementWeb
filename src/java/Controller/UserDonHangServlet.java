/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTDonHangModel;
import Model.DonHangModel;
import Model.MessagesModel;
import Model.SachModel;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "UserDonHangServlet", urlPatterns = {"/donhang"})
public class UserDonHangServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Oops!", "Bạn chưa đăng nhập!", MessagesModel.ATT_TYPE_ERROR));
            req.getRequestDispatcher("dangnhap.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("txtTitle", "Đơn hàng của tôi");
        Connection conn = MyUtils.getStoredConnection(req);

        String action = req.getParameter("action");
        boolean isFailed = false;

        int maThanhVien = MyUtils.getLoginedThanhVien(session).getMaThanhVien();

        DonHangModel donHang;
        String noiDungThongBao = "";

        if (action != null && action.equals("cancel")) {

            try {
                int maDonHang = Integer.parseInt(req.getParameter("id"));
                donHang = DonHangModel.FindByMaDonHang(conn, maDonHang);

                if (donHang == null) {
                    throw new Exception("Không thể tìm thấy đơn hàng!");
                }

                if (donHang.getMaThanhVien() != maThanhVien) {
                    throw new Exception("Bạn không có quyền thực hiện yêu cầu này!");
                }

                int trangThaiHienTai = donHang.getTrangThai();

                if (trangThaiHienTai == DonHangModel.TRANGTHAI_CHO_XAC_NHAN) // đang chờ xác nhận
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

                } else {
                    throw new Exception("Không thể hủy đơn hàng!");
                }

                donHang.setTrangThai(DonHangModel.TRANGTHAI_HUY_DON_HANG);

                if (DonHangModel.UpdateDonHang(conn, donHang) == false) {
                    throw new Exception("Cập nhật trạng thái đơn hàng thất bại!");
                }

                isFailed = false;
                noiDungThongBao = "Đã cập nhật trạng thái của đơn hàng!";
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));

                conn.commit();

            } catch (Exception ex) {

                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                }

                isFailed = true;
                noiDungThongBao = ex.getMessage();

            }

        }

        if (isFailed) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        }

        List<DonHangModel> listDonHang = DonHangModel.getAllDonHangByMaThanhVien(conn, maThanhVien);
        req.setAttribute("listDonHang", listDonHang);

        req.getRequestDispatcher("list-donhang.jsp").forward(req, resp);

    }
}
