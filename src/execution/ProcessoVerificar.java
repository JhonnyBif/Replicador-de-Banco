package execution;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import database.dao.ProcessoDAO;
import database.model.Processo;

public class ProcessoVerificar {
	public static void execute(Connection connectionControle, Boolean realTransfer) throws SQLException {
		
		ProcessoDAO dao = new ProcessoDAO(connectionControle);
		ArrayList<Processo> resultado = dao.selectAll();
		
		for (Processo p : resultado) {
			System.out.println("O processo "+p.getName() +" encontra-se habilitado!!!");
			DirecaoVerificar.execute(connectionControle, p.getId(), realTransfer);
		}
	}
}
