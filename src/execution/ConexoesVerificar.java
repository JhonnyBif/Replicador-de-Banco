package execution;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import database.dao.ConexoesDAO;
import database.model.Conexao;

public class ConexoesVerificar {
	public static void execute(Connection connectionControle, int id_conexao_origem, int id_conexao_destino, int id_processo) throws SQLException {
		
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
				TabelasProcessar.execute(id_processo);
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
