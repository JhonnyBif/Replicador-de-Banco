package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.Conexao;
import database.model.DatabaseKind;

public class ConexoesDAO {
	
	private String select = "select * from connection where id = ?";
	private PreparedStatement pstSelect;
	
	public ConexoesDAO(Connection conn) throws SQLException {
		pstSelect = conn.prepareStatement(select);
	}
	
	public Conexao select(int id) throws SQLException {
		
		Conexao conexoes = null;
		
		pstSelect.setInt(1, id);
		ResultSet resultado = pstSelect.executeQuery();
		
		if (resultado.next()) {
			conexoes = new Conexao();
			conexoes.setId(resultado.getInt("id"));
			conexoes.setAddress(resultado.getString("hostname"));
			conexoes.setPort(resultado.getInt("port"));
			conexoes.setUser(resultado.getString("user"));
			conexoes.setPassword(resultado.getString("password"));
			conexoes.setDatabase(resultado.getString("database"));
			conexoes.setDatabaseKindId(DatabaseKind.valueOf(resultado.getString("database_kind_id")));
		}
		
		return conexoes;
		
	}

}











