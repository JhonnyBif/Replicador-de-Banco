package database.model;

public class Conexoes {
	
	private int id;
	private String endereco_ip;
	private String endereco_porta;
	private String usuario;
	private String senha;
	private String nome_banco;
	private String tipo_banco;
	
	public Conexoes() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndereco_ip() {
		return endereco_ip;
	}

	public void setEndereco_ip(String endereco_ip) {
		this.endereco_ip = endereco_ip;
	}

	public String getEndereco_porta() {
		return endereco_porta;
	}

	public void setEndereco_porta(String endereco_porta) {
		this.endereco_porta = endereco_porta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo_banco() {
		return tipo_banco;
	}

	public void setTipo_banco(String tipo_banco) {
		this.tipo_banco = tipo_banco;
	}
	
	public void setNome_banco(String nome_banco) {
		this.nome_banco = nome_banco;
	}
	
	public String getNome_banco() {
		return nome_banco;
	}

}
