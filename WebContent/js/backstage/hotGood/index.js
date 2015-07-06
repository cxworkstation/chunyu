function searchRange(){
	var range=$('#range').val();
	$.ajax({
		url:"hotGood/manage/show",
		type:"Post",
		data:"data="+range,
	    dataType:"json",
	    success:function(data){
		//[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object],[object Object]
		var s="<table border='1'><tr><td>商品ID</td><td>商品名称</td><td>商品已购买数</td><td>商品评论数</td><td>商品类型</td><td>商品性质</td><td>商品简介</td>";
		for(var i=0;i<data.length;i++){
			s+="<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].hasBuyNum+"</td><td>"+data[i].commentNum+"</td>";
			if(data[i].isfree==1){
				s+="<td>免费商品</td>";
			}else{
				s+="<td>非免费商品</td>";
			}
			if(data[i].isAgency==1){
				s+="<td>代理商品</td>";
			}else{
				s+="<td>本店商品</td>";
			}
			s+="<td>"+data[i].introduction+"</td></tr>";
		}
		s+="</table>";
		$('#show').html(s);
	    }
	});
}