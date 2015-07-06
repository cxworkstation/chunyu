package com.chunyu.web.controller.test;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunyu.web.service.ClassifyService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Classify;

@Controller
@RequestMapping("classify/manage")
public class ClassifyController extends BaseController{
   
	@Resource(name="classifyService")
	private ClassifyService classifyService;
	
	private Logger log=LoggerFactory.getLogger(ClassifyController.class);
	//查询所有分类信息
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		String pagerOffset=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffset!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffset);
		}
		PageModel<Classify> pm=new PageModel<Classify>();
		List<Classify> list=classifyService.getAllClassify(pageOffSet,pm.getPageSize());
		long recordNum=classifyService.getCount();
		pm.setList(list);
		pm.setTotalCount(recordNum);
		pm.setPageOffSet(pageOffSet);
		request.setAttribute("pm", pm);
		return "classify/index";
	}
	
	@RequestMapping(value="checkNext",method=RequestMethod.GET)
	public String checkNext(HttpServletRequest request,int id){
		List<Classify> list=classifyService.checkNext(id);
		request.setAttribute("list", list);
		return "classify/check";
	}
	
	@RequestMapping(value="checkPre",method=RequestMethod.GET)
	public String checkPre(HttpServletRequest request,int id){
		int parentId=classifyService.getParentId(id);
		List<Classify> list=classifyService.selectById(parentId);
		request.setAttribute("list", list);
		return "classify/check";
	}

	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public void delete(HttpServletResponse response,int id){
		int count=classifyService.getNextCount(id);
		int result=-1;
		if(count==0){
			result=classifyService.delete(id);
		}
		if(result==0){
			printStrToHtml(response, "cao");
			return;
		}else if(result==-1){
			printStrToHtml(response, "classify");
			return;
		}
		printStrToHtml(response, id+"");
	}
	
	@RequestMapping(value="addInput",method=RequestMethod.GET)
	public String addInput(HttpServletRequest request){
		return "classify/addInput";
	}
	
	@RequestMapping(value="addNextInput",method=RequestMethod.GET)
	public String addNextInput(HttpServletRequest request,int id){
		int level=classifyService.getLevel(id);
		int flag=classifyService.checkParentClassify(id);
		request.setAttribute("level", level+1);
		request.setAttribute("parentId", id);
		request.setAttribute("flag", flag);
		return "classify/addNextInput";
	}
	
	@RequestMapping(value="add")
	public String add(HttpServletRequest request,HttpServletResponse response,String name){
		String StrOnlyReservation=request.getParameter("onlyReservation");
		int onlyReservation=TypeConversion.StringToInt(StrOnlyReservation);
		int result=classifyService.add(name,0,1,onlyReservation);
		if(result>0) {
			return "common/success";
		}
		return "common/error";
	}
	
	
	@RequestMapping(value="addNext")
	public String addNext(HttpServletRequest request,HttpServletResponse response,String name,int parentId,int level,int flag){
		int onlyReservation=1;
		if(flag!=1){
			String StrOnlyReservation=request.getParameter("onlyReservation");
			onlyReservation=TypeConversion.StringToInt(StrOnlyReservation);
		}
		int result=classifyService.add(name,parentId,level,onlyReservation);
		if(result>0) {
			return "common/success";
		}
		return "common/error";
	}
	
	@RequestMapping(value="changeFree",method=RequestMethod.GET)
	public void changeFree(int id){
		classifyService.changeFree(id);
	}
	
}
