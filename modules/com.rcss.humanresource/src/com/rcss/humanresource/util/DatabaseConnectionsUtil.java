package com.rcss.humanresource.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DatabaseConnectionsUtil {
   protected static Logger logger = Logger.getLogger("DatabaseConnectionsUtil.java");
    public static Connection SqlCon(){
        // TODO Auto-generated method stub
        Connection con=null;
        String connectionUrl = "jdbc:sqlserver://172.16.0.47:1433;" +
                "databaseName=esslsmartoffice;user=sa;password=ksml@123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String userName = "sa";
            String password = "kallam";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=etimetracklite1;integratedSecurity=true";
            //con = DriverManager.getConnection(url, userName, password);
            con = DriverManager.getConnection(connectionUrl);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.info("Connection in DatabaseConnectionUtil "+con);
        return con;
    }
    public static Connection PostgresCon() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/production", "postgres", "rcss@123");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
