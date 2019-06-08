/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MessagesModel;
import Model.PhieuChiModelWithTenNhaCungCap;
import Model.PhieuNhapModel;
import Model.PhieuNhapModelWithTenNhaCungCapAndTenNhanVien;
import Model.SachModel;
import Model.ThanhVienModel;
import Utility.MyUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "PhieuNhapServlet", urlPatterns = {"/admin/phieunhap"})
public class PhieuNhapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(req);
        List<PhieuNhapModelWithTenNhaCungCapAndTenNhanVien> listPhieuNhap = PhieuNhapModelWithTenNhaCungCapAndTenNhanVien.getAllPhieuNhap(conn);
        System.out.println(listPhieuNhap.size());
        req.setAttribute("listPhieuNhap", listPhieuNhap);

        req.getRequestDispatcher("/admin/list-phieunhap.jsp").forward(req, resp);
    }

}
