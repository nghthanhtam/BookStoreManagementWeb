/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhieuChiModel;
import Model.PhieuChiModelWithTenNhaCungCap;
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
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 

        Connection conn = MyUtils.getStoredConnection(request);
        List<PhieuChiModelWithTenNhaCungCap> listAllPhieuChiWithTenNhaCungCap = PhieuChiModelWithTenNhaCungCap.getAllPhieuChiWithTenNhaCungCap(conn);       
        
        request.setAttribute("listAllPhieuChiWithTenNhaCungCap", listAllPhieuChiWithTenNhaCungCap);
  
        request.getRequestDispatcher("/admin/phieuchi.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";

        //Lấy mã thành viên hiện đang đăng nhập
        HttpSession session = req.getSession();    
        ThanhVienModel thanhvien = MyUtils.getLoginedThanhVien(session);
        int maThanhVien = thanhvien.getMaThanhVien();

        String submitValue = req.getParameter("submit");
        if (submitValue != null && submitValue.equals("them")) {
            
            double tongTien = 0;
            Pattern tongTienPattern = Pattern.compile("^(?:[1-9]\\d+|\\d)$");
            if (tongTienPattern.matcher(req.getParameter("tongtien")).matches()) {
                try {

                    int maNhaCungCap = Integer.parseInt(req.getParameter("manhacungcap"));
                    tongTien = Double.parseDouble(req.getParameter("tongtien"));
                    String ghiChu = (String) req.getParameter("ghichu");

                    Connection conn = MyUtils.getStoredConnection(req);

                    boolean isOk = PhieuChiModel.InsertNewPhieuChi(conn,
                            new PhieuChiModel(0, maNhaCungCap, maThanhVien, tongTien, null, ghiChu));
        
                    if (isOk) {
                        isFailedRequest = false;
                        noiDungThongBao = "Đã thêm phiếu chi!";
                        
                        //<editor-fold defaultstate="collapsed" desc="-- Cap nhat lai tien no --">                  
                        NhaCungCapModel nhaCungCap = NhaCungCapModel.FindByMaNhaCungCap(conn, maNhaCungCap);
                        nhaCungCap.setSoTienNo(nhaCungCap.getSoTienNo() - tongTien);

                        try {
                            boolean isOkUpdate = NhaCungCapModel.UpdateNhaCungCap(conn, nhaCungCap);
                            if (isOkUpdate) {
                                isFailedRequest = false;  
                                
                            } else {
                                conn.rollback();
                                isFailedRequest = true;
                            }

                        } catch (SQLException ex) {
                            isFailedRequest = true;
                            try {
                                conn.rollback();
                            } catch (Exception ex1) {
                                // errors that may have occurred
                                // on the server that would cause the rollback to fail, such as
                                // a closed connection.
                                System.out.println("Rollback Failed");
                            }                
                        }              
                        //</editor-fold>
                        
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
        List<PhieuChiModelWithTenNhaCungCap> listAllPhieuChiWithTenNhaCungCap = PhieuChiModelWithTenNhaCungCap.getAllPhieuChiWithTenNhaCungCap(conn);       
        List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);
               
        req.setAttribute("listAllNhaCungCap", listAllNhaCungCap);
        req.setAttribute("listAllPhieuChiWithTenNhaCungCap", listAllPhieuChiWithTenNhaCungCap);
        
        req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);

    }
}
