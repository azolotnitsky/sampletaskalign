package com.aligntechnology.sampletask.model;
	
public class Product {

	private String name;

	private String brand;

	private double price;

	private int quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product()
	{
		name = "";
		brand = "";
		price = 0;
		quantity = 0;
	}
	
	public Product(String name, String brand, double price, int quantity){
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
	}
	
	@Override
	public int hashCode() {
		int result = (int)(((long)name.hashCode() >>> 32 + brand.hashCode()) % Integer.MAX_VALUE);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name != other.name || brand != other.brand)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", brand=" + brand + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}


}
