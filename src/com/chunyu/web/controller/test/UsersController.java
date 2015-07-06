package com.chunyu.web.controller.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunyu.web.service.UsersService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Users;

@Controller
@RequestMapping("users/manage")
public class UsersController extends BaseController{

	@Resource(name="usersService")
	private UsersService usersService;
	
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		String pagerOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		PageModel<Users> pm=new PageModel<Users>();
		List<Users> list=usersService.getAllUsers(pageOffSet,pm.getPageSize());
		long totalCount=usersService.getCount();
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "users/index";
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response,int id){
		int result=usersService.delete(id);
		if(result!=1){
			printToHtml(response, "cao");
			return;
		}
		printToHtml(response, id+"");
	}
	
	@RequestMapping(value="toBlackUser")
	public void toBlackUser(HttpServletResponse response,int id){
		int result=usersService.toBlackUser(id);
		if(result!=1){
			printStrToHtml(response, "cao");
			return;
		}
		printStrToHtml(response, "xlq");
	}
	
	@RequestMapping(value="check")
	public String check(HttpServletRequest request,String typeName,String keyword){
		String pagerOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		PageModel<Users> pm=new PageModel<Users>();
		List<Users> list=usersService.getCheckUsers(pageOffSet,pm.getPageSize(),typeName,keyword);
		long totalCount=usersService.getCheckCount(typeName,keyword);
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "users/index";
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
		int result=usersService.bulkdel(list);
		if(result!=1){
			printStrToHtml(response, "cao");
			return;
		}
		printStrToHtml(response, str);
	}
	

}
