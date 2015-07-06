package com.chunyun.web.model;
/**
 * 
 * @author 用户表
 *
 */
public class Users {
private int id;//用户id
private String username;//用户名
private String password;//密码
private String telephone;//电话号码
private String email;//邮箱
private int isBlackUser;//0表示不是黑名单用户，1表示是黑名单用户
private String registTime;//注册时间
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getIsBlackUser() {
	return isBlackUser;
}
public void setIsBlackUser(int isBlackUser) {
	this.isBlackUser = isBlackUser;
}
public String getRegistTime() {
	return registTime;
}
public void setRegistTime(String registTime) {
	this.registTime = registTime;
}

}
