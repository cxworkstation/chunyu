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
    <title>添加商品</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<style type="text/css">
        table { margin-top:10px;}
        td {border:1px solid #cbd8ac; border-collapse:collapse; font-size:14px; text-align:left; padding:10px; }
    </style>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript">
         function doit() {
             var img='<%=request.getAttribute("image")%>';
             if(img=='null') {
                 alert('必须给商品添加图片');
                 return;
             }
             var name=$('input[name=name]').val().trim();
             var introduction=$('textarea').val().trim();
             if(name=='') {
                 alert('商品名称不能为空');
                 return;
             }
             if(introduction=='') {
                 alert('商品简介不能为空');
                 return;
             }

             var good=$('input[name=good]').val();
             var id=$('input[name=id]').val();
             var inArea=$('select[name=inArea]').val();
             var onlyReservation=$('select[name=onlyReservation]').val();

             var url='<%=basePath%>homepage/manage/broad/addGoods';
             $.ajax({
                  url:url,
                  type:'POST',
                  data:{
                     name:name,
                     image:img,
                     introduction:introduction,
                     good:good,
                     id:id,
                     inArea:inArea,
                     onlyReservation:onlyReservation
                     },
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

       function uploadImg() {
           document.forms[0].submit();
       }
    </script>
  </head>
  <body>
 <center>
  <form action="<%=basePath %>homepage/manage/broad/uploadGoodsImg" method="post" enctype="multipart/form-data">
    <input type="hidden" name="image" value="${image}">
    <input type="hidden" name="good" value="${good}">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="goodsId" value="${goodsId}">
    <div style="border:solid green 1px;width:800px;height:443px;">
      <div style="border-right:solid green 1px;float:left;width:300px;height:443px;">
        <div>
           <h3>上传商品展示图片</h3>
         </div>
        <div style="border:solid green 1px;height:182px;width:150px;">
         <img alt="" src="<%=basePath%>${image}">
         <div style="margin-top:5px;width:150px;">
           <input type="file" name="imageFile" onchange="uploadImg();">
         </div>
        </div>
      </div>
      
      <div style="border-left:solid green 1px;float:right;height:443px;width:450px;">
        <div>
          <h4>添加商品部分信息</h4>
          <table>
             <tr>
               <td>商品名称</td>
               <td>
                  <input type="text" size="30" name="name" value="${name}">
               </td>
             </tr>
             
             <tr>
               <td>设置区域</td>
               <td>
                  <select name="inArea">
                     <%Object o=request.getAttribute("inArea");
                       int inArea=0;
                       if(o!=null) {
                    	   inArea=Integer.parseInt(o.toString());
                       }
                       if(inArea==0) {
                     %>
                        <option value="0" selected="selected">任何区域</option>
                        <option value="1">只限配送区域</option>
                     <%}else if(inArea==1) {%>
                        <option value="0">任何区域</option>
                        <option value="1" selected="selected">只限配送区域</option>
                     <%}%>
                  </select>
               </td>
             </tr>
             
             <tr>
               <td>是否为只可预定商品</td>
               <td>
                  <select name="onlyReservation">
                    <%Object o1=request.getAttribute("onlyReservation");
                      int only=0;
                      if(o1!=null) {
                    	 only=Integer.parseInt(o1.toString()); 
                      }
                      if(only==0) {
                    %>
                       <option value="0" selected="selected">不是</option>
                       <option value="1">是</option>
                     <%}else if(only==1) { %>
                       <option value="0">不是</option>
                       <option value="1"  selected="selected">是</option>
                     <%} %>
                  </select>
               </td>
             </tr>
             
             <tr>
               <td>
                                 商品简短介绍
               </td>
               <td>
                 <textarea rows="4" cols="30" name="introduction">
                   <%Object o2=request.getAttribute("introduction");
                     if(o2!=null) {
                    	 String introduction=o2.toString();
                   %>
                       <%=introduction%>
                   <%
                     }
                   %>
                 </textarea>
               </td>
             </tr>
          </table>
        </div>
      </div>
    </div>
     <div style="border:solid green 1px;width:800px;margin-top:10px;">
         <input type="button" value="确定" onclick="doit()" id="ok">&nbsp;&nbsp;
         <input type="button" value="关闭窗口" onclick="window.close();">
      </div>
</form>
</center>
  </body>
</html>
