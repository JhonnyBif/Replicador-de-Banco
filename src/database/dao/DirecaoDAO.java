package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.Direcao;

public class DirecaoDAO {
	
	private String select = "select * from tb_direcao where id_processo = ? and habilitado";
	private PreparedStatement pstSelect;
	
	public DirecaoDAO(Connection conn) throws SQLException {
		pstSelect = conn.prepareStatement(select);
	}
	
	public ArrayList<Direcao> select(int id_processo) throws SQLException {
		
		ArrayList<Direcao> listaDirecao = new ArrayList<Direcao>();
		pstSelect.setInt(1, id_processo);
		
		ResultSet resultado = pstSelect.executeQuery();
		while (resultado.next()) {
			Direcao direcao = new Direcao();
			direcao.setId(resultado.getInt("id"));
			direcao.setId_processo(resultado.getInt("id_processo"));
			direcao.setId_conexao_origem(resultado.getInt("id_conexao_origem"));
			direcao.setId_conexao_destino(resultado.getInt("id_conexao_destino"));
			direcao.setHabilitado(resultado.getBoolean("habilitado"));
			listaDirecao.add(direcao);
		}
		
		return listaDirecao;
	}

}










