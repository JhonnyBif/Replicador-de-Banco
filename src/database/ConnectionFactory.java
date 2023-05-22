package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static final String TIPO_BANCO_POSTGRES = "postgres";
	public static final String TIPO_BANCO_MYSQL = "mysql";

	public static Connection getConnection
								(
									final String ip, 
									final String porta, 
									final String nomeBanco,
									final String usuario, 
									final String senha,
									final String tipoBanco
								)  throws SQLException {
		return  tipoBanco.equalsIgnoreCase("postgres")
					? DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + porta + "/" + nomeBanco, usuario, senha)
					: DriverManager.getConnection("jdbc:mysql://" + ip + ":" + porta + "/" + nomeBanco, usuario, senha);
	}
}






