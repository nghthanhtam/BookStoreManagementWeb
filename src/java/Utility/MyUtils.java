/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
 
import Model.ThanhVienModel;
import java.io.File; 
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection; 
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie; 
import javax.servlet.http.HttpSession; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 *
 * @author MITICC06
 */

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    private static final String ATT_NAME_UPLOAD_DIR = "Upload";
    public static final String ATT_NAME_AUTO_REDIRECT = "autoRedirect";

    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
     
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
    public static void storeLoginedThanhVien(HttpSession session, ThanhVienModel loginedUser) {
          session.setAttribute("loginedThanhVien", loginedUser); 
    }
    public static void LogOutThanhVien(HttpSession session) {
          session.invalidate();
    }
    public static ThanhVienModel getLoginedThanhVien(HttpSession session) {
        ThanhVienModel loginedUser = (ThanhVienModel) session.getAttribute("loginedThanhVien");
        return loginedUser;
    }
      
    public static String MD5(String plaintext) throws NoSuchAlgorithmException { 
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }
    /*
    public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }
*/
    
    
    
    /*
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }*/
    
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
    
    
    
    
    
    
    
    public static String uploadFile(HttpServletRequest request, String inputName) throws IOException, ServletException{
        String fileName="";
        try{
            Part filePart = request.getPart(inputName);
            Date date= new Date(); 
            fileName = date.getTime()+ "_" + getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + File.separator + ATT_NAME_UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new  File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes =  new  byte[1024];
                while((read = inputStream.read(bytes)) != -1){
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            }finally{
                if(inputStream != null){
                    inputStream.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
            }
            
        }catch(Exception e){
            fileName = "";
        }
        
        if (fileName!="")
            return ATT_NAME_UPLOAD_DIR + File.separator + fileName;
        else
            return fileName;
    }
    private static String  getFileName(Part part){
        final String  partHeader = part.getHeader("content-disposition");
        System.out.println("*****partHeader :"+ partHeader);
        for(String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf('=')+1).trim().replace("\"", "" );
            }
        }
        
        return null;
    }
    
    
    
}