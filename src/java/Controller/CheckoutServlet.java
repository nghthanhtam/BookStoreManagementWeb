/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */     
package Controller;

import Model.CTDonHangModel;
import Model.DonHangModel;
import Model.MessagesModel;
import Model.SachModel;
import Utility.MyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        HttpSession session = ((HttpServletRequest) req).getSession();
        
        //int maThanhVien = MyUtils.getLoginedThanhVien(session).getMaThanhVien();      
        int maThanhVien = 45;
                
        if (button != null && button.equals("them")) {
            try {
                //String firstName = (String) req.getParameter("comment");
                //int maNhaCungCap = Integer.parseInt(req.getParameter("manhacungcap"));             
         
                int maDonHang = DonHangModel.getMaDonHangCurrent(conn);
                
                String diaChi = (String) req.getParameter("address");
                String soDienThoai = (String) req.getParameter("tel");
                String ghiChu = (String) req.getParameter("comment");
                String listCT = (String) req.getParameter("listctdonhang");
      
                System.out.print(listCT);
                Double tongTien=0.0;
                
               try {
                    //JSONArray jsonArr = new JSONArray("[{\"id\":\"61\",\"price\":\"15700.0\",\"qty\":\"1\",\"name\":\"Kẻ may mắn\"},{\"id\":\"52\",\"price\":\"25000.0\",\"qty\":\"1\",\"name\":\"Sách số 123\"}]");
                    JSONArray jsonArr = new JSONArray(listCT);
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                        listCTDonHang.add(new CTDonHangModel(0,maDonHang,Integer.parseInt(jsonObj.getString("id")),Integer.parseInt(jsonObj.getString("qty")),Double.parseDouble(jsonObj.getString("price")),0));
                        
                        System.out.println(jsonObj);
                        System.out.println(jsonObj.getString("id"));
                        System.out.println(jsonObj.getString("price"));
                        System.out.println(Double.parseDouble(jsonObj.getString("price"))+1.0);
                        
                        tongTien+= Double.parseDouble(jsonObj.getString("price"))*Integer.parseInt(jsonObj.getString("qty"));
                    }
    
                } catch (JSONException ex) {
                   
                    ex.printStackTrace();
                };
                
                Date date = new Date();
                //Date ngayLap = dateFormat.format(date);
                
                boolean isOKDonHang = DonHangModel.InsertDonHang(conn, new DonHangModel(
                        0,
                        maThanhVien,
                        new java.sql.Date(date.getTime()),
                        tongTien,
                        1,
                        diaChi,
                        70,
                        soDienThoai,
                        ghiChu        
                ));
                // conn.commit();
                if (isOKDonHang == false) {
                    throw new Exception("Thêm đơn hàng thất bại!");
                }
    
              
        
                boolean isOkCTDonHang = CTDonHangModel.InsertList(conn, listCTDonHang);
                if (isOkCTDonHang == false) {
                    throw new Exception("Thêm chi tiết đơn hàng thất bại!");
                }
                isFailed = false;
                noiDungThongBao = "Đã thêm đơn hàng mới!";

            } catch (Exception ex) {

                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex1);
                }

                isFailed = true;
                noiDungThongBao = ex.getMessage();
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (isFailed) {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Có lỗi xảy ra!", "Yêu cầu của bạn không được xử lý!", MessagesModel.ATT_TYPE_ERROR));
        } else {
            req.setAttribute(MessagesModel.ATT_STORE, new MessagesModel("Thông báo!", noiDungThongBao, MessagesModel.ATT_TYPE_SUCCESS));
            req.getRequestDispatcher("trangthaisaudathang.jsp").forward(req, resp);
        }
        
        
        req.setAttribute("txtTitle", "Trang chủ - Book Store");

            
        List<SachModel> listSachMoiNhat = SachModel.getListSachMoiNhatTop7(conn);
        SachModel objectOne = listSachMoiNhat.get(0);
        listSachMoiNhat.remove(0);
        req.setAttribute("listSachMoiNhat", listSachMoiNhat);
        req.setAttribute("sachMoiNhat", objectOne);
        List<SachModel> listSachGiamGia = SachModel.getListSachGiamGiaTop7(conn);
        SachModel objectGiamGiaNhat = listSachGiamGia.get(0);
        listSachGiamGia.remove(0);
        req.setAttribute("sachGiamGiaNhat", objectGiamGiaNhat);
        req.setAttribute("listSachGiamGia", listSachGiamGia);
        
        
        req.getRequestDispatcher("home.jsp").forward(req, resp);
   
    }

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("txtTitle", "Checkout");
    
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
        

    }

}
