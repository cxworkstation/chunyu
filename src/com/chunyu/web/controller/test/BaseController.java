package com.chunyu.web.controller.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import flexjson.JSONSerializer;



public class BaseController {
	//把字符串json输出到html
	protected void printToHtml(HttpServletResponse response ,String jsonStr) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			response.setDateHeader("Expires", 0);
			PrintWriter out=response.getWriter();
			out.print(jsonStr);
			out.flush();
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//把字符串输出到html
	protected void printStrToHtml(HttpServletResponse response,String Str) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setDateHeader("Expires", 0);
			PrintWriter out=response.getWriter();
			out.print(Str);
			out.flush();
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//把map转化成json to Html
	protected void printToJson(HttpServletResponse response,Map jsonMap) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			response.setDateHeader("Expires", 0);
			PrintWriter out=response.getWriter();
			
			JSONSerializer jsonSerializer=new JSONSerializer();
			out.print(jsonSerializer.deepSerialize(jsonMap));
			out.flush();
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//把list转换成json to Html
	protected void printListToHtml(HttpServletResponse response,List list){
		try{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			response.setDateHeader("Expires", 0);
			PrintWriter out=response.getWriter();
			JSONSerializer jsonSerializer=new JSONSerializer();
			out.print(jsonSerializer.deepSerialize(list));
			out.flush();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
