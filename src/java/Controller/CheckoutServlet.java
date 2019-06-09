/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTDonHangModel;
import Model.DonHangModel;
import Model.MessagesModel;
import Model.PhiShipModel;
import Model.SachModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.*;

/**
 *
 * @author TamTorres
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false;
        String noiDungThongBao = "";

        String button = req.getParameter("submit");

        List<CTDonHangModel> listCTDonHang = new ArrayList<>();
        List<SachModel> listSachHetHang = new ArrayList<>();
        boolean isValidDonHang = true;
        HttpSession session = ((HttpServletRequest) req).getSession();

        try {

            if (button != null && button.equals("them")) {
            } else {
                throw new Exception("Yêu cầu của bạn không được xử lí!");
            }

            // conn.setAutoCommit(false);
            if (MyUtils.getLoginedThanhVien(session) == null) {
                throw new Exception("Vui lòng đăng nhập trước khi mua hàng!");
            }
            int maThanhVien = MyUtils.getLoginedThanhVien(session).getMaThanhVien();

            String diaChi = (String) req.getParameter("address");
            String soDienThoai = (String) req.getParameter("tel");
            if (MyUtils.checkSoDienThoai(soDienThoai) == false) {
                throw new Exception("Số điện thoại không hợp lệ!");
            }

            String ghiChu = (String) req.getParameter("comment");
            int maPhiShip = 0;
            if (req.getParameter("maphiship") != null && !req.getParameter("maphiship").equals("")) {
                maPhiShip = Integer.parseInt(req.getParameter("maphiship"));
            } else {
                throw new Exception("Bạn chưa chọn tỉnh/thành phố!");
            }
            PhiShipModel phishipModel = PhiShipModel.FindByMaPhiShip(conn, maPhiShip);
            if (phishipModel == null) {
                throw new Exception("Tỉnh/thành phố không hợp lệ!");
            }

            Double phiShip = phishipModel.getPhiShip();

            String listCT = (String) req.getParameter("listctdonhang");
            JSONArray jsonListCT = null;

            Double tongTien = 0.0;

            Date datenow = new Date(); //lấy ngày hiện tại
            
            /* Thêm chi tiết đơn hàng */
            jsonListCT = new JSONArray(listCT);
            if (jsonListCT.length() <= 0) {
                throw new Exception("Không có sản phẩm nào trong giỏ hàng!");
            }

            /* Kiểm tra-Cập nhật số lượng tồn của sách, trạng thái sách đang được bán */
            for (int i = 0; i < jsonListCT.length(); i++) {
                JSONObject jsonObj = jsonListCT.getJSONObject(i);

                SachModel sach = SachModel.FindByMaSach(conn, Integer.parseInt(jsonObj.getString("id")));
                if (sach == null) {
                    throw new Exception("Không tìm thấy thông tin sách [MASACH] = " + jsonObj.getString("id"));
                }
                if ((sach.getSoLuongTon() - Integer.parseInt(jsonObj.getString("qty"))) < 0) {
                    listSachHetHang.add(sach);
                } else {
                    sach.setSoLuongTon(sach.getSoLuongTon() - Integer.parseInt(jsonObj.getString("qty")));
                    if (listSachHetHang.size() == 0) // không có sách nào hết hàng 
                    {
                        if (SachModel.UpdateSach(conn, sach) == false) {
                            throw new Exception("Không thể cập nhật tồn sách!");
                        }
                    }
                }
                
                if (sach.getTrangThai() != SachModel.TRANGTHAI_DANG_BAN)
                {
                    throw new Exception("Sách \"" + sach.getTenSach() + "\" đã ngưng kinh doanh!");
                }

                double TongGiaCTDonHang = 0;

                if (MyUtils.checkGiamGia(sach) == true) { // nếu ngày hiện tại còn trong giảm giá
                    TongGiaCTDonHang = sach.getGiaBan() * Integer.parseInt(jsonObj.getString("qty")) * (100f - sach.getPhanTramGiamGia()) * 0.01;
                } else {
                    TongGiaCTDonHang = sach.getGiaBan() * Integer.parseInt(jsonObj.getString("qty"));
                }
                tongTien += TongGiaCTDonHang;
            }

            if (listSachHetHang.size() > 0) {
                req.setAttribute("listSachHetHang", listSachHetHang);
                throw new Exception("Lượng tồn sách không đủ để thực hiện đơn hàng!");
            }

            /* Kiểm tra-Cập nhật số lượng tồn của sách */
            int maDonHang = DonHangModel.getMaDonHangCurrent(conn);
            boolean isOk = DonHangModel.InsertDonHang(conn, new DonHangModel(
                    0,
                    maThanhVien,
                    new java.sql.Date(datenow.getTime()),
                    tongTien,
                    DonHangModel.TRANGTHAI_CHO_XAC_NHAN,
                    diaChi,
                    maPhiShip,
                    phiShip,
                    soDienThoai,
                    ghiChu
            ));

            if (isOk == false) {
                throw new Exception("Thêm đơn hàng thất bại!");
            }

            /* Thêm chi tiết đơn hàng */
            for (int i = 0; i < jsonListCT.length(); i++) {
                JSONObject jsonObj = jsonListCT.getJSONObject(i);

                SachModel sachModel = SachModel.FindByMaSach(conn, Integer.parseInt(jsonObj.getString("id")));
                CTDonHangModel cTDonHangModel = new CTDonHangModel(0, maDonHang, Integer.parseInt(jsonObj.getString("id")), Integer.parseInt(jsonObj.getString("qty")), sachModel.getGiaBan(), 0);
                if (MyUtils.checkGiamGia(sachModel) == true) { // nếu ngày hiện tại còn trong giảm giá
                    cTDonHangModel.setPhanTramGiamGia(sachModel.getPhanTramGiamGia());
                }

                listCTDonHang.add(cTDonHangModel);
            }
            if (CTDonHangModel.InsertList(conn, listCTDonHang) == false) {
                throw new Exception("Thêm chi tiết đơn hàng thất bại!");
            }
            /* Thêm chi tiết đơn hàng */

            isFailed = false;
            noiDungThongBao = "Đã thêm đơn hàng mới thành công!";
            conn.commit();
        } catch (Exception ex) {

            try {
                conn.rollback();
            } catch (SQLException ex1) {
            }

            isFailed = true;
            noiDungThongBao = ex.getMessage();
        } finally {
//            try {
//            //    conn.setAutoCommit(true);
//            } catch (SQLException ex) {
//            }
        }

        List<PhiShipModel> listPhiShip = PhiShipModel.getAllPhiShip(conn);
        req.setAttribute("listPhiShip", listPhiShip);

        ThanhVienModel thanhVienModel = MyUtils.getLoginedThanhVien(session);
        req.setAttribute("tvModel", thanhVienModel);

        if (isFailed) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
        } else {

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
            req.getRequestDispatcher("trangthaisaudathang.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Checkout");

        Connection conn = MyUtils.getStoredConnection(req);

        List<PhiShipModel> listPhiShip = PhiShipModel.getAllPhiShip(conn);
        req.setAttribute("listPhiShip", listPhiShip);
        HttpSession session = req.getSession();

        ThanhVienModel thanhVienModel = MyUtils.getLoginedThanhVien(session);

        if (thanhVienModel == null) { // chưa đăng nhập

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Oops!", "Đăng nhập để tiếp tục mua hàng!", MessagesModel.ATT_TYPE_ERROR));
            req.getRequestDispatcher("giohang.jsp").forward(req, resp);
        } else {

            req.setAttribute("tvModel", thanhVienModel);
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);

        }

    }

}
