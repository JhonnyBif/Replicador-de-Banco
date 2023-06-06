package execution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import database.dao.ProcessoTabelaDAO;
import database.model.ProcessTable;

public class TabelasProcessar {
	public static void execute(int id_processo, Connection control, Connection origin, Connection destiny, Boolean realTransfer) throws SQLException {
		
		System.out.println(id_processo);
		
		ProcessoTabelaDAO dao = new ProcessoTabelaDAO(control);
		List<ProcessTable> m = dao.selectAll(realTransfer);
		
		for (ProcessTable processTable : m) {
			System.out.println("tabela: " + processTable);

			String selectQuery = processTable.getCondition() == null || processTable.getCondition().isEmpty() 
							? "SELECT * FROM "+processTable.getOriginTableName() 
							: "SELECT * FROM "+processTable.getOriginTableName()+" where id > "+processTable.getCondition();
			Statement stmt = origin.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);

//			Statement stmt2 = destiny.createStatement();
//			ResultSet rs2 = stmt2.executeQuery(selectQuery);
			
			// ------------------------------ DELETE
			
			String sqlDelete = "DELETE FROM " + processTable.getDestinyTableName();
			PreparedStatement psDelete = destiny.prepareStatement(sqlDelete);
			psDelete.executeUpdate();
			
			// ------------------------------ INSERT

			String sqlInsert = "INSERT INTO "+processTable.getDestinyTableName()+" (";
			String sqlValues = "VALUES (";

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
			    sqlInsert += rsmd.getColumnName(i);
			    sqlValues += "?";
			    if (i < columnCount) {
			        sqlInsert += ", ";
			        sqlValues += ", ";
			    }
			}

			sqlInsert += ") ";
			sqlValues += ") ";

			String InsertQuery = sqlInsert + sqlValues;
			PreparedStatement pstmt = destiny.prepareStatement(InsertQuery);

			int ultimoIdInserido = 0;
			
			while(rs.next()) {
			    for (int i = 1; i <= columnCount; i++) {
			        Object value = rs.getObject(i);
			        pstmt.setObject(i, value);
			    }
			    try {
			    	pstmt.executeUpdate();
				    ultimoIdInserido = rs.getInt("id");
			    } catch (SQLException ex) {
			    	System.out.println("id já inserido");
			    }
			}
			
			PreparedStatement ps = control.prepareStatement("update process_table set condition = ?");
			ps.setString(1, String.valueOf(ultimoIdInserido));
			
			// --------------------- UPDATE
			
//			rs = stmt.executeQuery(selectQuery);
//			
//			String sqlUpdate = "UPDATE "+processTable.getDestinyTableName()+" SET ";
//
//			for (int i = 1; i <= columnCount; i++) {
//			    sqlUpdate += rsmd.getColumnName(i) + "=?";
//			    if (i < columnCount) {
//			        sqlUpdate += ", ";
//			    }
//			}
//
//			sqlUpdate += " WHERE id=?";
//
//			String UpdateQuery = sqlUpdate;
//			PreparedStatement pstmtUpdate = destiny.prepareStatement(UpdateQuery);
//			
//			while(rs.next()) {
//			    for (int i = 1; i <= columnCount; i++) {
//			        Object value = rs.getObject(i);
//			        pstmtUpdate.setObject(i, value);
//			    }
//			    pstmtUpdate.setObject(columnCount + 1, rs.getObject("id"));
//			    pstmtUpdate.executeUpdate();
//			}
			
			
		}		
	}
}
