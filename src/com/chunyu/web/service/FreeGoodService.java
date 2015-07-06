package com.chunyu.web.service;

import java.util.List;

import com.chunyun.web.model.Goods;

public interface FreeGoodService {

	//查询所有免费商品
	List<Goods> selectFreeGoods(int pageOffSet, int pageSize);

	//得到所有记录条数
	long getCount();

	//将免费商品转换为普通商品
	int update(int id);

	//删除免费商品
	int delete(int id);

	//批量删除
	int bulkdel(String[] ids);

   // 条件查询商品
	List<Goods> queryCheck(int pageOffSet, int pageSize, String checkName,
			String keyword);
	
    //条件查询记录数
	long getRecordNumCheck(String checkName, String keyword);
}
