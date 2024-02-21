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
@WebServlet(urlPatterns = {"/login_servlet"})
public class LogIn extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession seccion = request.getSession();
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        String phone_no = String.valueOf(request.getParameter("phone_no"));
        String pass = String.valueOf(request.getParameter("password"));
        
        // validating the inputs
        if (phone_no.equals("") || pass.equals("")){
            db.close();
            seccion.setAttribute("error", "Feild Cannot be empty");
            response.sendRedirect("html/login.jsp");
            return;
        }
        if (phone_no.length() != 10 || pass.length() < 5){
            seccion.setAttribute("error", "There is something wrong with your with your inputs");
            response.sendRedirect("html/login.jsp");
            return;
        }
        // checking if user exists on database
        if (db.statementCreated()){
            try{
                Statement st = db.getStatment();
                
                
                
                String query = String.format("select ac_name from Users where phone_number = %s and password = \'%s\'", phone_no, pass);
                
                ResultSet value = st.executeQuery(query);
                if(value.next()){
                    seccion.setAttribute(Constants.AS, Constants.USER);
                    seccion.setAttribute(Constants.NAME, value.getString(1));
                    seccion.setAttribute(Constants.PHONE_NUMBER, phone_no);
                    seccion.setAttribute(Constants.PASSWORD, pass);
                    seccion.setAttribute("phone_no", value.getString(1));
                    response.sendRedirect("html/home.jsp");
                    
                }else{
                    seccion.setAttribute(Constants.ERROR, "No such user");
                    response.sendRedirect("html/login.jsp");
                }
            }catch (SQLException e){
                seccion.setAttribute(Constants.ERROR, e.getMessage());//"Somethin went wrong, please try again!");
                response.sendRedirect("html/login.jsp");
            }
            
        }else{
            
            seccion.setAttribute("error", "Somethin went wrong, please try again !");
            response.sendRedirect("html/login.jsp");
        }
        
        
        
    }

}
