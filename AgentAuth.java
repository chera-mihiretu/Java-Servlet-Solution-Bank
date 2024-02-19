package com.start;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
@WebServlet(urlPatterns = {"/login_agent_servlet"})
public class AgentAuth extends HttpServlet{
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession seccion = request.getSession();
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        String phone = request.getParameter("phone_no");
        
        String password = request.getParameter("phone_no");
        
        if(Validator.empty(phone) || Validator.empty("password")){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Feild Cannot be empty");
            response.sendRedirect("html/login_agent.jsp");
        }
        if (Validator.lengthCheck(phone, 10) || phone.length() > 10){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Check phone number ");
            response.sendRedirect("html/login_agent.jsp");
        }
        if (Validator.lengthCheck(password, 6)){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Too short password");
            response.sendRedirect("html/login_agent.jsp");
        }
        
        
    }
    
}
