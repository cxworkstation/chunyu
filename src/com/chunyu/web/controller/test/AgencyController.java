package com.chunyu.web.controller.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chunyu.web.service.AgencyService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Agency;
import com.chunyun.web.model.Goods;
import com.sun.org.apache.regexp.internal.recompile;

@Controller
@RequestMapping("agency/manage")
public class AgencyController extends BaseController{

	@Resource(name="agencyService")
	private AgencyService agencyService;
	
	private Logger log=LoggerFactory.getLogger(AgencyController.class);
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request){
		int pageOffSet=0;
		String pagerOffSet=request.getParameter("pager.offset");
		if(pagerOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(pagerOffSet);
		}
		PageModel<Agency> pm=new PageModel<Agency>();
		List<Agency> list=agencyService.queryAllAngency(pageOffSet,pm.getPageSize());
		long totalCount=agencyService.getCount();
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "agency/index";
	}
	
	@RequestMapping(value="addAgencyInput",method=RequestMethod.GET)
	public String addAgencyInput(){
		return "agency/addAgencyInput";
	}
	
	@RequestMapping(value="addAgency")
	public String addAgency(HttpServletRequest request,int flag,String name,String introduction,String manageTime,String image){
		if(flag==1){
			//表示这是图片提交
			try{
		    MultipartHttpServletRequest req=(MultipartHttpServletRequest)request;
		    MultipartFile mfile=req.getFile("imageFile");
		    String path=request.getSession().getServletContext().getRealPath("/imgs/agencyImg");
		    log.info(path);
		    String fileName=new Date().getTime()+".png";
		    File file=new File(path,fileName);
		    if(!file.exists()){
		    	file.mkdirs();
		    }
		    mfile.transferTo(file);
		    request.setAttribute("name", name);
		    request.setAttribute("manageTime", manageTime);
		    request.setAttribute("introduction", introduction);
		    String newFileUrl="imgs/agencyImg/"+fileName;
		    request.setAttribute("image", newFileUrl);
		    return "agency/addAgencyInput";
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			//第二次表单提交
		Agency agency=new Agency();
		agency.setImage(image);
		agency.setIntroduction(introduction);
		agency.setManageTime(manageTime);
		agency.setName(name);
		int result=agencyService.addAgency(agency);
		//..............??
		}
		return "index";
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response,int id){
		int result=agencyService.delete(id);
		if(result==0){
			printToHtml(response, "cao");
			return;
		}
		printToHtml(response, id+"");
	}
	
	
	@RequestMapping(value="addAgencyGoodInput",method=RequestMethod.GET)
	public String addAgencyGoodInput(HttpServletRequest request,int id){
		request.setAttribute("id", id);
		return "agency/addAgencyGoodInput";
	}
	
	//这里必须是string类型的flag
	@RequestMapping(value="addAgencyGood")
	public String addAgencyGood(HttpServletRequest request,String flag,String id,String name,String introduction,String image,String unit){
		if("1".equals(flag)){
			//采用jquery提交
			 try {
				    MultipartHttpServletRequest req=(MultipartHttpServletRequest) request;
				    MultipartFile mfile=req.getFile("imageFile");
					String fileName=new Date().getTime()+".jpg";
					String path=request.getSession().getServletContext().getRealPath("/imgs/agencyImgGood");
					//String path=this.getClass().getClassLoader().getResource("/").getPath();
					File file=new File(path,fileName);
					if(!file.exists()){
						file.mkdir();
					}
					mfile.transferTo(file);
					String weight=request.getParameter("weight");
					String inArea=request.getParameter("inArea");
					String newFileUrl="imgs/agencyImgGood/"+fileName;
					request.setAttribute("image", newFileUrl);
					request.setAttribute("name", name);
					request.setAttribute("inArea", inArea);
					request.setAttribute("weight", weight);
					request.setAttribute("introduction", introduction);
					request.setAttribute("id", id);
                    return "agency/addAgencyGoodInput";
					} catch (IOException e) {
					e.printStackTrace();
				}
		     }else{
			//采用form提交
			Goods goods=new Goods();
			String StrWeight=request.getParameter("weight");
			float weight=Float.parseFloat(StrWeight);
			if("kg".equals(unit)){
				weight=weight*1000;
			}
			String StrInarea=request.getParameter("inArea");
			int	inArea=TypeConversion.StringToInt(StrInarea);
			int isfree=0;
			int isAgency=0;
			goods.setName(name);
			goods.setIntroduction(introduction);
			goods.setInArea(inArea);
			goods.setImage(image);
			goods.setIsAgency(isAgency);
			goods.setIsfree(isfree);
			int agencyId=TypeConversion.StringToInt(id);
			agencyService.addGoods(goods,agencyId);
		}
		return "index";
		
	}
	
	@RequestMapping(value="selectAgencyGood",method=RequestMethod.GET)
	public String selectAgencyGood(HttpServletRequest request,int id){
		List<Map<String,Object>> list=agencyService.selectAgencyGood(id);
		request.setAttribute("list", list);
		return "agency/selectAgencyGood";
	}
	
	@RequestMapping(value="lookImg",method=RequestMethod.GET)
	public String lookImg(HttpServletRequest request,int id){
		List<Map<String,Object>> list=agencyService.getImgAddress(id);
		String address="";
		if(list.size()>0){
			address=(String) list.get(0).get("image");
		}
		request.setAttribute("address", address);
		return "agency/lookImg";
	}
	
	@RequestMapping(value="deleteGood")
	public void deleteGood(HttpServletResponse response,int id,int goodsId){
		int result=agencyService.deleteGood(id,goodsId);
		if(result==0){
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
		PageModel<Agency> pm=new PageModel<Agency>();
		List<Agency> list=agencyService.queryCheckAngency(pageOffSet,pm.getPageSize(),selectName,keyword);
		long totalCount=agencyService.getCheckCount(selectName,keyword);
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "agency/index";
	}
}
