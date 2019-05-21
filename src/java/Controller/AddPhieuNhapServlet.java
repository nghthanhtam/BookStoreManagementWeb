/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTPhieuNhapModel;
import Model.MessagesModel;
import Model.NhaCungCapModel;
import Model.PhieuNhapModel;
import Model.SachModel;
import Utility.MyUtils;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MITICC06
 */
@WebServlet(name = "AddPhieuNhapServlet", urlPatterns = {"/admin/phieunhap/add"})
public class AddPhieuNhapServlet extends HttpServlet {

    public class CustomChiTietPhieuNhapJsonDeserializer implements JsonDeserializer<CTPhieuNhapModel> {

        @Override
        public CTPhieuNhapModel deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            JsonObject jObject = je.getAsJsonObject();

            int masach = 0;
            double dongia = 0;
            int soluongnhap = 0;
            try {
                masach = Integer.parseInt(jObject.get("masach").getAsString());
                dongia = jObject.get("dongia").getAsDouble();
                soluongnhap = jObject.get("soluongnhap").getAsInt();

            } catch (Exception ex) {
                return null;
            }
            return new CTPhieuNhapModel(0, 0, masach, dongia, soluongnhap);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        boolean isFailed = false; // request thất bại

        String noiDungThongBao = "";
        String button = req.getParameter("submit");

        if (button != null && button.equals("them")) {

            try {
                int maNhaCungCap = Integer.parseInt(req.getParameter("manhacungcap"));
                String ghiChu = (String) req.getParameter("ghichu");

                java.sql.Date ngayNhap = null;
                if (req.getParameter("ngaylapphieu") != null && !req.getParameter("ngaylapphieu").equals("")) {
                    String ngayLapPhieuString = (String) req.getParameter("ngaylapphieu");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    ngayNhap = new java.sql.Date(dateFormat.parse(ngayLapPhieuString).getTime());
                } else {
                    throw new Exception("Ngày lập phiếu chưa có hoặc không hợp lệ!");
                }

                HttpSession session = ((HttpServletRequest) req).getSession();
                int maNhanVien = MyUtils.getLoginedThanhVien(session).getMaThanhVien();

                String jsonDataTable = req.getParameter("jsonDataTable");

                GsonBuilder gsonBldr = new GsonBuilder();
                gsonBldr.registerTypeAdapter(CTPhieuNhapModel.class, new CustomChiTietPhieuNhapJsonDeserializer());
                Gson gson = gsonBldr.setPrettyPrinting().create();
                Type collectionType = new TypeToken<List<CTPhieuNhapModel>>() {
                }.getType();

                List<CTPhieuNhapModel> listCTPhieuNhap = gson.fromJson(jsonDataTable, collectionType);
               
                conn.setAutoCommit(false);

                int maPhieuNhap = PhieuNhapModel.getMaPhieuNhapCurrent(conn);

                boolean isOKPhieuNhap = PhieuNhapModel.InsertPhieuNhap(conn, new PhieuNhapModel(
                        0,
                        ngayNhap,
                        maNhaCungCap,
                        maNhanVien,
                        ghiChu
                ));
                // conn.commit();
                if (isOKPhieuNhap == false) {
                    throw new Exception("Thêm phiếu nhập thất bại!");
                }

                /* cập nhật lại mã phiếu nhập và xóa các object rỗng, tính tổng tiền, cập nhật tồn*/
                Iterator itr = listCTPhieuNhap.iterator();
                double tongTien = 0;
                while (itr.hasNext()) {
                    CTPhieuNhapModel obj = (CTPhieuNhapModel) itr.next();
                    if (obj == null) {
                        itr.remove();
                    } else {
                        tongTien += obj.getDonGia() * obj.getSoLuongNhap(); // đếm tổng tiền
                        obj.setMaPhieuNhap(maPhieuNhap); // cập nhật lại mã phiếu nhập
                        
                        /* Cập nhật tồn của sách */
                        SachModel sach = SachModel.FindByMaSach(conn, obj.getMaSach());
                        sach.setSoLuongTon(sach.getSoLuongTon() + obj.getSoLuongNhap());
                        SachModel.UpdateSach(conn, sach);
                        /* Cập nhật tồn của sách */
                        
                    }
                }
                /* cập nhật lại mã phiếu nhập và xóa các object rỗng, tính tổng tiền*/

                boolean isOkCTPhieuNhap = CTPhieuNhapModel.InsertList(conn, listCTPhieuNhap);
                if (isOkCTPhieuNhap == false) {
                    throw new Exception("Thêm chi tiết phiếu nhập thất bại!");
                }
                 
                /* Cập nhật tiền nợ nhà cung cấp */
                NhaCungCapModel nhaCungCapModel = NhaCungCapModel.FindByMaNhaCungCap(conn, maNhaCungCap);
                nhaCungCapModel.setSoTienNo(nhaCungCapModel.getSoTienNo() + tongTien);
                NhaCungCapModel.UpdateNhaCungCap(conn, nhaCungCapModel);
                /* Cập nhật tiền nợ nhà cung cấp */

                conn.commit();

                isFailed = false;
                noiDungThongBao = "Đã thêm phiếu nhập mới!";

            } catch (Exception ex) {

                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(AddPhieuNhapServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }

                isFailed = true;
                noiDungThongBao = ex.getMessage();
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddPhieuNhapServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (isFailed) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
        }

        req.setAttribute("txtTitle", "Phiếu nhập");
        List<PhieuNhapModel> listPhieuNhap = PhieuNhapModel.getAllPhieuNhap(conn);
        req.setAttribute("listPhieuNhap", listPhieuNhap);
 
        req.getRequestDispatcher("/admin/list-phieunhap.jsp").forward(req, resp);

        //jsonDataTable
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("txtTitle", "Thêm phiếu nhập");
        req.getRequestDispatcher("/admin/phieunhap.jsp").forward(req, resp);

    }

}
