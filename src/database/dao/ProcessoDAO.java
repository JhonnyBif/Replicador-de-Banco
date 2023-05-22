package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.Processo;

public class ProcessoDAO {
	
	private String select = "select * from tb_processo where habilitado";
	private PreparedStatement pstSelect;
	
	public ProcessoDAO(Connection conn) throws SQLException {
		pstSelect = conn.prepareStatement(select);
	}
	
	public ArrayList<Processo> selectAll() throws SQLException {
		
		ArrayList<Processo> arlProcessos = new ArrayList<Processo>();
		
		ResultSet resultado = pstSelect.executeQuery();
		while (resultado.next()) {
			Processo p = new Processo();
			p.setId(resultado.getInt("id"));
			p.setNome_processo(resultado.getString("nome_processo"));
			p.setDescricao(resultado.getString("descricao"));
			p.setHabilitado(resultado.getBoolean("habilitado"));
			arlProcessos.add(p);
		}
		
		return arlProcessos;
		
	}
	
}







