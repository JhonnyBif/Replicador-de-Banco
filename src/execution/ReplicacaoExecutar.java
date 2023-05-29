package execution;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectionFactory;
import database.dao.ConexoesDAO;
import database.dao.DirecaoDAO;
import database.dao.ProcessoDAO;
import database.model.Conexoes;
import database.model.Direcao;
import database.model.Processo;

public class ReplicacaoExecutar {
	
	private Connection connectionControle;
	
	public ReplicacaoExecutar(long sleepReplication) throws SQLException {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				try {
					while (!Thread.currentThread().isInterrupted()) {
						
						// Faz a conexão no banco de controle ...
						connectionControle = ConnectionFactory.getConnection
											(
												"localhost", 
												"5432",
												"postgres", 
												"postgres", 
												"admin", 
												ConnectionFactory.TIPO_BANCO_POSTGRES
											);
						
						if (connectionControle != null) {
							ProcessoVerificar.execute(connectionControle);	
						}
						else {
							System.out.println("Não foi possível conectar no banco do replicador");
						}
						
						Thread.sleep(sleepReplication);
					}	
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();		
	}
	
	public static void main(String[] args) {
		try {
			new ReplicacaoExecutar(45000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}











