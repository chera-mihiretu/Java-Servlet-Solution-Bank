<%@page import="jakarta.servlet.http.HttpSession,com.start.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Webpage</title>
  <link rel="stylesheet" href="../style/styles.css">
  <link rel="stylesheet" href="../style/font.css">

</head>
<body>
    <%
      String as = (String) session.getAttribute(Constants.AS);
      if (as == null || as.isEmpty() || !as.equals(Constants.USER)){
            response.sendRedirect("../index.html");
      }
  %>
    <div class="login-root">
        <div class="box-root flex-flex flex-direction--column" style="min-height: 100vh;flex-grow: 1;">
        
          <div class="box-root padding-top--24 flex-flex flex-direction--column" style="flex-grow: 1; z-index: 9;">
            <div class="box-root padding-top--48 padding-bottom--24 flex-flex flex-justifyContent--center">
              <h1><a rel="dofollow">Transfer Money</a></h1>
            </div>
            <div class="formbg-outer">
              <div class="formbg">
                <div class="formbg-inner padding-horizontal--48">
                  <form id="stripe-login" action="../transfere" method="post">
                    <div class="field padding-bottom--24">
                      <label for="number">Account number</label>
                      <input type="number" name="ac_number">
                    </div>
                    <div class="field padding-bottom--24">
                        <label for="text">Amount</label>
                        <input type="number" name="amount">
                      </div>
                    <div class="field padding-bottom--24">
                      <div class="grid--50-50">
                        <label for="password">Password</label>
                      </div>
                      <input type="password" name="password">
                    </div>
                      <label style="color: red;">
                          <%
                              String error = (String)session.getAttribute(Constants.ERROR);
                              if (error != null && !error.isEmpty())
                                out.print(error);
                              
                                session.removeAttribute(Constants.ERROR);
                              %>
                      </label>
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