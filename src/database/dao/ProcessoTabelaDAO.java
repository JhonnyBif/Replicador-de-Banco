package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.ProcessTable;

public class ProcessoTabelaDAO {
	
	private String select = "select * from process_table";
	private String selectWhereRealTransfer = "select * from process_table where real_transfer";
	private String selectByOrdem = "select * from process_table where process_id = ? and real_transfer order by order";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectWhereRealTransfer;
	private PreparedStatement pstSelectByOrdem;
	
	public ProcessoTabelaDAO(Connection conn) throws SQLException {
		pstSelect = conn.prepareStatement(select);
		pstSelectByOrdem = conn.prepareStatement(selectByOrdem);
	}
	
	public ArrayList<ProcessTable> selectAll(Boolean whereRealTransfer) throws SQLException {
		
		ArrayList<ProcessTable> arlProcessos = new ArrayList<ProcessTable>();
		
		ResultSet resultado = whereRealTransfer ? pstSelectWhereRealTransfer.executeQuery() : pstSelect.executeQuery();
		while (resultado.next()) {
			ProcessTable p = new ProcessTable();
			p.setId(resultado.getInt("id"));
			p.setProcessId(resultado.getInt("process_id"));
			p.setOriginTableName(resultado.getString("origin_table_name"));
			p.setDestinyTableName(resultado.getString("destiny_table_name"));
			p.setOrder(resultado.getInt("order"));
			p.setCondition(resultado.getString("condition"));
			p.setRealTransfer(resultado.getBoolean("real_transfer"));
			arlProcessos.add(p);
		}
		
		return arlProcessos;
		
	}
	
	public ArrayList<ProcessTable> selectByOrdem() throws SQLException {
		
		ArrayList<ProcessTable> arlProcessos = new ArrayList<ProcessTable>();
		
		ResultSet resultado = pstSelectByOrdem.executeQuery();
		while (resultado.next()) {
			ProcessTable p = new ProcessTable();
			p.setId(resultado.getInt("id"));
			p.setProcessId(resultado.getInt("process_id"));
			p.setOriginTableName(resultado.getString("origin_table_name"));
			p.setDestinyTableName(resultado.getString("destiny_table_name"));
			p.setOrder(resultado.getInt("order"));
			p.setCondition(resultado.getString("condition"));
			p.setRealTransfer(resultado.getBoolean("real_transfer"));
			arlProcessos.add(p);
		}
		
		return arlProcessos;
		
	}
	
}







