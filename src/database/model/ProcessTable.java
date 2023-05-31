package database.model;

public class ProcessTable {
	private Integer id;
	private Integer processId;
	private String originTableName;
	private String destinyTableName;
	private Integer order;
	private String condition;
	private Boolean realTransfer;
	
	public ProcessTable() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public String getOriginTableName() {
		return originTableName;
	}

	public void setOriginTableName(String originTableName) {
		this.originTableName = originTableName;
	}

	public String getDestinyTableName() {
		return destinyTableName;
	}

	public void setDestinyTableName(String destinyTableName) {
		this.destinyTableName = destinyTableName;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Boolean getRealTransfer() {
		return realTransfer;
	}

	public void setRealTransfer(Boolean realTransfer) {
		this.realTransfer = realTransfer;
	}
}
