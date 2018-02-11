package com.exmaple.misc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/*
 * Copied from : https://stackoverflow.com/questions/2451892/how-do-i-connect-to-a-sql-server-2008-database-using-jdbc
 */

public class ConnectMSSQLServer
{
   public Connection dbConnect()
   {
      try {
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         Connection conn = DriverManager.getConnection("jdbc:sqlserver://data.hacktechvalley.com;databaseName=GPSDATA",
        		 "ReadOnly", "ReadOnly");
         System.out.println("connected");
         return conn;
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println("Connection Failed ....");
      return null;
   }
}