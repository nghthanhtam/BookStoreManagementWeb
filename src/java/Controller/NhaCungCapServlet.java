/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "NhaCungCapServlet", urlPatterns = {"/admin/nhacungcap"})
public class NhaCungCapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Set page */
        System.out.print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        //req.setAttribute("txtTitle", "Nhà cung cấp");
        boolean isFailedRequest = false; // request thất bại
        boolean isInvalidNumber = false; // request thất bại

        String noiDungThongBao = "";
        //String submitValue = req.getParameter("submit");
        String button = req.getParameter("submit");

        if (button != null && button.equals("them")) {

            String tenNhaCungCap = (String) req.getParameter("tennhacungcap");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            Double soTienNo = 200.0;

            Pattern pattern = Pattern.compile(".*\\D.*");// check so dien thoai co hợp lệ hay không
            Pattern pattern2 = Pattern.compile("(\\+84|0)\\d{9,11}");

            // Lấy ra đối tượng Matcher
            Matcher matcher = pattern2.matcher(soDienThoai);

            boolean match = matcher.matches();

            Connection conn = MyUtils.getStoredConnection(req);
            try {
                if (match == true) {
                    isInvalidNumber = false;
                    boolean isOk = NhaCungCapModel.InsertNewNhaCungCap(conn, new NhaCungCapModel(0, tenNhaCungCap, diaChi, soDienThoai, soTienNo));
                    if (isOk) {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã thêm nhà cung cấp mới!";
                    } else {
                        isFailedRequest = true;
                    }

                } else {
                    isInvalidNumber = true;
                }

            } catch (SQLException ex) {
                isFailedRequest = true;
                Logger.getLogger(NhaCungCapServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            isFailedRequest = true;
            isInvalidNumber = true;
        }

        if (isFailedRequest || isInvalidNumber) // nếu có lỗi thì hiện thông báo
        {
            if (isFailedRequest) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
            }
            if (isInvalidNumber) {
                req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Số điện thoại không hợp lệ!", MessagesModel.ATT_TYPE_ERROR));
            }
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }
        Connection conn = MyUtils.getStoredConnection(req);
        List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);

        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        req.setAttribute("txtTitle", "Nhà cung cấp");

        Connection conn = MyUtils.getStoredConnection(req);
        List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);

        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);

        req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);
    }

}
