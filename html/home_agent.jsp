<%@page import="jakarta.servlet.http.HttpSession,com.start.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>My Webpage</title>
  <link rel="stylesheet" href="../style/style-home.css">
  <link rel="stylesheet" href="../style/font.css">
  <style>
      a{
          text-decoration: none;
          color: black;
      }
  </style>

</head>
<body>
    <%
      String as = (String) session.getAttribute(Constants.AS);
      if (as == null || as.isEmpty() || !as.equals(Constants.AGENT)){
            response.sendRedirect("../index.html");
      }
  %>
  <nav>
    <div class="nav-container">
      <div class="spacer"></div>
      <a href="#">
        <img src="../assets/user.png" class="avatar" alt="Profile Picture">
      </a>
      <p><%=as%></p>
    </div>
  </nav>
  
  <div class="container">
    <h1>Do Your Transaction!</h1>
    
    <table class="card-table">
      <tr>
        <td>
          <a href="UserCreate.jsp">
          <div class="card">
            <img src="../assets/Debit.jpg" alt="Card Image">
            <div class="card-content">
              <h2>Account Create</h2>
              <p>This is the description for the Debit card.</p>
            </div>
          </div>
            </a>
        </td>
        <td>
          <a href="agent_debit_aprove.jsp">
            <div class="card">
              <img src="../assets/Debit.jpg" alt="Card Image">
              <div class="card-content">
                <h2>Debit Approve</h2>
                <p>This is the description for the Debit card.</p>
              </div>
          </div>
        </a>
        </td>
      </tr>
      <tr>
        <td>
          <div class="card">
            <img src="../assets/transfer.jpg" alt="Card Image">
            <div class="card-content">
              <h2>Transfer</h2>
              <p>This is the description for the Transfer card.</p>
            </div>
          </div>
        </td>
      </tr>
    </table>
  </div>
  
  <footer>
    <p>Solution Bank</p>
  </footer>
</body>
</html>