package com.lanren.liangmall.entity;

import android.R.integer;

public class CommodityEntity {
	Integer id;
	String name;
	Integer price;
	String sellername;
	Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getSellername() {
		return sellername;
	}
	public void setSellername(String sellername) {
		this.sellername = sellername;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
