/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BaoCaoDoanhThuNgayModel;
import Model.CTDonHangModel;
import Model.DonHangModel;
import Model.MessagesModel;
import Model.PhiShipModel;
import Model.SachModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "BaoCaoDoanhThuServlet", urlPatterns = {"/admin/baocao/doanhthu"})
public class BaoCaoDoanhThuServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false;
        String noiDungThongBao = "";

        String button = req.getParameter("submit");
        HttpSession session = ((HttpServletRequest) req).getSession();

        java.sql.Date ngayBatDau = null;
        java.sql.Date ngayKetThuc = null;

        List<BaoCaoDoanhThuNgayModel> listDoanhThuNgayDonHangChuaHoanTat = null;
        List<BaoCaoDoanhThuNgayModel> listDoanhThuNgayDonHangHoanTat = null;
 
        try {
            if (button != null && button.equals("baocaodoanhthu")) {

                String khoangThoiGian = (String) req.getParameter("khoangthoigian");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                ngayBatDau = new java.sql.Date(dateFormat.parse(khoangThoiGian.substring(0, 10)).getTime());
                ngayKetThuc = new java.sql.Date(dateFormat.parse(khoangThoiGian.substring(13)).getTime());

                listDoanhThuNgayDonHangChuaHoanTat = BaoCaoDoanhThuNgayModel.getThongKeDoanhThuDonHangChuaHoanTat(conn, ngayBatDau, ngayKetThuc);
                listDoanhThuNgayDonHangHoanTat = BaoCaoDoanhThuNgayModel.getThongKeDoanhThuDonHangHoanTat(conn, ngayBatDau, ngayKetThuc);

                 String tableHTML = "";
                for (int i = 0; i < listDoanhThuNgayDonHangChuaHoanTat.size(); i++) {
                    tableHTML += "<tr>\n"
                            + "                                        <th scope=\"row\">" + (i + 1) + "</th>\n"
                            + "                                        <td>" + dateFormat.format(listDoanhThuNgayDonHangChuaHoanTat.get(i).getNgay()) + "</td>\n"
                            + "                                        <td>" + Math.round(listDoanhThuNgayDonHangHoanTat.get(i).getDoanhThu()) + "</td>\n"
                            + "                                        <td>" + Math.round(listDoanhThuNgayDonHangChuaHoanTat.get(i).getDoanhThu()) + "</td>\n"
                            + "                                        <td>" + Math.round(listDoanhThuNgayDonHangHoanTat.get(i).getDoanhThu() + listDoanhThuNgayDonHangChuaHoanTat.get(i).getDoanhThu()) + "</td>\n"
                            + "                                    </tr> ";
                }

                req.setAttribute("tableHTML", tableHTML);

            } else {
                throw new Exception("Yêu cầu của bạn không được xử lí!");
            }

            isFailed = false;
        } catch (Exception ex) {
            noiDungThongBao = ex.getMessage();
            isFailed = true;
        }

        if (isFailed) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        }

        req.getRequestDispatcher("/admin/baocaodoanhthu.jsp").forward(req, resp);
 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/admin/baocaodoanhthu.jsp").forward(req, resp);

    }

}
