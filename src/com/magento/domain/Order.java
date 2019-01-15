package com.magento.domain;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
			
		private static final long serialVersionUID = -2312322006857066070L;

		private int id;
		private int store_id;
		private List<Line> lines;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getStore_id() {
			return store_id;
		}
		public void setStore_id(int store_id) {
			this.store_id = store_id;
		}
		public List<Line> getLines() {
			return lines;
		}
		public void setLines(List<Line> lines) {
			this.lines = lines;
		}
		public boolean isLinesNumberSequential() {
			boolean isLineNumbersSequential = true;
			for (int i = 0; i < this.lines.size() && isLineNumbersSequential; i++) {				
				if (this.lines.get(i).getLine_number() != (i+1))
					isLineNumbersSequential = false;
			}
			return isLineNumbersSequential;
		}
}


