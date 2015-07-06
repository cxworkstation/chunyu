package com.chunyun.web.model;

/**
 * 
 * @author 订单表
 *
 */
public class Orders {
private int id;
private String orderId;//订单号
private String address;
private String telephone;
private int userId;
private int payMethod;//支付方式：0代表货到付款，1代表在线支付
private int isBulk;
private String message;
private float totalMoney;
private int state;
private String goodsJson;
private String orderTime;
private float fee;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getOrderId() {
	return orderId;
}
public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getPayMethod() {
	return payMethod;
}
public void setPayMethod(int payMethod) {
	this.payMethod = payMethod;
}
public int getIsBulk() {
	return isBulk;
}
public void setIsBulk(int isBulk) {
	this.isBulk = isBulk;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public float getTotalMoney() {
	return totalMoney;
}
public void setTotalMoney(float totalMoney) {
	this.totalMoney = totalMoney;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public String getGoodsJson() {
	return goodsJson;
}
public void setGoodsJson(String goodsJson) {
	this.goodsJson = goodsJson;
}
public String getOrderTime() {
	return orderTime;
}
public void setOrderTime(String orderTime) {
	this.orderTime = orderTime;
}
public float getFee() {
	return fee;
}
public void setFee(float fee) {
	this.fee = fee;
}
}
//json串格式：[{"id": 23,"name":"芒果","price": "1.2","number": 2,"counts": "0.85"},{"id": 23,"name": "芒果","price": "3.8","number": 1,"counts": "0.9"}]
