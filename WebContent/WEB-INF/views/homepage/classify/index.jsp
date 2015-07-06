<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/base.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
</head>

<body style="margin-left: 8 ;margin-top:  8">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="8" background="<%=basePath %>imgs/backstage/wbg.gif">&nbsp;分类列表</td>
</tr>
<tr bgcolor="#FAFAF1" height="22" align="center">
	<th>分类名称</th>
	<th>操作</th>
</tr>
<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" 
     onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"  align="center">
     <td>蔬菜水果区</td>
     <td>
            <a href="<%=basePath%>homepage/manage/classify/auClassifyInput?classifyId=1">编辑</a> 
     </td>
</tr>
<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" 
     onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" align="center">
     <td>水产冻品区</td>
     <td>
             <a href="<%=basePath%>homepage/manage/classify/auClassifyInput?classifyId=2">编辑</a> 
     </td>
</tr>
<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" 
     onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" align="center">
     <td>肉禽蛋品区</td>
      <td>
             <a href="<%=basePath%>homepage/manage/classify/auClassifyInput?classifyId=3">编辑</a> 
     </td>
</tr>
<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" 
     onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" align="center">
     <td>面店豆制品区</td>
      <td>
             <a href="<%=basePath%>homepage/manage/classify/auClassifyInput?classifyId=4">编辑</a> 
     </td>
</tr>

<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" 
     onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" align="center">
     <td>粮油调料区</td>
      <td>
             <a href="<%=basePath%>homepage/manage/classify/auClassifyInput?classifyId=5">编辑</a> 
     </td>
</tr>

<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" 
     onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" align="center">
     <td>副食品干货区</td>
      <td>
             <a href="<%=basePath%>homepage/manage/classify/auClassifyInput?classifyId=6">编辑</a> 
     </td>
</tr>

<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" 
     onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" align="center">
     <td>日常用品区</td>
      <td>
             <a href="<%=basePath%>homepage/manage/classify/auClassifyInput?classifyId=7">编辑</a> 
     </td>
</tr>
</table>

</body>
</html>