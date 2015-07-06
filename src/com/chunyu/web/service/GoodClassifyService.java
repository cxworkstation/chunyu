package com.chunyu.web.service;

import java.util.List;
import java.util.Map;


public interface GoodClassifyService {

	//得到所有记录条数
	long getCount();

	//查询所有本店商品以及三个级的分类
	List<Map<String,Object>> selectAllGoods(int pageOffSet, int pageSize);

	//得到不重复的goodId
	List<Map<String,Object>> getGoodIds(int pageOffSet, int pageSize);

	//删除现有分类
	int resubmit(int id);

}
