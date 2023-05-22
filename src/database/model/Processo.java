package database.model;

public class Processo {

	private int id;
	private String nome_processo;
	private String descricao;
	private boolean habilitado;
	
	public Processo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_processo() {
		return nome_processo;
	}

	public void setNome_processo(String nome_processo) {
		this.nome_processo = nome_processo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
	
	
}
