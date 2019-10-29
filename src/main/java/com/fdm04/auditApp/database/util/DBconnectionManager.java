package com.fdm04.auditApp.database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnectionManager {
	
	public String url;
	public Properties properties;
		
	// DB connection properties
	public DBconnectionManager(String host, String dbName, String username, String password) {
		
		this.url = "jdbc:mysql://"+host+"/"+dbName;
		this.properties = new Properties();
		this.properties.setProperty("user", username);
		this.properties.setProperty("password", password);
	}
	
	public Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(this.url, this.properties);
	}
	
}
