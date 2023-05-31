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
	public static void execute(int id_processo, Connection control, Connection origin, Connection destiny) throws SQLException {
		
		System.out.println(id_processo);
		
		ProcessoTabelaDAO dao = new ProcessoTabelaDAO(control);
		List<ProcessTable> m = dao.selectAll();
		
		for (ProcessTable tabela : m) {

			String sql = tabela.getCondition().isEmpty() 
							? "SELECT * FROM "+tabela.getOriginTableName() 
							: "SELECT * FROM "+tabela.getOriginTableName()+" where id > "+tabela.getCondition();
			Statement stmt = origin.createStatement();
			ResultSet rs = stmt.executeQuery(sql);


			String sqlInsert = "INSERT INTO "+tabela.getDestinyTableName()+" (";
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

			String sql2 = sqlInsert + sqlValues;
			PreparedStatement pstmt = destiny.prepareStatement(sql2);

			int ultimoIdInserido = 0;
			
			while(rs.next()) {
			    for (int i = 1; i <= columnCount; i++) {
			        Object value = rs.getObject(i);
			        pstmt.setObject(i, value);
			    }
			    pstmt.executeUpdate();
			}
			ultimoIdInserido = rs.getInt("id");
			
			// Fazer um update setando a condicao.
			
			
		}		
	}
}
