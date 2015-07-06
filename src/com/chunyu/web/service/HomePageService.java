package com.chunyu.web.service;

import java.util.List;

import com.chunyun.web.model.BroadCast;
import com.chunyun.web.model.Module;

public interface HomePageService {
	/********轮播图操作*******/
	boolean addBroad(String chiefPic,String pic1,String pic2,String pic3,String pic4,int orderVal);
	
	boolean addOrUpdateBroadImg(Integer id,String path,Integer index);
	
	int getLastestBroadImgId();
	
	List<BroadCast> getBroadList();
	
	boolean delBroad(int id);
	
	BroadCast getBroadById(int id);

	boolean updateBroad(BroadCast broad);
	
	boolean addBroadToGoods(int id,int good,int goodsId);
	
	int getGoodsId(int id,int good) throws Exception;
	
	/********公告操作*********/
	boolean addNotice(String title,int flag);
	
	List<List<Object>>getNotices() throws Exception;
	
	boolean addContent(int id,String content);
	
	String getContentById(int noticeId) throws Exception;
	
	/*******模块操作*********/
	boolean addModulePic(String pic1,String pic2,String pic3,String pic4,String pic5,String pic6,String module);
	boolean updateModulePic(Module m);
	Module getModule(String module);
	
	boolean addModuleToGoods(int id,int col,int goodsId);
	
	int getModuleGoodsId(int id,int col) throws Exception;
	
	boolean delModel(int id);
	
	/*******类别操作*********/
	boolean addClassifyPic(String pic,String classify,String postion,String mode);
	List<List<Object>> getClassify(String classify) throws Exception;
	boolean updateClassifyPic(String pic,int id);
	boolean delClassifyPic(int id);
	
	int getClassifyGoodsId(String pic,String classify) throws Exception;
	
	boolean addClassifyToGoods(String classify,String pic,int goodsId);
	
	boolean delDesc(List<List<Object>> descList);
	
	boolean delClassifyGoods(String classify);
	
	boolean delClassifyPic(String position);
	
	/*******健康合理膳食**********/
	boolean addHealthPic(String pic);
	
	List<List<Object>> getHealthPic() throws Exception;
	
	boolean updateHealthPic(int id,String pic);
	
	boolean addHealthContent(int id,String content);
	
	String getHealthContent(int id) throws Exception;
	
	/********特别推荐区************/
	boolean addEspecialImg(String img,int position);
	
	List<List<Object>>getEspecial() throws Exception;
	
	boolean updateEspecialImg(String img,int id);
	
	boolean addGoodsToEsPecial(int especialId,int id);
	
	int getEspecialGoodsId(int id) throws Exception;
}
