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
   $(function() {
        var mode="<%=request.getAttribute("mode")%>";
        if(mode=='mode2') {
           $('#mode2').css('display','block');
           $('#mode1').css('display','none');
        }else{
           $('#mode2').css('display','none');
           $('#mode1').css('display','block');
        }
        
	   });
   function changeMode(thisO) {
	   var $this=$(thisO);
	   var $div=$this.parent().parent();
	   var id=$div.attr('id');
	   $div.css('display','none');
	   var $mode=$('input[name=mode]');
	   if(id=='mode1') {
         $('#mode2').css('display','block');
         $mode.val('mode2');
	   }else {
		   $('#mode1').css('display','block');
          $mode.val('mode1');
		}
   }

   function fileBtn(flag) {
	   if(flag==11) {
		   var desc11=$($('input[name=desc11]')[flag]).val().trim();
		   var desc12=$($('input[name=desc12]')[flag]).val().trim();
		   var desc13=$($('input[name=desc13]')[flag]).val().trim();
		   if(desc11==''||desc12==''||desc13=='') {
			   alert('该图片的对应链接信息没有填写全');
			   return;
		   }
		   if(desc11.length>=16) {
			   alert('第一条链接信息超过了16个字符');
			   return;
		   }
		   if(desc12.length>=16) {
			   alert('第二条链接信息超过了16个字符');
			   return;
		   }
		   if(desc13.length>=16) {
			   alert('第三条链接信息超过了16个字符');
			   return;
		   }
	   }else if(flag==12) {
		   var desc21=$($('input[name=desc21]')[flag]).val().trim();
		   var desc22=$($('input[name=desc22]')[flag]).val().trim();
		   var desc23=$($('input[name=desc23]')[flag]).val().trim();
		   if(desc21==''||desc22==''||desc23=='') {
			   alert('该图片的对应链接信息没有填写全');
			   return;
		   }
		   if(desc21.length>=16) {
			   alert('第一条链接信息超过了16个字符');
			   return;
		   }
		   if(desc22.length>=16) {
			   alert('第二条链接信息超过了16个字符');
			   return;
		   }
		   if(desc23.length>=16) {
			   alert('第三条链接信息超过了16个字符');
			   return;
		   }
	   }
       document.forms[flag].submit();
   }


   function delClassifyGoods() {
	   var classify=$($('input[name=classify]')[0]).val();
       var url='<%=basePath%>homepage/manage/classify/delClassifyGoods';
       $.ajax({
           url:url,
           type:'POST',
           data:{
             classify:classify
           },
           dataType:'text',
           success:function(data) {
               if(data=='1') {
                   $('#beSure').attr('disabled','disabled');
                   $('#delBtn').attr('disabled','disabled');
                   alert('操作成功');
               }

               alert('操作失败，请联系管理员');
           }
       }); 
    }
   function beSure() {
     var $form0=$($('form')[0]);
     var $inputs=$form0.children('input');
     var pic1=$($inputs[1]).val();
     var pic2=$($inputs[2]).val();
     var pic3=$($inputs[3]).val();
     var pic4=$($inputs[4]).val();
     var pic5=$($inputs[5]).val();
     var pic6=$($inputs[6]).val();
     var pic7=$($inputs[7]).val();
     var pic8=$($inputs[8]).val();
     var pic9=$($inputs[9]).val();
     var pic10=$($inputs[10]).val();
     var pic11=$($inputs[11]).val();
     var pic12=$($inputs[12]).val();
     var pic13=$($inputs[13]).val();
     var pic14=$($inputs[14]).val();
     var pic15=$($inputs[15]).val();
     var pic16=$($inputs[16]).val();
     var desc11=$($inputs[17]).val().trim();
     var desc12=$($inputs[18]).val().trim();
     var desc13=$($inputs[19]).val().trim();
     var desc21=$($inputs[20]).val().trim();
     var desc22=$($inputs[21]).val().trim();
     var desc23=$($inputs[22]).val().trim();
     var classify=$($inputs[23]).val();
     var mode=$($inputs[24]).val();
     var id=$($inputs[25]).val();
     if(pic1==''||pic2==''||pic3==''||pic4==''||pic5==''||pic6==''||pic7==''||pic8==''||pic9==''
         ||pic10==''||pic11=='') {
          alert('还存在没有上传的图片');
          return;
         }
     if(mode=='mode1') {
         if(pic12==''||pic13==''||pic14=='') {
        	 alert('还存在没有上传的图片');
             return;
         }

         if(desc11==''||desc12==''||desc13=='') {
             alert('模式一下第一张图片下的相关链接没有添加');
             return;
         }

         if(desc11.length>=16||desc12.length>=16||desc13.length>=16) {
             alert('模式一下的第一张图片下的相关链接超出了16个字符');
             return;
         }
     }
     if(mode=='mode2') {
        if(pic15==''||pic16=='') {
        	 alert('还存在没有上传的图片');
             return;
         }

        if(desc21==''||desc22==''||desc23=='') {
            alert('模式一下第一张图片下的相关链接没有添加');
            return;
        }

        if(desc21.length>=16||desc22.length>=16||desc23.length>=16) {
            alert('模式一下的第一张图片下的相关链接超出了16个字符');
            return;
        }
     }
     var url="<%=basePath%>homepage/manage/classify/addClassifyPic";
     $.ajax({
         url:url,
         type:'POST',
         data:{
             pic1:pic1,
             pic2:pic2,
             pic3:pic3,
             pic4:pic4,
             pic5:pic5,
             pic6:pic6,
             pic7:pic7,
             pic8:pic8,
             pic9:pic9,
             pic10:pic10,
             pic11:pic11,
             pic12:pic12,
             pic13:pic13,
             pic14:pic14,
             pic15:pic15,
             pic16:pic16,
             desc11:desc11,
             desc12:desc12,
             desc13:desc13,
             desc21:desc21,
             desc22:desc22,
             desc23:desc23,
             classify:classify,
             mode:mode,
             id:id
         },
         dataType:'text',
         success:function(data) {
            if(data==1) {
                alert('操作成功');
                $('#beSure').attr('disabled','disabled');
                return;
             }
            alert('操作失败，请联系管理员');
          }
         });
   }
</script>
<style type="text/css">
  ul li {
    width:150px;
    height:150px;
    list-style-type: none;
    float:left;
    border:solid green 1px;
    margin-left:10px;
  }
</style>
</head>
<body>
    <center>
       <% Object picArrayObj=request.getAttribute("picArray");
          String[]picArray=(String[])picArrayObj;
          Object descArrayObj=request.getAttribute("descArray");
          String[]descArray=(String[])descArrayObj;
          String[] descName=new String[]{"desc11","desc12","desc13","desc21","desc22","desc23"};
       %>
       <div style="border:solid green 1px;width:1192px;height:1539px;"> 
          <div><h2>${classify}</h2></div>
          <div style="border-top:solid green 1px;border-bottom:solid green 1px;width:1192px;height:1339px;">
          
          <!-- 上传左边图片区域 -->
            <div style="float:left;width:177px">
               <!-- 左边第一张图片 -->
               <div style="height:413px;border-bottom:solid green 1px">
                  <%
                    if(picArray[0]!=null) {
                  %>
                  <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic1&classifyId=${classifyId}');">
                  <img alt="" src="<%=basePath%><%=picArray[0]%>"></a>
                  <%} %>
                  <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(0);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="flag" value="0">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                     
                  </form>
               </div>
               
               <!-- 左边第二张图片 -->
               <div style="height:413px;border-top:solid green 1px;border-bottom:solid green 1px;
                   border-right:solid green 1px;margin-top:20px;">
                <% 
                   if(picArray[1]!=null) {
                %>
                  <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic2&classifyId=${classifyId}')">
                  <img alt="" src="<%=basePath%><%=picArray[1]%>"></a>
                 <%} %>
                 <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(1);">
                    <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                      <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="1">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                  </form>
               </div>
               
               <!-- 左边第三张图片 -->
               <div style="height:413px;border-top:solid green 1px;border-bottom:solid green 1px;
                   border-right:solid green 1px;margin-top:20px;margin-top: 20px;">
                  <% 
                     if(picArray[2]!=null) {
                  %>
                    <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic3&classifyId=${classifyId}');">
                    <img alt="" src="<%=basePath%><%=picArray[2]%>"></a>
                  <%} %>
                  <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(2);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                      <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="2">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                  </form>
               </div>
            </div>
            <!-- 上传左边图片区域结束 -->
            
            <!-- 中间八个商品图片的上传 -->
            <div style="float:left;width:804px;border-left:solid green 1px;border-bottom:solid green 1px;
            border-right:solid green 1px;height:412px;">
              <div style="height:150px;">
                <ul>
                   <li>
                   <% 
                      if(picArray[3]!=null) {
                   %>
                      <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic4&classifyId=${classifyId}');">
                       <img alt="" src="<%=basePath%><%=picArray[3]%>"></a>
                     <%} %>
                  <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                    <input type="file" name="file" onchange="fileBtn(3);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                    <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="3">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                     </form>
                   </li>
                   <li>
                   <% 
                     if(picArray[4]!=null) {
                   %>
                    <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic5&classifyId=${classifyId}');">
                     <img alt="" src="<%=basePath%><%=picArray[4]%>"></a>
                    <%} %>
                    <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(4);">
                    <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="4">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                     </form>
                   </li>
                   <li>
                     <%
                       if(picArray[5]!=null) {
                     %>
                       <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic6&classifyId=${classifyId}');">
                        <img alt="" src="<%=basePath%><%=picArray[5]%>"></a>
                      <%} %>
                   <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(5);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                      <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="5">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                      </form>
                   </li>
                   <li>
                    <%
                       if(picArray[6]!=null) {
                    %>
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic7&classifyId=${classifyId}');">
                     <img alt="" src="<%=basePath%><%=picArray[6]%>"></a>
                     <%} %>
                   <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(6);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                      <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="6">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                       </form>
                   </li>
                </ul>
              </div>
              <div style="height:150px;margin-top:40px;">
              <ul>
                   <li>
                    <%
                      if(picArray[7]!=null) {
                    %>
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic8&classifyId=${classifyId}');">
                       <img alt="" src="<%=basePath%><%=picArray[7]%>"></a>
                     <%} %>
                    <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(7);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="7">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                     </form>
                   </li>
                   <li>
                   <%
                      if(picArray[8]!=null) {
                   %>
                     <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic9&classifyId=${classifyId}');">
                       <img alt="" src="<%=basePath%><%=picArray[8]%>"></a>
                     <%} %>
                    <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(8);">
                    <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="8">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                     </form>
                   </li>
                   <li>
                     <% 
                        if(picArray[9]!=null) {
                     %>
                       <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic10&classifyId=${classifyId}');">
                        <img alt="" src="<%=basePath%><%=picArray[9]%>"></a>
                      <%} %>
                    <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(9);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                      <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="9">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                     </form>
                   </li>
                   <li>
                   <% 
                      if(picArray[10]!=null) {
                   %>
                      <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic11&classifyId=${classifyId}');">
                       <img alt="" src="<%=basePath%><%=picArray[10]%>"></a>
                     <%} %>
                   <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(10);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="10">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                     </form>
                   </li>
                </ul>
              </div>
            </div>
            
            <!-- 最右边图片的上传 -->
            
            <!-- 模式一下的三张图片加六条链接信息 -->
            <div style="float:right;width:206px;border-bottom:solid green 1px;height:412px;display:block" id="mode1">
               <div style="height:110px;border:solid green 1px;">
              <% 
                 if(picArray[11]!=null) {
              %>
                 <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic12&classifyId=${classifyId}');">
                   <img alt="" src="<%=basePath%><%=picArray[11]%>">
                 </a>
               <%} %>
                 <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(11);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                      <%for(int i=0;i<6;i++) {
                    	 String str="";
                    	 String m="hidden";
                    	 String v="";
                         if(i<=2) {
                        	 str="style=\"margin-top:5px;width:184px;\"";
                        	 m="text";
                         }
                         if(descArray[i]!=null) {
                    		 v=descArray[i];
                    	 }
                      %>
                        <input type="<%=m%>" name="<%=descName[i] %>" value="<%=v%>" <%=str%>>
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="11">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                 </form>
               </div>
               <div style="height:110px;border:solid green 1px;margin-top:144px;">
               <%
                 if(picArray[12]!=null) {
               %>
                <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic13&classifyId=${classifyId}');">
                <img alt="" src="<%=basePath%><%=picArray[12]%>"></a>
               <%} %>
                 <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(12);">
                    <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                      <%for(int i=0;i<6;i++) {
                    	 String str="";
                    	 String m="hidden";
                    	 String v="";
                         if(i>=3) {
                        	 str="style=\"margin-top:5px;width:184px;\"";
                        	 m="text";
                         }
                         if(descArray[i]!=null) {
                    		 v=descArray[i];
                    	 }
                      %>
                        <input type="<%=m%>" name="<%=descName[i] %>" value="<%=v%>" <%=str%>>
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="12">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                 </form>
               </div>
               <div style="height:110px;border:solid green 1px;margin-top:144px;">
               <%
                 if(picArray[13]!=null) {
               %>
                <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic14&classifyId=${classifyId}');">
                <img alt="" src="<%=basePath%><%=picArray[13]%>"></a>
               <%} %>
                 <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(13);">
                    <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="13">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                 </form>
               </div>
            </div>
            
            <!-- 模式二下的两张图片 -->
            <div style="float:right;width:206px;height:412px;display:none" id="mode2">
               <div style="height:246px;border-bottom:solid green 1px;">
               <%
                  if(picArray[14]!=null) {
               %>
                 <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic15&classifyId=${classifyId}');">
                 <img alt="" src="<%=basePath%><%=picArray[14]%>"></a>
                 <%} %>
                 <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(14);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                     <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="14">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                 </form>
               </div>
               <div style="height:246px;border:solid green 1px;margin-top:30px;">
               <% 
                  if(picArray[15]!=null) {
               %>
                 <a href="javascript:openWin('<%=basePath%>homepage/manage/classify/addClassifyGoodsInput?pic=pic16&classifyId=${classifyId}');">
                  <img alt="" src="<%=basePath%><%=picArray[15]%>"></a>
                 <%} %>
                 <form action="<%=basePath%>homepage/manage/classify/uploadClassifyImg" method="post" enctype="multipart/form-data" >
                     <input type="file" name="file" onchange="fileBtn(15);">
                     <% for(int i=1;i<=16;i++) {%>
                       <input type="hidden" name="pic<%=i%>" value="<%=picArray[i-1]%>">
                     <%}%>
                     
                      <%for(int i=0;i<6;i++) {%>
                     <input type="hidden" name="<%=descName[i] %>" value="<%=descArray[i]%>">
                     <%} %>
                     <input type="hidden" name="mode" value="${mode }">
                     <input type="hidden" name="classify" value="${classify }">
                     <input type="hidden" name="flag" value="15">
                     <input type="hidden" name="id" value="${id}">
                     <input type="hidden" name="classifyId" value="${classifyId }">
                 </form>
               </div>
            </div>
          </div>
          
          <div style="margin-top:10px;">
             <input type="button"  value="确定" onclick="beSure()" id="beSure">&nbsp;&nbsp;
             <input type="button"  value="删除对应的商品" onclick="delClassifyGoods()" id="delBtn">&nbsp;&nbsp;
             <input type="button"  value="返回" onclick="javascript:location='<%=basePath%>homepage/manage/classify/index'">
          </div>
       </div>
    </center>
</body>
</html>