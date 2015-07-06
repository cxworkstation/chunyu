package com.chunyu.web.controller.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


import com.chunyu.web.service.GoodsService;
import com.chunyu.web.service.HomePageService;
import com.chunyun.web.model.BroadCast;
import com.chunyun.web.model.Goods;
import com.chunyun.web.model.Module;

@Controller
@RequestMapping("homepage/manage")
public class HomePageController extends BaseController {
    Logger log=LoggerFactory.getLogger(HomePageController.class);
	@Resource(name="homePageService")
	private HomePageService homePageService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	/******首页轮播**********/
	@RequestMapping(value="broad/index",method=RequestMethod.GET)
	public String broad(HttpServletRequest request) {
		//加载轮播图列表
		List<BroadCast> list=homePageService.getBroadList();
		int size=list.size();
		if(size>0) {
			BroadCast b1=list.get(0);
			request.setAttribute("pic1", b1.getPic());
			request.setAttribute("id1", b1.getId());
		}
		if(size>1) {
			BroadCast b2=list.get(1);
			request.setAttribute("pic2", b2.getPic());
			request.setAttribute("id2", b2.getId());
		}
		if(size>2) {
			BroadCast b3=list.get(2);
			request.setAttribute("pic3", b3.getPic());
			request.setAttribute("id3", b3.getId());
		}
		if(size>3) {
			BroadCast b4=list.get(3);
			request.setAttribute("pic4", b4.getPic());
			request.setAttribute("id4", b4.getId());
		}
		if(size>4) {
			BroadCast b5=list.get(4);
			request.setAttribute("pic5", b5.getPic());
			request.setAttribute("id5", b5.getId());
		}
		if(size>5) {
			BroadCast b6=list.get(5);
			request.setAttribute("pic6", b6.getPic());
			request.setAttribute("id6", b6.getId());
		}
		return "homepage/broad/index";
	}
	
	
	@RequestMapping(value="broad/addInput",method=RequestMethod.GET)
	public String addInputBroad() {
		return "homepage/broad/addInput";
	}
	
	
	@RequestMapping(value="broad/addGoodsInput",method=RequestMethod.GET)
	public String addInputGoods(HttpServletRequest request,Integer id,Integer good) throws Exception{
		int goodsId=homePageService.getGoodsId(id, good);
		if(goodsId!=0) {
		  Goods goods=goodsService.getGoodsById(goodsId);
		  request.setAttribute("image",goods.getImage());
		  request.setAttribute("name",goods.getName());
		  request.setAttribute("onlyReservation",goods.getOnlyReservation());
		  request.setAttribute("goodsId",goods.getId());
		  request.setAttribute("inArea",goods.getInArea());
		  request.setAttribute("introduction",goods.getIntroduction());
		}
		request.setAttribute("id", id);
		request.setAttribute("good", good);
		return "homepage/broad/addGoodsInput";
	}
	
	@RequestMapping("broad/uploadGoodsImg")
	public String uploadImg(HttpServletRequest request,MultipartFile imageFile,Integer goodsId,String image,String name,
			String introduction,Integer inArea,Integer onlyReservation,Integer id,Integer good) throws Exception{
		  String fileName=imageFile.getOriginalFilename();
		  fileName=System.currentTimeMillis()+"_"+fileName;
		  String path=request.getSession().getServletContext().getRealPath("/imgs/upload");
		  File file=new File(path,fileName);
		  if(!file.exists()){
			 file.mkdir();
		  }
		  imageFile.transferTo(file);
		  request.setAttribute("image","imgs/upload/"+fileName);
		  request.setAttribute("name",name);
		  request.setAttribute("introduction",introduction);
		  request.setAttribute("inArea", inArea);
		  request.setAttribute("onlyReservation",onlyReservation);
		  request.setAttribute("id",id);
		  request.setAttribute("goodsId",goodsId);
		  request.setAttribute("good",good);
		  return "homepage/broad/addGoodsInput";
	}
	
	@RequestMapping("broad/addGoods")
	public void addGoods(HttpServletResponse response,Integer goodsId,String name,String image,String introduction,Integer good,Integer id,
			Integer inArea,Integer onlyReservation) throws Exception {
		PrintWriter out=response.getWriter();
		Goods goods=null;
		if(goodsId!=null) {
			goods=goodsService.getGoodsById(goodsId);
			goods=new Goods(goodsId,name,goods.getHasBuyNum(),introduction,image,inArea,goods.getIsfree(),goods.getIsAgency(),onlyReservation);
			if(goodsService.update(goods)) {
				out.print('1');
				return;
			}
			out.print('0');
			return;
		}
		
		goods=new Goods(name,image,inArea,0,onlyReservation,introduction);
		boolean result=goodsService.add(goods);
		int goodsId_=0;
		if(result) {
			goodsId_=goodsService.getLastGoodsId();
		}
		if(goodsId_!=0) {
			boolean flag=homePageService.addBroadToGoods(id,good,goodsId_);
			if(flag) {
				out.print('1');
				return;
			}
		}
		
		out.print('0');
	}
	
	//将轮播图图片上传到服务器
	@RequestMapping(value="broad/add")
	public String addBroad(HttpServletRequest request,MultipartFile file,Integer flag,String pic1,String pic2,String pic3,
			String pic4,String pic5,String pic6,Integer id,Integer h,
			Integer id1,Integer id2,Integer id3,Integer id4,Integer id5,Integer id6) throws Exception{
		 String fileName=file.getOriginalFilename();
		 fileName=System.currentTimeMillis()+"_"+fileName;
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/broad");
		 File imgFile=new File(path,fileName);
		 if(!imgFile.exists()) {
			imgFile.mkdirs();
		 }
		
		 if(file!=null) {
			file.transferTo(imgFile);
		 }
		 passParam(request,flag,fileName,pic1,pic2,pic3,pic4,pic5,pic6,1);
		 request.setAttribute("id1",id1);
		 request.setAttribute("id2",id2);
		 request.setAttribute("id3",id3);
		 request.setAttribute("id4",id4);
		 request.setAttribute("id5",id5);
		 request.setAttribute("id6",id6);
		 request.setAttribute("h", h);//再次返回到页面时，在同一位置
		 return "homepage/broad/index";
	}
	
	@RequestMapping(value="broad/del")
	public void delBroad(HttpServletResponse response,Integer id) throws Exception{
		boolean result=homePageService.delBroad(id);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		if(result) {
			out.print(id);
			return;
		}
		
	   out.print("cao");
	}
	
	//将轮播图片添加到数据库
	@RequestMapping(value="broad/addBroadImg",method=RequestMethod.POST)
	public void addBroadImg(HttpServletResponse response,Integer id,String path,Integer index) {
		boolean result=homePageService.addOrUpdateBroadImg(id,path,index);
		if(result) {
			if(id==null||id==0) {
			 int lastestId=homePageService.getLastestBroadImgId();
			 printStrToHtml(response,lastestId+"");
			 return;
			}
			printStrToHtml(response,id+"");
		}else {
			printStrToHtml(response, "0");
		}
	}
	
	
	@RequestMapping(value="broad/updateInput")
	public String updateBroad(HttpServletRequest request,Integer id) {
		BroadCast broad=homePageService.getBroadById(id);
		return "homepage/broad/addInput";
	}
	@RequestMapping("broad/beSure")
	public void broadBeSure(HttpServletRequest request,HttpServletResponse response,String chiefPic,
			String pic1,String pic2,String pic3,String pic4,Integer orderVal,Integer id) throws Exception{
		 response.setCharacterEncoding("UTF-8");
		 PrintWriter out=response.getWriter();
		 boolean result=false;
		 if(id==null) {
		    result=homePageService.addBroad(chiefPic,pic1,pic2,pic3,pic4,orderVal);
		 }else {
			//BroadCast broad=new BroadCast(id,chiefPic,pic1,pic2,pic3,pic4,orderVal);
		//	result=homePageService.updateBroad(broad);
		 }
			 if(result) {
				out.print("1");
				return;
			 }
			 out.print("0");
	}
		 
	private void passParam(HttpServletRequest request,int flag,String fileName,String pic1,String pic2,
			String pic3,String pic4,String pic5,String pic6,int distinct) {
		String prefix="";
		if(distinct==1) {
		  prefix="imgs/homepage/broad/";
		}else if(distinct==0) {
		  prefix="imgs/homepage/module/";
		}
		switch(flag) {
		case 1:
			request.setAttribute("pic1",prefix+fileName);
			request.setAttribute("pic2",pic2);
			request.setAttribute("pic3",pic3);
			request.setAttribute("pic4",pic4);
			request.setAttribute("pic5",pic5);
			request.setAttribute("pic6",pic6);
			return;
		case 2:
			request.setAttribute("pic1",pic1);
			request.setAttribute("pic2",prefix+fileName);
			request.setAttribute("pic3",pic3);
			request.setAttribute("pic4",pic4);
			request.setAttribute("pic5",pic5);
			request.setAttribute("pic6",pic6);
			return;
		case 3:
			request.setAttribute("pic1",pic1);
			request.setAttribute("pic2",pic2);
			request.setAttribute("pic3",prefix+fileName);
			request.setAttribute("pic4",pic4);
			request.setAttribute("pic5",pic5);
			request.setAttribute("pic6",pic6);
			return;
		case 4:
			request.setAttribute("pic1",pic1);
			request.setAttribute("pic2",pic2);
			request.setAttribute("pic3",pic3);
			request.setAttribute("pic4",prefix+fileName);
			request.setAttribute("pic5",pic5);
			request.setAttribute("pic6",pic6);
			return;
		case 5:
			request.setAttribute("pic1",pic1);
			request.setAttribute("pic2",pic2);
			request.setAttribute("pic3",pic3);
			request.setAttribute("pic4",pic4);
			request.setAttribute("pic5",prefix+fileName);
			request.setAttribute("pic6",pic6);
			return;
		case 6:
			request.setAttribute("pic1",pic1);
			request.setAttribute("pic2",pic2);
			request.setAttribute("pic3",pic3);
			request.setAttribute("pic4",pic4);
			request.setAttribute("pic5",pic5);
			request.setAttribute("pic6",prefix+fileName);
			return;
		}
	}
	
	
	/*********首页公告************/
	
	@RequestMapping(value="notice/index",method=RequestMethod.GET)
	public String notice(HttpServletRequest request) throws Exception{
		List<List<Object>> list=homePageService.getNotices();
		if(list!=null&&list.size()>0) {
			int z=0;
			int t=0;
			for(int i=0;i<list.size();i++) {
				List<Object> innerList=list.get(i);
				int flag=Integer.parseInt(innerList.get(3).toString());
				if(flag==0) {
					z++;
					request.setAttribute("pic"+z,innerList.get(1));
					request.setAttribute("picId"+z,innerList.get(1));
				}else {
					t++;
					request.setAttribute("notice"+t,innerList.get(1));
					request.setAttribute("noticeId"+t,innerList.get(0));
				}
				
				
			}
		}
		return "homepage/notice/index";
	}
	
	@RequestMapping("notice/uploadImg")
	public String uploadImg(HttpServletRequest request,MultipartFile file1,String pic,
			String notice1,String notice2,String notice3,String notice4,String notice5,int distinct) throws Exception{
		 boolean uploadAble=false;
		 //distinct区分是确定提交还是file按钮触发的表达提交，0表示是确定提交，1表示是file按钮触发提交
		 if(distinct!=0) {
			 uploadAble=true;
		 }
		 
		 //如果是文件按钮触发的表单提交
		 if(uploadAble) {
		   String fileName=file1.getOriginalFilename();
		   fileName=System.currentTimeMillis()+"_"+fileName;
		   String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/notice");
		   File imgFile=new File(path,fileName);
		   if(!imgFile.exists()) {
			 imgFile.mkdirs();
		   }
		
		   if(file1!=null) {
			   file1.transferTo(imgFile);
		   }
		 
		   String prefix="imgs/homepage/notice/";
		   request.setAttribute("pic",prefix+fileName);
		   request.setAttribute("notice1",notice1);
		   request.setAttribute("notice2",notice2);
		   request.setAttribute("notice3",notice3);
		   request.setAttribute("notice4",notice4);
		   request.setAttribute("notice5",notice5);
		}
		return "homepage/notice/index";
	}
	
	@RequestMapping("notice/add")
	public void addNotice(HttpServletResponse response,String pic,String notice1,
			String notice2,String notice3,String notice4,String notice5) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=null;
	   try {
		out=response.getWriter();
		homePageService.addNotice(pic,0);
		homePageService.addNotice(notice1,1);
		homePageService.addNotice(notice2,1);
		homePageService.addNotice(notice3,1);
		homePageService.addNotice(notice4,1);
		homePageService.addNotice(notice5,1);
		out.print('1');
	  }catch(Exception e) {
		 out.print('0');
	  }
	}
	
	@RequestMapping("notice/addContentInput")
	public String addNoticeContent(HttpServletRequest request,Integer noticeId) throws Exception{
		request.setAttribute("noticeId",noticeId);
		String content=homePageService.getContentById(noticeId);
		if(!"".equals(content)) {
			request.setAttribute("content",content);
		}
		return "homepage/notice/content";
	}
	
	@RequestMapping("notice/addContent")
	public void addContent(HttpServletResponse response,Integer id,String content) throws Exception{
		boolean result=homePageService.addContent(id,content);
		PrintWriter out=response.getWriter();
		if(result) {
			out.print('1');
			return;
		}
		out.print('0');
	}
	
	/**********四个模块************/
	@RequestMapping(value="module/index",method=RequestMethod.GET)
	public String index() {
		return "homepage/module/index";
	}
	
	@RequestMapping(value="module/addOrUpdateInput",method=RequestMethod.GET)
	public String addOrUpdateInput(HttpServletRequest request,int moduleId) {
		String[] modules=new String[]{"","今日免费","热门商品","新品上市","推荐食品"};
		String module=modules[moduleId];
		request.setAttribute("module",module);
		
		Module m=homePageService.getModule(module);
		if(m!=null) {
			request.setAttribute("pic1",m.getPic1());
			request.setAttribute("pic2",m.getPic2());
			request.setAttribute("pic3",m.getPic3());
			request.setAttribute("pic4",m.getPic4());
			request.setAttribute("pic5",m.getPic5());
			request.setAttribute("pic6",m.getPic6());
			request.setAttribute("id",m.getId());
		}
		return "homepage/module/input";
	}
	
	@RequestMapping("module/uploadImg")
	public String uploadImg(HttpServletRequest request,MultipartFile file,String pic1,
		String pic2,String pic3,String pic4,String pic5,String pic6,Integer flag,String module) throws Exception{
		 String fileName=file.getOriginalFilename();
		 fileName=System.currentTimeMillis()+"_"+fileName;
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/module");
		 File imgFile=new File(path,fileName);
		 if(!imgFile.exists()) {
			imgFile.mkdirs();
		 }
		
		 if(file!=null) {
			file.transferTo(imgFile);
		 }
		 passParam(request,flag+1,fileName,pic1,pic2,pic3,pic4,pic5,pic6,0);
		 request.setAttribute("module",module);
		 return "homepage/module/input";
	}
	
	
	@RequestMapping("module/addOrUpdate")
	public void addOrUpdate(HttpServletResponse response,Integer id,String pic1,String pic2,String pic3,
			String pic4,String pic5,String pic6,String module) throws Exception{
		boolean result=false;
		if(id==null) {
		   result=homePageService.addModulePic(pic1, pic2, pic3, pic4,pic5,pic6, module);
		}else {
		   Module m=new Module(id,pic1,pic2,pic3,pic4,pic5,pic6);
		   result=homePageService.updateModulePic(m);
		}
		PrintWriter out=response.getWriter();
		if(result) {
			out.print('1');
			return;
		}
		out.print('0');
	}
	
	@RequestMapping("module/addOrUpdateGoods")
	public String addOrUpdateGoods(HttpServletRequest request,Integer id,Integer col) throws Exception{
		request.setAttribute("id", id);
		request.setAttribute("col",col);
		int goodsId=homePageService.getModuleGoodsId(id,col);
		if(goodsId!=0) {
			Goods goods=goodsService.getGoodsById(goodsId);
			request.setAttribute("name",goods.getName());
			request.setAttribute("onlyReservation",goods.getOnlyReservation());
			request.setAttribute("inArea",goods.getInArea());
			request.setAttribute("image",goods.getImage());
			request.setAttribute("introduction",goods.getIntroduction());
			request.setAttribute("goodsId",goodsId);
		}
		return "homepage/module/addGoodsInput";
	}
	
	@RequestMapping("module/uploadModuleGoodsImg")
	public String uploadModuleImg(HttpServletRequest request,MultipartFile imageFile,String image,
			Integer id,Integer col,Integer goodsId,String name,Integer onlyReservation,Integer inArea,String introduction) throws Exception{
		String fileName=imageFile.getOriginalFilename();
		fileName=System.currentTimeMillis()+"_"+fileName;
		
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/module");
		 File file=new File(path,fileName);
		 if(!file.exists()) {
			 file.mkdirs();
		 }
		
		 if(imageFile!=null) {
			 imageFile.transferTo(file);
		 }
		 
		 request.setAttribute("image","imgs/homepage/module/"+fileName);
		 request.setAttribute("id",id);
		 request.setAttribute("col",col);
		 request.setAttribute("goodsId",goodsId);
		 request.setAttribute("name",name);
		 request.setAttribute("onlyReservation",onlyReservation);
		 request.setAttribute("inArea",inArea);
		 request.setAttribute("introduction", introduction);
		return "homepage/module/addGoodsInput";
	}
	
	@RequestMapping("module/addModuleGoods")
	public void addModuleGoods(HttpServletResponse response,Integer id,Integer col,String name,Integer inArea,
			Integer onlyReservation,String introduction,String image,Integer goodsId) throws Exception{
		PrintWriter out=response.getWriter();
		Goods goods=null;
		if(goodsId!=null) {
			goods=goodsService.getGoodsById(goodsId);
			goods=new Goods(goodsId,name,goods.getHasBuyNum(),introduction,image,inArea,goods.getIsfree(),
					goods.getIsAgency(),onlyReservation);
			if(goodsService.update(goods)) {
				out.print('1');
				return;
			}
			out.print('0');
			return;
		}
		
		goods=new Goods(name,image,inArea,0,onlyReservation,introduction);
		boolean result=goodsService.add(goods);
		int goodsId_=0;
		if(result) {
			goodsId_=goodsService.getLastGoodsId();
		}
		if(goodsId_!=0) {
			boolean flag=homePageService.addModuleToGoods(id,col,goodsId_);
			if(flag) {
				out.print('1');
				return;
			}
		}
		out.print('0');
	}
	
	@RequestMapping(value="module/del",method=RequestMethod.GET)
	public void delModule(HttpServletResponse response,Integer id) throws Exception{
		boolean result=homePageService.delModel(id);
		PrintWriter out=response.getWriter();
		if(result) {
			out.print('1');
			return;
		}
		out.print('0');
	}
	
	
	
	/*********分类管理*************/
	@RequestMapping(value="classify/index",method=RequestMethod.GET)
	public String classify() {
		return "homepage/classify/index";
	}
	
	@RequestMapping("classify/auClassifyInput")
	public String auClassifyInput(HttpServletRequest request,Integer classifyId) throws Exception{
		String[] classifys=new String[]{"","蔬菜水果区","水产冻品区","肉禽蛋品区","面点豆制品区","粮油调料区","副食干货区","日常用品区"};
		String classify=classifys[classifyId];
		request.setAttribute("classify",classify);
		request.setAttribute("classifyId",classifyId);
		//根据类名找到这个类下的商品
		List<List<Object>> list=homePageService.getClassify(classify);
		String[]picArray=new String[16];
		String[]descArray=new String[6];
		String mode=null;
		if(classifyId==3||classifyId==5||classifyId==6) {
			mode="mode2";
		}else if(classifyId==1||classifyId==2||classifyId==4) {
			mode="mode1";
		}
		if(list!=null&&list.size()>0) {//说明已经为该区上传图片
			//id,img,position,mode 
			if(list.size()>0) {
				//保存此个类别第一个物品的Id，根据它来看是对这个类别进行更改还是添加
				request.setAttribute("id",list.get(0).get(0));
				//request.setAttribute("mode",mode);
			}
		   for(int i=1;i<=16;i++) {
			   String position_="pic"+i;
			for(int j=0;j<list.size();j++) {
				//id,img,position
				List<Object> innerList=list.get(j);
				String pic=innerList.get(1).toString();
				String position=innerList.get(2).toString();
				if(position_.equals(position)) {
					picArray[i-1]=pic;
					break;
				}
				
				if("mode1".equals(mode)) {//说明这些图片是在模式一下
					if("desc11".equals(position)) {
						descArray[0]=pic;
					}else if("desc12".equals(position)) {
					    descArray[1]=pic;
					}else if("desc13".equals(position)) {
						descArray[2]=pic;
					}else if("desc21".equals(position)) {
						descArray[3]=pic;
					}else if("desc22".equals(position)) {
						descArray[4]=pic;
					}else if("desc23".equals(position)) {
						descArray[5]=pic;
					}
				}
			}
		   }
		}
		request.setAttribute("mode",mode);
		request.setAttribute("descArray",descArray);
		request.setAttribute("picArray",picArray);
		return "homepage/classify/input";
	}
	
	@RequestMapping("classify/uploadClassifyImg")
	public String uploadClassifyImg(HttpServletRequest response,HttpServletRequest request,MultipartFile file,
			String mode,String classify,int flag,Integer id,Integer classifyId) throws Exception {
		 if(mode==null||"".equals(mode)) {
			 mode="mode1";
		 }
		 String pic1=request.getParameter("pic1");
		 String pic2=request.getParameter("pic2");
		 String pic3=request.getParameter("pic3");
		 String pic4=request.getParameter("pic4");
		 String pic5=request.getParameter("pic5");
		 String pic6=request.getParameter("pic6");
		 String pic7=request.getParameter("pic7");
		 String pic8=request.getParameter("pic8");
		 String pic9=request.getParameter("pic9");
		 String pic10=request.getParameter("pic10");
		 String pic11=request.getParameter("pic11");
		 String pic12=request.getParameter("pic12");
		 String pic13=request.getParameter("pic13");
		 String pic14=request.getParameter("pic14");
		 String pic15=request.getParameter("pic15");
		 String pic16=request.getParameter("pic16");
		 String desc11=request.getParameter("desc11");
		 String desc12=request.getParameter("desc12");
		 String desc13=request.getParameter("desc13");
		 String desc21=request.getParameter("desc21");
		 String desc22=request.getParameter("desc22");
		 String desc23=request.getParameter("desc23");
		 String fileName=file.getOriginalFilename();
		 fileName=System.currentTimeMillis()+"_"+fileName;
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/classify");
		 File imgFile=new File(path,fileName);
		 if(!imgFile.exists()) {
			imgFile.mkdirs();
		 }
		 if(file!=null) {
			file.transferTo(imgFile);
		 }
		 passParam(request,flag,fileName,pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,
				 pic13,pic14,pic15,pic16);
		 request.setAttribute("mode",mode);
		 request.setAttribute("classify",classify);
		 request.setAttribute("classifyId",classifyId);
		 request.setAttribute("id",id);
		 String[] descArray=new String[]{desc11,desc12,desc13,desc21,desc22,desc23};
		 request.setAttribute("descArray",descArray);
		 return "homepage/classify/input";
	}
	
	
	@RequestMapping("classify/addClassifyPic")
	public void addClassifyPic(HttpServletRequest request,HttpServletResponse response,
			String classify,String mode,Integer id)throws Exception {
		 boolean isAdd=true;
		 if(id!=null) {
			 isAdd=false;
		 }
		 String pic1=request.getParameter("pic1");
		 String pic2=request.getParameter("pic2");
		 String pic3=request.getParameter("pic3");
		 String pic4=request.getParameter("pic4");
		 String pic5=request.getParameter("pic5");
		 String pic6=request.getParameter("pic6");
		 String pic7=request.getParameter("pic7");
		 String pic8=request.getParameter("pic8");
		 String pic9=request.getParameter("pic9");
		 String pic10=request.getParameter("pic10");
		 String pic11=request.getParameter("pic11");
		 String pic12=request.getParameter("pic12");
		 String pic13=request.getParameter("pic13");
		 String pic14=request.getParameter("pic14");
		 String pic15=request.getParameter("pic15");
		 String pic16=request.getParameter("pic16");
		 String desc11=request.getParameter("desc11");
		 String desc12=request.getParameter("desc12");
		 String desc13=request.getParameter("desc13");
		 String desc21=request.getParameter("desc21");
		 String desc22=request.getParameter("desc22");
		 String desc23=request.getParameter("desc23");
		 
		 PrintWriter out=null;
		 try {
		 out=response.getWriter();
		 if(isAdd) {//如果为添加操作
			 homePageService.addClassifyPic(pic1, classify,"pic1",mode);
			 homePageService.addClassifyPic(pic2, classify,"pic2",mode);
			 homePageService.addClassifyPic(pic3, classify,"pic3",mode);
			 homePageService.addClassifyPic(pic4, classify,"pic4",mode);
			 homePageService.addClassifyPic(pic5, classify,"pic5",mode);
			 homePageService.addClassifyPic(pic6, classify,"pic6",mode);
			 homePageService.addClassifyPic(pic7, classify,"pic7",mode);
			 homePageService.addClassifyPic(pic8, classify,"pic8",mode);
			 homePageService.addClassifyPic(pic9, classify,"pic9",mode);
			 homePageService.addClassifyPic(pic10, classify,"pic10",mode);
			 homePageService.addClassifyPic(pic11, classify,"pic11",mode);
		 if("mode1".equals(mode)) {
			 homePageService.addClassifyPic(pic12, classify,"pic12",mode);
			 homePageService.addClassifyPic(pic13, classify,"pic13",mode);
			 homePageService.addClassifyPic(pic14, classify,"pic14",mode);
			 homePageService.addClassifyPic(desc11, classify,"desc11", mode);
			 homePageService.addClassifyPic(desc12, classify,"desc12", mode);
			 homePageService.addClassifyPic(desc13, classify,"desc13", mode);
			 homePageService.addClassifyPic(desc21, classify,"desc21", mode);
			 homePageService.addClassifyPic(desc22, classify,"desc22", mode);
			 homePageService.addClassifyPic(desc23, classify,"desc23", mode);
		 }else if("mode2".equals(mode)) { 
			 homePageService.addClassifyPic(pic15, classify,"pic15",mode);
			 homePageService.addClassifyPic(pic16, classify,"pic16",mode);
		 }
		 }else {//如果为更新操作
			 List<List<Object>> list=homePageService.getClassify(classify);
			 for(int i=0;i<list.size();i++) {
				 List<Object> innerList=list.get(i);
				 int id_=Integer.parseInt(innerList.get(0).toString());
				 String pic=innerList.get(1).toString();
				 homePageService.updateClassifyPic(pic, id_);
			 }
		 }
		 }catch(Exception e) {
			 e.printStackTrace();
			 out.print(0);
			 return;
		 }
		 out.print(1);
	}
	
	@RequestMapping(value="classify/addClassifyGoodsInput",method=RequestMethod.GET)
	public String addClassifyGoodsInput(HttpServletRequest request,String pic,Integer classifyId) throws Exception{
		String[] classifys=new String[]{"","蔬菜水果区","水产冻品区","肉禽蛋品区","面点豆制品区","粮油调料区","副食干货区","日常用品区"};
		int goodsId=homePageService.getClassifyGoodsId(pic,classifys[classifyId]);
		//说明该类下商品首页展现图片还存在，但是没上传商品详细信息
		if(goodsId!=0) {
			Goods goods=goodsService.getGoodsById(goodsId);
			if(goods==null) {
				log.info("在商品管理区域，已将首页关联商品删除了");
				homePageService.addClassifyToGoods(classifys[classifyId], pic,0);
				request.setAttribute("pic",pic);
				request.setAttribute("classifyId",classifyId);
				return "homepage/classify/addGoodsInput";
			}
			request.setAttribute("name",goods.getName());
			request.setAttribute("inArea",goods.getInArea());
			request.setAttribute("onlyReservation",goods.getOnlyReservation());
			request.setAttribute("image",goods.getImage());
			request.setAttribute("introduction",goods.getIntroduction());
			request.setAttribute("goodsId",goodsId);
		}
		request.setAttribute("pic",pic);
		request.setAttribute("classifyId",classifyId);
		return "homepage/classify/addGoodsInput";
	}

	@RequestMapping("classify/upGoodsImg")
	public String classifyGoodsImg(HttpServletRequest request,MultipartFile imageFile,String pic,Integer classifyId,String image,Integer goodsId,
			String name,Integer onlyReservation,Integer inArea,String introduction) throws Exception{
		String fileName=imageFile.getOriginalFilename();
		fileName=System.currentTimeMillis()+"_"+fileName;
		
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/classify");
		 File file=new File(path,fileName);
		 if(!file.exists()) {
			 file.mkdirs();
		 }
		
		 if(imageFile!=null) {
			 imageFile.transferTo(file);
		 }
		 
		 request.setAttribute("image","imgs/homepage/classify/"+fileName);
		 request.setAttribute("goodsId",goodsId);
		 request.setAttribute("name",name);
		 request.setAttribute("onlyReservation",onlyReservation);
		 request.setAttribute("inArea",inArea);
		 request.setAttribute("introduction", introduction);
		 request.setAttribute("pic",pic);
		 request.setAttribute("classifyId",classifyId);
		return "homepage/classify/addGoodsInput";
	}
	
	@RequestMapping("classify/addClassifyGoods")
	public void addClassifyGoods(HttpServletResponse response,Integer goodsId,String name,String image,Integer inArea,
			String pic,Integer classifyId,Integer onlyReservation,String introduction) throws Exception{
		PrintWriter out=response.getWriter();
		Goods goods=null;
		if(goodsId!=null) {
			goods=goodsService.getGoodsById(goodsId);
			goods=new Goods(goodsId,name,goods.getHasBuyNum(),introduction,image,inArea,goods.getIsfree(),
					goods.getIsAgency(),onlyReservation);
			if(goodsService.update(goods)) {
				out.print('1');
				return;
			}
			out.print('0');
			return;
		}
		
		goods=new Goods(name,image,inArea,0,onlyReservation,introduction);
		boolean result=goodsService.add(goods);
		int goodsId_=0;
		if(result) {
			goodsId_=goodsService.getLastGoodsId();
		}
		if(goodsId_!=0) {
			String[] classifys=new String[]{"","蔬菜水果区","水产冻品区","肉禽蛋品区","面点豆制品区","粮油调料区","副食干货区","日常用品区"};
			boolean flag=homePageService.addClassifyToGoods(classifys[classifyId],pic,goodsId_);
			if(flag) {
				out.print('1');
				return;
			}
		}
		out.print('0');
		
	}
	
	@RequestMapping("classify/delClassifyGoods")
	public void delClassifyGoods(HttpServletResponse response,String classify) throws Exception{
		PrintWriter out=response.getWriter();
		boolean flag=homePageService.delClassifyGoods(classify);
		if(flag) {
			out.print('1');
			return;
		}
        out.print('0');
	}
	
	private void passParam(HttpServletRequest request,int flag,String fileName,String pic1,
			String pic2,String pic3,String pic4,String pic5,String pic6,String pic7,String pic8,
			String pic9,String pic10,String pic11,String pic12,String pic13,String pic14,String pic15,String pic16) {
			String prefix="imgs/homepage/classify/";
			String[] picArray=null;
			switch(flag) {
			case 0:
				picArray=new String[]{prefix+fileName,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 1:
				picArray=new String[]{pic1,prefix+fileName,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 2:
				picArray=new String[]{pic1,pic2,prefix+fileName,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 3:
				picArray=new String[]{pic1,pic2,pic3,prefix+fileName,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 4:
				picArray=new String[]{pic1,pic2,pic3,pic4,prefix+fileName,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 5:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,prefix+fileName,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 6:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,prefix+fileName,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 7:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,prefix+fileName,pic9,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 8:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,prefix+fileName,pic10,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 9:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,prefix+fileName,pic11,pic12,pic13,pic14,pic15,pic16};
				break;
			case 10:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,prefix+fileName,pic12,pic13,pic14,pic15,pic16};
				break;
			case 11:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,prefix+fileName,pic13,pic14,pic15,pic16};
				break;
			case 12:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,prefix+fileName,pic14,pic15,pic16};
				break;
			case 13:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,prefix+fileName,pic15,pic16};
				break;
			case 14:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,prefix+fileName,pic16};
				break;
			case 15:
				picArray=new String[]{pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8,pic9,pic10,pic11,pic12,pic13,pic14,pic15,prefix+fileName};
				break;
			}
			
			request.setAttribute("picArray", picArray);
		}
	
	/************首页健康合理膳食****************/
	@RequestMapping(value="health/index",method=RequestMethod.GET)
	public String health(HttpServletRequest request) throws Exception{
		List<List<Object>> list=homePageService.getHealthPic();
		if(list.size()>0) {
			request.setAttribute("pic",list.get(0).get(1));
			request.setAttribute("id",list.get(0).get(0));
		}
		return "homepage/health/index";
	}
	
	@RequestMapping("health/uploadImg")
	public String uploadImg(HttpServletRequest request,MultipartFile imgFile,Integer id) throws Exception{
		
		String fileName=imgFile.getOriginalFilename();
		 fileName=System.currentTimeMillis()+"_"+fileName;
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/health");
		 File file=new File(path,fileName);
		 if(!file.exists()) {
			 file.mkdirs();
		 }
		
		 if(imgFile!=null) {
			 imgFile.transferTo(file);
		 }
		 String pic="imgs/homepage/health/"+fileName;
		 if(id==null) {
		   homePageService.addHealthPic(pic);
		 }else {
		   homePageService.updateHealthPic(id,pic);
		 }
		 request.setAttribute("pic",pic);
		 return "homepage/health/index";
	}
	
	@RequestMapping(value="health/editContentInput",method=RequestMethod.GET)
	public String editContentInput(HttpServletRequest request,Integer id) throws Exception{
		request.setAttribute("id",id);
		String content=homePageService.getHealthContent(id);
		if(content!=null) {
			request.setAttribute("content",content);
		}
		return "homepage/health/content";
	}
	
	@RequestMapping("health/addHealthContent")
	public void addContent(HttpServletResponse response,String content,Integer id) throws Exception{
		boolean result= homePageService.addHealthContent(id,content);
		PrintWriter out=response.getWriter();
		if(result) {
			out.print('1');
			return;
		}
		out.print('0');
	}
	
	/********特别推荐专区************/
	
	 @RequestMapping(value="especial/index",method=RequestMethod.GET)
	  public String especial(HttpServletRequest request) throws Exception{
		  List<List<Object>> list=homePageService.getEspecial();
		  if(list.size()>0) {
			  request.setAttribute("pic1",list.get(0).get(1));
			  request.setAttribute("pic2",list.get(1).get(1));
			  request.setAttribute("id1",list.get(0).get(0));
			  request.setAttribute("id2",list.get(1).get(0));
		  }
		  return "homepage/especial/index";
	  }
	 
	 @RequestMapping(value="especial/uploadEspecialImg",method=RequestMethod.POST)
	 public String uploadEspecialImg(HttpServletRequest request,MultipartFile imgFile,String pic1,
			 String pic2,Integer flag,Integer id1,Integer id2) throws Exception{
		 String fileName=imgFile.getOriginalFilename();
		 fileName=System.currentTimeMillis()+"_"+fileName;
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/especial");
		 File file=new File(path,fileName);
		 if(!file.exists()) {
			 file.mkdirs();
		 }
		
		 if(imgFile!=null) {
			 imgFile.transferTo(file);
		 }
		 
		 String prefix="imgs/homepage/especial/"+fileName;
		 if(flag.intValue()==0) {
			 request.setAttribute("pic1",prefix);
			 request.setAttribute("pic2",pic2);
		 }else if(flag.intValue()==1) {
			 request.setAttribute("pic1",pic1);
			 request.setAttribute("pic2",prefix);
		 }
		 
		 request.setAttribute("id1",id1);
		 request.setAttribute("id2",id2);
		 return "homepage/especial/index";
	 }
	 
	 @RequestMapping("especial/addEspecialImg")
	 public void addEspecialImg(HttpServletResponse response,String pic1,String pic2,Integer id1,Integer id2) {
		 PrintWriter out=null;
		 try {
			out=response.getWriter();
		  if(id1==null) {
		      homePageService.addEspecialImg(pic1,1);
		      homePageService.addEspecialImg(pic2,2);
		  }else {
			  homePageService.updateEspecialImg(pic1,id1);
			  homePageService.updateEspecialImg(pic2,id2);
		  }
		 }catch(Exception e) {
			 out.print('0');
			 return;
		 }
		 out.print('1');
	 }
	 
	 @RequestMapping(value="especial/addEspecialGoodsInput",method=RequestMethod.GET)
	 public String addEspecialGoodsInput(HttpServletRequest request,Integer id) throws Exception{
		 request.setAttribute("especialId",id);
		 int goodsId=homePageService.getEspecialGoodsId(id);
		 if(goodsId!=0) {
			 Goods goods=goodsService.getGoodsById(goodsId);
			 request.setAttribute("name",goods.getName());
			 request.setAttribute("img",goods.getImage());
			 request.setAttribute("inArea",goods.getInArea());
			 request.setAttribute("introduction",goods.getIntroduction());
			 request.setAttribute("goodsId",goodsId);
		 }
		 return "homepage/especial/addGoodsInput";
	 }
	 
	 @RequestMapping("especial/addEspecialGoodsImg")
	 public String addEspecialGoodsImg(HttpServletRequest request,MultipartFile imgFile,
			 String name,String introduction,Integer inArea,Integer especialId,Integer goodsId) throws Exception{
		 String fileName=imgFile.getOriginalFilename();
		 fileName=System.currentTimeMillis()+"_"+fileName;
		 String path=request.getSession().getServletContext().getRealPath("/imgs/homepage/especial");
		 File file=new File(path,fileName);
		 if(!file.exists()) {
			 file.mkdirs();
		 }
		
		 if(imgFile!=null) {
			 imgFile.transferTo(file);
		 }
		 request.setAttribute("img","imgs/homepage/especial/"+fileName);
		 request.setAttribute("name",name);
		 request.setAttribute("introduction",introduction);
		 request.setAttribute("inArea",inArea);
		 request.setAttribute("especialId",especialId);
		 request.setAttribute("goodsId",goodsId);
		 return "homepage/especial/addGoodsInput";
	 }
	 
	 
	 @RequestMapping("especial/addEspecialGoods")
	 public void addEspecialGoods(HttpServletResponse response,String name,String img,String introduction,
			 Integer inArea,Integer especialId,Integer goodsId) throws Exception{
		 Goods goods=new Goods(name, img, inArea, 0, 0, introduction);
		 PrintWriter out=response.getWriter();
		 boolean flag=false;
		 if(goodsId==null) {
		  if(goodsService.add(goods)) {
			 int id=goodsService.getLastGoodsId();
			 flag=homePageService.addGoodsToEsPecial(especialId,id);
		  }
		 }else {
			 Goods g=goodsService.getGoodsById(goodsId);
			 Goods newGoods=new Goods(goodsId, name, g.getHasBuyNum(), introduction, img, 
					 inArea, g.getIsfree(), g.getIsAgency(), g.getOnlyReservation());
			 flag=goodsService.update(newGoods);
		 }
		 if(flag) {
			 out.print('1');
			 return;
		 }
		 out.print('0');
	 }
	 
	
}

