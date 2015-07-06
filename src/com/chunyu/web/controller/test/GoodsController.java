package com.chunyu.web.controller.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.chunyu.web.service.ClassifyService;
import com.chunyu.web.service.GoodsService;
import com.chunyu.web.utils.PageModel;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Classify;
import com.chunyun.web.model.GoodPrice;
import com.chunyun.web.model.Goods;
import com.google.common.cache.Weigher;

@Controller
@RequestMapping("goods/manage")
public class GoodsController extends BaseController  {
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	@Resource(name="classifyService")
	private ClassifyService classifyService;
	
	//展示所有商品的页面
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		PageModel<Goods> pm=new PageModel<Goods>();
		//pager.offset是那个包要求的,返回的是数据库查询起始值
		String StrPageOffSet=request.getParameter("pager.offset");
		int pageOffSet=0;
		if(StrPageOffSet!=null){
			pageOffSet=TypeConversion.StringToInt(StrPageOffSet);
		}
		List<Goods> list=goodsService.query(pageOffSet,pm.pageSize);
		long totalCount=goodsService.getRecordNum();
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "goods/index";
	}
	
	@RequestMapping(value="getSubClassify")
	public void getSubClassify(HttpServletResponse response,int classifyId){
		List<Classify> innerlist=classifyService.getSubClassify(classifyId);
        Map<String,List<Classify>> jsonMap=new HashMap<String,List<Classify>>();
        jsonMap.put("goods",innerlist);
        printToJson(response, jsonMap);
	}
	
	@RequestMapping(value="addClassify")
	public void addClassify(HttpServletResponse response,int id,int classify1,int classify2,
			int classify3) throws Exception{
		//要求三个分类不能为空
		PrintWriter out=response.getWriter();
		try {
         goodsService.addClassify(id,classify1,classify2,classify3);
		}catch(Exception e) {
			out.print('0');
			return;
		}
		out.print('1');
	}
	
	@RequestMapping(value="setClassifyInput",method=RequestMethod.GET)
	public String setClassifyInput(HttpServletRequest request,int id) throws Exception{
		//查询所属分类是否已经添加记录
		int hasClassigy=goodsService.selectRecordinClassify(id);
		Goods goods=goodsService.getGoodsById(id);
		request.setAttribute("goodsName",goods.getName());
		 request.setAttribute("id", id);
		//当该商品没有分类时
		if(hasClassigy==0){
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
		  return "goods/setClassifyInput";
		}
		
		//如果该商品已有分类则将其分类进行展示
		List<String> list=classifyService.getGoodsClassify(id);
		request.setAttribute("list",list);
		return "goods/classify";
	}
	
	@RequestMapping(value="delClassify",method=RequestMethod.GET)
	public String delClassify(HttpServletRequest request,int id) {
		goodsService.delClassify(id);
		return index(request);
	}
	
	@RequestMapping(value="addColumn")
	public void addColumn(HttpServletRequest request,int id){
		String[] types=request.getParameterValues("column");
		int len=types.length;
		if(len!=0){
			int[] columnIds = new int[len];
			for(int i=0;i<len;i++){
				columnIds[i]=Integer.parseInt(types[i]);
			}
			 goodsService.addColumn(id,columnIds);
		}
       
	}
	
	@RequestMapping(value="setColumnInput")
	public String setColumnInput(HttpServletRequest request,int id){
		//查询所属栏目是否已经添加记录
		int hasColumn=goodsService.selectRecordinColumn(id);
		if(hasColumn==0){
			request.setAttribute("id", id);
			return "goods/setColumnInput";
		}
		//这里跳转的页面要重新做一个。。。。。。。。。。。。
		return "goods/index";
	}
	
	@RequestMapping(value="setPriceInput")
	public String setPriceInput(HttpServletRequest request,int goodId){
		List<GoodPrice> goodPrices=goodsService.selectAllPrice(goodId);
		Goods goods=goodsService.getGoodsById(goodId);
		request.setAttribute("goodId", goodId);
		request.setAttribute("goodPrices", goodPrices);
		request.setAttribute("name",goods.getName());
		return "goods/setPriceInput";
	}
	
	@RequestMapping(value="setPrice")
	public String setPrice(HttpServletRequest request,int id,String size,float price,String weight) throws Exception{
		float w=0F;
		if(weight!=null&&!"".equals(weight)) {
			w=Float.parseFloat(weight);
		}
		boolean flag=goodsService.getValidPrice(id);
		boolean result=goodsService.setPrice(id,size,price,w,flag);
		List<GoodPrice> goodPrices=goodsService.selectAllPrice(id);
		request.setAttribute("goodId", id);
		request.setAttribute("goodPrices", goodPrices);
		if(result) {
			return "common/success";
		}
		return "common/error";
	}

	@RequestMapping(value="sizeAttrInput",method=RequestMethod.GET)
	public String setSizeAttrInput(HttpServletRequest request,Integer id) throws Exception{
		Goods goods=goodsService.getGoodsById(id);
		request.setAttribute("name", goods.getName());
		request.setAttribute("id",id);
		return "goods/attr";
	}
	
	@RequestMapping("delSizeAttr")
	public void delSizeAttr(HttpServletResponse response,int id) throws Exception{
		boolean flag=goodsService.delSizeAttr(id);
		PrintWriter out=response.getWriter();
		if(flag) {
			out.print(id);
			return;
		}
		out.print("cao");
	}
	
	@RequestMapping(value="setPriceState",method=RequestMethod.GET)
	public void setPriceState(Integer id,Integer id_) {
	    goodsService.setPriceState(id,1);	
	    goodsService.setPriceState(id_,0);	
	}
	
	@RequestMapping(value="upImgInput",method=RequestMethod.GET)
	public String upImg(HttpServletRequest request) throws Exception{
		String idStr=request.getParameter("id");
		int id=Integer.parseInt(idStr);
		List<List<Object>> list=goodsService.getGoodsSubImgList(id);
		if(list!=null&&list.size()>0) {
			List<Object> innerList=list.get(0);
		    for(int i=0;i<innerList.size();i++ ) {
		    	if(i==0) {
		    		request.setAttribute("subImgId",innerList.get(i));
		    		continue;
		    	}
		    	
		    	if(i<=4) {
		    		int t=i-1;
		    		request.setAttribute("img"+t,innerList.get(i));
		    	}else {
		    		int t=i-5;
		    		request.setAttribute("subImg"+t,innerList.get(i));
		    	}
		    }
		}
		request.setAttribute("id", id);
		return "goods/upImgInput";
	}
	
	@RequestMapping(value="upMoreImg")
	public String upMoreImg(HttpServletRequest request,MultipartFile mfile,Integer id,Integer subImgId,String img0,String img1,
			String img2,String img3,String subImg0,String subImg1,String subImg2,String subImg3,int flag,float screenY) throws Exception{
		 String fileName=mfile.getOriginalFilename();
		 fileName=System.currentTimeMillis()+"_"+fileName;
		 String path=request.getSession().getServletContext().getRealPath("/imgs/upload0");
		 File file=new File(path,fileName);
         if(!file.exists()){
         	file.mkdirs();
         }
        mfile.transferTo(file);
        request.setAttribute("id",id);
        request.setAttribute("subImgId",subImgId);
        request.setAttribute("screenY", screenY);
        passParam(request,fileName,img0,img1,img2,img3,subImg0,subImg1,subImg2,subImg3,flag);
		return "goods/upImgInput";
		
	}
	
	@RequestMapping("addMoreImg")
	public void addMoreImg(HttpServletResponse response,Integer id,Integer subImgId,String img0,String img1,
			String img2,String img3,String subImg0,
			String subImg1,String subImg2,String subImg3) throws Exception{
		boolean result=false;
		if(subImgId==null) {
		    result=goodsService.addSubImage(img0,img1,img2,img3,subImg0,subImg1,subImg2,subImg3,id);
		    
		}else {
			result=goodsService.updateSubImage(subImgId,img0,img1,img2,img3,subImg0,subImg1,subImg2,subImg3);
		}
		PrintWriter out=response.getWriter();
		if(result) {
			out.print('1');
			return;
		}
		out.print('0');
		
	}
	private void passParam(HttpServletRequest request,String fileName,String img0,String img1,String img2,String img3,
			String subImg0,String subImg1,String subImg2,String subImg3,int flag) {
		String prefix="imgs/upload0/";
		  switch(flag) {
		  case 0:
			  request.setAttribute("img0",prefix+fileName);
			  request.setAttribute("img1",img1);
		      request.setAttribute("img2",img2);
		      request.setAttribute("img3",img3);
		      request.setAttribute("subImg0",subImg0);
		      request.setAttribute("subImg1",subImg1);
		      request.setAttribute("subImg2",subImg2);
		      request.setAttribute("subImg3",subImg3);
		      return;
		  case 1:
			  request.setAttribute("img0",img0);
			  request.setAttribute("img1",img1);
		      request.setAttribute("img2",img2);
		      request.setAttribute("img3",img3);
		      request.setAttribute("subImg0",prefix+fileName);
		      request.setAttribute("subImg1",subImg1);
		      request.setAttribute("subImg2",subImg2);
		      request.setAttribute("subImg3",subImg3);
		      return;
		  case 2:
			  request.setAttribute("img0",img0);
			  request.setAttribute("img1",prefix+fileName);
		      request.setAttribute("img2",img2);
		      request.setAttribute("img3",img3);
		      request.setAttribute("subImg0",subImg0);
		      request.setAttribute("subImg1",subImg1);
		      request.setAttribute("subImg2",subImg2);
		      request.setAttribute("subImg3",subImg3);
		      return;
		  case 3:
			  request.setAttribute("img0",img0);
			  request.setAttribute("img1",img1);
		      request.setAttribute("img2",img2);
		      request.setAttribute("img3",img3);
		      request.setAttribute("subImg0",subImg0);
		      request.setAttribute("subImg1",prefix+fileName);
		      request.setAttribute("subImg2",subImg2);
		      request.setAttribute("subImg3",subImg3);
		      return;  
		  case 4:
			  request.setAttribute("img0",img0);
			  request.setAttribute("img1",img1);
		      request.setAttribute("img2",prefix+fileName);
		      request.setAttribute("img3",img3);
		      request.setAttribute("subImg0",subImg0);
		      request.setAttribute("subImg1",subImg1);
		      request.setAttribute("subImg2",subImg2);
		      request.setAttribute("subImg3",subImg3);
		      return; 
		  case 5:
			  request.setAttribute("img0",img0);
			  request.setAttribute("img1",img1);
		      request.setAttribute("img2",img2);
		      request.setAttribute("img3",img3);
		      request.setAttribute("subImg0",subImg0);
		      request.setAttribute("subImg1",subImg1);
		      request.setAttribute("subImg2",prefix+fileName);
		      request.setAttribute("subImg3",subImg3);
		      return; 
		  case 6:
			  request.setAttribute("img0",img0);
			  request.setAttribute("img1",img1);
		      request.setAttribute("img2",img2);
		      request.setAttribute("img3",prefix+fileName);
		      request.setAttribute("subImg0",subImg0);
		      request.setAttribute("subImg1",subImg1);
		      request.setAttribute("subImg2",subImg2);
		      request.setAttribute("subImg3",subImg3);
		      return;
		  case 7:
			  request.setAttribute("img0",img0);
			  request.setAttribute("img1",img1);
		      request.setAttribute("img2",img2);
		      request.setAttribute("img3",img3);
		      request.setAttribute("subImg0",subImg0);
		      request.setAttribute("subImg1",subImg1);
		      request.setAttribute("subImg2",subImg2);
		      request.setAttribute("subImg3",prefix+fileName);
		      return;
		  }
	}
	
	@RequestMapping(value="upImg")
	public String upImg(String subImage,int ID){
		goodsService.addImage(subImage,ID);
		return "index";
	}

	
	//跳转到addInput页面
	@RequestMapping(value="addInput",method=RequestMethod.GET)
	public String addInput(HttpServletRequest request,Integer isfree) {
		request.setAttribute("isfree", isfree);
		return "goods/addInput";
	}
	
	/**
	 * 
	 * @param request
	 * @param imageFile:上传的图片文件
	 * @param name：商品名称
	 * @param introduction：商品介绍
	 * @param isfree：是否免费:0不免费、1免费
	 * @param inArea：是否在配送区域：0不在、1在配送区域
	 * @param onlyReservation：是否只能预定：0任意、1只能预定
	 * @return
	 */
	@RequestMapping("uploadImg")
	public String uploadImg(HttpServletRequest request,MultipartFile imageFile,String image,String name,String introduction,
			Integer isfree,Integer inArea,Integer onlyReservation) throws Exception{
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
		  request.setAttribute("isfree",isfree);
		  request.setAttribute("inArea", inArea);
		  request.setAttribute("onlyReservation",onlyReservation);
		  return "goods/addInput";
	}
	
	
	@RequestMapping("add")
	public void add(HttpServletResponse response,String name,String image,Integer inArea,Integer isfree,
			Integer onlyReservation,String introduction)throws Exception{
		Goods goods=new Goods(name,image,inArea,isfree,onlyReservation,introduction);
		boolean result=goodsService.add(goods);
		PrintWriter out=response.getWriter();
		if(result) {
			out.print('1');
			return;
		}
		out.print('0');
	}
	
	@RequestMapping(value="delete")
	public void delete(HttpServletResponse response,int id){
		int result=goodsService.delete(id);
		if(result!=1){
			printStrToHtml(response,"cao");
			return;
		}
			printStrToHtml(response,id+"");
	}
			
	//跳转到updateInput页面
	@RequestMapping(value="updateInput",method=RequestMethod.GET)
	public String updateInput(HttpServletRequest request,int id){
        Goods good=goodsService.getGoodsById(id);
        request.setAttribute("id", id);
		request.setAttribute("name",good.getName());
		request.setAttribute("introduction", good.getIntroduction());
		request.setAttribute("image", good.getImage());
		request.setAttribute("hasBuyNum", good.getHasBuyNum());
		//这是多选框
		request.setAttribute("isfree",good.getIsfree());
		request.setAttribute("inArea",good.getInArea());
		request.setAttribute("isAgency",good.getIsAgency());
		request.setAttribute("onlyReservation",good.getOnlyReservation());
		return "goods/updateInput";
	}
	
	@RequestMapping("updateUploadImg")
	public String updateUploadImg(HttpServletRequest request,MultipartFile imageFile,Integer id,String image,String name,
			Integer inArea,Integer isfree,Integer isAgency,Integer hasBuyNum,Integer onlyReservation,String introduction) throws Exception{
		String fileName=imageFile.getOriginalFilename();
		fileName=System.currentTimeMillis()+"_"+fileName;
		String path=request.getSession().getServletContext().getRealPath("/imgs/upload");
		File file=new File(path,fileName);
		if(!file.exists()){
			file.mkdirs();
		}
		imageFile.transferTo(file);
		request.setAttribute("id",id);
		request.setAttribute("name",name);
		request.setAttribute("inArea",inArea);
		request.setAttribute("isfree",isfree);
		request.setAttribute("isAgency", isAgency);
		request.setAttribute("hasBuyNum",hasBuyNum);
		request.setAttribute("onlyReservation",onlyReservation);
		request.setAttribute("introduction",introduction);
		request.setAttribute("image","imgs/upload/"+fileName);
		return "goods/updateInput";
	}
	
	//实现了update
	@RequestMapping("update")
	public void update(HttpServletResponse response,Integer id,String image,String name,Integer inArea,Integer isfree,
			Integer isAgency,Integer hasBuyNum,Integer onlyReservation,String introduction) throws Exception{
		Goods goods=new Goods(id,name,hasBuyNum,introduction,image,inArea,isfree,isAgency,onlyReservation);
		boolean result=goodsService.update(goods);
		PrintWriter out=response.getWriter();
		if(result) {
			out.print('1');
			return;
		}
		out.print('0');
	}
	
	
	@RequestMapping(value="detail",method=RequestMethod.GET)
	public String detail(HttpServletRequest request,int id){
		//首先根据id查询商品的详情，如果存在则展现
		List<Map<String,Object>> contentList=goodsService.checkDetail(id);
		String content="";
		if(contentList.size()>0){
		 Map<String,Object> contentMap=contentList.get(0);
		 content=contentMap.get("content").toString();
		}
		request.setAttribute("content", content);
		request.setAttribute("id", id);
		return "goods/ueditor";
	}
	
	@RequestMapping(value="getContent")
	public void getContent(HttpServletResponse response,String content,int id){
		int counts=goodsService.getDetailCount(id);
		//需要在其后加上jsp的路径，因为有可能在更改的时候，有部分字符串是经过处理的，而有部分是没有
		//经过处理的。加上jsp路径后，经过处理的就不会相匹配。
		String prefix_domain="http://localhost:8080/chunyu/jsp/";
		if(content!=null) {
			//因为图片和前台展现不在同一个项目，因此要对图片地址做一下处理
			content=content.replaceAll("src=\"jsp", "src=\""+prefix_domain);
		}
		int result=-1;
		if(counts==0){
	    //不存在-----添加
		result=goodsService.addUeditorContent(id,content);
		}else{
	    //存在-----修改
		result=goodsService.updateEditor(content,id);
		}
		if(result==1){
			printStrToHtml(response, "操作成功");
			return;
		}
		printStrToHtml(response, "操作失败");
	}
	
	@RequestMapping(value="bulkdel")
	public void bulkdel(HttpServletResponse response,String str){
		String[] ids=str.split("_");
		int result=goodsService.bulkdel(ids);
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
		List<Goods> list=goodsService.queryCheck(pageOffSet,pm.pageSize,checkName,keyword);
		long totalCount=goodsService.getRecordNumCheck(checkName,keyword);
		pm.setList(list);
		pm.setPageOffSet(pageOffSet);
		pm.setTotalCount(totalCount);
		request.setAttribute("pm", pm);
		return "goods/index";
	}
	
}
