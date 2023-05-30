package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.Processo;

public class ProcessoDAO {
	
	private String select = "select * from process where active";
	private String selectByOrdem = "select * from tb_processo_tabela where id_processo = ? and habilitado order by ordem";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectByOrdem;
	
	public ProcessoDAO(Connection conn) throws SQLException {
		pstSelect = conn.prepareStatement(select);
		pstSelectByOrdem = conn.prepareStatement(selectByOrdem);
	}
	
	public ArrayList<Processo> selectAll() throws SQLException {
		
		ArrayList<Processo> arlProcessos = new ArrayList<Processo>();
		
		ResultSet resultado = pstSelect.executeQuery();
		while (resultado.next()) {
			Processo p = new Processo();
			p.setId(resultado.getInt("id"));
			p.setName(resultado.getString("name"));
			p.setDescription(resultado.getString("description"));
			p.setActive(resultado.getBoolean("active"));
			arlProcessos.add(p);
		}
		
		return arlProcessos;
		
	}
	
	public ArrayList<Processo> selectByOrdem() throws SQLException {
		
		ArrayList<Processo> arlProcessos = new ArrayList<Processo>();
		
		ResultSet resultado = pstSelectByOrdem.executeQuery();
		while (resultado.next()) {
			Processo p = new Processo();
			p.setId(resultado.getInt("id"));
			p.setName(resultado.getString("nome_processo"));
			p.setDescription(resultado.getString("descricao"));
			p.setActive(resultado.getBoolean("habilitado"));
			arlProcessos.add(p);
		}
		
		return arlProcessos;
		
	}
	
}







