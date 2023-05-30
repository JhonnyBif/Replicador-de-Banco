package database.model;

public class Conexao {
	
	private int id;
	private String hostname;
	private Integer port;
	private String user;
	private String password;
	private String database;
	private DatabaseKind databaseKindId;
	
	public Conexao() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return hostname;
	}

	public void setAddress(String address) {
		this.hostname = address;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public DatabaseKind getDatabaseKindId() {
		return databaseKindId;
	}

	public void setDatabaseKindId(DatabaseKind databaseKindId) {
		this.databaseKindId = databaseKindId;
	}

}
