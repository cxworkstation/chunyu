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
<title>订单管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/base.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
</head>

<body style="margin-left: 8 ;margin-top:  8">
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="14" background="<%=basePath %>imgs/backstage/wbg.gif">&nbsp;订单列表</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="4%">ID</td>
	<td width="5%">订单号</td>
	<td width="6%">团购/个人</td>
	<td width="15%">送货地址</td>
	<td width="7%">电话号码</td>
	<td width="5%">支付方式</td>
	<td width="13%%">留言</td>
	<td width="6%">总价(含运费)</td>
	<td width="5%">运费</td>
	<td width="5%">订单状态</td>
	<td width="4%">客户ID</td>
	<td width="8%">下单时间</td>
	<td width="13%">操作</td>
</tr>
<c:if test="${!empty pm.list}">
 <c:forEach items="${pm.list}" var="order">
    <tr id="${order.id}" align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="${good.id}" class="np" value="${order.id}"></td>
	<td>${order.id}</td>
	<td>${order.orderId}</td>
	<c:if test="${order.isBulk eq 0}">
	<td>个人</td>
	</c:if>
	<c:if test="${order.isBulk eq 1}">
	<td>团购</td>
	</c:if>
	<td>${order.address}</td>
	<td>${order.telephone}</td>
	<c:if test="${order.payMethod eq 0}">
	<td>货到付款</td>
	</c:if>
	<c:if test="${order.payMethod eq 1}">
	<td>在线支付</td>
	</c:if>
	<td>${order.message}</td>
	<td>${order.totalMoney}</td>
	<td>${order.fee}</td>
	<c:if test="${order.state eq 0}">
	<td>未发货</td>
	</c:if>
	<c:if test="${order.state eq 1}">
	<td>已发货</td>
	</c:if>
	<c:if test="${order.state eq 2}">
	<td>已完成</td>
	</c:if>
	<td>${order.userId}</td>
	<td>${order.orderTime}</td>
	<td>
	<a href="javascript:del('<%=basePath %>orders/manage/delete?id=${order.id}');">删除</a> | 
	<a href="javascript:openWin('goodDetails?id=${order.id}');" class="coolbg np">查看订单内商品</a>
	</td>
	</tr>
</c:forEach>
<tr bgcolor="#FAFAF1">
<td height="28" colspan="6" align="left">
	&nbsp;
	<a href="javascript:selAll()" class="coolbg">全选</a>
	<a href="javascript:noSelAll()" class="coolbg">取消</a>
	<a href="javascript:bulkdel('<%=basePath %>orders/manage/bulkdel')" class="coolbg">&nbsp;删除&nbsp;</a>
</td>
<td colspan="8" valign="top">
  <!--  搜索表单  -->
<form name='from1' action='<%=basePath %>orders/manage/check' method='post'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='<%=basePath %>imgs/backstage/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='selectName' style='width:150'>
            <option value='1'>订单号</option>
          	<option value='2'>电话号码</option>
          	<option value='3'>地址</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' size="40" />
        </td>
        <td align="center">
          <input type="image" src="<%=basePath %>imgs/backstage/search.gif" width="45" height="20" border="0" class="np" />
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
	<td height="35" colspan="14" align="center">
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
						<td colspan="14" align="center">
							数据库没有相关数据
						</td>
					</tr>
				</c:if>
</table>

</body>
</html>