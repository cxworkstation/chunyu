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
    
    <title>修改栏目</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<style type="text/css">
table { margin-top:10px;background-color:#eef4ea; 
}
td {
border:1px solid #cbd8ac; border-collapse:collapse; font-size:14px; text-align:left; padding:10px; }
.finput input{ background:#f1fbb4;}
table img{ float:left;}
table .floatLeft{float:left; }
</style>
<script type="text/javascript">
$(document).ready(function(){
	var list=${list};
	for(var i=0;i<list.length;i++){
		var col1=$('#column1').val();
		var col2=$('#column2').val();
		if(col1==list[i]){
			$('#column1').attr("checked","true");
		}else if(col2==list[i]){
			$('#column2').attr("checked","true");
		}
	}
});
</script>
  </head>
  <body>
  <form action="<%=basePath %>columns/manage/update" method="post" id="form1" name="form1">
   <input type="hidden" name="goodId" id="goodId" value="${goodId}">
     <table align="center" width="820px;">
   	   <tr align="center">
  		<td colspan="2" align="center">栏目设置 : </td>
  	   </tr>
  	   <tr>
  	   <td><input type="checkbox" name="column" id="column1" value="1"/>新品上市</td>
  	   <td><input type="checkbox" name="column" id="column2" value="2"/>当季推荐</td>
  	   </tr>
   	</table>
	<div style="text-align: center;">
	  <input type="submit"  value="提交内容"  onclick="return checking()">
	  <input type="button"  onclick="window.close();" value="关闭窗口" >
	</div>
</form>
  </body>
</html>
