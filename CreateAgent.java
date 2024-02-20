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
@WebServlet(urlPatterns = {"/create_agent"})
public class CreateAgent extends HttpServlet {

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession seccion = request.getSession();
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        
        String name = String.valueOf(request.getParameter("agent_name"));
        String phone = String.valueOf(request.getParameter("agent_phone"));
        System.out.print("slghjksl");
        if(name.isEmpty() || phone.isEmpty()){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Feild Cannot be Empty");
            response.sendRedirect("html/admin_page.jsp");
            return;
        }
        
        if (Validator.lengthCheck(phone, 10) && phone.length() > 11){
            
            db.close();
            seccion.setAttribute(Constants.ERROR, "Check Phone number");
            response.sendRedirect("html/admin_page.jsp");
            return;
        }
        
        if (Validator.containsNumber(name) || Validator.lengthCheck(name, 8)){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Check the name of the agent" );
            response.sendRedirect("html/admin_page.jsp");
            return;
        }
        
        if (db.statementCreated()){
            try{
                String password = GeneratePassword.generatePassword(10);
                Statement st = db.getStatment();
                
                String qq = String.format("insert into Agent(ag_name, ag_password, phone_no) values(\'%s\', \'%s\', %s);", name, password, phone);
                st.execute(qq);
                
                
                
                
                response.sendRedirect("html/admin_page.jsp");
                
            }catch(SQLException e){
                seccion.setAttribute(Constants.ERROR, e.getMessage());
                response.sendRedirect("html/admin_page.jsp");
            }
        }else{
            seccion.setAttribute(Constants.ERROR, "Something went wrong");
            response.sendRedirect("html/admin_page.jsp");
            return;
        }
        
        
    }

   

}
