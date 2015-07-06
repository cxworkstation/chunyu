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
    
    <title>添加经营户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/backstage/agency/addAgencyImage.js"></script>
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
  <form action="<%=basePath %>agency/manage/addAgency" method="post" id="form2" enctype="multipart/form-data">
   <input type="hidden" name="flag" id="flag" value="0">
    <input type="hidden" name="image" value="${image}">
     <table align="center" width="820px;">
       <tr>
         <td>
          <table>
           <tr>
             <td colspan="2">
               <img alt="图片展现区域" src="${image}" height="300px" width="300px">
             </td>
           </tr>
           <tr>
  	         <td> 上传图片:</td>
  		     <td> 
		       <input type="file"  name="imageFile"  size="30" class="selectinput" onchange="uploadImage();"> 
             </td>
  	      </tr>
         </table>
         </td>
         <td>
         <table>
  	   <tr>
  		<td>经营户名称 : </td>
  		<td colspan="2"><input type="text"  name="name" value="${name}" size="30" /></td>
   	   </tr>
   	   <tr>
  		<td>经营户简短:</td>
  		<td colspan="2">
  		    <textarea rows="4" cols="30" name="introduction" id="introduction">${introduction}</textarea>
  		</td>
   	  </tr>
   	   <tr>
  		<td>经营户经营时间:</td>
  		<td >
  		    <input type="text" name="manageTime" id="manageTime">${manageTime}
  		</td>
  		<td>年</td>
   	  </tr>
   	</table>
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
