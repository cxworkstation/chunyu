<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<base target="_self">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/base.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/backstage/main.css" />
</head>
<body style="margin-left: 8 ;margin-top: 8">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <div style='float:right;padding-right:8px;'>
        <!--  //保留接口  -->
      </div></td>
  </tr>
  
</table>

<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
  <tr>
    <td colspan="2" background="<%=basePath %>imgs/backstage/wbg.gif" bgcolor="#EEF4EA" class='title'>
    	<div style='float:left'><span>快捷操作</span><font color="red">${message }</font></div>
    	<div style='float:right;padding-right:10px;'></div>
   </td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td height="30" colspan="2" align="center" valign="bottom"><table width="100%" border="0" cellspacing="1" cellpadding="1">
        <tr>
          <td width="15%" height="31" align="center"><img src="<%=basePath %>imgs/backstage/qc.gif" width="90" height="30" /></td>
          <td width="85%" valign="bottom"><div class='icoitem'>
              <div class='ico'><img src='<%=basePath %>imgs/backstage/addnews.gif' width='16' height='16' /></div>
              <div class='txt'><a href='<%=basePath %>news/news_list?type=5'><u>考试经验</u></a></div>
            </div>
            <div class='icoitem'>
              <div class='ico'><img src='<%=basePath %>imgs/backstage/menuarrow.gif' width='16' height='16' /></div>
              <div class='txt'><a href='<%=basePath %>news/news_list?type=9'><u>网站公告</u></a></div>
            </div>
            <div class='icoitem'>
              <div class='ico'><img src='<%=basePath %>imgs/backstage/manage1.gif' width='16' height='16' /></div>
              <div class='txt'><a href='<%=basePath %>news/news_list?type=8'><u>课程表</u></a></div>
            </div>
            <div class='icoitem'>
              <div class='ico'><img src='<%=basePath %>imgs/backstage/file_dir.gif' width='16' height='16' /></div>
              <div class='txt'><a href='<%=basePath %>examin/examin_index'><u>题库与备考</u></a></div>
            </div>
            <div class='icoitem'>
              <div class='ico'><img src='<%=basePath %>imgs/backstage/part-index.gif' width='16' height='16' /></div>
              <div class='txt'><a href='<%=basePath %>system/system_clear'><u>更新系统缓存</u></a></div>
            </div>
            </td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
  <tr bgcolor="#EEF4EA">
    <td colspan="2" background="<%=basePath %>imgs/backstage/wbg.gif" class='title'><span>系统基本信息</span></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td width="25%" bgcolor="#FFFFFF">您的级别：</td>
    <td width="75%" bgcolor="#FFFFFF">管理员</td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td>软件版本信息：</td>
    <td>V 1.0</td>
  </tr>
</table>

</body>
</html>