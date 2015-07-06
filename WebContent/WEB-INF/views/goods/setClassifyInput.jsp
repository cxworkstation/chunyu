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
    
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
       <style type="text/css">
          table { margin-top:10px;border:solid green 1px;}
          td {font-size:14px; text-align:left; padding:10px; }
       </style>
  </head>
  <script language="javascript">
  function changeclassify(classifyId){ 
	  $.ajax({ 
		url: "goods/manage/getSubClassify",
		type:"POST",
		data:"classifyId="+classifyId,
		dateType:"json",
	    success: function(data){
		  var list=data.goods;
		  var str2="<select name=\"classify2\" onchange=\"changeSub(document.form1.classify2.options[document.form1.classify2.selectedIndex].value)\"><option value=\"-1\">-------</option>";
		  for(var i=0;i<list.length;i++){
	  		str2+="<option value=\""+list[i].id+"\">"+list[i].name+"</option>";
		  }
		  str2+="</select>";
		 $('#div2').html(str2);
      }});
  }
  
  function changeSub(classifyId){
	$.ajax({   
	 url: "goods/manage/getSubClassify",
		type:"POST",
		data:"classifyId="+classifyId,
		dateType:"json",
	    success: function(data){
		var list=data.goods;
		  var str3="<select name=\"classify3\"><option value=\"-1\">-------</option>";
		   for(var i=0;i<list.length;i++){
	  		str3+="<option value=\""+list[i].id+"\">"+list[i].name+"</option>";
		   }
		   str3+"</select>";
		   $('#div3').html(str3);
		}});
  }

  function beSure(){
	  var classify1=$('#classify1').val();
	  if(classify1==-1) {
		  alert('还没有选择分类，还不能够进行此操作');
		  return;
	  }
	  var classify2=$('select[name=classify2]').val();
	  if(classify2==''||classify2==-1) {
		  alert('没有选择第二分类');
		  return;
	  }
	  
	  var classify3=$('select[name=classify3]').val();
	  if(classify3==''||classify3==-1) {
		  alert('没有选择第三分类');
		  return;
	  }
	 var id=$('#id').val();
	 var url='<%=basePath%>goods/manage/addClassify';
	 $.ajax({
		 url:url,
         type:'POST',
         data:{
            id:id,
            classify1:classify1,
            classify2:classify2,
            classify3:classify3
          },
          dataType:'text',
          success:function(data) {
              if(data=='1') {
                  $('#ok').attr('disabled','disabled');
                  alert('操作成功');
                  return;
               }
              alert('操作失败，请联系管理员');
           }
		 });
  }
</script>
  <body>
  <form action="<%=basePath %>goods/manage/addClassify" method="post" id="form1" name="form1">
   <input type="hidden" name="id" id="id" value="${id}">
     <table align="center" width="820px;">
   	   <tr>
  		<td colspan="3"  style="border-bottom:solid green 1px;text-align:center;"><h4>给商品【${goodsName}】设置分类</h4> </td>
  	   </tr>
  	   <tr>
  		<td style="border-bottom:solid green 1px;border-right:solid green 1px;">设置一级分类 : </td>
  		<td style="border-bottom:solid green 1px;border-right:solid green 1px;">设置二级分类: </td>
  		<td style="border-bottom:solid green 1px;">设置三级分类: </td>
  	   </tr>
  	   <tr>
  		<td style="border-right:solid green 1px;">
  		<select name="classify1" id="classify1" onchange="changeclassify(document.form1.classify1.options[document.form1.classify1.selectedIndex].value)">
  		 <option value="-1">-------</option>
  		 <c:forEach items="${list}" var="classify">
  		   <option value="${classify.id}">${classify.name}</option>
  		 </c:forEach>
  		</select>
  		</td>
  		<td style="border-right:solid green 1px;">
  		  <div id="div2" name="div2"></div>
  		</td>
  		<td>
  		  <div id="div3" name="div3"></div>
  		</td>
   	   </tr>
   	   <tr>
   	      <td colspan="3"  style="border-top:solid green 1px;text-align: center">
   	        <input type="button"  value="提交内容"  onclick="beSure();" id="ok">&nbsp;&nbsp;
	        <input type="button"  onclick="javascript:location='<%=basePath%>goods/manage/index'" value="返回" >
   	      </td>
   	   </tr>
   	</table>
   </form>
  </body>
</html>
