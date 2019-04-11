/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhanQuyenModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
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
@WebServlet(name = "EditNhaCungCapServlet", urlPatterns = {"/admin/nhacungcap/edit"})
public class EditNhaCungCapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Set page */
        req.setAttribute("txtTitle", "Nhà cung cấp");
        boolean isFailedRequest = false; // request thất bại
        boolean isInvalidNumber = false;
        String noiDungThongBao = "";

        Connection conn = MyUtils.getStoredConnection(req);

        String submitValue = req.getParameter("submit");
        if (submitValue != null && submitValue.equals("capnhat")) {

            String tenNhaCungCap = (String) req.getParameter("tennhacungcap");
            String diaChi = (String) req.getParameter("diachi");
            String soDienThoai = (String) req.getParameter("sodienthoai");
            int maNhaCungCap = Integer.parseInt(req.getParameter("manhacungcap"));
            Double soTienNo = 0.0;

            
            Pattern pattern = Pattern.compile(".*\\D.*");// check so dien thoai co hợp lệ hay không
            Pattern pattern2 = Pattern.compile("(\\+84|0)\\d{9,11}");

            // Lấy ra đối tượng Matcher
            Matcher matcher = pattern2.matcher(soDienThoai);

            boolean match = matcher.matches();

            NhaCungCapModel nhaCungCapModel = new NhaCungCapModel(maNhaCungCap, tenNhaCungCap, diaChi, soDienThoai, soTienNo);
            try {
                if (match == true) {
                    isInvalidNumber = false;
                    boolean isOk = nhaCungCapModel.UpdateNhaCungCap(conn, nhaCungCapModel);
                    System.out.println(isOk);
                    if (isOk) {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã cập nhật thành công!";
                    } else {
                        isFailedRequest = true;
                    }
                }
                else
                    isInvalidNumber = true;

            } catch (SQLException ex) {
                isFailedRequest = true;
                Logger.getLogger(NhaCungCapModel.class.getName()).log(Level.SEVERE, null, ex);
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

        List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);
        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        NhaCungCapModel nhaCungCapModel = null;
        boolean result = false;
        try {
            int maNhaCungCap = Integer.parseInt((String) req.getParameter("id"));
            System.out.println(maNhaCungCap);
            nhaCungCapModel = NhaCungCapModel.FindByMaNhaCungCap(conn, maNhaCungCap);
            if (nhaCungCapModel != null) {
                result = true;
            }
        } catch (Exception ex) {
            result = false;
        }

        if (result == true) {

            req.setAttribute("txtTitle", "Sửa thông tin nhà cung cấp");
            req.setAttribute("nhaCungCapModel", nhaCungCapModel);
            req.getRequestDispatcher("/admin/nhacungcap-edit.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));

            /* Hiển thị view */
            req.setAttribute("txtTitle", "Nhà cung cấp");

            List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);
            req.setAttribute("listAllPhanQuyen", listAllNhaCungCap);
            req.getRequestDispatcher("/admin/nhacungcap.jsp").forward(req, resp);
        }

    }

}
