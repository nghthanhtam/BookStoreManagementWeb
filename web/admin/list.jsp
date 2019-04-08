<%@page import="Utility.MyUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>

   <%
   try{
     String s[]=null;

     Class.forName("oracle.jdbc.driver.OracleDriver");
     
     //Connection con = MyUtils.getStoredConnection("org.apache.catalina.connector.RequestFacade@6d66ba18");
     Connection con =DriverManager.getConnection("org.apache.catalina.connector.RequestFacade@6d66ba18");
     
     Statement st=con.createStatement();
     ResultSet rs = st.executeQuery("select * from phieuchi");

       List li = new ArrayList();

       while(rs.next())
       {
           li.add(rs.getString(1));
       }

       String[] str = new String[li.size()];
       Iterator it = li.iterator();

       int i = 0;
       while(it.hasNext())
       {
           String p = (String)it.next();
           str[i] = p;
           i++;
       }

    //jQuery related start
       String query = (String)request.getParameter("q");

       int cnt=1;
       for(int j=0;j<str.length;j++)
       {
           if(str[j].toUpperCase().startsWith(query.toUpperCase()))
           {
              out.print(str[j]+"\n");
              if(cnt>=5)// 5=How many results have to show while we are typing(auto suggestions)
              break;
              cnt++;
            }
       }
    //jQuery related end

rs.close();
st.close();
con.close();

}
catch(Exception e){
e.printStackTrace();
}

//www.java4s.com
%>