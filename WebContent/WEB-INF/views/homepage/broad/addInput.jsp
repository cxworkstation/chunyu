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
<script type="text/javascript">
   function fileBtn(flag) {
	   document.forms[flag].submit();
   }

   function beSure() {
	   var $formObj=$('#form0');
	   var inputArray=$formObj.children('input');
	   var chiefPic=$(inputArray[1]).val();
	   var pic1=$(inputArray[2]).val();
	   var pic2=$(inputArray[3]).val();
	   var pic3=$(inputArray[4]).val();
	   var pic4=$(inputArray[5]).val();
	   if(chiefPic==null||chiefPic=='') {
		   alert('轮播主图不能为空');
		   return;
	   }
	   if(pic1==null||pic1=='') {
		   alert('附属图1不能为空');
		   return;
	   }
	   if(pic2==null||pic2=='') {
		   alert('附属图2不能为空');
		   return;
	   }
	   if(pic3==null||pic3=='') {
		   alert('附属图3不能为空');
		   return;
	   }
	   if(pic4==null||pic4=='') {
		   alert('附属图4不能为空');
		   return;
	   }

	   var orderInputVal=$('input[name=order]').val().trim();
	   var reg=/^[1-9]\d*$/;
	   if(orderInputVal=='') {
		   alert('请输入该轮播图的级别');
		   return;
	   }
	   if(!reg.test(orderInputVal)) {
		   alert('级别不合法，请填写一个正整数');
		   return;
	   }

	   var id=$('input[name=id]').val();
	   var url='<%=basePath%>homepage/manage/broad/beSure';
	   $.ajax({
		   url:url,
           type:'POST',
           data:{
              chiefPic:chiefPic,
              pic1:pic1,
              pic2:pic2,
              pic3:pic3,
              pic4:pic4,
              orderVal:orderInputVal,
              id:id
             },
           dataType:'text',
           success:function(data) {
               if(data==1) {
                 alert('操作成功！');
                 $('input[name=beSure]').attr('disabled','disabled');
                 return;
               }
               alert('操作失败，请联系管理员');
           }
 
		   });
	  
   }
</script>
<style type="text/css">
  ul li {
    width:197px;
    border:solid green 1px;
    float: left;
    height: 100px;
  }
</style>
</head>

<body style="margin-left: 8 ;margin-top:  8">
<center>
<div style="margin-top:8px;height: 550px;border:solid #666600 1px;width:806px;">
      <!-- 首页轮播图大图 -->
      <div style="width:790px;height:316px;">
          <img alt="" src="<%=basePath%>${chiefPic}">
          <form action="<%=basePath%>homepage/manage/broad/add" method="post" enctype="multipart/form-data" id="form0">
             <input  type="file" onchange="fileBtn(0);" name="file">
             <input type="hidden" name="chiefPic" value="${chiefPic}">
             <input type="hidden" name="pic1" value="${pic1 }">
             <input type="hidden" name="pic2" value="${pic2 }">
             <input type="hidden" name="pic3" value="${pic3 }">
             <input type="hidden" name="pic4" value="${pic4 }">
             <input type="hidden" name="flag" value="0">
             <input type="hidden" name="id" value="${id}">
             <input type="hidden" name="orderVal" value="${orderVal}">
          </form>
      </div>
      
      <!-- 附属的4张小图 -->
      <div style="margin-top:5px;height:127px;">
         <ul>
           <li>
             <img alt="" src="<%=basePath%>${pic1}">
             <form action="<%=basePath%>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
              <input type="file" onchange="fileBtn(1);" name="file">
              <input type="hidden" name="chiefPic" value="${chiefPic}">
              <input type="hidden" name="pic1" value="${pic1 }">
              <input type="hidden" name="pic2" value="${pic2 }">
              <input type="hidden" name="pic3" value="${pic3 }">
              <input type="hidden" name="pic4" value="${pic4 }">
              <input type="hidden" name="flag" value="1">
              <input type="hidden" name="id" value="${id}">
              <input type="hidden" name="orderVal" value="${orderVal}">
             </form>
           </li>
           <li>
            <img alt="" src="<%=basePath%>${pic2}">
            <form action="<%=basePath%>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
             <input type="file" onchange="fileBtn(2);" name="file">
             <input type="hidden" name="chiefPic" value="${chiefPic}">
             <input type="hidden" name="pic1" value="${pic1 }">
             <input type="hidden" name="pic2" value="${pic2 }">
             <input type="hidden" name="pic3" value="${pic3 }">
             <input type="hidden" name="pic4" value="${pic4 }">
             <input type="hidden" name="flag" value="2">
             <input type="hidden" name="id" value="${id}">
             <input type="hidden" name="orderVal" value="${orderVal}">
            </form>
           </li>
           <li>
            <img alt="" src="<%=basePath%>${pic3}">
            <form action="<%=basePath%>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
             <input type="file" onchange="fileBtn(3);" name="file">
             <input type="hidden" name="chiefPic" value="${chiefPic}">
             <input type="hidden" name="pic1" value="${pic1 }">
             <input type="hidden" name="pic2" value="${pic2 }">
             <input type="hidden" name="pic3" value="${pic3 }">
             <input type="hidden" name="pic4" value="${pic4 }">
             <input type="hidden" name="flag" value="3">
             <input type="hidden" name="id" value="${id}">
             <input type="hidden" name="orderVal" value="${orderVal}">
            </form>
           </li>
           <li>
            <img alt="" src="<%=basePath%>${pic4}">
            <form action="<%=basePath%>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
             <input type="file" onchange="fileBtn(4);" name="file">
             <input type="hidden" name="chiefPic" value="${chiefPic}">
             <input type="hidden" name="pic1" value="${pic1 }">
             <input type="hidden" name="pic2" value="${pic2 }">
             <input type="hidden" name="pic3" value="${pic3 }">
             <input type="hidden" name="pic4" value="${pic4 }">
             <input type="hidden" name="flag" value="4">
             <input type="hidden" name="id" value="${id}">
             <input type="hidden" name="orderVal" value="${orderVal}">
            </form>
           </li>
         </ul>
      </div>
      
      <!-- 填写轮播播放级别，数字越低表示级别越高，排在前面 -->
      <div style="margin-top: 10px;">
          <input type="text" size="40px" name="order" value="${orderVal}">
      </div>
      <!-- 确定按钮 -->
      <div style="margin-top:20px;">
          <input type="button" value="确定" onclick="beSure();" name="beSure">
          <input type="button" value="返回" onclick="javascript:location='<%=basePath%>homepage/manage/broad/index'">
      </div>
   </div>
</center>
</body>
</html>