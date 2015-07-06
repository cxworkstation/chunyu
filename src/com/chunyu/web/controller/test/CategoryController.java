package com.chunyu.web.controller.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.chunyu.web.service.CategoryService;
import com.chunyu.web.service.GoodsService;
import com.chunyun.web.model.Goods;

@Controller
@RequestMapping("category/manage")
public class CategoryController{
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	@RequestMapping("main")
	public String main(HttpServletRequest request) throws Exception{
		List<List<Object>> list=categoryService.getCateList();
		request.setAttribute("list", list);
		return "category/main";
	}
	
	@RequestMapping(value="broad",method=RequestMethod.GET)
	 public String broad(HttpServletRequest request,Integer cateId) throws Exception {
		 List<List<Object>> list=categoryService.getCateImgList(cateId);
		 request.setAttribute("cateId", cateId);
		 if(list.size()==0) {
			 return "category/index"; 
		 }
		 //id,img1,img2,goodsId
		 int t=1;
		 for(int i=0;i<list.size();i++) {
			 List<Object> innerList=list.get(i);
			 request.setAttribute("id"+i,innerList.get(0));
			 request.setAttribute("img"+t,innerList.get(1));
			 request.setAttribute("img"+(t+1),innerList.get(2));
			 request.setAttribute("goodsId"+i,innerList.get(3));
			 t+=2;
		 }
		 
		 return "category/index"; 
	 }
	
	//将轮播图图片上传到服务器
	@RequestMapping("uploadImg")
	public String uploadImg(HttpServletRequest request,Integer flag,MultipartFile imgFile,String img1,String img2,
			String img3,String img4,String img5,String img6,String img7,String img8,Integer position,Integer cateId) throws Exception{
		  String fileName=imgFile.getOriginalFilename();
		  fileName=System.currentTimeMillis()+"_"+fileName;
		  String path=request.getSession().getServletContext().getRealPath("/imgs/category");
		  File file=new File(path,fileName);
		  if(!file.exists()){
			 file.mkdir();
		  }
		  imgFile.transferTo(file);
		  passParam(request,flag,fileName,img1,img2,img3,img4,img5,img6,img7,img8);
		  request.setAttribute("position",position);
		  request.setAttribute("cateId",cateId);
		  return "category/index";
	}
	
	//将轮播图图片存储到数据库
	@RequestMapping("addImg")
	public void addImg(HttpServletResponse response,String img1,String img2,String img3,String img4,String img5,
			String img6,String img7,String img8,Integer cateId) {
		   PrintWriter out=null; 
		   try {
			out=response.getWriter();
		    categoryService.addCateImgs(cateId,img1, img2,1);
		    categoryService.addCateImgs(cateId,img3, img4,2);
		    categoryService.addCateImgs(cateId,img5, img6,3);
		    categoryService.addCateImgs(cateId,img7, img8,4);
		   }catch(Exception e) {
			   out.print('0');
			   return;
		   }
		 out.print('1');
	}
	
	@RequestMapping(value="addGoodsInput",method=RequestMethod.GET)
	public String addGoodsInput(HttpServletRequest request,Integer goodsId,Integer id) {
		//如果goodsId不为空，说明已经添加了商品信息，意味着是对商品信息进行更改
		if(goodsId!=null) {
			Goods goods=goodsService.getGoodsById(goodsId);
			request.setAttribute("pic",goods.getImage());
			request.setAttribute("name",goods.getName());
			request.setAttribute("introduction",goods.getIntroduction());
			request.setAttribute("inArea",goods.getInArea());
			request.setAttribute("goodsId",goodsId);
		}
		request.setAttribute("id",id);
		return "category/addGoodsInput";
	}
	
	//上传商品图片到服务器
	@RequestMapping(value="addGoodsImg",method=RequestMethod.POST)
	public String addGoodsImg(HttpServletRequest request,MultipartFile imgFile,String pic,String name,
			Integer inArea,String introduction,Integer id,Integer goodsId) throws Exception{
		  String fileName=imgFile.getOriginalFilename();
		  fileName=System.currentTimeMillis()+"_"+fileName;
		  String path=request.getSession().getServletContext().getRealPath("/imgs/upload0");
		  File file=new File(path,fileName);
		  if(!file.exists()){
			 file.mkdir();
		  }
		  imgFile.transferTo(file);
		  request.setAttribute("pic","imgs/upload0/"+fileName);
		  request.setAttribute("name",name);
		  request.setAttribute("inArea",inArea);
		  request.setAttribute("introduction",introduction);
		  request.setAttribute("id",id);
		  request.setAttribute("goodsId",goodsId);
		  return "category/addGoodsInput";
	}
	
	//添加商品信息到数据库
	@RequestMapping(value="addGoods",method=RequestMethod.POST)
	public void addGoods(HttpServletResponse response,Integer id,String name,String pic,Integer inArea,
			String introduction,Integer goodsId) throws Exception{
		PrintWriter out=response.getWriter();
		if(goodsId==null) {
		 Goods goods=new Goods(name, pic, inArea, 0, 0, introduction);
		 boolean flag=goodsService.add(goods);
		 if(flag) {
	      int goodid=goodsService.getLastGoodsId();
	      flag=categoryService.addGoodsToCate(id,goodid);
		  if(flag) {
		    out.print('1');
		    return;
		  }
		 }
		out.print('0');
		return;
	  }
		
	  Goods pro=goodsService.getGoodsById(goodsId);
	  Goods goods=new Goods(goodsId, name,pro.getHasBuyNum(), introduction, pic, inArea, pro.getIsfree(), pro.getIsAgency(), pro.getOnlyReservation());
	  
	  if(goodsService.update(goods)) {
		  out.print('1');
		  return;
	  }
	  out.print('0');
	}
	
	private void passParam(HttpServletRequest request,int flag,String fileName,String img1,String img2,String img3,String img4,
			String img5,String img6,String img7,String img8) {
		  String prefix="imgs/category/"+fileName;
		  switch(flag) {
		  case 0:
			  request.setAttribute("img1",prefix);
			  request.setAttribute("img2",img2);
			  request.setAttribute("img3",img3);
			  request.setAttribute("img4",img4);
			  request.setAttribute("img5",img5);
			  request.setAttribute("img6",img6);
			  request.setAttribute("img7",img7);
			  request.setAttribute("img8",img8);
			  return;
			  
		  case 1:
			  request.setAttribute("img1",img1);
			  request.setAttribute("img2",prefix);
			  request.setAttribute("img3",img3);
			  request.setAttribute("img4",img4);
			  request.setAttribute("img5",img5);
			  request.setAttribute("img6",img6);
			  request.setAttribute("img7",img7);
			  request.setAttribute("img8",img8);
			  return;
			  
		  case 2:
			  request.setAttribute("img1",img1);
			  request.setAttribute("img2",img2);
			  request.setAttribute("img3",prefix);
			  request.setAttribute("img4",img4);
			  request.setAttribute("img5",img5);
			  request.setAttribute("img6",img6);
			  request.setAttribute("img7",img7);
			  request.setAttribute("img8",img8);
			  return;
			  
		  case 3:
			  request.setAttribute("img1",img1);
			  request.setAttribute("img2",img2);
			  request.setAttribute("img3",img3);
			  request.setAttribute("img4",prefix);
			  request.setAttribute("img5",img5);
			  request.setAttribute("img6",img6);
			  request.setAttribute("img7",img7);
			  request.setAttribute("img8",img8);
			  return;
			  
		  case 4:
			  request.setAttribute("img1",img1);
			  request.setAttribute("img2",img2);
			  request.setAttribute("img3",img3);
			  request.setAttribute("img4",img4);
			  request.setAttribute("img5",prefix);
			  request.setAttribute("img6",img6);
			  request.setAttribute("img7",img7);
			  request.setAttribute("img8",img8);
			  return;
			  
		  case 5:
			  request.setAttribute("img1",img1);
			  request.setAttribute("img2",img2);
			  request.setAttribute("img3",img3);
			  request.setAttribute("img4",img4);
			  request.setAttribute("img5",img5);
			  request.setAttribute("img6",prefix);
			  request.setAttribute("img7",img7);
			  request.setAttribute("img8",img8);
			  return;
			  
		  case 6:
			  request.setAttribute("img1",img1);
			  request.setAttribute("img2",img2);
			  request.setAttribute("img3",img3);
			  request.setAttribute("img4",img4);
			  request.setAttribute("img5",img5);
			  request.setAttribute("img6",img6);
			  request.setAttribute("img7",prefix);
			  request.setAttribute("img8",img8);
			  return;
			  
		  case 7:
			  request.setAttribute("img1",img1);
			  request.setAttribute("img2",img2);
			  request.setAttribute("img3",img3);
			  request.setAttribute("img4",img4);
			  request.setAttribute("img5",img5);
			  request.setAttribute("img6",img6);
			  request.setAttribute("img7",img7);
			  request.setAttribute("img8",prefix);
			  return;
			  
		  }
		
	}
}
