<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectAgencyGood.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
  </head>
  <body>
  <table border="1" >
  <tr><td>ID</td><td>商品ID</td><td>商品名称</td><td>商品简介</td><td>商品上传时间</td><td>操作</td></tr>
  <c:forEach items="${list}" var="goodMap">
  <tr id="${goodMap.id}" height="22">
  <td>${goodMap.id}</td>
  <td>${goodMap.goodsId}</td>
  <td>
  <a href="javascript:openWin('<%=basePath %>agency/manage/lookImg?id=${goodMap.goodsId}');" >${goodMap.name}</a>
  </td>
  <td>${goodMap.introduction}</td>
  <td>${goodMap.uploadTime}</td>
  <td>
  <a href="javascript:del('<%=basePath%>agency/manage/deleteGood?id=${goodMap.id}&goodsId=${goodMap.goodsId}');">删除</a>
  </td>
  </tr>
  </c:forEach>
  </table>
  </body>
</html>
