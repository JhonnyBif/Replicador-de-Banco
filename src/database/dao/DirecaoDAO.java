package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.Direcao;

public class DirecaoDAO {
	
	private String select = "select * from direction where process_id = ? and active";
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
			direcao.setProcessId(resultado.getInt("process_id"));
			direcao.setOriginConnectionId(resultado.getInt("origin_connection_id"));
			direcao.setDestinyConnectionId(resultado.getInt("destiny_connection_id"));
			direcao.setActive(resultado.getBoolean("active"));
			listaDirecao.add(direcao);
		}
		
		return listaDirecao;
	}

}










