package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.NhaCungCapModel;
import Utility.MyUtils;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ajax")

public class AjaxServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             
		Gson gson = new Gson();
		String term = request.getParameter("term");
                
            
		NhaCungCapModel nhaCungCapModel = new NhaCungCapModel();
		PrintWriter out = response.getWriter();
                
                Connection conn = MyUtils.getStoredConnection(request);
                List<NhaCungCapModel> listAllNhaCungCap = NhaCungCapModel.getAllNhaCungCap(conn);
             
     
                out.print(gson.toJson(nhaCungCapModel.search1(term, listAllNhaCungCap)));
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}