package br.com.fabricio.models;

public class InputDTO {

	private String filename;
	private String data;

	public InputDTO(String filename, String data) {
		this.filename = filename;
		this.data = data;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
