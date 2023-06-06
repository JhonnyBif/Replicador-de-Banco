package execution;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import database.dao.DirecaoDAO;
import database.model.Direcao;

public class DirecaoVerificar {
	public static void execute(Connection connectionControle, int idProcesso, Boolean realTransfer) throws SQLException {
		
		DirecaoDAO dao = new DirecaoDAO(connectionControle);
		ArrayList<Direcao> resultado = dao.select(idProcesso);
		
		for (Direcao d : resultado) {
			ConexoesVerificar.execute(connectionControle, d.getOriginConnectionId(), d.getDestinyConnectionId(), idProcesso, realTransfer);
		}
	}
}
