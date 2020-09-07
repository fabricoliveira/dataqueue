package br.com.fabricio.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import br.com.fabricio.enums.DataType;
import br.com.fabricio.models.InputDTO;
import br.com.fabricio.models.Item;
import br.com.fabricio.models.OutputDTO;
import br.com.fabricio.models.Sale;

public class SaleAnalytics {

	private String json;

	
	public SaleAnalytics(String json) {
		this.json = json;
	}

	
	public OutputDTO analyzeData() {
		List<String> data = new ArrayList<>();
		List<Sale> sales = new ArrayList<>();
		int salesQuantity = 0;
		int customerQuantity = 0;

		InputDTO inputDTO = new Gson().fromJson(json, InputDTO.class);

		if (!inputDTO.getData().trim().isEmpty()) {
			data = extractData(inputDTO.getData());
		}

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).equalsIgnoreCase(DataType.SALESMAN.getId())) {
				salesQuantity++;
			}

			if (data.get(i).equalsIgnoreCase(DataType.CUSTOMER.getId())) {
				customerQuantity++;
			}

			if (data.get(i).equalsIgnoreCase(DataType.SALE.getId())) {
				Long id = Long.valueOf(data.get(++i));
				String items = data.get(++i);
				String salesman = data.get(++i);
				sales.add(new Sale(id, items, salesman));
			}
		}

		Long mostExpensiveSaleId = findTheMostExpensiveSale(sales);

		String worstSalesman = findTheWorstSalesman(sales);

		return new OutputDTO(inputDTO.getFilename(), customerQuantity, salesQuantity, mostExpensiveSaleId, worstSalesman);
	}

	
	private List<String> extractData(String data) {
		List<String> dataResult = new ArrayList<>();
		int i = 0;
		int initial = 0;
		
		data = data.replace("\t", "\s").replace("\b", "\s").replace("\n", "\s").replace("\r", "\s");
		
		for (i = 0; i < data.length(); i++) {
			if (String.valueOf(data.charAt(i)).equalsIgnoreCase(DataType.SEPARATOR.getName())) {
				String extractedData = data.substring(initial, i).trim();

				if (extractedData.contains("\s") && (extractedData.endsWith(DataType.SALESMAN.getId())
												|| extractedData.endsWith(DataType.CUSTOMER.getId())
												|| extractedData.endsWith(DataType.SALE.getId()))) {
					dataResult.add(extractedData.split("\\s+")[0]);
					dataResult.add(extractedData.split("\\s+")[1]);
				} else {
					dataResult.add(extractedData);
				}

				initial = i + 1;
			}
		}

		if (!dataResult.isEmpty() && !data.endsWith(DataType.SEPARATOR.getName())) {
			String extractedData = data.substring(initial, i).trim();
			int size = dataResult.size() - 1;
			if (!extractedData.trim().isEmpty() && !extractedData.equalsIgnoreCase(dataResult.get(size))) {
				dataResult.add(extractedData);
			}
		}

		return dataResult;
	}

	
	public Long findTheMostExpensiveSale(List<Sale> sales) {
		Map<Long, Float> salesmap = new HashMap<>();

		for (Sale sale : sales) {
			Long saleId = sale.getId();
			Float price = 0f;

			for (Item item : sale.getItems()) {
				price += item.getPrice() * item.getQuantity();
			}

			if (salesmap.containsKey(saleId)) {
				salesmap.put(saleId, salesmap.get(saleId) + price);
			} else {
				salesmap.put(saleId, price);
			}
		}

		return salesmap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
	}

	
	public String findTheWorstSalesman(List<Sale> sales) {
		Map<String, Float> salesmap = new HashMap<>();

		for (Sale sale : sales) {
			String salesman = sale.getSalesman();
			Float sold = 0f;

			for (Item item : sale.getItems()) {
				sold += item.getPrice() * item.getQuantity();
			}

			if (salesmap.containsKey(salesman)) {
				salesmap.put(salesman, salesmap.get(salesman) + sold);
			} else {
				salesmap.put(salesman, sold);
			}
		}

		return salesmap.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();
	}

}
