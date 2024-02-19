package com.start;
import java.sql.*;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class DatabaseAlter {
    private Connection connection;
    private Statement statment;
    public DatabaseAlter(String database){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,"root", "root");
            connection.setAutoCommit(false);
            statment = connection.createStatement();
            
            
        }catch(SQLException e){
            try{
                connection.close();
            }catch (Exception _e){
                _e.printStackTrace();
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int insertValue(String table, List<String> values_list) throws SQLException{
        StringBuilder values = new StringBuilder("");
        for(String value : values_list){
            values.append(value+" ");
        }
        String query = String.format("insert into %s values (%s)", table, values);
        
        return this.statment.executeUpdate(query);
        
    }
    
    public Statement getStatment(){
        return this.statment;
    }
    public boolean statementCreated(){
        
        try{
            return !connection.isClosed();
        }catch (SQLException e){
            return false;
        }        
    }
    public void close(){
        try{
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
