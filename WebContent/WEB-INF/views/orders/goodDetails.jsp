<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单商品详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/backstage/orders/goodDetails.js"></script>
<style type="text/css">
table { margin-top:10px;background-color:#eef4ea; 
}
td {
border:1px solid #cbd8ac; border-collapse:collapse; font-size:14px; text-align:left; padding:10px; }
.finput input{ background:#f1fbb4;}
table img{ float:left;}
table .floatLeft{float:left; }
</style>
  </head>
  <body>
   <table align="center" width="820px;" border="1">
    <tr><td>ID</td><td>商品名称</td><td>商品单价</td><td>商品数量</td><td>享有的折扣</td></tr>
    <c:forEach items="${list}" var="map">
    <tr>
    <td>${map.id}</td><td>${map.name}</td><td>${map.price}</td><td>${map.number}</td><td>${map.counts}</td>
    </tr>
    </c:forEach>
   </table>
	<div style="text-align: center;" id="deliver" orderId="${id}">
	 <input type="button" id="deliverButton" onclick="changeOrderState()" value="发货" >
	</div>
  </body>
</html>
