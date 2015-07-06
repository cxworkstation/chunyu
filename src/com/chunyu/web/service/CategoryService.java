package com.chunyu.web.service;

import java.util.List;

public interface CategoryService {
	
	boolean addCateImgs(Integer cateId,String pic1,String pic2,int ordernum);
	
	List<List<Object>> getCateImgList(Integer cateId) throws Exception;
	
	boolean addGoodsToCate(int cateId,int goodsId);
	
	List<List<Object>> getCateList() throws Exception;

}
