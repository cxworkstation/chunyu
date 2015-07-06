package com.chunyun.web.model;
/**
 * 
 * @author 分类商品表
 *
 */
public class ClassifyGood {
private int id;//自增id
private int classify;//分类id
private int goodId;//商品id
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getClassify() {
	return classify;
}
public void setClassify(int classify) {
	this.classify = classify;
}
public int getGoodId() {
	return goodId;
}
public void setGoodId(int goodId) {
	this.goodId = goodId;
}

}
