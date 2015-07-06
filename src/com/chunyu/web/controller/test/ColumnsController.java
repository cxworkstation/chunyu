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

import com.chunyu.web.service.ColumnsService;
import com.chunyu.web.service.GoodsService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;

@Controller
@RequestMapping("columns/manage")
public class ColumnsController extends BaseController{
	
	@Resource(name="columnsService")
	private ColumnsService columnsService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	
	@RequestMapping(value="index",method=RequestMethod.GET)
    public String index(HttpServletRequest request){
		String pagerOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
	    PageModel<Map<String,Object>> pm=new PageModel<Map<String,Object>>();
	    //[{id=1, columnId=1, goodId=4, name=f, introduction=null}, {id=2, columnId=1, goodId=3, name=f, introduction=null}, {id=3, columnId=2, goodId=3, name=f, introduction=null}]
	    List<Map<String,Object>> list=columnsService.selectAllColumnGoods(pageOffSet,pm.getPageSize());
	    long totalCount=columnsService.getCount();
	    pm.setList(list);
	    pm.setPageOffSet(pageOffSet);
	    pm.setTotalCount(totalCount);
	    request.setAttribute("pm", pm);
		return "columns/index";
    }
	
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response,int id){
		int result=columnsService.delete(id);
		if(result!=1){
			printToHtml(response, "cao");
			return;
		}
		printToHtml(response, id+"");
	}
	
	@RequestMapping(value="update")
	public void update(HttpServletRequest request,HttpServletResponse response,int goodId){
		//首先要把原有的记录删除
		int result=columnsService.deleteRecord(goodId);
		if(result!=0){
			//成功删除了之后再添加新的选择
			String[] types=request.getParameterValues("column");
			int len=types.length;
			if(len!=0){
				int[] columnIds = new int[len];
				for(int i=0;i<len;i++){
					columnIds[i]=Integer.parseInt(types[i]);
				}
				 goodsService.addColumn(goodId,columnIds);
	      }
		}
		//删除不成功。。。？？
	}
	
	@RequestMapping(value="updateInput",method=RequestMethod.GET)
	public String updateInput(HttpServletRequest request,int goodId){
		List<Map<String,Object>> lists=columnsService.selectColumnId(goodId);
		List<Object> list=new ArrayList<Object>();
		if(lists.size()>0){
			for(int i=0;i<lists.size();i++){
				Map<String,Object> map=lists.get(i);
				list.add(map.get("columnId"));
			}
		}
		request.setAttribute("list", list);
		request.setAttribute("goodId", goodId);
		return "columns/updateInput";
	}
	
	@RequestMapping(value="bulkdel")
	public void bulkdel(HttpServletResponse response,String str){
		int result=columnsService.bulkdel(str);
		if(result!=1){
			printStrToHtml(response, "cao");
			return;
		}
		printStrToHtml(response, str);
	}
	
	@RequestMapping(value="check")
	public String check(HttpServletRequest request,String selectName,String keyword){
		String pagerOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
	    PageModel<Map<String,Object>> pm=new PageModel<Map<String,Object>>();
	    List<Map<String,Object>> list=columnsService.selectCheckColumnGoods(pageOffSet,pm.getPageSize(),selectName,keyword);
	    long totalCount=columnsService.getCheckCount(selectName,keyword);
	    pm.setList(list);
	    pm.setPageOffSet(pageOffSet);
	    pm.setTotalCount(totalCount);
	    request.setAttribute("pm", pm);
		return "columns/index";
	}
}
