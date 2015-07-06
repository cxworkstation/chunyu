package com.chunyu.web.service;

import java.util.List;
import java.util.Map;

import com.chunyun.web.model.Agency;
import com.chunyun.web.model.Goods;

public interface AgencyService {

	//查询所有经营户
	List<Agency> queryAllAngency(int pageOffSet, int pageSize);
	
	//获得所有记录条数
	long getCount();

	//添加经营户
	int addAgency(Agency agency);

	//删除经营户以及经营户下的所有商品
	int delete(int id);

	//添加经营户商品
	void addGoods(Goods goods, int id);

	//查找某个代理商的商品
	List<Map<String, Object>> selectAgencyGood(int agencyId);

	//查找图片地址
	List<Map<String,Object>> getImgAddress(int id);

	//删除代理商商品
	int deleteGood(int id,int goodsId);

	//查询条件下经营户
	List<Agency> queryCheckAngency(int pageOffSet, int pageSize,
			String selectName, String keyword);

	//查询条目记录
	long getCheckCount(String selectName, String keyword);

}
