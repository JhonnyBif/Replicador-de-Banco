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
import graphic.Replicacao;

public class ReplicacaoExecutar {
	private static Replicacao rep;
	private static Boolean first;

    public ReplicacaoExecutar(Replicacao replicacao) {
        this.rep = replicacao;
    }
		
	public static void execute(long sleepReplication, Boolean realTransfer) throws SQLException {
		first = false;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				rep.startProgress();
				try {
					while (!Thread.currentThread().isInterrupted()) {
						
						// Faz a conexão no banco de controle ...
						Connection connectionControle = ConnectionFactory.getConnection
											(
												"localhost", 
												"5432",
												"control", 
												"postgres", 
												"12345", 
												ConnectionFactory.TIPO_BANCO_POSTGRES
											);
						
						if (connectionControle != null) {
							if(first == true) {
							rep.startProgress();
							}
							ProcessoVerificar.execute(connectionControle, realTransfer);
							first = true;
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











