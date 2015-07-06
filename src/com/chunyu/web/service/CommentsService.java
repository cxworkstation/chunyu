package com.chunyu.web.service;

import java.util.List;
import java.util.Map;

public interface CommentsService {

	//查询所有评论数
	long getCounts();

	//查询评论以及评论商品的部分信息
	List<Map<String, Object>> queryComments(int pageOffSet, int pageSize);

	//删除评论
	int delete(int id);

	//条件查询
	List<Map<String, Object>> queryCheckComments(int pageOffSet, int pageSize,
			String selectName, String keyword);

	//条件查询的记录数
	long getCheckCounts(String selectName, String keyword);

}
