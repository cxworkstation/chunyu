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
<title>特别推荐区域</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
<script type="text/javascript">
	    $(function() {
		    $('input[name=imgFile]').change(function() {
			    var $this=$(this);
			    $this.parent('form').submit();
			});

			$('input[name=beSure]').click(function() {
				var pic1=$('input[name=pic1]').val();
				var pic2=$('input[name=pic2]').val();
				if(pic1==''||pic2=='') {
					alert('未上传图片');
					return;
			    }

				var id1=$('input[name=id1]').val();
				var id2=$('input[name=id2]').val();
			    var url='<%=basePath%>homepage/manage/especial/addEspecialImg'
			    $.ajax({
				    url:url,
				    type:'POST',
				    data:{
					   pic1:pic1,
					   pic2:pic2,
					   id1:id1,
					   id2:id2
					},
					dataType:'text',
					success:function(data) {
						if(data=='1') {
							alert('操作成功');
							$('input[name=beSure]').attr('disabled','disabled');
							return;
						}
						alert('操作失败，请联系管理员');
					}
				});
		    });
		});
</script>
</head>
<body>
  <center>
    <div style="border:solid red 1px;height:600px;width:800px;">
     <!-- 图一 -->
       <div style="width:208px;height:110px;border:solid green 1px;">
         <% Object o1=request.getAttribute("pic1");
            if(o1!=null) {
         %>
          <a href="javascript:openWin('<%=basePath%>homepage/manage/especial/addEspecialGoodsInput?id=${id1}');"><img alt="" src="<%=basePath%><%=o1%>"></a>
         <%  	
            }
         %>
         <form action="<%=basePath%>homepage/manage/especial/uploadEspecialImg" method="post" enctype="multipart/form-data">
            <input type="file" name="imgFile">
            <input type="hidden" name="pic1" value="${pic1}">
            <input type="hidden" name="pic2" value="${pic2}">
            <input type="hidden" name="id1" value="${id1}">
            <input type="hidden" name="id2" value="${id2}">
            <input type="hidden" name="flag" value="0">
         </form>
       </div>
       
        <!-- 图二 -->
       <div style="width:208px;height:110px;border:solid green 1px;margin-top:20px;">
         <% Object o2=request.getAttribute("pic2");
            if(o2!=null) {
         %>
         <a href="javascript:openWin('<%=basePath%>homepage/manage/especial/addEspecialGoodsInput?id=${id1}');"> <img alt="" src="<%=basePath%><%=o2%>"></a>
         <%  	
            }
         %>
         <form action="<%=basePath%>homepage/manage/especial/uploadEspecialImg" method="post" enctype="multipart/form-data">
            <input type="file" name="imgFile">
            <input type="hidden" name="pic1" value="${pic1}">
            <input type="hidden" name="pic2" value="${pic2}">
            <input type="hidden" name="id1" value="${id1}">
            <input type="hidden" name="id2" value="${id2}">
            <input type="hidden" name="flag" value="1">
         </form>
       </div>
       <div style="margin-top:40px;">
          <input type="button" value="确定" name="beSure">
       </div>
    </div>
  </center>
</body>
</html>