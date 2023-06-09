package execution;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import database.dao.ConexoesDAO;
import database.model.Conexao;
import main.Main;

public class ConexoesVerificar {
	public static void execute(Connection connectionControle, int id_conexao_origem, int id_conexao_destino, int id_processo, Boolean realTransfer) throws SQLException {
		
		ConexoesDAO dao = new ConexoesDAO(connectionControle);
		Conexao conexoes = dao.select(id_conexao_origem);
		
		
		// Faz a conexão no banco de controle ...
		Connection connectionOrigem = ConnectionFactory.getConnection
							(
								conexoes.getAddress(), 
								conexoes.getPort().toString(),
								conexoes.getDatabase(), 
								conexoes.getUser(), 
								conexoes.getPassword(), 
								conexoes.getDatabaseKindId().name()
							);
		
		if (connectionOrigem != null) {
			System.out.println("connected to origin");
			Main.rep.setDataBaseOrigem(
					conexoes.getAddress()+ ":" + conexoes.getPort().toString()+":" +conexoes.getDatabase()
					);
			conexoes = dao.select(id_conexao_destino);
			Connection connectionDestino = ConnectionFactory.getConnection
					(
						conexoes.getAddress(), 
						conexoes.getPort().toString(),
						conexoes.getDatabase(), 
						conexoes.getUser(), 
						conexoes.getPassword(), 
						conexoes.getDatabaseKindId().name()
					);
			if (connectionDestino != null)	{
				System.out.println("connected to destiny");
				Main.rep.setDataBaseDestino(
						conexoes.getAddress()+ ":" +conexoes.getPort().toString()+":" +conexoes.getDatabase()
						);
				TabelasProcessar.execute(id_processo, connectionControle, connectionOrigem, connectionDestino, realTransfer);
			}
			else {
				System.out.println("Não foi possível conectar no banco destino: "+conexoes.getAddress()+":"+conexoes.getPort());
			}
		}
		else {
			System.out.println("Não foi possível conectar no banco origem: "+conexoes.getAddress()+":"+conexoes.getPort());
		}
	}
}
