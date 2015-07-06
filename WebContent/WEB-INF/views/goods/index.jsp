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
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/base.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
</head>

<body style="margin-left: 8 ;margin-top:  8">
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" align="center">
<tr>
  <td align="center" background="<%=basePath %>imgs/backstage/wbg.gif">
     <a href="javascript:location='<%=basePath %>goods/manage/addInput?isfree=0'" class="coolbg np">添加普通商品</a>&nbsp;
     <a href="javascript:location='<%=basePath %>goods/manage/addInput?isfree=1'" class="coolbg np">添加免费商品</a>
</td>
</tr>
</table>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:12px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="11" background="<%=basePath %>imgs/backstage/wbg.gif">&nbsp;商品列表</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="4%">ID</td>
	<td width="12%">商品名称</td>
	<td width="4%">是否免费</td>
	<td width="4%">是否可打折</td>
	<td width="4%">是否只限配送区</td>
	<td width="4%">是否只可预定</td>
	<td width="5%">评论数</td>
	<td width="5%">已购买数</td>
	<td width="10%">上传时间</td>
	<td width="44%">操作</td>
</tr>
<c:if test="${!empty pm.list}">
 <c:forEach items="${pm.list}" var="good">
    <tr id="${good.id }" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="${good.id}" class="np" value="${good.id}"></td>
	<td>${good.id}</td>
	<td>${good.name}</td>
	<td>
	  <c:if test="${good.isfree eq 0}">
	     否
	  </c:if>
	  <c:if test="${good.isfree eq 1}">
	     是
	  </c:if>
	</td>
	<td>
	  <c:if test="${good.isCount eq 0}">
	     否
	  </c:if>
	  <c:if test="${good.isCount eq 1}">
	     是
	  </c:if>
	</td>
	<td>
	  <c:if test="${good.inArea eq 0}">
	     否
	  </c:if>
	  <c:if test="${good.inArea eq 1}">
	     是
	  </c:if>
	</td>
	<td>
	  <c:if test="${good.onlyReservation eq 0}">
	     否
	  </c:if>
	  <c:if test="${good.onlyReservation eq 1}">
	     是
	  </c:if>
	</td>
	<td>${good.commentNum}</td>
	<td>${good.hasBuyNum}</td>
	<td>${good.uploadTime}</td>
	<td>
	<a href="<%=basePath %>goods/manage/updateInput?id=${good.id}">查看商品信息 or 修改</a>|
	<a href="javascript:del('<%=basePath %>goods/manage/delete?id=${good.id}');">删除</a>| 
	<a href="<%=basePath %>goods/manage/detail?id=${good.id}">编辑详情页面</a>|
	<a href="<%=basePath %>goods/manage/setPriceInput?goodId=${good.id}">设置价格</a>|
	<a href="<%=basePath %>goods/manage/upImgInput?id=${good.id}">上传多张图片</a>|
	<c:if test="${good.isAgency eq 0}">
	<a href="<%=basePath %>goods/manage/setClassifyInput?id=${good.id}">设置分类</a>|
	<a href="<%=basePath %>goods/manage/setColumnInput?id=${good.id}">设置所属栏目</a>
	</c:if>
	</td>
	</tr>
</c:forEach>
<tr bgcolor="#FAFAF1">
<td height="28" colspan="4" align="left">
	&nbsp;
	<a href="javascript:selAll()" class="coolbg">全选</a>
	<a href="javascript:noSelAll()" class="coolbg">取消</a>
	<a href="javascript:bulkdel('<%=basePath %>goods/manage/bulkdel')" class="coolbg">&nbsp;删除&nbsp;</a>
</td>
<td colspan="7" valign="top">
  <!--  搜索表单  -->
<form name='checkForm' action='<%=basePath %>goods/manage/check' method='post'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='<%=basePath %>imgs/backstage/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='checkName' style='width:150'>
          	<option value='name'>商品名称</option>
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
	<td height="35" colspan="11" align="center">
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
						<td colspan="11" align="center">
							数据库没有相关数据
						</td>
					</tr>
				</c:if>
</table>

</body>
</html>