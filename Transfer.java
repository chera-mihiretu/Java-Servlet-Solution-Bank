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
@WebServlet(urlPatterns = {"/transfer"})
public class Transfer extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        HttpSession session = request.getSession();
        String amount = String.valueOf(request.getParameter("amount"));
        String password = String.valueOf(request.getParameter("password"));
        String ac_number = String.valueOf(request.getParameter("ac_number"));
        
        String sec_pass = (String)session.getAttribute(Constants.PASSWORD);
        String phone = (String)session.getAttribute(Constants.PASSWORD);
        
        if (sec_pass != null && !sec_pass.isEmpty()){
            if (sec_pass == password){
                if (db.statementCreated()){
                    try{
                        Statement stat = db.getStatment();
                        ResultSet balance = stat.executeQuery("select balance from Users where phone_number = " + phone);
                        boolean sufficientBalance = false;
                        if (balance.next()){
                            int am = Integer.parseInt(amount);
                            if (balance.getInt(0) > am){
                                sufficientBalance = true;
                            }else{
                                session.setAttribute(Constants.ERROR, "Insufficient Balance");
                            }
                        }
                        
                        if (sufficientBalance){
                            String queryOne = String.format("update users set balance = balance-%s where phone_number = %s", amount, phone);
                            String queryTwo = String.format("update users  set balance = balance + %s where tr_id = %s", amount, ac_number);

                            db.notAuto();
                            stat.executeQuery(queryOne);
                            stat.executeQuery(queryTwo);
                            db.auto();
                            db.commit();
                        }else{
                            session.setAttribute(Constants.ERROR, "Insufficient balance");
                            response.sendRedirect("html/user_transaction.jsp");
                        }
                        
                    }catch (SQLException e){
                        session.setAttribute(Constants.ERROR, e.getMessage());
                        db.close();
                        response.sendRedirect("html/user_transaction.jsp");
                    }
                }
            }
        }else{
            session.setAttribute(Constants.ERROR,"Log in first please!");
            response.sendRedirect("html/user_transaction.jsp");
        }
    }

  
}
