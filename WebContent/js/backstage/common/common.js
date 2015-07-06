function openWin(url) {
	var screenW=screen.availWidth;
	var screenH=screen.availHeight;
	var width=screenW*2/3;
	var heigth=screenH*2/3;
	var offLeft=(screenW-width)/2;
	var offTop=(screenH-heigth)/2;
	 
	window.open(url,'newwin','height='+heigth+',width='+width+',top='+offTop+',left='+offLeft+',toolbar=no,menubar=no,scrollbars=yes,resizable=no');
}

function getback(url){
 	    location=url;
}

 function del(url) {
	  if(!confirm("确定删除吗？")) {
		  return;
	  }
	  $.post(url,function(id) {
		  if(id=='cao') {
				alert('刪除失敗');
				return ;
		  }
		  if(id=='classify'){
			  alert('操作不正确,请删除该分类的所有子分类，再删除该分类');
			  return;
		  }
		  var trNode=$("#"+id);
		  trNode.remove();
		  alert('刪除成功');
	  });
  }
 
 
  function bulkdel(url) {
	  var ids="";
	  var ele=document.getElementsByName("id");
		for(var i=0;i<ele.length;i++)
		{
			var check=ele[i];
			if(check.checked)
			{
				ids+="_"+check.value;
			}	
			
		}
		var str=ids.slice(1);
		if(str=="") {
			alert("没有选择删除的对象");
			return;
		}
	if(confirm("确定删除吗？")) {
		$.post(url,{str:str},function(data) {
			if(data=='cao') {
				alert('删除失败');
				return ;
			}
			  ids=data.split("_");
			  for(var i=0;i<ids.length;i++) {
				  var trNode=$("#"+ids[i]);
				  trNode.remove();
			  }
			  alert('删除成功');
		} );
	}
  }
  
  
function selAll()
{
	var eles=document.getElementsByName("id");
	for(var i=0;i<eles.length;i++)
	{
		var ele=eles[i];
		if(!ele.checked)
		{
			ele.checked=true;
		}
	}
}
function noSelAll()
{
	var eles=document.getElementsByName("id");
	for(var i=0;i<eles.length;i++)
	{
		var ele=eles[i];
		if(ele.checked)
		{
			ele.checked=false;
		}
	}
}

