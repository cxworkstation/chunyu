package com.chunyu.web.controller.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunyu.web.service.CommentsService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;

@Controller
@RequestMapping("comments/manage")
public class CommentsController extends BaseController{
	
	@Resource(name="commentsService")
	private CommentsService commentsService;
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		int pageOffSet=0;
		String pagerOffSet=request.getParameter("pager.offset");
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		//select d.id,d.name,d.content,b.username,d.commentTime 
		PageModel<Map<String,Object>> pm=new PageModel<Map<String,Object>>();
		List<Map<String,Object>> list=commentsService.queryComments(pageOffSet,pm.getPageSize());
		long totalCount=commentsService.getCounts();
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "comments/index";
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response,int id){
		int result=commentsService.delete(id);
		if(result!=1){
			printToHtml(response, "cao");
			return;
		}
		printToHtml(response, id+"");
	}
	
	@RequestMapping(value="check")
	public String check(HttpServletRequest request,String selectName,String keyword){
		int pageOffSet=0;
		String pagerOffSet=request.getParameter("pager.offset");
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		PageModel<Map<String,Object>> pm=new PageModel<Map<String,Object>>();
		List<Map<String,Object>> list=commentsService.queryCheckComments(pageOffSet,pm.getPageSize(),selectName,keyword);
		long totalCount=commentsService.getCheckCounts(selectName,keyword);
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "comments/index";
	}

}
