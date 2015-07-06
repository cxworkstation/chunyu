<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>给商品上传图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
    
    <script type="text/javascript">
        $(function() {
            var y=<%=request.getAttribute("screenY")%>;
            if(y!=null) {
        	 window.scrollTo(0,y);
            }
        });
    
        function fileBtn(flag) {
            var y=window.pageYOffset;
            $('input[name=screenY]').val(y);
            document.forms[flag].submit();
        } 

        function ok() {
             var img0=$('input[name=img0]').val();
             var subImg0=$('input[name=subImg0]').val();
             if(img0!=''&&subImg0==''||img0==''&&subImg0!='') {
                  alert('第1组图不完整');
                  return;
             }

             var img1=$('input[name=img1]').val();
             var subImg1=$('input[name=subImg1]').val();
             if(img1!=''&&subImg1==''||img1==''&&subImg1!='') {
                  alert('第2组图不完整');
                  return;
             }

             var img2=$('input[name=img2]').val();
             var subImg2=$('input[name=subImg2]').val();
             if(img2!=''&&subImg2==''||img2==''&&subImg2!='') {
                  alert('第3组图不完整');
                  return;
             }

             var img3=$('input[name=img3]').val();
             var subImg3=$('input[name=subImg3]').val();
             if(img3!=''&&subImg3==''||img3==''&&subImg3!='') {
                  alert('第4组图不完整');
                  return;
             }

             if(img0==''&&img1==''&&img2==''&&img3=='') {
                 alert('至少上传一组图片才能添加');
                 return;
             }
             
             var url='<%=basePath%>goods/manage/addMoreImg';
             var id=$('input[name=id]').val();
             var subImgId=$('input[name=subImgId]').val();
             $.ajax({
                url:url,
                type:'POST',
                data:{
                  id:id,
                  img0:img0,
                  img1:img1,
                  img2:img2,
                  img3:img3,
                  subImg0:subImg0,
                  subImg1:subImg1,
                  subImg2:subImg2,
                  subImg3:subImg3,
                  subImgId:subImgId
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
  </head>
  
  <body>
    <center>
       <div style="border:solid green 1px;height:3700px;width:1185px;">
          <div><h4>第一组图片</h4></div>
          <div style="width:1185px;height:800px;border-top:solid green 1px;border-bottom:solid green 1px">
           <div  style="border-bottom:solid green 1px;height:380px;width:380px;float:left;">
               <img alt="" src="<%=basePath%>${img0}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(0);">
                  <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }"> 
                  <input type="hidden" name="flag" value="0">
               </form>
           </div>
            <div  style="border-left:solid green 1px;height:800px;width:800px;float:left">
               <img alt="" src="<%=basePath%>${subImg0}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(1);">
                 <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }"> 
                  <input type="hidden" name="flag" value="1">
               </form>
           </div>
          </div>
          
          <div><h4>第二组图片</h4></div>
           <div style="width:1185px;border-top:solid green 1px;border-bottom:solid green 1px;height:800px;">
           <div  style="border-bottom:solid green 1px;height:380px;width:380px;float:left;">
               <img alt="" src="<%=basePath%>${img1}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(2);">
                 <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }"> 
                  <input type="hidden" name="flag" value="2">
               </form>
           </div>
            <div  style="border-left:solid green 1px;height:800px;width:800px;float:left">
               <img alt="" src="<%=basePath%>${subImg1}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(3);">
                  <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }"> 
                  <input type="hidden" name="flag" value="3">
               </form>
           </div>
          </div>
          
          <div><h4>第三组图片</h4></div>
           <div style="width:1185px;border-top:solid green 1px;border-bottom:solid green 1px;height:800px;">
           <div  style="border-bottom:solid green 1px;height:380px;width:380px;float:left;">
               <img alt="" src="<%=basePath%>${img2}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(4);">
                  <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }">                   
                  <input type="hidden" name="flag" value="4">
               </form>
           </div>
            <div  style="border-left:solid green 1px;height:800px;width:800px;float:left">
               <img alt="" src="<%=basePath%>${subImg2}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(5);">
                  <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }"> 
                  <input type="hidden" name="flag" value="5">
               </form>
           </div>
          </div>
          
          <div><h4>第四组图片</h4></div>
           <div style="width:1185px;border-top:solid green 1px;border-bottom:solid green 1px;height:800px;">
           <div  style="border-bottom:solid green 1px;height:380px;width:380px;float:left;">
               <img alt="" src="<%=basePath%>${img3}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(6);">
                  <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }"> 
                  <input type="hidden" name="flag" value="6">
               </form>
           </div>
            <div  style="border-left:solid green 1px;height:800px;width:800px;float:left">
               <img alt="" src="<%=basePath%>${subImg3}">
               <form action="<%=basePath%>goods/manage/upMoreImg" method="post" enctype="multipart/form-data">
                  <input type="file" name="mfile" onchange="fileBtn(7);">
                  <input type="hidden" name="id" value="${id}">
                  <input type="hidden" name="img0" value="${img0}">
                  <input type="hidden" name="img1" value="${img1}">
                  <input type="hidden" name="img2" value="${img2}">
                  <input type="hidden" name="img3" value="${img3}">
                  <input type="hidden" name="subImg0" value="${subImg0}">
                  <input type="hidden" name="subImg1" value="${subImg1}">
                  <input type="hidden" name="subImg2" value="${subImg2}">
                  <input type="hidden" name="subImg3" value="${subImg3}">
                  <input type="hidden" name="subImgId" value="${subImgId}">
                  <input type="hidden" name="screenY" value="${screenY }"> 
                  <input type="hidden" name="flag" value="7">
               </form>
           </div>
          </div>
          <div style="margin-top:20px;height:40px;">
              <input type="button" value="确定" onclick="ok();" id="ok">&nbsp;&nbsp;
              <input type="button" value="返回" onclick="javascript:location='<%=basePath%>goods/manage/index'" id="ok">
          </div>
      </div>
    </center>
  </body>
</html>
