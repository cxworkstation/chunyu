package com.chunyun.web.model;
/**
 * 
 * @author 商品详情编辑器表
 *
 */
public class Details {
private int id;//自增id
private int goodId;//商品id
private String details;//商品详情的编辑器字段
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
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}

}
