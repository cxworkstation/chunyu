package com.chunyu.web.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class TypeConversion {
    //String to int
	public static int StringToInt(String value){
		int result;
		if(value==null){
			return 0;
		}
		try{
			 result=Integer.parseInt(value);
		}catch (Exception e) {
			throw new RuntimeException("必须是数字的字符串格式");
		}
		return result;
		}
		
	//Date to String
	public static String DateToString(Date date){
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.format(date);
	}
	

	//Json to List
	public static List<Map<String,String>> JsonToList(String jsonString){
		if(jsonString==null){
			return null;
		}
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		JSONArray jsonArray=JSONArray.fromObject(jsonString);
		for(int i=0;i<jsonArray.size();i++){
		JSONObject jsonObj=jsonArray.getJSONObject(i);
		  Map<String,String> map=new HashMap<String,String>();
	      for(Iterator<String> it=jsonObj.keys();it.hasNext();){
			   String key=it.next();
			   String value=jsonObj.getString(key);
			   map.put(key, value);
		  }
	      list.add(map);
		}
		return list;
	}
	
	
}
