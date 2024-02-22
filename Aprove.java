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


@WebServlet(urlPatterns = {"/approve"})
public class Aprove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession seccion = request.getSession();
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        String id = (String) request.getParameter("id");
        String ac_number = (String) request.getParameter("ac_number");
        String amount = (String) request.getParameter("amount");
        PrintWriter out = response.getWriter();
        out.print(id);
        
        String user = (String) seccion.getAttribute(Constants.AS);
        String agent_id = (String) seccion.getAttribute(Constants.USER_ID);

        
        if (user == null || user.isEmpty() || !user.equals(Constants.AGENT)){
            response.sendRedirect("index.html");
            return;
        }
        
        try{
            

            if (db.statementCreated()){
                Statement stat = db.getStatment();
                String queryTwo = String.format("update TransactionDone set aproved = \"YES\", ag_id = %s where tr_id = %s", agent_id,id);
                String queryOne = String.format("update Users set balance = balance + %s where  ac_number = %s", amount, ac_number);
                db.notAuto();
                stat.executeUpdate(queryOne);
                stat.executeUpdate(queryTwo);
                db.commit();
            }
            
            db.rollBack();
            db.auto();
            response.sendRedirect("html/agent_debit_aprove.jsp");
        }catch (SQLException e){
            out.print(e.getMessage());
            db.close();
            response.sendRedirect("html/agent_debit_aprove.jsp");
        }
    }


}
