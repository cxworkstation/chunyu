<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
<script type="text/javascript">
   function fileBtn(distinct) {
	  document.forms[distinct].submit();
   }

   function beSure() {
     var $inputObj=$('#form1').children('input');
     var pic1=$($inputObj[1]).val();
     var pic2=$($inputObj[2]).val();
     var pic3=$($inputObj[3]).val();
     var pic4=$($inputObj[4]).val();
     var pic5=$($inputObj[5]).val();
     var pic6=$($inputObj[6]).val();
     var module=$($inputObj[7]).val();

     if(pic1==''||pic2==''||pic3==''||pic4==''||pic5==''||pic6=='') {
         alert('对于每个模块必须上传6张图片');
         return;
     }

     var id=$('input[name=id]').val();
     var url="<%=basePath%>homepage/manage/module/addOrUpdate";
     $.ajax({
         url:url,
         type:'POST',
         data:{
            id:id,
            pic1:pic1,
            pic2:pic2,
            pic3:pic3,
            pic4:pic4,
            pic5:pic5,
            pic6:pic6,
            module:module
             },
          dataType:'text',
          success:function(data) {
               if(data=='1') {
                   alert('操作成功！');
                   $('input[name=beSure]').attr('disabled','disabled');
                   return;
               }
               alert('操作失败，请联系管理员');
              }
         });  
   }

   function del(id) {
	   if(!confirm("确定删除这个模块么？")) {
			  return;
	   }
	   var url='<%=basePath%>homepage/manage/module/del?id='+id;
	   $.ajax({
           url:url,
           type:'GET',
           dataType:'text',
           success:function(data) {
               if(data=='1') {
                   alert('删除成功');
                   $('input[name=beSure]').attr('disabled','disabled');
                   $('input[name=delBtn]').attr('disabled','disabled');
                   return;
               }
               alert('删除失败');   
           }
		   });
   }
</script>
<style type="text/css">
  ul li {
    float:left;
    height:150px;
    border-right:solid green 1px;
    list-style-type: none;
    width:150px;
    margin-top:-18px;
  }
</style>
</head>
<body>
   <center>
      <div style="border:solid green 1px;height:600px;width:1000px">
         <input type="hidden" name="id" value="${id}">
         <div>
           <span style="color:#9a2d0f">${module}</span>
         </div>
         <div style="width:1000px;border-top:solid green 1px;border-bottom:solid green 1px;height:150px;margin-top:20px;">
            <ul>
               <li style="border-left:solid green 1px;">
                 <form action="<%=basePath%>homepage/manage/module/uploadImg" method="post" enctype="multipart/form-data" id="form1">
                  <%
                    Object o1=request.getAttribute("pic1");
                    if(o1!=null) {
                    %>	
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/module/addOrUpdateGoods?id=${id}&col=1')">
                     <img alt="" src="<%=basePath%>${pic1}"></a>
                  <%
                    }
                  %>
                  <input type="file" name="file" onchange="fileBtn(0);">
                  <input type="hidden" name="pic1" value="${pic1 }">
                  <input type="hidden" name="pic2" value="${pic2 }">
                  <input type="hidden" name="pic3" value="${pic3 }">
                  <input type="hidden" name="pic4" value="${pic4 }">
                  <input type="hidden" name="pic5" value="${pic5 }">
                  <input type="hidden" name="pic6" value="${pic6 }">
                  <input type="hidden" name="module" value="${module }">
                  <input type="hidden" name="flag" value="0">
                 </form>
               </li>
               
               <li>
                 <form action="<%=basePath%>homepage/manage/module/uploadImg" method="post" enctype="multipart/form-data">
                 <%
                    Object o2=request.getAttribute("pic2");
                    if(o2!=null) {
                    %>	
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/module/addOrUpdateGoods?id=${id}&col=2')">
                     <img alt="" src="<%=basePath%>${pic2}"></a>
                  <%
                    }
                  %>
                  <input type="file" name="file" onchange="fileBtn(1);">
                  <input type="hidden" name="pic1" value="${pic1 }">
                  <input type="hidden" name="pic2" value="${pic2 }">
                  <input type="hidden" name="pic3" value="${pic3 }">
                  <input type="hidden" name="pic4" value="${pic4 }">
                  <input type="hidden" name="pic5" value="${pic5 }">
                  <input type="hidden" name="pic6" value="${pic6 }">
                  <input type="hidden" name="flag" value="1">
                  <input type="hidden" name="module" value="${module }">
                 </form>
               </li>
               
               <li>
                 <form action="<%=basePath%>homepage/manage/module/uploadImg" method="post" enctype="multipart/form-data">
                  <%
                    Object o3=request.getAttribute("pic3");
                    if(o3!=null) {
                    %>	
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/module/addOrUpdateGoods?id=${id}&col=3')">
                     <img alt="" src="<%=basePath%>${pic3}"></a>
                  <%
                    }
                  %>
                  <input type="file" name="file" onchange="fileBtn(2);">
                  <input type="hidden" name="pic1" value="${pic1 }">
                  <input type="hidden" name="pic2" value="${pic2 }">
                  <input type="hidden" name="pic3" value="${pic3 }">
                  <input type="hidden" name="pic4" value="${pic4 }">
                  <input type="hidden" name="pic5" value="${pic5 }">
                  <input type="hidden" name="pic6" value="${pic6 }">
                  <input type="hidden" name="flag" value="2">
                  <input type="hidden" name="module" value="${module }">
                 </form>
               </li>
               
               <li>
                 <form action="<%=basePath%>homepage/manage/module/uploadImg" method="post" enctype="multipart/form-data">
                  <%
                    Object o4=request.getAttribute("pic4");
                    if(o4!=null) {
                    %>	
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/module/addOrUpdateGoods?id=${id}&col=4')">
                     <img alt="" src="<%=basePath%>${pic4}"></a>
                  <%
                    }
                  %>
                  <input type="file" name="file" onchange="fileBtn(3);">
                  <input type="hidden" name="pic1" value="${pic1 }">
                  <input type="hidden" name="pic2" value="${pic2 }">
                  <input type="hidden" name="pic3" value="${pic3 }">
                  <input type="hidden" name="pic4" value="${pic4 }">
                  <input type="hidden" name="pic5" value="${pic5 }">
                  <input type="hidden" name="pic6" value="${pic6 }">
                  <input type="hidden" name="flag" value="3">
                  <input type="hidden" name="module" value="${module }">
                 </form>
               </li>
               
                <li>
                 <form action="<%=basePath%>homepage/manage/module/uploadImg" method="post" enctype="multipart/form-data">
                  <%
                    Object o5=request.getAttribute("pic5");
                    if(o4!=null) {
                    %>	
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/module/addOrUpdateGoods?id=${id}&col=5')">
                     <img alt="" src="<%=basePath%>${pic5}"></a>
                  <%
                    }
                  %>
                  <input type="file" name="file" onchange="fileBtn(4);">
                  <input type="hidden" name="pic1" value="${pic1 }">
                  <input type="hidden" name="pic2" value="${pic2 }">
                  <input type="hidden" name="pic3" value="${pic3 }">
                  <input type="hidden" name="pic4" value="${pic4 }">
                  <input type="hidden" name="pic5" value="${pic5 }">
                  <input type="hidden" name="pic6" value="${pic6 }">
                  <input type="hidden" name="flag" value="4">
                  <input type="hidden" name="module" value="${module }">
                 </form>
               </li>
               
                <li>
                 <form action="<%=basePath%>homepage/manage/module/uploadImg" method="post" enctype="multipart/form-data">
                  <%
                    Object o6=request.getAttribute("pic6");
                    if(o6!=null) {
                    %>	
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/module/addOrUpdateGoods?id=${id}&col=6')">
                     <img alt="" src="<%=basePath%>${pic6}"></a>
                  <%
                    }
                  %>
                  <input type="file" name="file" onchange="fileBtn(5);">
                  <input type="hidden" name="pic1" value="${pic1 }">
                  <input type="hidden" name="pic2" value="${pic2 }">
                  <input type="hidden" name="pic3" value="${pic3 }">
                  <input type="hidden" name="pic4" value="${pic4 }">
                  <input type="hidden" name="pic5" value="${pic5 }">
                  <input type="hidden" name="pic6" value="${pic6 }">
                  <input type="hidden" name="flag" value="5">
                  <input type="hidden" name="module" value="${module }">
                 </form>
               </li>
            </ul>
         </div>
         <div style="margin-top:30px">
            <input type="button" value="确定" onclick="beSure();" name="beSure">
            <input type="button" value="返回" onclick="javascript:location='<%=basePath%>homepage/manage/module/index'">
            <%Object obj=request.getAttribute("id");
              if(obj!=null)
              {
             %>
             <input type="button" value="删除" onclick="del(<%=obj%>);" name="delBtn">
             <%
              }
           %>
         </div>
      </div>
   </center>
</body>
</html>