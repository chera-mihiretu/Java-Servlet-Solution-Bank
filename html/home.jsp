<%@page import="jakarta.servlet.http.HttpSession,com.start.*, java.sql.*" %>
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
      if (as == null || as.isEmpty() || !as.equals(Constants.USER)){
            response.sendRedirect("../index.html");
      }
  %>
  <nav>
    <div class="nav-container">
      <div class="spacer"></div>
      <a href="#">
        <img src="../assets/user.png" class="avatar" alt="Profile Picture">
      </a>
      <p>User</p>
    </div>
  </nav>
  
  <div class="container">
    <h1>Do Your Transaction!</h1>
    
    <table class="card-table">
      <tr>
        <td>
            <a href="debit_user.jsp">
                <div class="card">
                  <img src="../assets/Debit.jpg" alt="Card Image">
                  <div class="card-content">
                    <h2>Debit</h2>
                    <p>We will keep your money safe.</p>
                  </div>
                </div>
            </a>
        </td>
        <td>
            <a href="cash_out.jsp">
                <div class="card">
                  <img src="../assets/cash_out.jpg" alt="Card Image">
                  <div class="card-content">
                    <h2>Cash Out</h2>
                    <p>Want your money on your hand.</p>
                  </div>
                </div>
            </a>
        </td>
      </tr>
      <tr>
        <td>
            <a href="user_transaction.jsp">
                <div class="card">
                  <img src="../assets/transfer.jpg" alt="Card Image">
                  <div class="card-content">
                    <h2>Transfer</h2>
                    <p>Send money to any one.</p>
                  </div>
                </div>
            </a>
        </td>
        <td>
            <a href="">          
                <div class="card">
                    <img src="../assets/pay_bill.jpg" alt="Card Image">
                    <div class="card-content">
                        <h2>See Info</h2>
                        <p>Check your status.</p>
                    </div>
                </div>
            </a>  
        </td>
      </tr>
      <tr>
        <td>
            <a>
                <div class="card">
                  <img src="../assets/ATM_request.jpg" alt="Card Image">
                  <div class="card-content">
                    <h2>ATM Request</h2>
                    <p>Request ATM for fast cash out.</p>
                    <label>Not yet implemented.</label>
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