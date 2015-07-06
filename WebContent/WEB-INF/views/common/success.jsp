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
  <script type="text/javascript">
         $(function() {
             setInterval('litte()',1000);
         });

        function litte() {
            var count=$('font').html();
            count--;
            $('font').html(count);
            if(count<=0) {
                var parentWin=window.opener;
                window.close();
                parentWin.location.reload();
            }
        } 

        function closeWin() {
        	var parentWin=window.opener;
            window.close();
            parentWin.location.reload();
        }
  </script>
</head>
<body>
     操作成功，此页面将在<font color="red">4</font>后关闭<br>
     如若没有，请手动<a href="javascript:closeWin();">关闭</a>
</body>
</html>