package com.accenture.tcf.bars.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDatasource {
	
	static Connection conn;
	
	
	
	public MySQLDatasource() {
		
	}
	public static Connection getConnection() {
		try {
			// Login credentials for SQL
			String url = "jdbc:mysql://localhost:3306/bars_db?useSSL=false";
			String user = "root";
			String password = "root";
			// Loading the jdbc driver
			Class.forName("com.mysql.jdbc.Driver");
			// To Set the connection
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

}
