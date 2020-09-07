package br.com.fabricio.models;

public class Item {
	
	private Long id;
	private Integer quantity;
	private Float price;
	
	public Item(Long id, Integer quantity, Float price) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Float getPrice() {
		return price;
	}
	
}
