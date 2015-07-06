package com.chunyu.web.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunyu.web.service.OrdersService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Orders;




	
@Controller
@RequestMapping("orders/manage")
public class OrdersController extends BaseController{

	@Resource(name="ordersService")
	private OrdersService ordersService;

	
	
	//展示所有订单的页面
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		String pagerOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		PageModel<Orders> pm=new PageModel<Orders>();
		List<Orders> list=ordersService.selectAllOrders(pageOffSet,pm.getPageSize());
		long totalCount=ordersService.getCount();
		pm.setPageOffSet(pageOffSet);
        pm.setTotalCount(totalCount);
        pm.setList(list);
        request.setAttribute("pm", pm);
		return "orders/index";
	}
	
	//删除订单
	@RequestMapping(value="delete")
	public String delete(HttpServletResponse response,int id){
		int result=ordersService.delete(id);
		if(result!=1){
			printToHtml(response,"cao");
		}else{
			printToHtml(response,id+"");
		}
		return "index";
	}
	
	@RequestMapping(value="goodDetails")
	public String goodDetails(HttpServletRequest request,int id){
		//查询出来json串并且处理展示 
		List<Map<String,String>> list=ordersService.selectOrderGoods(id);
		if(list!=null){
		request.setAttribute("list",list);
		}
		request.setAttribute("id", id);
		return "orders/goodDetails";
	}
	
	//确认发货
	@RequestMapping(value="deliver")
	public void deliver(HttpServletResponse response,int orderId){
		int state=ordersService.checkState(orderId);
		int result=-1;
		if(state==0){
		result=ordersService.deliver(orderId);
		}else if(state==2){
		result=7;
		}
		if(result==0){
		//程序没有执行正确
			printStrToHtml(response,"xlq");
		}else if(result==-1){
		//state为1，已经是已发货状态
			printStrToHtml(response, "love");
		}else if(result==7){
		//state为2，已经是订单完成状态
			printStrToHtml(response, "feiji");
		}else{
		//state为0，发货成功
			printStrToHtml(response, "1");
		}
	}
	
	@RequestMapping(value="check")
	public String check(HttpServletRequest request,String selectName,String keyword){
		String pagerOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		PageModel<Orders> pm=new PageModel<Orders>();
		List<Orders> list=ordersService.selectCheckOrders(pageOffSet,pm.getPageSize(),selectName,keyword);
		long totalCount=ordersService.getCheckCount(selectName,keyword);
		pm.setPageOffSet(pageOffSet);
        pm.setTotalCount(totalCount);
        pm.setList(list);
        request.setAttribute("pm", pm);
		return "orders/index";
	}
	
	@RequestMapping(value="bulkdel")
	public void bulkdel(HttpServletResponse response,String str){
		String[] ids=str.split("_");
		List<List<Object>> list=new ArrayList<List<Object>>();
		for(int i=0;i<ids.length;i++){
			List<Object> innerList=new ArrayList<Object>();
			int id=Integer.parseInt(ids[i]);
			innerList.add(id);
			list.add(innerList);
		}
		int result=ordersService.bulkdel(list);
		if(result!=1){
			printStrToHtml(response, "cao");
			return;
		}
		printStrToHtml(response, str);
	}
	
}
