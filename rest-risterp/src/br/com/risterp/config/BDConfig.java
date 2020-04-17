package br.com.risterp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/comunitariafm?useSSL=false", "root", "Menino!@12");
	}
}

