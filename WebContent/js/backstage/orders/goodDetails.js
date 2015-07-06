function changeOrderState(){
	var orderId=$('#deliver').attr('orderId');
	if(!confirm("确认发货吗？")) {
		  return;
	 }
    $.ajax({
    type: "POST",
    url: "orders/manage/deliver",
    data:"orderId="+orderId,
    dataType:"text",
    success: function(msg){
    	msg=msg.trim();
    	if(msg=='xlq'){
        alert('网络问题，请重新点击发货');
        return;
      }else if(msg=='love'){
    	 alert('已经是已发货状态');
    	 return;
     }else if(msg=='feiji'){
    	 alert('已经是已完成订单');
    	 return;
     }
	 alert('发货成功');
    }
   });
}