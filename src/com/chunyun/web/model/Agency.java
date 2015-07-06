package com.chunyun.web.model;
/**
 * 
 * @author 其他经营户表
 *
 */
public class Agency {
private int id;//经营户id
private String name;//经营户名称
private String introduction;//经营户简介
private String manageTime;//经营时长
private String image;//经营户图片
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
public String getIntroduction() {
	return introduction;
}
public void setIntroduction(String introduction) {
	this.introduction = introduction;
}
public String getManageTime() {
	return manageTime;
}
public void setManageTime(String manageTime) {
	this.manageTime = manageTime;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
}
