<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设置商品规格与单价</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
    <script type="text/javascript">
       $(function() {
           var id_;
           $('input[type=radio]').each(function(i,e) {
               var $radio=$(e);
               if($radio.attr('checked')==true) {
                   id_=$radio.attr('id').substring(4);
               }
           });
           $('input[type=radio]').change(function() {
                var $this=$(this);
                var idStr=$this.attr('id');
                var id=idStr.substring(4);
                if(id==id_) {
                    return;
                }
                var url='<%=basePath%>goods/manage/setPriceState?id='+id+'&id_='+id_;
                $.ajax({
                  url:url,
                  type:'GET'
                    });
               });
           });
    
       function newWin(url){
    	   var screenW=screen.availWidth;
    	   var screenH=screen.availHeight;
    	   var w=screenW/5;
    	   var h=screenH/5;
    	   window.open(url,'newwin','height='+h+',width='+w+',top='+2*h+',left='+2*w+',toolbar=no,menubar=no,scrollbars=yes,resizable=no');
       }

    </script>
    <style type="text/css">
        table{border:solid green 1px;width:600px;margin-top:15px;}
        tr {border:solid green 1px}
        td {font:14px; text-align:center; padding:10px; }
    </style>
  </head>
  
  <body>
   <center>
      <div style="border:solid green 1px;height:600px;width:800px;">
            <div>
               <h3 style="color:red;font-size:12px;">提示：规格设置(例如：300ml、200ml;1斤、2g;1包;1份;1条)
                  <br>设置规格的同时设置这种规格的单位价格和重量(作为运费的标准)<br>
               </h3>
            </div>
            
            <div style="margin-top:30px;">
              <div style="border:solid green 1px;text-align:left;font-size:12px;background-image:url('<%=basePath%>imgs/backstage/wbg.gif');">商品【${name}】已有规格及其单价、重量列表</div>
              <table>
                 <tr>
                  <td style="border-right:solid green 1px;border-bottom:solid green 1px ">标价</td>
                  <td style="border-right:solid green 1px;border-bottom:solid green 1px ">商品规格</td>
                  <td style="border-right:solid green 1px;border-bottom:solid green 1px">商品单价</td>
                  <td style="border-right:solid green 1px;border-bottom:solid green 1px">商品货重(g)</td>
                  <td style="border-bottom:solid green 1px">操作</td>
                 </tr>
                 <c:if test="${!empty goodPrices }">
                 <c:forEach items="${goodPrices}" var="good">
                 <tr id="${good.id}">
                   <td style="border-right:solid green 1px;border-bottom:solid green 1px">
                     <c:if test="${good.isShow eq 1}">
                      <input type="radio" id="good${good.id}" checked="checked" name="radio">
                     </c:if>
                     <c:if test="${good.isShow eq 0}">
                      <input type="radio" id="good${good.id}" name="radio">
                     </c:if>
                   </td>
                   <td style="border-right:solid green 1px;border-bottom:solid green 1px">${good.size}</td>
                   <td style="border-right:solid green 1px;border-bottom:solid green 1px">${good.price}</td>
                   <td style="border-right:solid green 1px;border-bottom:solid green 1px">${good.weight}</td>
                   <td style="border-bottom:solid green 1px">
                     <a href="javascript:del('<%=basePath%>goods/manage/delSizeAttr?id=${good.id}');">删除</a>  
                   </td>
                 </tr>
                 </c:forEach>
                 </c:if>
                 <c:if test="${empty goodPrices}">
                   <tr>
                     <td colspan="4">槽糕，还没有给改商品提供规格与单价</td>
                   </tr>
                 </c:if>
                 <tr>
                   <td colspan="4">
                     <input type="button" value="添加规格" onclick="newWin('<%=basePath%>goods/manage/sizeAttrInput?id=${goodId}')">&nbsp;
                     <input type="button" value="返回" onclick="javascript:location='<%=basePath%>goods/manage/index'">
                   </td>
                 </tr>
              </table>
            </div>
            
      </div>
   <!--  
     <form action="goods/manage/setPrice" method="post">
      <input type="hidden" name="goodId" id="goodId" value="${goodId}"/>
      
      <table style="border:solid red 1px;">
        <tr>
          <td>提示：规格设置(例如：300ml、200ml;1斤、2g;1包;1份;1条)</td>
          <td>此类尺码单价设置</td>
        </tr>
        <tr>
          <td colspan="2">已有设置</td>
        </tr>
        <c:forEach items="${goodPrices}" var="good">
        <tr>
          <td>${good.size}</td>
          <td>${good.price}</td>
        </tr>
       </c:forEach>
       <tr>
         <td colspan="2">
            <input type="button" value="添加新的价格设置" onclick="setPrice()"/>
         </td>
       </tr>
       <tr id="setPriceTr">
         <td>
           <input type="text" name="size" id="size" />
         </td>
         <td>
           <input type="text" name="price" id="price"/>
         </td>
      </tr>
      <tr>
          <td colspan="2">
              <input type="submit" value="提交价格设置"/>
          </td>
      </tr>
   </table>
  </form>
  -->
  </center>
  </body>
</html>
