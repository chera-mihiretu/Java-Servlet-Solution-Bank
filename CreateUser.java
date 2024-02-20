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
@WebServlet(urlPatterns = {"/CreateUser"})
public class CreateUser extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession seccion = request.getSession();
        String name = String.valueOf(request.getParameter("name"));
        String phone_no = String.valueOf(request.getParameter("phone_no"));
        String password = String.valueOf(request.getParameter("password"));
        String balance = String.valueOf(request.getParameter("balance"));
        String as = (String) seccion.getAttribute(Constants.AS);
        if (as == null || as.isEmpty() || !as.equals(Constants.AGENT)){
              response.sendRedirect("../index.html");
        }
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        if(Validator.empty(name) || Validator.empty(phone_no) || Validator.empty(password) || Validator.empty(balance)){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Feild Cannot be empty");
            response.sendRedirect("html/UserCreate.jsp");
            return;
        } 
        if (Validator.containsSpecial(name)){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Name Cannot Have any character other than letters");
            response.sendRedirect("html/UserCreate.jsp");
            return;
        }
        if (Validator.lengthCheck(password, 6)){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Short password");
            response.sendRedirect("html/UserCreate.jsp");
            return;
        }

        if (Validator.lengthCheck(phone_no, 10)&& phone_no.length() > 10){
            db.close();
            seccion.setAttribute(Constants.ERROR, "Phone number is not correct");
            response.sendRedirect("html/UserCreate.jsp");
            return;
        }
        
        
        if (db.statementCreated()){
            Statement st = db.getStatment();
            
            
            String query = String.format("insert into Users(ac_name, password, balance, phone_number) values(\'%s\', \'%s\', %s, %s)", name, password, balance, phone_no);
            try{
                st.executeUpdate(query);
                
                ResultSet set = st.executeQuery("select ac_number from Users where phone_number = "+phone_no +" and ac_name = "+"\'"+name+"\'");
                seccion.setAttribute(Constants.ERROR, null);
                String ac_number = "";
                if (set.next()){
                    ac_number = set.getString(1);
                }
                
                seccion.setAttribute(Constants.ERROR, null);
                seccion.setAttribute(Constants.NAME, name);
                seccion.setAttribute(Constants.PASSWORD, password);
                
                seccion.setAttribute("ac_number", ac_number);

                response.sendRedirect("html/CreatedUserInfo.jsp");
            }catch (SQLException e){
                db.close();
                seccion.setAttribute(Constants.ERROR, "Something went wrong, check the phone number");
                response.sendRedirect("html/UserCreate.jsp");
                
            }
            
        }
    }


}
