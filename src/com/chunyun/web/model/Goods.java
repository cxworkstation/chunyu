package com.chunyun.web.model;


/**
 * 
 * @author 商品表
 *
 */
public class Goods {
private int id;//商品ID
private String name;//商品名称

private int hasBuyNum;//已经购买数
private int commentNum;//评论数
private String uploadTime;//商品上传时间
private String introduction;//商品简短介绍
private String image;//商品主要图片地址
private String subImage;//商品附属图片（多张）文件夹地址
private int inArea;//0任何区域，1表示在配送区域内
private int isfree;// 0表示不免费，1表示免费
private int isAgency;//0表示不是代购商品，1表示是代购商品

private int onlyReservation;//0表示不是只能预定，1表示只能预定
private int isCount;//0表示不能打折，1代表可以打折


public Goods() {}

//添加商品时的构造函数
public Goods(String name,String image,int inArea,int isfree,int onlyReservation,String introduction) {
	this.name=name;
	this.image=image;
	this.inArea=inArea;
	this.isfree=isfree;
	this.onlyReservation=onlyReservation;
	this.introduction=introduction;
}

//更新时候的构造函数
public Goods(int id,String name,int hasBuyNum,String introduction,String image,int inArea,
		int isfree,int isAgency,int onlyReservation) {
	this(name,image,inArea,isfree,onlyReservation,introduction);
	this.id=id;
	this.hasBuyNum=hasBuyNum;
	this.isAgency=isAgency;
}

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
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getSubImage() {
	return subImage;
}
public void setSubImage(String subImage) {
	this.subImage = subImage;
}
public int getIsfree() {
	return isfree;
}
public void setIsfree(int isfree) {
	this.isfree = isfree;
}
public int getIsAgency() {
	return isAgency;
}
public void setIsAgency(int isAgency) {
	this.isAgency = isAgency;
}
public int getOnlyReservation() {
	return onlyReservation;
}
public void setOnlyReservation(int onlyReservation) {
	this.onlyReservation = onlyReservation;
}
public int getIsCount() {
	return isCount;
}
public void setIsCount(int isCount) {
	this.isCount = isCount;
}
public int getInArea() {
	return inArea;
}
public void setInArea(int inArea) {
	this.inArea = inArea;
}

public int getHasBuyNum() {
	return hasBuyNum;
}
public void setHasBuyNum(int hasBuyNum) {
	this.hasBuyNum = hasBuyNum;
}
public int getCommentNum() {
	return commentNum;
}
public void setCommentNum(int commentNum) {
	this.commentNum = commentNum;
}
public String getUploadTime() {
	return uploadTime;
}
public void setUploadTime(String uploadTime) {
	this.uploadTime = uploadTime;
}

}
