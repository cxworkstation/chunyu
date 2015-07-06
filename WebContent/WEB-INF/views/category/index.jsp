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
<title>分类页轮播图管理</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
<script type="text/javascript">
  $(function() {
	 var pos='<%=request.getAttribute("position")%>';
	 if(pos!='null') {
		 $(document).scrollTop(pos);
	 }
	 
     $('input[name=imgFile]').change(function() {
         var $this=$(this);
         var position=$(document).scrollTop();
         $('input[name=position]').val(position);
         $this.parent('form').submit();
     }); 

     $('input[name=beSure]').click(function() {
         var $form0=$($('form')[0]);
         var $inputs=$form0.children('input');
         var img1=$($inputs[1]).val();
         var img2=$($inputs[2]).val();
         var img3=$($inputs[3]).val();
         var img4=$($inputs[4]).val();
         var img5=$($inputs[5]).val();
         var img6=$($inputs[6]).val();
         var img7=$($inputs[7]).val();
         var img8=$($inputs[8]).val();
         var cateId=$($('input[name=cateId]')[0]).val();
         if(img1==''||img2==''||img3==''||img4==''||img5==''||img6==''||img7==''||img8=='') {
             alert('还有图片没有上传');
             return;
         }

         var url='<%=basePath%>category/manage/addImg';
         $.ajax({
            url:url,
            type:'POST',
            data:{
                img1:img1,
                img2:img2,
                img3:img3,
                img4:img4,
                img5:img5,
                img6:img6,
                img7:img7,
                img8:img8,
                cateId:cateId
            },
            dataType:'text',
            success:function(data) {
                if(data=='1') {
                    $('input[name=beSure]').attr('disabled','disabled');
                    alert('操作成功');
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
   <!-- 轮播图第一个轮播图组合 -->
   <div style="border:solid green 1px;width:1018px;">
    <div style="border-bottom:solid green 1px;border-right:solid green 1px;width:1018px;height:362px;">
      <div style="border-right:solid green 1px;width:803px;height:362px;float:left;">
        <%
           Object o1=request.getAttribute("img1");
           if(o1!=null) {
        %>
          <a href="javascript:openWin('<%=basePath%>category/manage/addGoodsInput?goodsId=${goodsId0}&id=${id0}');"><img alt="" src="<%=basePath%><%=o1%>"></a>
        <%	   
           }
        %>
        <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">;' 
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="flag" value="0">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
      <div style="border-bottom:solid green 1px;width:210px;float:right;height:90px;">
        <%
           Object o2=request.getAttribute("img2");
           if(o2!=null) {
        %>
           <img alt="" src="<%=basePath%><%=o2%>">
        <%	   
           }
        %>
         <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="flag" value="1">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
    </div>
 
  <!--轮播图第二个轮播组合 -->
   <div style="border-top:solid green 1px;border-bottom:solid green 1px;width:1018px;height:362px;margin-top:20px;">
      <div style="border-right:solid green 1px;width:803px;height:362px;float:left;">
        <%
           Object o3=request.getAttribute("img3");
           if(o3!=null) {
        %>
           <a href="javascript:openWin('<%=basePath%>category/manage/addGoodsInput?goodsId=${goodsId1}&id=${id1}');"><img alt="" src="<%=basePath%><%=o3%>"></a>
        <%	   
           }
        %>
         <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="flag" value="2">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
      <div style="border-bottom:solid green 1px;width:210px;float:right;height:90px;">
        <%
           Object o4=request.getAttribute("img4");
           if(o4!=null) {
        %>
           <img alt="" src="<%=basePath%><%=o4%>">
        <%	   
           }
        %>
         <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="flag" value="3">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
    </div>
    
    <!--轮播图第三个轮播组合 -->
   <div style="border-top:solid green 1px;border-bottom:solid green 1px;width:1018px;height:362px;margin-top:20px;">
      <div style="border-right:solid green 1px;width:803px;height:362px;float:left;">
          <%
           Object o5=request.getAttribute("img5");
           if(o5!=null) {
        %>
           <a href="javascript:openWin('<%=basePath%>category/manage/addGoodsInput?goodsId=${goodsId2}&id=${id2}');"><img alt="" src="<%=basePath%><%=o5%>"></a>
        <%	   
           }
        %>
          <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="flag" value="4">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
      <div style="border-bottom:solid green 1px;width:210px;float:right;height:90px;">
        <%
           Object o6=request.getAttribute("img6");
           if(o6!=null) {
        %>
           <img alt="" src="<%=basePath%><%=o6%>">
        <%	   
           }
        %>
          <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="flag" value="5">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
    </div>
    
     <!--轮播图第四个轮播组合 -->
   <div style="border-top:solid green 1px;border-bottom:solid green 1px;width:1018px;height:362px;margin-top:20px;">
      <div style="border-right:solid green 1px;width:803px;height:362px;float:left;">
        <%
           Object o7=request.getAttribute("img7");
           if(o7!=null) {
        %>
           <a href="javascript:openWin('<%=basePath%>category/manage/addGoodsInput?goodsId=${goodsId3}&id=${id3}');"><img alt="" src="<%=basePath%><%=o7%>"></a>
        <%	   
           }
        %>
          <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="flag" value="6">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
      <div style="border-bottom:solid green 1px;width:210px;float:right;height:90px;">
         <%
           Object o8=request.getAttribute("img8");
           if(o8!=null) {
        %>
           <img alt="" src="<%=basePath%><%=o8%>">
        <%	   
           }
        %>
         <form action="<%=basePath%>category/manage/uploadImg" method="post" enctype="multipart/form-data">
           <input type="file" name="imgFile">
           <input type="hidden" name="flag" value="7">
           <input type="hidden" name="img1" value="${img1}">
           <input type="hidden" name="img2" value="${img2}">
           <input type="hidden" name="img3" value="${img3}">
           <input type="hidden" name="img4" value="${img4}">
           <input type="hidden" name="img5" value="${img5}">
           <input type="hidden" name="img6" value="${img6}">
           <input type="hidden" name="img7" value="${img7}">
           <input type="hidden" name="img8" value="${img8}">
           <input type="hidden" name="goodsId0" value="${goodsId0 }">
           <input type="hidden" name="goodsId1" value="${goodsId1 }">
           <input type="hidden" name="goodsId2" value="${goodsId2 }">
           <input type="hidden" name="goodsId3" value="${goodsId3 }">
           <input type="hidden" name="goodsId4" value="${goodsId4 }">
           <input type="hidden" name="goodsId5" value="${goodsId5 }">
           <input type="hidden" name="goodsId6" value="${goodsId6 }">
           <input type="hidden" name="goodsId7" value="${goodsId7 }">
           <input type="hidden" name="position" value="${position }">
           <input type="hidden" name="cateId" value="${cateId }">
        </form>
      </div>
    </div>
    
     <div style="margin-top:30px;height:50px;">
          <input type="button" name="beSure" value="  确定  ">
      </div>
    </div>
 </center>
</body>
</html>