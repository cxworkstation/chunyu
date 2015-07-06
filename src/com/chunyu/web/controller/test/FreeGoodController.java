package com.chunyu.web.controller.test;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunyu.web.service.FreeGoodService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Goods;

@Controller
@RequestMapping("freeGood/manage")
public class FreeGoodController extends BaseController{

	@Resource(name="freeGoodService")
	private FreeGoodService freeGoodService;
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		String pagerOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		PageModel<Goods> pm=new PageModel<Goods>();
		List<Goods> list=freeGoodService.selectFreeGoods(pageOffSet,pm.getPageSize());
		long totalCount=freeGoodService.getCount();
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "freeGood/index";
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response,int id){
		int result=freeGoodService.delete(id);
		if(result!=1){
			printStrToHtml(response, "cao");
			return;
		}
		printStrToHtml(response, id+"");
	}
	
	@RequestMapping(value="update")
	public void update(int id){
		int result=freeGoodService.update(id);
		//成功与否的反应。。。？？？
	}
	
	@RequestMapping(value="bulkdel")
	public void bulkdel(HttpServletResponse response,String str){
		String[] ids=str.split("_");
		int result=freeGoodService.bulkdel(ids);
		if(result!=1){
			printStrToHtml(response, "cao");
			return;
		}
		printStrToHtml(response, str);
	}
	
	@RequestMapping(value="check")
	public String check(HttpServletRequest request,String checkName,String keyword){
		PageModel<Goods> pm=new PageModel<Goods>();
		String StrPageOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(StrPageOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(StrPageOffSet);
		}
		List<Goods> list=freeGoodService.queryCheck(pageOffSet,pm.pageSize,checkName,keyword);
		long totalCount=freeGoodService.getRecordNumCheck(checkName,keyword);
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "freeGood/index";
	}
}
