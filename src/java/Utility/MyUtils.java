/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Model.ThanhVienModel;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MITICC06
 */

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    
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
}