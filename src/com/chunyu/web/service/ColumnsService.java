package com.chunyu.web.service;

import java.util.List;
import java.util.Map;

public interface ColumnsService {

	//查询所有栏目的商品信息和栏目信息
	List<Map<String,Object>> selectAllColumnGoods(int pageOffSet, int pageSize);

	//得到所有记录条数
	long getCount();

	//删除商品和栏目的管理
	int delete(int id);

	//修改商品所属栏目
	
	//将商品所属栏目id查询出来
	List<Map<String,Object>> selectColumnId(int goodId);

	//删除该商品id管理的栏目记录
	int deleteRecord(int goodId);

	//批量删除
	int bulkdel(String str);

	//条件查询的内容
	List<Map<String, Object>> selectCheckColumnGoods(int pageOffSet,
			int pageSize, String selectName, String keyword);

	//条件查询记录数
	long getCheckCount(String selectName, String keyword);


}
