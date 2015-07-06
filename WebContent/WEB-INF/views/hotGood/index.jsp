<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script language="javascript" type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=basePath%>js/backstage/hotGood/index.js"></script>
  </head>
  
  <body>
  <table border="1">
  	<tr>
  		<td>请输入要查询购买量前N位的商品：</td><td><input type="text" name="range" id="range"/></td>
  		<td><input type="button" value="查询" onclick="searchRange()"/></td>
  	</tr>
  </table>
  <div id="show"></div>
  </body>
</html>
