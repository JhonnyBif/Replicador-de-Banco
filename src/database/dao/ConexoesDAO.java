package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.Conexoes;

public class ConexoesDAO {
	
	private String select = "select * from tb_conexoes where id = ?";
	private PreparedStatement pstSelect;
	
	public ConexoesDAO(Connection conn) throws SQLException {
		pstSelect = conn.prepareStatement(select);
	}
	
	public Conexoes select(int id) throws SQLException {
		
		Conexoes conexoes = null;
		
		pstSelect.setInt(1, id);
		ResultSet resultado = pstSelect.executeQuery();
		
		if (resultado.next()) {
			conexoes = new Conexoes();
			conexoes.setId(resultado.getInt("id"));
			conexoes.setEndereco_ip(resultado.getString("endereco_ip"));
			conexoes.setEndereco_porta(resultado.getString("endereco_porta"));
			conexoes.setUsuario(resultado.getString("usuario"));
			conexoes.setSenha(resultado.getString("senha"));
			conexoes.setNome_banco(resultado.getString("nome_banco"));
			conexoes.setTipo_banco(resultado.getString("tipo_banco"));
		}
		
		return conexoes;
		
	}

}











