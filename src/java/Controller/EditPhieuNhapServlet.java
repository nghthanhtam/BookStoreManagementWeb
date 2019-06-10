/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTDonHangModelWithTenSach;
import Model.CTPhieuNhapModel;
import Model.CTPhieuNhapModelWithTenSach;
import Model.DonHangModelWithTenThanhVienAndTenDangNhap;
import Model.MessagesModel;
import Model.PhiShipModel;
import Model.PhieuNhapModel;
import Model.PhieuNhapModelWithTenNhaCungCapAndTenNhanVien;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditPhieuNhapServlet", urlPatterns = {"/admin/phieunhap/edit"})
public class EditPhieuNhapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false; // request th?t b?i
        String noiDungThongBao = "";

        String button = req.getParameter("submit");

        int maPhieuNhap = 0;
        if (button != null && button.equals("sua")) {
            try {
                maPhieuNhap = Integer.parseInt(req.getParameter("maphieunhap"));

                PhieuNhapModel phieuNhapTemp = PhieuNhapModel.FindByMaPhieuNhap(conn, maPhieuNhap);

                String ghiChu = (String) req.getParameter("ghichu");
                phieuNhapTemp.setGhiChu(ghiChu);

                boolean isOk = PhieuNhapModel.UpdatePhieuNhap(conn, phieuNhapTemp);

                if (isOk == false) {
                    throw new Exception("C?p nh?t phi?u nh?p th?t b?i!");
                }

                isFailed = false;
                noiDungThongBao = "Ðã c?p nh?t phi?u nh?p thành công!";

            } catch (Exception ex) {
                isFailed = true;
                noiDungThongBao = ex.getMessage();
            }

        }
        if (isFailed) // n?u có l?i thì hi?n thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có l?i x?y ra!", noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }
        
        
        
        req.setAttribute("txtTitle", "S?a phi?u nh?p");

        PhieuNhapModelWithTenNhaCungCapAndTenNhanVien phieuNhap = null;
        try {
            phieuNhap = PhieuNhapModelWithTenNhaCungCapAndTenNhanVien.FindByMaPhieuNhap(conn, maPhieuNhap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        req.setAttribute("phieuNhap", phieuNhap);

        List<CTPhieuNhapModelWithTenSach> listCTPhieuNhap = null;
        try {
            phieuNhap = PhieuNhapModelWithTenNhaCungCapAndTenNhanVien.FindByMaPhieuNhap(conn, maPhieuNhap);
            listCTPhieuNhap = CTPhieuNhapModelWithTenSach.FindAllByMaPhieuNhap(conn, maPhieuNhap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        req.setAttribute("listCTPhieuNhap", listCTPhieuNhap);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
        req.getRequestDispatcher("/admin/phieunhap.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(req);

        req.setAttribute("txtTitle", "S?a phi?u nh?p");

        PhieuNhapModelWithTenNhaCungCapAndTenNhanVien phieuNhap = null;

        List<CTPhieuNhapModelWithTenSach> listCTPhieuNhap = null;
        try {
            int maPhieuNhap = Integer.parseInt((String) req.getParameter("id"));
            System.out.println(maPhieuNhap);
            phieuNhap = PhieuNhapModelWithTenNhaCungCapAndTenNhanVien.FindByMaPhieuNhap(conn, maPhieuNhap);
            if (phieuNhap == null) {
                resp.sendRedirect("/admin/phieunhap");
                return;
            }
            listCTPhieuNhap = CTPhieuNhapModelWithTenSach.FindAllByMaPhieuNhap(conn, maPhieuNhap);
        } catch (Exception ex) {
        }

        req.setAttribute("phieuNhap", phieuNhap);
        req.setAttribute("listCTPhieuNhap", listCTPhieuNhap);
        req.getRequestDispatcher("/admin/phieunhap-edit.jsp").forward(req, resp);
    }
}
