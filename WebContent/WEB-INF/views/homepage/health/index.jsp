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
<title>健康合理膳食</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
   $(function() {
	   $('input[name=imgFile]').change(function() {
	         document.forms[0].submit();
	   });
   });
</script>
</head>
<body>
   <center>
      <div style="border:solid red 1px;width:800px;height:600px;">
         <div style="border:solid green 1px;width:173px;height:173px;">
            <% Object o=request.getAttribute("pic");
               if(o!=null) {
             %>
              <a href="javascript:location='<%=basePath%>homepage/manage/health/editContentInput?id=${id}';"><img alt="" src="<%=basePath%><%=o%>"></a>
             <% 	   
               }
             %>
            <form action="<%=basePath%>homepage/manage/health/uploadImg" method="post" enctype="multipart/form-data">
               <input type="file" name="imgFile">
               <input type="hidden" name="id" value="${id }">
            </form>
         </div>
      </div>
   </center>
</body>
</html>