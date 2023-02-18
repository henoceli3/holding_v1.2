package application;

import java.sql.*;

public class ConnexionMysql {
	public Connection cn = null;
	public static Connection connexionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/holding_bd?useSSL=false","root","");
			System.out.println("Connexion etablie");
			return cn;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Connexion échouée");
			e.printStackTrace();
			return null;
		}
	}

}
