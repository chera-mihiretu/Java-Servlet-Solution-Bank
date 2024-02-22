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
      label{
          font-size: 10px;
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
    <h1>Agent</h1>
    
    <table class="card-table">
      <tr>
        <td>
          <a href="UserCreate.jsp">
          <div class="card">
            <img src="../assets/book.jpg" alt="Card Image">
            <div class="card-content">
              <h2>Account Create</h2>
              <label>Our a bank account offers secure financial services with options like saving, and more.</lable>
            </div>
          </div>
            </a>
        </td>
        <td>
          <a href="agent_debit_aprove.jsp">
            <div class="card">
              <img src="../assets/aprove.jpg" alt="Card Image">
              <div class="card-content">
                <h2>Debit Approve</h2>
                <label>When creating a debit account, the approval process typically involves verification by a bank agent!</label>
              </div>
          </div>
        </a>
        </td>
      </tr>
      <tr>
        <td>
           <a href="debit_agent.jsp">
            <div class="card">
              <img src="../assets/Debit.jpg" alt="Card Image">
              <div class="card-content">
                <h2>Debit</h2>
                <label>Debiting money allows for the payment or transfer of funds from the account holder's balance.</label>
              </div>
          </div>
        </a>
        </td>
      </tr>
    </table>
  </div>
  
  <footer>
    <p>Solution Bank</p>
  </footer>
</body>
</html>