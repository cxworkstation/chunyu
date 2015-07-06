<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
<link rel="stylesheet" href="<%=basePath%>css/backstage/base.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/backstage/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="<%=basePath%>js/backstage/menu.js"></script>
<base target="main" />
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
    <!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>定制管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
             <li>
                <a href="<%=basePath%>homepage/manage/broad/index"   target='main'>首页轮播图管理</a>
            </li>
            <li>
            	<a href="<%=basePath%>homepage/manage/notice/index"  target='main'>首页公告管理</a>
            </li>
            <li>
            	<a href="<%=basePath%>homepage/manage/health/index"  target='main'>首页健康合理膳食</a>
            </li>
            <li>
            	<a href="<%=basePath%>homepage/manage/especial/index"  target='main'>首页特别推荐</a>
            </li>
             <li>
            	<a href="<%=basePath%>homepage/manage/module/index"   target='main'>首页四模块管理</a>
            </li>
            <li>
            	<a href="<%=basePath%>homepage/manage/classify/index"   target='main'>首页分类管理</a>
            </li>
            <li>
            	<a href="<%=basePath%>category/manage/main"   target='main'>分类轮播图管理</a>
            </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
    
	<!-- Item 2 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>类目管理</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
             <li>
                <a href="<%=basePath%>goods/manage/index"   target='main'>商品管理</a>
            </li>
            <li>
            	<a href="<%=basePath%>classify/manage/index"  target='main'>分类管理</a>
            </li>
             <li>
            	<a href="<%=basePath%>users/manage/index"   target='main'>用户管理</a>
            </li>
            <li>
            	<a href="<%=basePath%>agency/manage/index"   target='main'>市场经营户管理</a>
            </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 2 End -->
      
      <!-- Item 3 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>事务管理</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
			<li><a href="<%=basePath%>orders/manage/index" target='main'>订单管理</a></li>
          </ul>
          <ul class='sitemu'>
			<li><a href="<%=basePath%>comments/manage/index" target='main'>评论管理</a></li>
          </ul>
        </dd>
      </dl>
      <!-- Item 3 End -->
      
      
       <!-- Item 4 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items4_1")'><b>模块管理</b></dt>
         <dd style='display:block' class='sitem' id='items4_1'>
         <ul class='sitemu'>
			<li><a href="<%=basePath%>freeGood/manage/index" target='main'>免费商品管理</a></li>
          </ul>
          <ul class='sitemu'>
			<li><a href="<%=basePath%>hotGood/manage/index" target='main'>热门商品管理</a></li>
          </ul>
          <ul class='sitemu'>
			<li><a href="<%=basePath%>columns/manage/index?type=1" target='main'>栏目管理</a></li>
          </ul>
          <ul class='sitemu'>
			<li><a href="<%=basePath%>goodClassify/manage/index" target='main'>商品所属分类管理</a></li>
          </ul>
        </dd>
      </dl>
      <!-- Item 4 End -->
	  </td>
  </tr>
</table>
</body>
</html>