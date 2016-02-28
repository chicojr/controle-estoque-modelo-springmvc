package br.com.ifpe.estoque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() throws SQLException {

	String nomeBanco = "estoque";
	String usuario = "root";
	String senha = "123456";

	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
	    throw new SQLException(e);
	}

	return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nomeBanco, usuario, senha);
    }

}
