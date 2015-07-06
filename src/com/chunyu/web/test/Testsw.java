package com.chunyu.web.test;

import java.io.File;

import org.junit.Test;

public class Testsw {
	
   @Test
   public void main(){
	File f=new File("E:"+File.separator+"abc"+File.separator+"cc","1.txt");
	if(!f.exists()){
		f.mkdirs();
	}
}
}
