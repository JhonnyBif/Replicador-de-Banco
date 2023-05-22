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
	
	private Connection connectionOrigem;
	private Connection connectionDestino;
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
												"replicador", 
												"postgres", 
												"12345", 
												ConnectionFactory.TIPO_BANCO_POSTGRES
											);
						
						if (connectionControle != null) {
							processoVerificar();	
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
	
	private void processoVerificar() throws SQLException {
		
		ProcessoDAO dao = new ProcessoDAO(connectionControle);
		ArrayList<Processo> resultado = dao.selectAll();
		
		for (Processo p : resultado) {
			System.out.println("O processo "+p.getNome_processo()+" encontra-se habilitado!!!");
			direcaoVerificar(p.getId());
		}
	}
	
	private void direcaoVerificar(int idProcesso) throws SQLException {
		
		DirecaoDAO dao = new DirecaoDAO(connectionControle);
		ArrayList<Direcao> resultado = dao.select(idProcesso);
		
		for (Direcao d : resultado) {
			conexoesVerificar(d.getId_conexao_origem(), d.getId_conexao_destino(), idProcesso);
		}
	}
	
	private void conexoesVerificar(int id_conexao_origem, int id_conexao_destino, int id_processo) throws SQLException {
		
		ConexoesDAO dao = new ConexoesDAO(connectionControle);
		Conexoes conexoes = dao.select(id_conexao_origem);
		
		// Faz a conexão no banco de controle ...
		connectionOrigem = ConnectionFactory.getConnection
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
			connectionDestino = ConnectionFactory.getConnection
					(
						conexoes.getEndereco_ip(), 
						conexoes.getEndereco_porta(),
						conexoes.getNome_banco(), 
						conexoes.getUsuario(), 
						conexoes.getSenha(), 
						conexoes.getTipo_banco()
					);
			if (connectionDestino != null)	{
				tabelasProcessar(id_processo);
			}
			else {
				System.out.println("Não foi possível conectar no banco destino: "+conexoes.getEndereco_ip()+":"+conexoes.getEndereco_porta());
			}
		}
		else {
			System.out.println("Não foi possível conectar no banco origem: "+conexoes.getEndereco_ip()+":"+conexoes.getEndereco_porta());
		}
	}
	
	private void tabelasProcessar(int id_processo) {
		
		// select * from tb_processo_tabela where id_processo = ? and habilitado order by ordem;
		
	}
	
	public static void main(String[] args) {
		try {
			new ReplicacaoExecutar(45000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}











