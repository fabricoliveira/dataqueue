package br.com.fabricio.models;

import java.util.ArrayList;
import java.util.List;

public class Sale {
	
	private Long id;
	private List<Item> items;
	private String salesman;
	
	public Sale(Long id, String items, String salesman) {
		this.id = id;
		setItems(items);
		this.salesman = salesman;
	}

	public Long getId() {
		return id;
	}

	public List<Item> getItems() {
		return items;
	}
	

	public String getSalesman() {
		return salesman;
	}
	
	private void setItems(String items) {
		this.items = new ArrayList<>();
		
		items = items.replaceAll("\\[|\\]", "");
		String[] itemsStringArray = items.split(",");
		
		for (String itemString : itemsStringArray) {
			String[] itemsArray = itemString.split("-");
			if(itemsArray.length == 3) {
				Long id = Long.valueOf(itemsArray[0]);
				Integer quantity = Integer.valueOf(itemsArray[1]);
				Float price = Float.valueOf(itemsArray[2]);
				Item item = new Item(id, quantity, price);
				this.items.add(item);
			}
		}
	}

}
