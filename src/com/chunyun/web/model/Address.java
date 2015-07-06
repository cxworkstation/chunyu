package com.chunyun.web.model;
/**
 * 
 * @author 地址表
 *
 */
public class Address {
private int id;//自增id
private int userId;//用户id
private String address;//用户地址（可多个）
private int isDefault;//0表示不是默认地址，1表示是默认地址
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getIsDefault() {
	return isDefault;
}
public void setIsDefault(int isDefault) {
	this.isDefault = isDefault;
}

}
