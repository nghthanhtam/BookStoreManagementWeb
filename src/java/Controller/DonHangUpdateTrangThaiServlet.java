/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTDonHangModel;
import Model.DonHangModel;
import Model.DonHangModelWithTenThanhVienAndTenDangNhap;
import Model.MessagesModel;
import Model.SachModel;
import Utility.MyUtils;
import java.io.IOException;
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
 

@WebServlet(name = "DonHangUpdateTrangThaiServlet", urlPatterns = {"/admin/donhang/updatetrangthai"})
public class DonHangUpdateTrangThaiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false; // request thất bại
        String noiDungThongBao = "";
        int maDonHang = 0;
        int trangThai = 0;
        int currentPage = 0;

        try {
            try {
                maDonHang = Integer.parseInt(req.getParameter("id"));
                trangThai = Integer.parseInt(req.getParameter("trangthai"));
                currentPage = Integer.parseInt(req.getParameter("page"));
            } catch (Exception ex1) {
                throw new Exception("Tham số truy vấn không hợp lệ!");
            }

            DonHangModel donHangTemp = DonHangModel.FindByMaDonHang(conn, maDonHang);
            int trangThaiHienTai = donHangTemp.getTrangThai();

            conn.setAutoCommit(false);

            if (trangThaiHienTai == DonHangModel.TRANGTHAI_CHO_XAC_NHAN) // đang chờ xác nhận
            {
                switch (trangThai) {
                    case DonHangModel.TRANGTHAI_HUY_DON_HANG: {

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

                        break;
                    }

                    case DonHangModel.TRANGTHAI_DANG_TIEP_NHAN: {

                        break;
                    }

                    default: {
                        throw new Exception("Trạng thái không hợp lệ!");
                    }

                }
 
            }
            else
            {
                throw new Exception("Yêu cầu của bạn không được xử lý!");
            }
 
            donHangTemp.setTrangThai(trangThai);
            Boolean isOkUpdateDonHang = DonHangModel.UpdateDonHang(conn, donHangTemp);

            if (isOkUpdateDonHang == false) {
                throw new Exception("Cập nhật trạng thái đơn hàng thất bại!");
            }

            isFailed = false;
            noiDungThongBao = "Đã cập nhật trạng thái của đơn hàng!";
            conn.commit();

        } catch (Exception ex) {

            try {
                conn.rollback();
            } catch (SQLException ex1) {
            }

            isFailed = true;
            noiDungThongBao = ex.getMessage();

        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex1) {
            }
        }

        if (isFailed) // nếu có lỗi thì hiện thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Đơn hàng");

        Integer numOfDonHangInOnePage = 20;
        List<DonHangModelWithTenThanhVienAndTenDangNhap> listDonHang = DonHangModelWithTenThanhVienAndTenDangNhap.getAllDonHang(conn, currentPage, numOfDonHangInOnePage);

        int numOfBookFound;
        int numOfPage = 0;
        try {
            numOfBookFound = DonHangModelWithTenThanhVienAndTenDangNhap.CountAllDonHang(conn);
     
            if (numOfBookFound % numOfDonHangInOnePage == 0) {
                numOfPage = numOfBookFound / numOfDonHangInOnePage;
            } else {
                numOfPage = numOfBookFound / numOfDonHangInOnePage + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonHangServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        req.setAttribute("numofpage", numOfPage);
        req.setAttribute("currentpage", currentPage);

        req.setAttribute("listDonHang", listDonHang);
        req.getRequestDispatcher("/admin/list-donhang.jsp").forward(req, resp);
    }

}
