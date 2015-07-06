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
<style type="text/css">
  td {text-align:center;padding-top:4px;font-size:12px;}
</style>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
 <script type="text/javascript">
    function beSure() {
         var size=$('input[name=size]').val().trim();
         var price=$('input[name=price]').val().trim();
         var weight=$('input[name=weight]').val().trim();
         var reg=/^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$/;//匹配非负浮点型数和0
         var reg1=/^[1-9]\d*|0$/;//匹配正整数和0
         if(size=='') {
             alert('商品规格不能为空');
             return;
         }
         if(!reg1.test(price)&&!reg.test(price)) {
             alert('价格格式不合法');
             return;
         }

         if(weight!=''&&!reg.test(weight)&&!reg1.test(weight)) {
             alert('重量格式不合法');
             return;
         }
        document.forms[0].submit();
    }
 </script>
</head>
<body>
  <center>
    <div>
      <h4>给商品【${name}】添加规格</h4>
    </div>
    <form action="<%=basePath%>goods/manage/setPrice" method="post">
      <input type="hidden" name="id" value="${id}">
       <table>
         <tr>
           <td>商品规格</td>
           <td>
              <input type="text" size="20" name="size">
           </td>
         </tr>
         <tr>
            <td>商品单价</td>
            <td>
               <input type="text" size="20" name="price">
            </td>
         </tr>
         <tr>
            <td>此规格货重</td>
            <td>
               <input type="text" size="20" name="weight">（以<font color="color">g</font>为单位）
            </td>
         </tr>
         <tr>
           <td>
             <input type="button" value="确定" onclick="beSure();">
           </td>
           <td>
             <input type="button" value="关闭窗口" onclick="window.close();">
           </td>
         </tr>
       </table>
    </form>
  </center>
</body>
</html>