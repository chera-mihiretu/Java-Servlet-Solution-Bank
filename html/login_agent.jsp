<!DOCTYPE html>
<%@page import="jakarta.servlet.http.HttpSession,com.start.*" %>
<html>
<head>
  <title>My Webpage</title>
  <link rel="stylesheet" href="../style/styles.css">
  <link rel="stylesheet" href="../style/font.css">

</head>
<body>
    <div class="login-root">
        <div class="box-root flex-flex flex-direction--column" style="min-height: 100vh;flex-grow: 1;">
          <div class="box-root padding-top--24 flex-flex flex-direction--column" style="flex-grow: 1; z-index: 9;">
            <div class="box-root padding-top--48 padding-bottom--24 flex-flex flex-justifyContent--center">
              <h1><a rel="dofollow">Solution Bank</a></h1>
            </div>
            <div class="formbg-outer">
              <div class="formbg">
                <div class="formbg-inner padding-horizontal--48">
                  <span class="padding-bottom--15">For Agents</span>
                  <form id="stripe-login" action="../login_agent_servlet" method="post">
                    <div class="field padding-bottom--24">
                      <label for="email">Phone Number</label>
                      <input type="text" name="phone_no">
                    </div>
                    <div class="field padding-bottom--24">
                      <div class="grid--50-50">
                        <label for="password">Password</label>
                      </div>
                      <input type="password" name="password">
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