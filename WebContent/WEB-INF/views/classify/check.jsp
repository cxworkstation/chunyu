<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/base.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
</head>

<body style="margin-left: 8 ;margin-top:  8">
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td align="center" background="<%=basePath %>imgs/backstage/wbg.gif">
     <a href="javascript:openWin('<%=basePath %>classify/manage/addInput?level=1');" class="coolbg np">添加第一级的分类</a>&nbsp;&nbsp;
     <a href="javascript:location='<%=basePath%>classify/manage/index';" class="coolbg np">返回顶级</a>
     
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="9" background="<%=basePath %>imgs/backstage/wbg.gif">&nbsp;分类列表</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="6%">选择</td>
	<td width="6%">分类ID</td>
	<td width="13%">分类名称</td>
	<td width="7%">分类级别</td>
	<td width="14%">是否属折扣分类区</td>
	<td width="12%">是否属预定分类区</td>
	<td width="17%">查看操作</td>
	<td width="15%">增删操作</td>
	<td width="10%">其他操作</td>
</tr>
<c:if test="${!empty list}">
 <c:forEach items="${list}" var="classify">
    <tr id="${classify.id }" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="${classify.id}" class="np" value="${classify.id}"></td>
	<td>${classify.id}</td>
	<td>${classify.name}</td>
	<td>${classify.level}</td>
	<c:if test="${classify.level eq 3 and classify.isCount eq 1}">
	<td>有折区</td>
	</c:if>
	<c:if test="${classify.level eq 3 and classify.isCount eq 0}">
	<td>无折区</td>
	</c:if>
	<c:if test="${classify.level ne 3}">
	<td>不是第三级分类,不分折扣区</td>
	</c:if>
	<c:if test="${classify.onlyReservation eq 1}">
	<td>预定区</td>
	</c:if>
	<c:if test="${classify.onlyReservation eq 0}">
	<td>非预定区</td>
	</c:if>
	<td>
	<c:if test="${classify.level ne 1}">
	<a href="<%=basePath %>classify/manage/checkPre?id=${classify.id}">查看上一级分类</a>&nbsp;
	</c:if>
	<c:if test="${classify.level ne 3}">
	<a href="<%=basePath %>classify/manage/checkNext?id=${classify.id}">查看下一级分类</a>
	</c:if>
	</td>
	<td>
	<a href="javascript:del('<%=basePath %>classify/manage/delete?id=${classify.id}');">删除</a> &nbsp; 
	<c:if test="${classify.level ne 3}">
	<a href="javascript:openWin('<%=basePath %>classify/manage/addNextInput?id=${classify.id}');" class="coolbg np">添加下一级分类</a>
	</c:if>
	</td>
	<td>
	<a href="<%=basePath %>classify/manage/changeFree?id=${classify.id}">设置成为折扣区</a> 
	</td>
	</tr>
</c:forEach>
</c:if>
<!-- 在列表数据为空的时候，要显示的提示信息 -->
				<c:if test="${empty list}">
					<tr>
						<td colspan="8" align="center">
							数据库没有相关数据
						</td>
					</tr>
				</c:if>
</table>
</body>
</html>