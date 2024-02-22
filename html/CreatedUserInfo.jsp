<%-- 
    Document   : CreatedUserInfo
    Created on : Feb 20, 2024, 12:28:08 AM
    Author     : ASUS
--%>
<%@page import="jakarta.servlet.http.HttpSession,com.start.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
          <link rel="stylesheet" href="../style/profile.css">

    </head>
    <body>
        <%
      String as = (String) session.getAttribute(Constants.AS);
      if (as == null || as.isEmpty()){
            response.sendRedirect("../index.html");
      }
      boolean a = !as.equals(Constants.AGENT);
      boolean b = !as.equals(Constants.USER);
      if (!((!a && b) || (a && !b))){
        response.sendRedirect("../index.html");
      }
  %>
  
  
            <%
      String name = (String) session.getAttribute(Constants.NAME);
      String account = (String) session.getAttribute(Constants.ACCOUNT);
      String  password = (String) session.getAttribute(Constants.PASSWORD);
      String balance = (String) session.getAttribute(Constants.BALANCE);
      
  %>
  
            <div class="card">
      <div class="img-avatar">
          
        </div>
      <div class="card-text">
        <div class="portada">
            
        </div>
        <div class="title-total">   
          <div class="title">Account</div>
          <h2><%=name%></h2>

      <div class="desc">
          <label>Password :<%=password%> </label><br>
            <label>Account  :<%=account%></label>
            <label>Balance  :<%=balance%></label>

      </div>
      

      </div>



    </div>
        
    </body>
</html>
