package com.start;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

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
        
        String password = request.getParameter("password");
        
        if(password == null || phone == null){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Feild Cannot be empty");
            response.sendRedirect("html/login_agent.jsp");
            return;
        }
        
        if(Validator.empty(phone) || Validator.empty(password)){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Feild Cannot be empty");
            response.sendRedirect("html/login_agent.jsp");
            return;
        }
        if (phone.length() != 10){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Check phone number ");
            response.sendRedirect("html/login_agent.jsp");
            return;
        }
        if (Validator.lengthCheck(password, 6)){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Too short password");
            response.sendRedirect("html/login_agent.jsp");
            return;
        }
        
        if(db.statementCreated()){
            try{
                Statement st = db.getStatment();
                String query = String.format("select * from Agent where phone_no = %s and ag_password=\'%s\'", phone, password);

                ResultSet setVal = st.executeQuery(query);
                
                if (setVal.next()){
                    seccion.setAttribute(Constants.ERROR,null);
                    seccion.setAttribute(Constants.AS,Constants.AGENT);
                    seccion.setAttribute(Constants.USER_ID, setVal.getString(1));
                    response.sendRedirect("html/home_agent.jsp");
                    
                }else{
                    seccion.setAttribute(Constants.ERROR, "No such User!");
                    response.sendRedirect("html/login_agent.jsp");
                }
                
            }catch (SQLException e){
                seccion.setAttribute(Constants.ERROR, e.getMessage());//"There have been database Error, please try again!");
                    response.sendRedirect("html/login_agent.jsp");
            }
        }else{

            seccion.setAttribute(Constants.ERROR, "Something went wrong, please Try again!");
            response.sendRedirect("html/login_agent.jsp");
            return;
        }
    }
    
}
