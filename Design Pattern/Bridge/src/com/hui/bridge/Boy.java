package com.hui.bridge;

public class Boy {
	private String name;	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void purse(MM mm) {
		 Gift g = new WarmGift(new Flower()); //����ά���������
		 give(g, mm);
	}
	
	public void give(Gift g,MM mm) {
		 
	}
	
}
