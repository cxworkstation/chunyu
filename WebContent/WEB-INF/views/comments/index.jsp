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
<title>评论管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/base.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
</head>

<body style="margin-left: 8 ;margin-top:  8">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="7" background="<%=basePath %>imgs/backstage/wbg.gif">&nbsp;商品列表</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="6%">选择</td>
	<td width="6%">ID</td>
	<td width="12%">商品名称</td>
	<td width="45%">评论内容</td>
	<td width="10%">评论用户名</td>
	<td width="12%">评论时间</td>
	<td width="9%">操作</td>
</tr>
<c:if test="${!empty pm.list}">
 <c:forEach items="${pm.list}" var="comment">
    <tr id="${comment.id }" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="${comment.id}" class="np" value="${comment.id}"></td>
	<td>${comment.id}</td>
	<td>${comment.name}</td>
	<td>${comment.content}</td>
	<td>${comment.username}</td>
	<td>${comment.commentTime}</td>
	<td>
	<a href="javascript:del('<%=basePath %>comments/manage/delete?id=${comment.id}');">删除</a>
	</td>
	</tr>
</c:forEach>
<tr bgcolor="#FAFAF1">
<td height="28" colspan="3" align="left">
	&nbsp;
	<a href="javascript:selAll()" class="coolbg">全选</a>
	<a href="javascript:noSelAll()" class="coolbg">取消</a>
	<a href="javascript:bulkdel('<%=basePath %>comments/manage/bulkdel')" class="coolbg">&nbsp;删除&nbsp;</a>
</td>
<td colspan="4" valign="top">
  <!--  搜索表单  -->
<form name='from1' action='<%=basePath %>comments/manage/check' method='post'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='<%=basePath %>imgs/backstage/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='selectName' style='width:150'>
            <option value='1'>商品名</option>
          	<option value='2'>用户名</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' size="40" />
        </td>
        <td align="center">
         <input type="submit" value="查询" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</td>
</tr>
	<tr align="right" bgcolor="#EEF4EA">
	<td height="35" colspan="7" align="center">
	   <pg:pager  url="index"  items="${pm.totalCount}" maxPageItems="${pm.pageSize}" maxIndexPages="7" export="currentPageNumber = pageNumber">
      <pg:first>
         <a href="${pageUrl}">首页</a>
      </pg:first>
      
      <pg:prev>
       <a href="${pageUrl}">上一页</a>
      </pg:prev>
     
      <pg:pages>
       <c:choose>
        <c:when test="${currentPageNumber eq pageNumber}">
         <font color="red"> ${pageNumber }</font>
        </c:when>
        <c:otherwise><a href="${pageUrl}">${pageNumber }</a></c:otherwise>
       </c:choose>   
      </pg:pages>
      
      <pg:next>
       <a href="${pageUrl }">下一页</a>
      </pg:next>
      <pg:last>
       <a href="${pageUrl }">尾页</a>
      </pg:last>
    </pg:pager>
	</td>
</tr>
</c:if>
<!-- 在列表数据为空的时候，要显示的提示信息 -->
				<c:if test="${empty pm.list}">
					<tr>
						<td colspan="7" align="center">
							数据库没有相关数据
						</td>
					</tr>
				</c:if>
</table>

</body>
</html>