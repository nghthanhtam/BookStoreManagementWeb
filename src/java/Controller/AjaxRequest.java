package Controller;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

@WebServlet(name = "AjaxRequest", urlPatterns = {"/admin/autocomplete"})
public class AjaxRequest extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final String[] COUNTRIES = new String[] {
         "30.0", "50.0"   
        };
 
 
    public AjaxRequest() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
        
        JSONArray arrayObj=new JSONArray();
        
        String query = request.getParameter("term");
        System.out.println(query);
        query = query.toLowerCase();
        for(int i=0; i<COUNTRIES.length; i++) {
            String country = COUNTRIES[i].toLowerCase();
            if(country.startsWith(query)) {
                arrayObj.add(COUNTRIES[i]);
            }
        }
        
        out.println(arrayObj.toString());
        out.close();
        
        request.getRequestDispatcher("/admin/autocomplete.jsp").forward(request, response);
        
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher("/admin/autocomplete.jsp").forward(request, response); 
    }

}
