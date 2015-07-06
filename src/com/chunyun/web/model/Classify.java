package com.chunyun.web.model;
/**
 * 
 * @author 分类表
 *
 */
public class Classify {
private int id;//分类id
private String name;//分类名称
private int parentId;//父分类ID
private int level;//分类级别（共3级）
private int isCount;//0代表不能此分类不能打折，1代表可以
private int onlyReservation;//0表示不是只能预定，1表示只能预定
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getParentId() {
	return parentId;
}
public void setParentId(int parentId) {
	this.parentId = parentId;
}

public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public int getIsCount() {
	return isCount;
}
public void setIsCount(int isCount) {
	this.isCount = isCount;
}
public int getOnlyReservation() {
	return onlyReservation;
}
public void setOnlyReservation(int onlyReservation) {
	this.onlyReservation = onlyReservation;
}

}
