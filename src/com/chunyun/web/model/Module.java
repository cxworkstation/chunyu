package com.chunyun.web.model;

public class Module {

     private int id;
     private String pic1;
     private String pic2;
     private String pic3;
     private String pic4;
     private String pic5;
     private String pic6;
     private String module;
     
    public Module(){}
    public Module(int id,String pic1,String pic2,String pic3,String pic4,String pic5,String pic6) {
    	this.id=id;
    	this.pic1=pic1;
    	this.pic2=pic2;
    	this.pic3=pic3;
    	this.pic4=pic4;
    	this.pic5=pic5;
    	this.pic6=pic6;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getPic4() {
		return pic4;
	}
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getPic5() {
		return pic5;
	}
	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}
	public String getPic6() {
		return pic6;
	}
	public void setPic6(String pic6) {
		this.pic6 = pic6;
	}
     
     
}
