
<%@page import="jakarta.servlet.http.HttpSession,com.start.*, java.sql.*" %>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin</title>
  <link rel="stylesheet" href="../style/admin.css">
  <link rel="stylesheet" href="../style/font.css">
  
  <style>
      a{
          text-decoration: none;
          color: white;
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
  <header>
    <nav>
      <div class="nav-container">
        <h1 class="nav-title">Agent</h1>
      </div>
    </nav>
  </header>

  <main>
      <%
            DatabaseAlter db = new DatabaseAlter(Constants.database);
            Statement stat= db.getStatment();
      %>
      
    <div class="container">
        
      <h2>Welcome to the Agent Panel</h2>
      
      <% 
        String queryRetrival = String.format("select * from TransactionDone");
        ResultSet sset = stat.executeQuery(queryRetrival);
        String balance = ""; 
        String ac_number = "";
        while (sset.next()){
      %>
      <div class="card">
        <img src="../assets/user.png" alt="Card 2 Image">
        <div class="card-content">
          <h3><%=sset.getString(7)%></h3>
          <p>Account Number:<%ac_number = sset.getString(5);%><%=ac_number%></p>
          <p>Amount:<% balance = sset.getString(3);%><%=balance%></p>
          <p>Date:<%=sset.getString(4)%></p>

           
        </div>
        <a class="approve_button" href="../approve?id=<%=sset.getString(1)%>&ac_number=<%=sset.getString(5)%>&amount=<%=sset.getString(3)%>">Approve</a>
                
      </div>
                <%
               }
           %>
           
     
    </div>

    
  </main>
  
</body>
</html>