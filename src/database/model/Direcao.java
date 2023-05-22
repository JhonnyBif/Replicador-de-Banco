package database.model;

public class Direcao {
	private int id;
	private int id_processo;
	private int id_conexao_origem;
	private int id_conexao_destino;
	private boolean habilitado;
	
	public Direcao() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_processo() {
		return id_processo;
	}
	public void setId_processo(int id_processo) {
		this.id_processo = id_processo;
	}
	public int getId_conexao_origem() {
		return id_conexao_origem;
	}
	public void setId_conexao_origem(int id_conexao_origem) {
		this.id_conexao_origem = id_conexao_origem;
	}
	public int getId_conexao_destino() {
		return id_conexao_destino;
	}
	public void setId_conexao_destino(int id_conexao_destino) {
		this.id_conexao_destino = id_conexao_destino;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
}
