$(document).ready(function(){
    //首先你要得到当前select下的所有option
	var options=$('#isfree').children();
	var options1=$('#isAgency').children();
   //然后循环所有的option，进行判断，如果它的值与传来的值相等，我们就添加属性selected
	for(var i=0;i<options.length;i++){
		var $obj=$(options[i]);
		var value=$obj.val();
		if(value==str){
			$obj.attr("selected","selected");
		}
	}
	for(var i=0;i<options1.length;i++){
		var $obj1=$(options1[i]);
		var value=$obj1.val();
		if(value==str1){
			$obj1.attr("selected","selected");
		}
	}
});