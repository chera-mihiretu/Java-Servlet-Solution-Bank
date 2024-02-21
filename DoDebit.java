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
@WebServlet(urlPatterns = {"/debit_money"})
public class DoDebit extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession seccion = request.getSession();
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        String name = (String)seccion.getAttribute(Constants.NAME);
        String phone_no = (String) seccion.getAttribute(Constants.PHONE_NUMBER);
        
        
        String amount = String.valueOf(request.getParameter("amount"));
        String account = String.valueOf(request.getParameter("ac_number"));
        
        if (amount.isEmpty() || account.isEmpty()){
            seccion.setAttribute(Constants.ERROR, "Feild Cannot Be empty");
            response.sendRedirect("html/debit_user.jsp");
            return;
        }
        if(db.statementCreated()){
            try{
                Statement stat= db.getStatment();
                String query = String.format("insert into TransactionDone(tr_type, amount, tr_date, ac_number, t_name) values"
                        + "(\'%s\', %s, CURRENT_DATE, %s, \'%s\')","debit", amount, account, name);
                
                String check = String.format("select * from Users where ac_number = " + account);
                ResultSet set= stat.executeQuery(check);
                if (set.next()){
                    stat.executeUpdate(query);
                    seccion.setAttribute(Constants.ERROR, "<label style=\"color:green;\">Transaction is done</label>");
                    response.sendRedirect("html/debit_user.jsp");
                }else{
                    seccion.setAttribute(Constants.ERROR, "Check The account Numbr");
                    response.sendRedirect("html/debit_user.jsp");
                    return;
                }
                
            }catch(SQLException e){
                 seccion.setAttribute(Constants.ERROR, e.getMessage());
                    response.sendRedirect("html/debit_user.jsp");
                   
            }
        }
        
        
    }
}
