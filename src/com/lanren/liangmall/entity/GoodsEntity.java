package com.lanren.liangmall.entity;

import android.graphics.drawable.Drawable;

public class GoodsEntity {

	private String sid;
	private String sClassIfy;
	private String sName;
	private String sPrice;
	private String sInventory;
	private String sAddr;
	private String sSeller;
	private String sSales;
	private Drawable sImgas;
	private String sColor;
	private String sType;
	private String sDrawable;

	public Drawable getsImgas() {
		return sImgas;
	}

	public void setsImgas(Drawable sImgas) {
		this.sImgas = sImgas;
	}

	public String getsDrawable() {
		return sDrawable;
	}

	public void setsDrawable(String sDrawable) {
		this.sDrawable = sDrawable;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getsClassIfy() {
		return sClassIfy;
	}

	public void setsClassIfy(String sClassIfy) {
		this.sClassIfy = sClassIfy;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPrice() {
		return sPrice;
	}

	public void setsPrice(String sPrice) {
		this.sPrice = sPrice;
	}

	public String getsInventory() {
		return sInventory;
	}

	public void setsInventory(String sInventory) {
		this.sInventory = sInventory;
	}

	public String getsAddr() {
		return sAddr;
	}

	public void setsAddr(String sAddr) {
		this.sAddr = sAddr;
	}

	public String getsSeller() {
		return sSeller;
	}

	public void setsSeller(String sSeller) {
		this.sSeller = sSeller;
	}

	public String getsSales() {
		return sSales;
	}

	public void setsSales(String sSales) {
		this.sSales = sSales;
	}

	public String getsColor() {
		return sColor;
	}

	public void setsColor(String sColor) {
		this.sColor = sColor;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	@Override
	public String toString() {
		String str = "{sid=" + sid + ";sClassIfy=" + sClassIfy + ";sName=" + sName + ";sPrice=" + sPrice
				+ ";sInventory=" + sInventory + ";sAddr=" + sAddr + ";sSeller=" + sSeller + ";sSales=" + sSales
				 +";sColor=" + sColor + ";sType=" + sType + "}";
		return str;
	}
}
