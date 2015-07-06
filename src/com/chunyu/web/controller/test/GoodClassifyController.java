package com.chunyu.web.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunyu.web.service.ClassifyService;
import com.chunyu.web.service.GoodClassifyService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Classify;

@Controller
@RequestMapping("goodClassify/manage")
public class GoodClassifyController extends BaseController{
	
	@Resource(name="goodClassifyService")
	private GoodClassifyService goodClassifyService;
	
	@Resource(name="classifyService")
	private ClassifyService classifyService;
	
	
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		int pageOffSet=0;
		String StrPagerOffSet=request.getParameter("pager.offset");
		if(StrPagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(StrPagerOffSet);
		}
		PageModel<Map<String,Object>> pm=new PageModel<Map<String,Object>>();
		List<Map<String,Object>> lists=goodClassifyService.selectAllGoods(pageOffSet,pm.getPageSize());
		List<Map<String,Object>> goodIds=goodClassifyService.getGoodIds(pageOffSet,pm.getPageSize());
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		if(lists!=null||list.size()>0){
			for(int j=0;j<goodIds.size();j++){
				Map<String,Object> hashMap=new HashMap<String,Object>();
				Map<String,Object> map=goodIds.get(j);
				    hashMap.put("goodId", map.get("goodId"));
								  for(int i=0;i<lists.size();i++){
									  Map<String,Object> map1=lists.get(i);
									 Integer level=(Integer) map1.get("level");
									 Integer goodId=(Integer)map1.get("goodId");
									 if(goodId==(Integer) hashMap.get("goodId")){
										if(level==1){
										hashMap.put("classify1", map1.get("classify"));
										}else if(level==2){
											hashMap.put("classify2", map1.get("classify"));
										}else{
											hashMap.put("classify3", map1.get("classify"));
											hashMap.put("name", map1.get("name"));
											hashMap.put("introduction", map1.get("introduction"));
										}
									}
								}
				  list.add(hashMap);
			}
		}
		long totalCount=goodClassifyService.getCount();
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "goodClassify/index";
	}
	
	@RequestMapping(value="updateClassifyInput",method=RequestMethod.GET)
	public String updateClassifyInput(HttpServletRequest request,int id){
		int result=goodClassifyService.resubmit(id);
		if(result>0){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			List<Classify> innerlist=classifyService.getLevel1();
			if(innerlist.size()>0){
			for(int i=0;i<innerlist.size();i++){
				Map<String,Object> map=new HashMap<String,Object>();
				Classify c=innerlist.get(i);
				map.put("id", c.getId());
				map.put("name", c.getName());
				list.add(map);
			}
			}
		request.setAttribute("list", list);
		request.setAttribute("id", id);
		return "goods/setClassifyInput";
		}
		return "index";
	}
}
