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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/base.css">
</head>
<body>
 <% List<List<Object>> list=(List<List<Object>>)request.getAttribute("list");
 %>
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:12px">
   <tr align="center" bgcolor="#FAFAF1" height="22">
     <td>ID</td><td>类别名称</td> <td>操作</td>
   </tr>
  <%for(int i=0;i<list.size();i++) {
     List<Object> inList=list.get(i);
  %>
  <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
     <td><%=inList.get(0) %></td><td><%=inList.get(1) %></td> 
     <td>
        <a href="<%=basePath%>category/manage/broad?cateId=<%=inList.get(0) %>">编辑</a> 
     </td>
   </tr>
  <%} %>
</table>
</body>
</html>