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
@WebServlet(name = "ShowInfo", urlPatterns = {"/show_info"})
public class ShowInfo extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String)session.getAttribute(Constants.ACCOUNT);

        DatabaseAlter db = new DatabaseAlter(Constants.database);
        if(db.statementCreated()){
            Statement stat = db.getStatment();
            try{
            ResultSet set = stat.executeQuery("select balance from Users where ac_number = "+ account);
            if(set.next()){
                session.setAttribute(Constants.BALANCE, set.getString(1));
            }
            }catch (SQLException e){
                e.printStackTrace();
            }
            
            response.sendRedirect("html/CreatedUserInfo.jsp");
        }

    }

}
