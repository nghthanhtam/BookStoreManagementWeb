/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DonHangModel;
import Model.MessagesModel;
import Model.SachModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
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

        req.setAttribute("txtTitle", "Đơn hàng của tôi");

        String action = req.getParameter("action");

        if (action != null && action.equals("cancel")) {

            int maDonHang = Integer.parseInt(req.getParameter("id"));

            Connection conn = MyUtils.getStoredConnection(req);

            DonHangModel donHangModel = new DonHangModel();

            try {
                donHangModel = DonHangModel.FindByMaDonHang(conn, maDonHang);

                donHangModel.setTrangThai(DonHangModel.TRANGTHAI_HUY_DON_HANG);

                boolean isOkUpdateDonHang = DonHangModel.UpdateDonHang(conn, donHangModel);
                if (isOkUpdateDonHang == false) {
                    throw new Exception("Không thể hủy đơn hàng!");
                }
            } catch (Exception ex) {

            }
        }

        HttpSession session = req.getSession();
        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Oops!", "Bạn chưa đăng nhập!", MessagesModel.ATT_TYPE_ERROR));
            req.getRequestDispatcher("dangnhap.jsp").forward(req, resp);
        } else {

            Connection conn = MyUtils.getStoredConnection(req);

            int maThanhVien = MyUtils.getLoginedThanhVien(session).getMaThanhVien();

            List<DonHangModel> listDonHang = DonHangModel.getAllDonHangByMaThanhVien(conn, maThanhVien);
            req.setAttribute("listDonHang", listDonHang);

            List<SachModel> listSach = SachModel.getAllSach(conn);
            req.setAttribute("listSach", listSach);

            req.getRequestDispatcher("list-donhang.jsp").forward(req, resp);

        }

    }
}
