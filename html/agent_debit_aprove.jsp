
<%@page import="jakarta.servlet.http.HttpSession,com.start.*" %>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin</title>
  <link rel="stylesheet" href="../style/admin.css">
</head>
<body>
    <%
      String as = (String) session.getAttribute(Constants.AS);
      if (as == null || as.isEmpty() || !as.equals(Constants.AGENT)){
            response.sendRedirect("../index.html");
      }
  %>
  <header>
    <nav>
      <div class="nav-container">
        <h1 class="nav-title">Agent</h1>
      </div>
    </nav>
  </header>

  <main>
    <div class="container">
      <h2>Welcome to the Agent Panel</h2>
      
      <div class="card">
        <img src="../assets/user.png" alt="Card 2 Image">
        <div class="card-content">
          <h3>Account Name</h3>
          <p>Account Number:</p>
          <p>Amount:</p>
          <p>Date:</p>


        </div>
        <button class="approve_button">Approve</button>
      </div>
      <div class="card">
        <img src="../assets/user.png" alt="Card 2 Image">
        <div class="card-content">
          <h3>Account Name</h3>
          <p>Account Number:</p>
          <p>Amount:</p>
          <p>Date:</p>


        </div>
        <button class="approve_button">Approve</button>
      </div>
      <div class="card">
        <img src="../assets/user.png" alt="Card 2 Image">
        <div class="card-content">
          <h3>Account Name</h3>
          <p>Account Number:</p>
          <p>Amount:</p>
          <p>Date:</p>


        </div>
        <button class="approve_button">Approve</button>
      </div>
    </div>

    
  </main>
  
</body>
</html>