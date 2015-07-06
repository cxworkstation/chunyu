package com.chunyun.web.model;
/**
 * 
 * @author 商品价格表（根据商品种类尺码制定价格）
 *
 */
public class GoodPrice {
private int id;//自增id
private int goodId;//商品id
private String size;//设置尺码（如：大小，如：200ml,50ml;如：250ml醇香，250ml酒香）
private float price;//上面尺码所对应的单价
private float weight;//货重
private int isShow;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getGoodId() {
	return goodId;
}
public void setGoodId(int goodId) {
	this.goodId = goodId;
}
public String getSize() {
	return size;
}
public void setSize(String size) {
	this.size = size;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public float getWeight() {
	return weight;
}
public void setWeight(float weight) {
	this.weight = weight;
}
public int getIsShow() {
	return isShow;
}
public void setIsShow(int isShow) {
	this.isShow = isShow;
}


}
