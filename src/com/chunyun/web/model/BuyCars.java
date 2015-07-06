package com.chunyun.web.model;


/**
 * 
 * @author 购物车表
 *
 */
public class BuyCars {
private int id;//自增id
private int number;//商品数量
private int goodId;//商品id
private float price;//商品价格
private int userId;//用户id
private int isPay;//0代表未支付，1代表支付
private float counts;//折扣
private String puttime;//提交到购物车的时间
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public int getGoodId() {
	return goodId;
}
public void setGoodId(int goodId) {
	this.goodId = goodId;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getIsPay() {
	return isPay;
}
public void setIsPay(int isPay) {
	this.isPay = isPay;
}
public float getCounts() {
	return counts;
}
public void setCounts(float counts) {
	this.counts = counts;
}
public String getPuttime() {
	return puttime;
}
public void setPuttime(String puttime) {
	this.puttime = puttime;
}



}
