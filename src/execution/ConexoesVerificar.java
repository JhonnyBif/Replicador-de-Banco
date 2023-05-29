package execution;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import database.dao.ConexoesDAO;
import database.model.Conexoes;

public class ConexoesVerificar {
	public static void execute(Connection connectionControle, int id_conexao_origem, int id_conexao_destino, int id_processo) throws SQLException {
		
		ConexoesDAO dao = new ConexoesDAO(connectionControle);
		Conexoes conexoes = dao.select(id_conexao_origem);
		
		// Faz a conexão no banco de controle ...
		Connection connectionOrigem = ConnectionFactory.getConnection
							(
								conexoes.getEndereco_ip(), 
								conexoes.getEndereco_porta(),
								conexoes.getNome_banco(), 
								conexoes.getUsuario(), 
								conexoes.getSenha(), 
								conexoes.getTipo_banco()
							);
		if (connectionOrigem != null) {
			
			conexoes = dao.select(id_conexao_destino);
			Connection connectionDestino = ConnectionFactory.getConnection
					(
						conexoes.getEndereco_ip(), 
						conexoes.getEndereco_porta(),
						conexoes.getNome_banco(), 
						conexoes.getUsuario(), 
						conexoes.getSenha(), 
						conexoes.getTipo_banco()
					);
			if (connectionDestino != null)	{
				TabelasProcessar.execute(id_processo);
			}
			else {
				System.out.println("Não foi possível conectar no banco destino: "+conexoes.getEndereco_ip()+":"+conexoes.getEndereco_porta());
			}
		}
		else {
			System.out.println("Não foi possível conectar no banco origem: "+conexoes.getEndereco_ip()+":"+conexoes.getEndereco_porta());
		}
	}
}
