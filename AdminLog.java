/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.start;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "AdminLog", urlPatterns = {"/admin_login"})
public class AdminLog extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = String.valueOf(request.getParameter("password"));
        HttpSession seccion = request.getSession();
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        if (Validator.empty(password) || Validator.lengthCheck(password, 6)){
            seccion.setAttribute(Constants.ERROR, "Check Password");
            response.sendRedirect("html/AdminLogin");
        }
        
        if (db.statementCreated()){
            try{
                Statement st = db.getStatment();
                String query = String.format("select * from admins where password=%s", password);
                ResultSet value = st.executeQuery(query);
                if (value.next()){
                    seccion.setAttribute(Constants.AS, Constants.ADMIN);
                    response.sendRedirect("html/admin_page.jsp");
                }
            }catch (SQLException e){
                seccion.setAttribute(Constants.ERROR, "Something went wrong");
            response.sendRedirect("html/AdminLogin");
            }
            
        }
    }
}
