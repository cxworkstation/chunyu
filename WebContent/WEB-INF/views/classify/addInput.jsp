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
    
    <title>添加一级分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
	table { margin-top:10px;background-color:#eef4ea;}
	td {border:1px solid #cbd8ac; border-collapse:collapse; font-size:14px; text-align:left; padding:10px; }
	</style>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
	 <script type="text/javascript">
        function checking() {
            var name=$('input[name=name]').val().trim();
            if(name=='') {
                alert('分类名称不能为空');
                return false;
            }
            if(name.length>10) {
                alert('分类名称过长');
                return false;
            }
            return true;
        }
	 </script>
    </head>
  <body>
  <form action="<%=basePath %>classify/manage/add" method="post" id="myform">
     <table align="center" width="820px;">
   	   <tr>
  		<td>添加分类名称: </td>
  		<td >
  		 <input type="text"  name="name" size="30" />
  		</td>
   	   </tr>
   	    <tr>
  		<td>是否是预定专区: </td>
  		<td >
  		<input type="radio" name="onlyReservation" checked="checked" value="0"/>否
  		<input type="radio" name="onlyReservation" value="1"/>是
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
