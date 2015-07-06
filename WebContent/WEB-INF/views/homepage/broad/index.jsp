<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.chunyun.web.model.BroadCast"%>
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
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
<script type="text/javascript">
    $(function() {
    	var h0='<%=request.getParameter("h")%>';
    	if(h0!='null') {
			$(window).scrollTop(h0);
		}
    	$('input[type=file]').change(function() {
    		var h=$(window).scrollTop();
    		$('input[name=h]').val(h);
    		$(this).parent('form').submit();
    	});
    	
    	$('input[name=uploadImg]').click(function() {
    		var $this=$(this);
    		var formDom=$this.parent().parent().parent().prev('div').children('form')[0];
    		var array=$(formDom).children();
    		var index=$(array[7]).val();
    		index=parseInt(index);//默认情况下，index作为字符串，因此会将+号当成字符串连接符
    		var _index=index+8;
    		var id=$(array[_index]).val();
    		var path=$(array[index]).val();
    		if(path=='') {
    			alert('请先选择上传的图片');
    			return;
    		}
    		
            var url='<%=basePath%>homepage/manage/broad/addBroadImg';
    		$.ajax({
    			url:url,
    			type:'POST',
    			data:{
    				id:id,
    				path:path,
    				index:index
    			},
    			dataType:'text',
    			success:function(result) {
    				if(result>=1) {
    					$this.parent().html('<font style="color:red">上传成功</font>');
    					$($(formDom).children()[_index]).val(result);
    					return;
    				}
    				alert('上传失败，请联系管理员');
    			}
    		});
    	});
    });
</script>
</head>
<body>
  <div style="width:100%;margin:auto auto;">
    <div style="width:100%;height:355px;">
      <div style="width:798px;height:384px;border:solid red 1px;float:left;text-align:center;">
       <% if(request.getAttribute("pic1")!=null) {%>
        <img src="<%=basePath%>${pic1}">
        <%} %>
        <form action="<%=basePath %>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
           <input type="file" name="file">
           <input type="hidden" name="pic1" value="${pic1 }">
           <input type="hidden" name="pic2" value="${pic2 }">
           <input type="hidden" name="pic3" value="${pic3 }">
           <input type="hidden" name="pic4" value="${pic4 }">
           <input type="hidden" name="pic5" value="${pic5 }">
           <input type="hidden" name="pic6" value="${pic6 }">
           <input type="hidden" name="flag" value="1">
           <input type="hidden" name="h" value="${h}">
           <input type="hidden" name="id1" value="${id1}">
           <input type="hidden" name="id2" value="${id2}">
           <input type="hidden" name="id3" value="${id3}">
           <input type="hidden" name="id4" value="${id4}">
           <input type="hidden" name="id5" value="${id5}">
           <input type="hidden" name="id6" value="${id6}">
        </form>
      </div>
      <div style="width:200px;height:354px;float:left;border:solid red 1px;">
        <ul style="padding-top:120px;padding-left:80px;">
          <li>
              <input type="button" value="上传" name="uploadImg" index="0"/>
          </li>
          <li style="margin-top:6px;">
              <input type="button" value="编辑" />
          </li>
        </ul>
      </div>
    </div>
    
     <div style="width:100%;height:355px;">
      <div style="width:798px;height:384px;border:solid red 1px;float:left;text-align:center;">
        <% if(request.getAttribute("pic2")!=null) {%>
         <img src="<%=basePath%>${pic2}">
        <%} %>
        <form action="<%=basePath %>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
         <input type="file" name="file">
           <input type="hidden" name="pic1" value="${pic1 }">
           <input type="hidden" name="pic2" value="${pic2 }">
           <input type="hidden" name="pic3" value="${pic3 }">
           <input type="hidden" name="pic4" value="${pic4 }">
           <input type="hidden" name="pic5" value="${pic5 }">
           <input type="hidden" name="pic6" value="${pic6 }">
           <input type="hidden" name="flag" value="2">
           <input type="hidden" name="h" value="${h}">
           <input type="hidden" name="id1" value="${id1}">
           <input type="hidden" name="id2" value="${id2}">
           <input type="hidden" name="id3" value="${id3}">
           <input type="hidden" name="id4" value="${id4}">
           <input type="hidden" name="id5" value="${id5}">
           <input type="hidden" name="id6" value="${id6}">
           </form>
      </div>
      <div style="width:200px;height:354px;float:left;border:solid red 1px;">
        <ul style="padding-top:120px;padding-left:80px;">
          <li>
              <input type="button" value="上传" name="uploadImg" index="1"/>
          </li>
          <li style="margin-top:6px;">
              <input type="button" value="编辑" />
          </li>
        </ul>
      </div>
    </div>
    
     <div style="width:100%;height:355px;">
      <div style="width:798px;height:384px;border:solid red 1px;float:left;text-align:center;">
      <% if(request.getAttribute("pic3")!=null) {%>
       <img src="<%=basePath%>${pic3}">
       <%} %>
        <form action="<%=basePath %>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
         <input type="file" name="file">
           <input type="hidden" name="pic1" value="${pic1 }">
           <input type="hidden" name="pic2" value="${pic2 }">
           <input type="hidden" name="pic3" value="${pic3 }">
           <input type="hidden" name="pic4" value="${pic4 }">
           <input type="hidden" name="pic5" value="${pic5 }">
           <input type="hidden" name="pic6" value="${pic6 }">
           <input type="hidden" name="flag" value="3">
           <input type="hidden" name="h" value="${h}">
           <input type="hidden" name="id1" value="${id1}">
           <input type="hidden" name="id2" value="${id2}">
           <input type="hidden" name="id3" value="${id3}">
           <input type="hidden" name="id4" value="${id4}">
           <input type="hidden" name="id5" value="${id5}">
           <input type="hidden" name="id6" value="${id6}">
           </form>
      </div>
      <div style="width:200px;height:354px;float:left;border:solid red 1px;">
        <ul style="padding-top:120px;padding-left:80px;">
          <li>
              <input type="button" value="上传" name="uploadImg" index="2"/>
          </li>
          <li style="margin-top:6px;">
              <input type="button" value="编辑" />
          </li>
        </ul>
      </div>
    </div>
    
    <div style="width:100%;height:355px;">
      <div style="width:798px;height:384px;border:solid red 1px;float:left;text-align:center;">
      <% if(request.getAttribute("pic4")!=null) {%>
       <img src="<%=basePath%>${pic4}">
       <%} %>
        <form action="<%=basePath %>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
          <input type="file" name="file">
           <input type="hidden" name="pic1" value="${pic1 }">
           <input type="hidden" name="pic2" value="${pic2 }">
           <input type="hidden" name="pic3" value="${pic3 }">
           <input type="hidden" name="pic4" value="${pic4 }">
           <input type="hidden" name="pic5" value="${pic5 }">
           <input type="hidden" name="pic6" value="${pic6 }">
           <input type="hidden" name="flag" value="4">
           <input type="hidden" name="h" value="${h}">
           <input type="hidden" name="id1" value="${id1}">
           <input type="hidden" name="id2" value="${id2}">
           <input type="hidden" name="id3" value="${id3}">
           <input type="hidden" name="id4" value="${id4}">
           <input type="hidden" name="id5" value="${id5}">
           <input type="hidden" name="id6" value="${id6}">
           </form>
      </div>
      <div style="width:200px;height:354px;float:left;border:solid red 1px;">
        <ul style="padding-top:120px;padding-left:80px;">
          <li>
              <input type="button" value="上传" name="uploadImg" index="3"/>
          </li>
          <li style="margin-top:6px;">
              <input type="button" value="编辑" />
          </li>
        </ul>
      </div>
    </div>
    
    <div style="width:100%;height:355px;">
      <div style="width:798px;height:384px;border:solid red 1px;float:left;text-align:center;">
      <% if(request.getAttribute("pic5")!=null) {%>
      <img src="<%=basePath%>${pic5}">
      <%} %>
        <form action="<%=basePath %>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
         <input type="file" name="file">
           <input type="hidden" name="pic1" value="${pic1 }">
           <input type="hidden" name="pic2" value="${pic2 }">
           <input type="hidden" name="pic3" value="${pic3 }">
           <input type="hidden" name="pic4" value="${pic4 }">
           <input type="hidden" name="pic5" value="${pic5 }">
           <input type="hidden" name="pic6" value="${pic6 }">
           <input type="hidden" name="flag" value="5">
           <input type="hidden" name="h" value="${h}">
           <input type="hidden" name="id1" value="${id1}">
           <input type="hidden" name="id2" value="${id2}">
           <input type="hidden" name="id3" value="${id3}">
           <input type="hidden" name="id4" value="${id4}">
           <input type="hidden" name="id5" value="${id5}">
           <input type="hidden" name="id6" value="${id6}">
           </form>
      </div>
      <div style="width:200px;height:354px;float:left;border:solid red 1px;">
        <ul style="padding-top:120px;padding-left:80px;">
         <li>
              <input type="button" value="上传" name="uploadImg" index="4"/>
          </li>
          <li style="margin-top:6px;">
              <input type="button" value="编辑" />
          </li>
        </ul>
      </div>
    </div>
    
    <div style="width:100%;height:355px;">
      <div style="width:798px;height:384px;border:solid red 1px;float:left;text-align:center;">
      <% if(request.getAttribute("pic6")!=null) {%>
      <img src="<%=basePath%>${pic6}">
      <%} %>
        <form action="<%=basePath %>homepage/manage/broad/add" method="post" enctype="multipart/form-data">
         <input type="file" name="file">
           <input type="hidden" name="pic1" value="${pic1 }">
           <input type="hidden" name="pic2" value="${pic2 }">
           <input type="hidden" name="pic3" value="${pic3 }">
           <input type="hidden" name="pic4" value="${pic4 }">
           <input type="hidden" name="pic5" value="${pic5 }">
           <input type="hidden" name="pic6" value="${pic6 }">
           <input type="hidden" name="flag" value="6">
           <input type="hidden" name="h" value="${h}">
           <input type="hidden" name="id1" value="${id1}">
           <input type="hidden" name="id2" value="${id2}">
           <input type="hidden" name="id3" value="${id3}">
           <input type="hidden" name="id4" value="${id4}">
           <input type="hidden" name="id5" value="${id5}">
           <input type="hidden" name="id6" value="${id6}">
           </form>
      </div>
      <div style="width:200px;height:354px;float:left;border:solid red 1px;">
        <ul style="padding-top:120px;padding-left:80px;">
          <li>
              <input type="button" value="上传" name="uploadImg" index="5"/>
          </li>
          <li style="margin-top:6px;">
              <input type="button" value="编辑" />
          </li>
        </ul>
      </div>
    </div>
   </div>
</body>
</html>