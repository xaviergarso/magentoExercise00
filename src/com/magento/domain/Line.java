package com.magento.domain;

import java.io.Serializable;

public class Line implements Serializable {
	
	private static final long serialVersionUID = -5192382691607959044L;
	
	private int line_number;
	private String sku;
	
	public int getLine_number() {
		return line_number;
	}
	public void setLine_number(int line_number) {
		this.line_number = line_number;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	

}
