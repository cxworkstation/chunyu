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
    
    <title>添加下一级分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
	table { margin-top:10px;background-color:#eef4ea; 
	}
	td {
	border:1px solid #cbd8ac; border-collapse:collapse; font-size:14px; text-align:left; padding:10px; }
	.finput input{ background:#f1fbb4;}
	table img{ float:left;}
	table .floatLeft{float:left; }
	</style>
	<script language="javascript" type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var flag=${flag};
	    if(flag==1){
	    	$('#radio1').attr("disabled",true);
	    	$('#radio2').attr("disabled",true);
	    	$('#radio2').attr("checked","checked");
	    }
	});
	</script>
    </head>
  <body>
  <form action="<%=basePath %>classify/manage/addNext" method="post" id="myform">
     <input type="hidden" name="level" value="${level}"/>
     <input type="hidden" name="parentId" value="${parentId}"/>
      <input type="hidden" name="flag" value="${flag}"/>
     <table align="center" width="820px;">
   	   <tr>
  		<td>添加分类名称: </td>
  		<td >
  		 <input type="text"  name="name" size="30" />
  		</td>
   	   </tr>
   	   <tr id="atr">
  		<td>是否是预定专区: </td>
  		<td>
  		<input type="radio" name="onlyReservation"  id="radio1"  value="0" checked="checked" />否
  		<input type="radio" name="onlyReservation"  id="radio2"  value="1"/>是
  		</td>
   	   </tr>
   	</table>
	<div style="text-align: center;">
	  <input type="submit"  value="提交内容"  onclick="return checking()">
	  <input type="button"  onclick="window.close();" value="关闭窗口" >
	</div>
</form>
  </body>
</html>
