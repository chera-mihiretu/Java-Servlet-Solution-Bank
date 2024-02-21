<%@page import="jakarta.servlet.http.HttpSession,com.start.*, java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin</title>
  <link rel="stylesheet" href="../style/admin.css">
  <style>
      a{
          text-decoration: none;
      }
  </style>
</head>
<body>
  <%
      String as = (String) session.getAttribute(Constants.AS);
      if (as == null || as.isEmpty() || !as.equals(Constants.ADMIN)){
            response.sendRedirect("../index.html");
      }
  %>
  <header>
    <nav>
      <div class="nav-container">
        <h1 class="nav-title">Admin</h1>
      </div>
    </nav>
  </header>
  <form class="fixed-form" action="../create_agent" method="post">
    <label for="agent-input" >Add Agent</label>
    <div class="form-content" >
      <input id="agent-input" type="text" placeholder="Enter agent Name" name="agent_name">
      <input id="agent-input" type="number" placeholder="Enter Agent Phone :09--" name="agent_phone">
      <label style="color: red;"><% 
            String s = (String) session.getAttribute(Constants.ERROR);
            if (s != null && !s.isEmpty()){
                out.print(s);
            }
            session.removeAttribute(Constants.ERROR);
            %></label>
            <br>
      <button type="submit">Submit</button>
    </div>
  </form>
  <div class="content">
    <div class="see-transaction-button">
        
      <button>See Transaction</button>
    </div>
  </div>
  <main>
    <div class="container">
      <h2>Welcome to the Admin Panel</h2>
      <%
          
            DatabaseAlter db = new DatabaseAlter(Constants.database);
            
            if (db.statementCreated()){
                ResultSet setVal;
                Statement st = db.getStatment();
                try{
                  setVal = st.executeQuery("select * from Agent");
                


                while (setVal.next()){
      %>
      <div class="card">
        <img src="../assets/user.png" alt="Card 1 Image">
        <div class="card-content">
          <h3><%= setVal.getString(2) %></h3>
          <p>Phone:<%= setVal.getString(4)%></p>
          <p>Pass:<%= setVal.getString(3)%></p>
        </div>
        <a class="delete-button" href="../deleteAgent?id=<%=setVal.getString(1)%>">Delete</a>
      </div>
      
      <%
          // end of the while loop of java
            }
            }
            catch (SQLException e){
                response.sendRedirect("admin_page.jsp");
              }
          }else{
            session.setAttribute(Constants.ERROR, "Something Went wrong");
          response.sendRedirect("admin_page.jsp");
            }
      %>

  </main>
  
</body>
</html>