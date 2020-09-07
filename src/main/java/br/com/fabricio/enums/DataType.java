package br.com.fabricio.enums;

public enum DataType {

	SALESMAN("001", "Salesman"), 
	CUSTOMER("002", "Customer"), 
	SALE("003", "Sale"),
	SEPARATOR("ç", "ç");

	private String id;
	private String name;

	private DataType(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
