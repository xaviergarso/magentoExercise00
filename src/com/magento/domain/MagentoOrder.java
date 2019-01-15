package com.magento.domain;

import java.io.Serializable;
import java.util.List;

public class MagentoOrder implements Serializable {
	
	private static final long serialVersionUID = -6144327156942930067L;
	
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public boolean validationLineNumbers() {
		boolean validationLineNumbers = true;
		for (int i = 0; i < this.orders.size() && validationLineNumbers; i++) 
			validationLineNumbers = orders.get(i).isLinesNumberSequential();		
		
		return validationLineNumbers;
	}
	
	public boolean validationIsUnicStoreOrder() {
		boolean isUnicStoreOrder = true;
		Order aux;
		for (int i = 0; i < this.orders.size() && isUnicStoreOrder; i++) {
			aux = (Order) orders.get(i);
			for (int j = i + 1 ; j <= this.orders.size()-1 && isUnicStoreOrder; j++) {				
				if (aux.getId() == orders.get(j).getId() && aux.getStore_id() == orders.get(j).getStore_id()) 
					isUnicStoreOrder = false;
			}
		}
		return isUnicStoreOrder;
	}
	
	public String detailOrders() {
		String body ="";
		for (int i = 0; i < this.orders.size(); i++) {
			body += "The Store id " + orders.get(i).getStore_id() + " contains this lines:<br>";
			for (int j = 0; j < orders.get(i).getLines().size(); j++) {
				body += "   Line Number " + orders.get(i).getLines().get(j).getLine_number() + "line sku: " + orders.get(i).getLines().get(j).getSku() + "<br>";
			}
			body += "<br>";
		}
		return body;
	}
}
