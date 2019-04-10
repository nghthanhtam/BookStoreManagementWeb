package Controller;

import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhieuChiModel;
import Model.PhieuChiModelWithTenNhaCungCap;
import Model.ThanhVienModel;
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
@WebServlet(name = "EditPhieuChiServlet", urlPatterns = {"/admin/phieuchi/edit"})
public class EditPhieuChiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("txtTitle", "Phiếu Chi");
        boolean isFailedRequest = false; // request thất bại
        String noiDungThongBao = "";
        PhieuChiModel phieuChiModel1 = null;
        boolean isFailed = false;
        
        Connection conn = MyUtils.getStoredConnection(req);

        HttpSession session = req.getSession();    
        ThanhVienModel thanhvien = MyUtils.getLoginedThanhVien(session);
        int maThanhVien = thanhvien.getMaThanhVien();
        
        String submitValue = req.getParameter("submit");
        if (submitValue != null && submitValue.equals("capnhat")) {
            
            int maPhieuChi = Integer.parseInt(req.getParameter("maphieuchi"));
            int maNhaCungCap = Integer.parseInt(req.getParameter("manhacungcap"));
            String ghiChu = (String) req.getParameter("ghichu");
                  

            try {             
                phieuChiModel1 = PhieuChiModel.FindByMaPhieuChi(conn, maPhieuChi);

                if (phieuChiModel1 != null) {
                    isFailed = true;          
                }
            } catch (Exception ex) {
                isFailed = false;
            }
            
            
            PhieuChiModel phieuChiModel = new PhieuChiModel(maPhieuChi, maNhaCungCap, maThanhVien, phieuChiModel1.getTongTien(), null, ghiChu);

            try {
                boolean isOk = PhieuChiModel.UpdatePhieuChi(conn, phieuChiModel);
                if (isOk) {
                    isFailedRequest = false;
                    noiDungThongBao = "Đã cập nhật thành công!";
                } else {
                    isFailedRequest = true;
                }

            } catch (SQLException ex) {
                isFailedRequest = true;
                Logger.getLogger(PhieuChiServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            isFailedRequest = true;
        }

        if (isFailedRequest || isFailed) // nếu có lỗi thì hiện thông báo
        {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Phiếu Chi");

        List<PhieuChiModelWithTenNhaCungCap> listAllPhieuChiWithTenNhaCungCap = PhieuChiModelWithTenNhaCungCap.getAllPhieuChiWithTenNhaCungCap(conn);       
     
        req.setAttribute("listAllPhieuChiWithTenNhaCungCap", listAllPhieuChiWithTenNhaCungCap);
        req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        PhieuChiModel phieuChiModel = null;
        NhaCungCapModel nhaCungCapModel = null;
        boolean result = false;
        boolean result1 = false;
 
       
        try {        
            int maPhieuChi = Integer.parseInt(req.getParameter("id"));
            phieuChiModel = PhieuChiModel.FindByMaPhieuChi(conn,maPhieuChi);
      
            if (phieuChiModel != null) {
               
                result = true;
                
                nhaCungCapModel = NhaCungCapModel.FindByMaNhaCungCap(conn, phieuChiModel.getMaNhaCungCap());
                if(nhaCungCapModel != null)
                    result1 = true;
            }
        } catch (Exception ex) {
            result = false;
        }

        if (result == true && result1 == true) {
            req.setAttribute("txtTitle", "Sửa phiếu chi");   
            req.setAttribute("phieuChiModel", phieuChiModel);
            req.setAttribute("nhaCungCapModel", nhaCungCapModel);
            req.getRequestDispatcher("/admin/phieuchi-edit.jsp").forward(req, resp);

        } else { // hiển thị view thông báo thất bại

            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được thực hiện!", MessagesModel.ATT_TYPE_ERROR));
            
            /* Hiển thị view */
            req.setAttribute("txtTitle", "Phiếu Chi");

            req.getRequestDispatcher("/admin/phieuchi.jsp").forward(req, resp);
        }

    }

}
