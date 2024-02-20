<%-- 
    Document   : UserCreate
    Created on : Feb 18, 2024, 9:58:47 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession,com.start.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
        <link rel="stylesheet" href="../style/styles.css">
        <link rel="stylesheet" href="../style/font.css">
    </head>
    <body>
        <%
            String as = (String) session.getAttribute(Constants.AS);
            if (as == null || as.isEmpty() || !as.equals(Constants.AGENT)){
                  response.sendRedirect("../index.html");
            }
        %>
        <div class="login-root">
        <div class="box-root flex-flex flex-direction--column" style="min-height: 100vh;flex-grow: 1;">
          <div class="box-root padding-top--24 flex-flex flex-direction--column" style="flex-grow: 1; z-index: 9;">
            <div class="box-root padding-top--48 padding-bottom--24 flex-flex flex-justifyContent--center">
              <h1><a rel="dofollow">Create Account</a></h1>
            </div>
            <div class="formbg-outer">
              <div class="formbg">
                <div class="formbg-inner padding-horizontal--48">
                  <span class="padding-bottom--15">Provide Appropriate Input!</span>
                  <form id="stripe-login" action="../CreateUser" method="post">
                    <div class="field padding-bottom--24">
                      <label>User Name</label>
                      <input type="text" name="name">
                    </div>
                    
                    <div class="field padding-bottom--24">
                      <div class="grid--50-50">
                        <label>Phone Number</label>
                      </div>
                      <input type="number" name="phone_no" placeholder = "09---">
                    </div>
                    <div class="field padding-bottom--24">
                      <div class="grid--50-50">
                        <label>Password</label>
                      </div>
                      <input type="password" name="password">
                    </div>
                      
                    <div class="field padding-bottom--24">
                      <div class="grid--50-50">
                        <label>Balance</label>
                      </div>
                      <input type="number" name="balance">
                    </div>
                    <div style="color: red;">
                          <label style="color: red;">
                             <%
                                  String error = (String)session.getAttribute(Constants.ERROR);
                                  if(error != null){
                                    out.print(error);
                                  }
                              %>
                          </label>
                      </div>
                    <div class="field padding-bottom--24">
                      
                        <input type="submit" name="submit" value="Continue">
                      
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
