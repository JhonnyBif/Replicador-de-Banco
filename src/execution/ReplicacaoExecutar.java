package execution;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectionFactory;
import database.dao.ConexoesDAO;
import database.dao.DirecaoDAO;
import database.dao.ProcessoDAO;
import database.model.Conexao;
import database.model.Direcao;
import database.model.Processo;

public class ReplicacaoExecutar {
		
	public static void execute(long sleepReplication, Boolean realTransfer) throws SQLException {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				try {
					while (!Thread.currentThread().isInterrupted()) {
						
						// Faz a conexão no banco de controle ...
						Connection connectionControle = ConnectionFactory.getConnection
											(
												"localhost", 
												"5432",
												"control", 
												"postgres", 
												"admin", 
												ConnectionFactory.TIPO_BANCO_POSTGRES
											);
						
						if (connectionControle != null) {
							ProcessoVerificar.execute(connectionControle, realTransfer);	
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

}











