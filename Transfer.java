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
import java.io.PrintWriter;
import java.sql.*;


/**
 *
 * @author ASUS
 */
@WebServlet(urlPatterns = {"/transfere"})
public class Transfer extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        HttpSession session = request.getSession();
        String amount = String.valueOf(request.getParameter("amount"));
        String password = String.valueOf(request.getParameter("password"));
        String ac_number = String.valueOf(request.getParameter("ac_number"));
        
        
        if(amount.isEmpty() || password.isEmpty() || ac_number.isEmpty()){
            session.setAttribute(Constants.ERROR,"Empty Feild!");
            response.sendRedirect("html/user_transaction.jsp");
            return;
        }
        
        
        String sec_pass = (String)session.getAttribute(Constants.PASSWORD);
        String phone = (String)session.getAttribute(Constants.PHONE_NUMBER);
        String user = (String) session.getAttribute(Constants.AS);
        
        if (user == null || user.isEmpty() || !user.equals(Constants.USER)){
            response.sendRedirect("index.html");
            return;
        }
        
        
        if (sec_pass != null && !sec_pass.isEmpty()){
            if (sec_pass.equals(password)){
                if (db.statementCreated()){
                    try{
                        Statement stat = db.getStatment();
                        ResultSet balance = stat.executeQuery("select balance from Users where phone_number = " + phone);
                        boolean sufficientBalance = false;
                        if (balance.next()){
                            int am = Integer.parseInt(amount);
                            if (balance.getInt(1) > am){
                                sufficientBalance = true;
                            }else{
                                session.setAttribute(Constants.ERROR, "Insufficient Balance");
                            }
                        }
                        
                        if (sufficientBalance){
                            String queryOne = String.format("update users set balance = balance-%s where phone_number = %s", amount, phone);
                            String queryTwo = String.format("update users  set balance = balance + %s where ac_number = %s", amount, ac_number);

                            db.notAuto();
                            stat.executeUpdate(queryOne);
                            int rowAffected = stat.executeUpdate(queryTwo);
                            if (rowAffected == 0){
                                db.rollBack();
                                session.setAttribute(Constants.ERROR, "No Such Account");
                                db.auto();
                                response.sendRedirect("html/user_transaction.jsp");
                                return;
                            } 
                            session.setAttribute(Constants.ERROR, "<label style=\"color: green;\">Transaction Done</label>");
                            db.auto();
                            db.commit();
                            response.sendRedirect("html/user_transaction.jsp");
                        }else{
                            db.rollBack();
                            db.auto();
                            db.close();
                            session.setAttribute(Constants.ERROR, "Insufficient balance");
                            response.sendRedirect("html/user_transaction.jsp");
                        }
//                        
                    }catch (SQLException e){
                        session.setAttribute(Constants.ERROR, e.getMessage());
                        db.rollBack();
                        db.auto();
                        db.close();
                        response.sendRedirect("html/user_transaction.jsp");
                    }
                }
            }else{
               session.setAttribute(Constants.ERROR,"Wrong Password!");
               response.sendRedirect("html/user_transaction.jsp");
            }
        }else{
            response.sendRedirect("index.html");
        }
    }

  
}
