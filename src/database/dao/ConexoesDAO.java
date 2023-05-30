package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.Conexao;

public class ConexoesDAO {
	
	private String select = "select * from tb_conexoes where id = ?";
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











