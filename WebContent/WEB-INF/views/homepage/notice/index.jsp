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
<title>网站公告</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/backstage/common/common.js"></script>
<script type="text/javascript">
   function keyInput(_this) {
	   var $this=$(_this);
       var text=$this.val();
       var len=text.length;
       var $promtObj=$this.siblings('div').children('font');
       var val=16-len;
       $promtObj.text(val);
       if($promtObj.text()<=0) {
           var notice=$this.val();
           notice=notice.substring(0,16);
           $this.attr('value',notice);
           $promtObj.text(0);
       }
   }

   function fileBtn() {
	$('input[name=distinct]').val(1);
	document.forms[0].submit();
   }

   function ok() {
	   var pic=$('input[name=pic]').val();
       var notice1=$('input[name=notice1]').val().trim();
       var notice2=$('input[name=notice2]').val().trim();
       var notice3=$('input[name=notice3]').val().trim();
       var notice4=$('input[name=notice4]').val().trim();
       var notice5=$('input[name=notice5]').val().trim();
       if(pic==''||notice1==''||notice2==''||notice3==''||notice4==''||notice5=='') {
           alert('请按照给定的格式来上传，并且都不能为空');
           return;
       }
       var url='<%=basePath%>homepage/manage/notice/add';
       $.ajax({
           url:url,
           type:'POST',
           data:{
               pic:pic,
               notice1:notice1,
               notice2:notice2,
               notice3:notice3,
               notice4:notice4,
               notice5:notice5
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
   }
</script>
<style type="text/css">
  ul {
    margin-top:-2px;
  }
  ul li {
    list-style-type: none;
    margin-left:-40px;
    border-bottom:solid green 1px;
  }
</style>
</head>
<body>
<%Object o1=request.getAttribute("noticeId1");
 boolean flag=false;
 if(o1!=null) {
	 flag=true;
 }
%>
   <center>
     <form action="<%=basePath%>homepage/manage/notice/uploadImg" method="post" enctype="multipart/form-data">
     <input type="hidden" name="distinct" value="0">
     <input type="hidden" name="pic" value="${pic }">
     <div style="width:220px;height:550px;border:solid green 1px">
       <div style="width:208px;height:33px;border-bottom:solid green 1px">
         <span style="color:#eb562e;font-weight: bold;">网站公告</span>
       </div>
       <div style="height:130px;border-buttom:solid green 1px">
         <ul>
           <li>
            <input type="text" size="25px;" name="notice1"  value="${notice1 }" onkeyup="keyInput(this);">
            <div style="margin-top:3px;margin-right:-150px;float:right;font-size:10px;width:150px;">
                                     还能输入<font color="red">16</font>个
                  <%if(flag) {%>
                   <a href="javascript:openWin('<%=basePath%>homepage/manage/notice/addContentInput?noticeId=${noticeId1 }');">链接内容</a>
                  <%} %>
            </div>
           </li>
           
           <li>
            <input type="text" size="25px;" name="notice2"  value="${notice2 }" onkeyup="keyInput(this);">
            <div style="margin-top:3px;margin-right:-150px;float:right;font-size:10px;width:150px;">
                             还能输入<font color="red">16</font>个
             <%if(flag) {%>
              <a href="javascript:openWin('<%=basePath%>homepage/manage/notice/addContentInput?noticeId=${noticeId2 }');">链接内容</a>
             <%} %>
            </div>
           </li>
           <li>
             <input type="text" size="25px;" name="notice3"  value="${notice3 }" onkeyup="keyInput(this);">
             <div style="margin-top:3px;margin-right:-150px;float:right;font-size:10px;width:150px;">
                             还能输入<font color="red">16</font>个
              <%if(flag) {%>
               <a href="javascript:openWin('<%=basePath%>homepage/manage/notice/addContentInput?noticeId=${noticeId3 }');">链接内容</a>
              <%} %>
             </div>
           </li>
           <li>
             <input type="text" size="25px;" name="notice4"  value="${notice4 }" onkeyup="keyInput(this);">
             <div style="margin-top:3px;margin-right:-150px;float:right;font-size:10px;width:150px;">
                                 还能输入<font color="red">16</font>个
                <%if(flag) { %>
                 <a href="javascript:openWin('<%=basePath%>homepage/manage/notice/addContentInput?noticeId=${noticeId4 }');">链接内容</a>
                <%} %>
             </div>
           </li>
           <li>
             <input type="text" size="25px;" name="notice5"  value="${notice5 }" onkeyup="keyInput(this);">
             <div style="margin-top:3px;margin-right:-150px;float:right;font-size:10px;width:150px;">
                             还能输入<font color="red">16</font>个
              <%if(flag) {%>
              <a href="javascript:openWin('<%=basePath%>homepage/manage/notice/addContentInput?noticeId=${noticeId5 }');">链接内容</a>
              <%} %>
             </div>
           </li>
         </ul>
       </div>
       
       <div style="height:209px;border-bottom:solid green 1px">
          <img alt="" src="<%=basePath%>${pic}">
          <input type="file" name="file1" onchange="fileBtn();">
       </div>
       <div style="font-size:10px;margin-top:30px">
         <%if(flag) {%>
          <a href="javascript:openWin('<%=basePath%>homepage/manage/notice/addContentInput?noticeId=${picId1 }');">链接内容</a>
         <%} %>
       </div>
       <div style="height:50px;margin-top:30px">
          <input type="button" value="确定" onclick="ok();" name="beSure">
          <input type="button" value="返回" onclick="javascript:location='<%=basePath%>homepage/manage/notice/index'">
       </div>
     </div>
     </form>
   </center>
</body>
</html>