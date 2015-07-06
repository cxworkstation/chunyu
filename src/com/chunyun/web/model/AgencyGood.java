package com.chunyun.web.model;
/**
 * 
 * @author 代理商商品表
 *
 */
public class AgencyGood {
private int id;//自增id
private int agencyId;//代理商id
private int goodId;//商品id
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getAgencyId() {
	return agencyId;
}
public void setAgencyId(int agencyId) {
	this.agencyId = agencyId;
}
public int getGoodId() {
	return goodId;
}
public void setGoodId(int goodId) {
	this.goodId = goodId;
}

}
