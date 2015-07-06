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
    
    <title>图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/backstage/goods/addInput.js"></script>
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
          <table>
           <tr>
             <td colspan="2">
               <img alt="图片展现区域" src="${address}" height="300px" width="300px">
             </td>
           </tr>
         </table>
  </body>
</html>
