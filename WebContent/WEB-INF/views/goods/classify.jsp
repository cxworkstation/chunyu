<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看分类</title>
</head>
<body> 
  <center>
      <div style="border:solid green 1px;width:800px;height:600px;">
        <%Object o=request.getAttribute("list"); 
          if(o==null) {
        %>
          <div style="margin-top:100px;"><h4>系统好像出了问题，请联系管理员</h4></div>
        <% 
          }else {
         %>
          <div style="margin-top:50px;">商品【${goodsName}】的分类</div>
         <%
        	  List<String> list=(List<String>)o;
        	  for(int i=0;i<list.size();i++) {
        		  String style0="margin-top:3px;";
        		  if(i==0) {
        			 style0="margin-top:30px;";
        		  }
         %>
          <div style="border: solid green 1px;<%=style0%>">第<%=i+1%>个分类：<%=list.get(i) %></div>
         <%
        	  }
         }
        %>
         <div style="margin-top:30px;">
           <form action="<%=basePath%>goods/manage/delClassify" method="get">
             <input type="hidden" name="id" value="${id }">
             <input type="submit" value="解体分类">&nbsp;&nbsp;
             <input type="button" value="返回" onclick="javascript:location='<%=basePath%>goods/manage/index'">
           </form>
         </div>
         
      </div>
  </center>
</body>
</html>