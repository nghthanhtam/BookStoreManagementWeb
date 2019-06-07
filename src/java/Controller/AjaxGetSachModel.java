/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.SachModel;
import Utility.MyUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author TamTorres
 */
@WebServlet(name = "AjaxGetSachModel", urlPatterns = {"/ajax"})
public class AjaxGetSachModel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String id = request.getParameter("idSach");
        BufferedReader br
                = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String jsonString = "";
        JSONArray jsonList = null;

        if (br != null) {
            jsonString = br.readLine();
            //System.out.println(jsonString);
        }

        try {
            jsonList = new JSONArray(jsonString);
            //System.out.println(jsonList.getJSONObject(0).getString("id"));
        } catch (Exception ex) {

        }

        Connection conn = MyUtils.getStoredConnection(request);
        SachModel sachModel = new SachModel();
        List<SachModel> listSach = new ArrayList<>();

        //JsonArray sach = new JsonArray();
        //JsonArray listSach = new JsonArray();    
        for (int i = 0; i < jsonList.length(); i++) {
            try {

                int id = Integer.parseInt(jsonList.getJSONObject(i).getString("id"));

                sachModel = SachModel.FindByMaSach(conn, id);
                if (sachModel == null) {
                    throw new Exception("Không tìm thấy thông tin sách [MASACH] = " + id);
                }

                listSach.add(sachModel);
                
//                sach.add( Integer.toString(sachModel.getMaSach()));
//                sach.add(sachModel.getTenSach());
//                sach.add(sachModel.getAnhDaiDien());
//                sach.add(sachModel.getGiaBan());
//                sach.add(jsonList.getJSONObject(i).getString("qty"));
                
                               
            } catch (Exception ex) {

            }
        }

//        List<String> list = new ArrayList<>();
//        list.add(id);
//        list.add(Double.toString(sach.getGiaBan()));
//        list.add(sach.getAnhDaiDien());
//        List<SachModel> listSach = new ArrayList<>();
//        listSach.add(sach);
        String json = new Gson().toJson(listSach);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
