function toBlackUser(url){
	if(!confirm("确定添加到黑名单吗？")) {
		  return;
	  }
	$.post(url,
		function(msg){
		 if(msg=='cao'){
			 alert('添加到黑名单操作失败，请重新操作！');
			 return;
		 }
		 alert('加入黑名单成功！');
		});
}