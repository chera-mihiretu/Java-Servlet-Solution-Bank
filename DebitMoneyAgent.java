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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author ASUS
 */
@WebServlet(name = "DebitMoneyAgent", urlPatterns = {"/debit_money_agent"})
public class DebitMoneyAgent extends HttpServlet {

     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession seccion = request.getSession();
        DatabaseAlter db = new DatabaseAlter(Constants.database);
        
        String id = (String)seccion.getAttribute(Constants.USER_ID);
        String name = (String)seccion.getAttribute(Constants.NAME);
        String amount = String.valueOf(request.getParameter("amount"));
        String account = String.valueOf(request.getParameter("ac_number"));
        

        String user = (String) seccion.getAttribute(Constants.AS);
        
        if (user == null || user.isEmpty() || !user.equals(Constants.AGENT)){
            response.sendRedirect("index.html");
            return;
        }
        
        
        if (amount.isEmpty() || account.isEmpty()){
            seccion.setAttribute(Constants.ERROR, "Feild Cannot Be empty");
            response.sendRedirect("html/debit_agent.jsp");
            return;
        }
        if(db.statementCreated()){
            try{
                Statement stat= db.getStatment();
                String query = String.format("insert into TransactionDone(tr_type, amount, tr_date, ac_number, t_name, ag_id, aproved) values"
                        + "(\'%s\', %s, CURRENT_DATE, %s, \'%s\', %s, \"YES\")","debit", amount, account, name, id);
                String queryOne = String.format("update Users set balance = balance + %s where  ac_number = %s", amount, account);
                String check = String.format("select * from Users where ac_number = " + account);
                db.notAuto();
                ResultSet set= stat.executeQuery(check);
                if (set.next()){
                    stat.executeUpdate(query);
                    stat.executeUpdate(queryOne);
                    seccion.setAttribute(Constants.ERROR, "<label style=\"color:green;\">Transaction is done</label>");
                    db.commit();
                    db.auto();
                    db.close();
                    response.sendRedirect("html/debit_agent.jsp");
                }else{
                    seccion.setAttribute(Constants.ERROR, "Check The account Numbr");
                    db.rollBack();
                    db.auto();
                    response.sendRedirect("html/debit_agent.jsp");
                    return;
                }
                
            }catch(SQLException e){
                 seccion.setAttribute(Constants.ERROR, e.getMessage());
                 db.rollBack();
                 db.close();
                    response.sendRedirect("html/debit_agent.jsp");
                   
            }
        }
        
        
    }

   

}
