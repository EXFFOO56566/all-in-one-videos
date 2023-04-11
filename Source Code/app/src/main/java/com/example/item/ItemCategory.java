package com.example.item;

public class ItemCategory {
	
	private String CategoryId;
	private String CategoryName;
	private String CategoryImageUrl; 
	
	public String getCategoryId() {
		return CategoryId;
	}
 	public void setCategoryId(String CategoryId) {
		this.CategoryId = CategoryId;
	}

	public String getCategoryName() {
		return CategoryName;
	}
 	public void setCategoryName(String CategoryName) {
		this.CategoryName = CategoryName;
	}
	
	public String getCategoryImageUrl() { return CategoryImageUrl; }
	public void setCategoryImageUrl(String CategoryImageUrl) { this.CategoryImageUrl=CategoryImageUrl; }

}
