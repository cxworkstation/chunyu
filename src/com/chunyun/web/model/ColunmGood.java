package com.chunyun.web.model;
/**
 * 
 * @author 类目和商品表
 *
 */
public class ColunmGood {
private int id;//自增id
private int columnId;//类目id
private int goodId;//商品id
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getColumnId() {
	return columnId;
}
public void setColumnId(int columnId) {
	this.columnId = columnId;
}
public int getGoodId() {
	return goodId;
}
public void setGoodId(int goodId) {
	this.goodId = goodId;
}
}
