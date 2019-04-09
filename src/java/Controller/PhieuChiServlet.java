/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhieuChiModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PhieuChiServlet", urlPatterns = {"/admin/phieuchi"})
public class PhieuChiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PhieuChiServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("txtTitle", "Phiếu chi");

        HttpSession session = request.getSession();
        if (MyUtils.getLoginedThanhVien(session) == null) // chưa đăng nhập
        {
            request.getRequestDispatcher("/admin/admin-login.jsp").forward(request, response);
        } else {
            ThanhVienModel thanhvien = MyUtils.getLoginedThanhVien(session);
            request.setAttribute("txtTenThanhVien", thanhvien.getTenDangNhap());
            request.setAttribute("maThanhVien", thanhvien.getMaThanhVien());
        }

        Connection conn = MyUtils.getStoredConnection(request);
        List<PhieuChiModel> listAllPhieuChi = PhieuChiModel.getAllPhieuChi(conn);

        request.setAttribute("listAllPhieuChi", listAllPhieuChi);

        request.getRequestDispatcher("/admin/phieuchi.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";

        String submitValue = req.getParameter("submit");
        if (submitValue != null && submitValue.equals("them")) {
            
            double tongTien = 0;
            Pattern tongTienPattern = Pattern.compile("^(?:[1-9]\\d+|\\d)$");
            if (tongTienPattern.matcher(req.getParameter("tongtien")).matches()) {
                try {

                    int maNhaCungCap = Integer.parseInt(req.getParameter("manhacungcap"));
                    int maThanhVien = Integer.parseInt(req.getParameter("mathanhvien"));
                    tongTien = Double.parseDouble(req.getParameter("tongtien"));
                    String ghiChu = (String) req.getParameter("ghichu");

                    Connection conn = MyUtils.getStoredConnection(req);

                    boolean isOk = PhieuChiModel.InsertNewPhieuChi(conn,
                            new PhieuChiModel(0, maNhaCungCap, maThanhVien, tongTien, null, ghiChu));

                    if (isOk) {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã thêm phiếu chi!";
                    } else {
                        isFailedRequest = true;
                    }

                } catch (Exception ex) {
                    isFailedRequest = true;
                    Logger.getLogger(PhieuChiServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                isFailedRequest = true;
                noiDungThongBao = "Tổng tiền không hợp lệ!";
            }

        } else {
            isFailedRequest = true;
        }

        // nếu có lỗi thì hiện thông báo
        if (isFailedRequest) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!" + "<br>" + noiDungThongBao, MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Phiếu Chi");

        Connection conn = MyUtils.getStoredConnection(req);
        List<PhieuChiModel> listAllPhieuChi = PhieuChiModel.getAllPhieuChi(conn);
        req.setAttribute("listAllPhieuChi", listAllPhieuChi);
        
        req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);

    }
}