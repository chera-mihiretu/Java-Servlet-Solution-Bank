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
@WebServlet(urlPatterns = {"/deleteAgent"})
public class DeleteAgent extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute(Constants.AS);
        
        
        
        if (user == null || !user.equals(Constants.ADMIN) || user.isEmpty()){
            response.sendRedirect("index.html");
            return;
        }
        
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        String id = (String) request.getParameter("id");
        if (db.statementCreated()){
            Statement st = db.getStatment();
            
            try{
                
                String query = String.format("delete from Agent where ag_id = %s", id);
                st.executeUpdate(query);
                
                response.sendRedirect("html/admin_page.jsp");
            }catch (SQLException e){
                response.sendRedirect("html/admin_page.jsp");
            }
            
            
        }
        else{
            response.sendRedirect("html/admin_page.jsp");
        }
        
    }

}
