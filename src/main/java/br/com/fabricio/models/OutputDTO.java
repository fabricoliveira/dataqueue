package br.com.fabricio.models;

public class OutputDTO {

	private Integer customerQuantity;
	private Integer salesmanQuantity;
	private Long mostExpensiveSaleId;
	private String worstSalesman;
	private String filename;

	public OutputDTO(String filename, Integer customerQuantity, Integer salesmanQuantity, Long mostExpensiveSaleId, String worstSalesman) {
		this.filename = filename;
		this.customerQuantity = customerQuantity;
		this.salesmanQuantity = salesmanQuantity;
		this.mostExpensiveSaleId = mostExpensiveSaleId;
		this.worstSalesman = worstSalesman;
	}
	
	public String getFilename() {
		return filename;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(String.format("Quantidade de clientes no arquivo de entrada: %d", customerQuantity))
			  .append("\n")
			  .append(String.format("Quantidade de vendedores no arquivo de entrada: %d", salesmanQuantity))
			  .append("\n")
			  .append(String.format("ID da venda mais cara: %d", mostExpensiveSaleId))
			  .append("\n")
			  .append(String.format("O pior vendedor: %s", worstSalesman));
		return output.toString();
	}

}