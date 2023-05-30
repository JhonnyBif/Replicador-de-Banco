package database.model;

public class Direcao {
	private int id;
	private int processId;
	private int originConnectionId;
	private int destinyConnectionId;
	private boolean active;
	
	public Direcao() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getOriginConnectionId() {
		return originConnectionId;
	}

	public void setOriginConnectionId(int originConnectionId) {
		this.originConnectionId = originConnectionId;
	}

	public int getDestinyConnectionId() {
		return destinyConnectionId;
	}

	public void setDestinyConnectionId(int destinyConnectionId) {
		this.destinyConnectionId = destinyConnectionId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
