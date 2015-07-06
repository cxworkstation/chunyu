package com.chunyu.web.service;

import java.util.List;

import com.chunyun.web.model.Goods;

public interface HotGoodService {

	//查询前range范围内，按销量排名的商品
	List<Goods> selectRangeGood(int data);

}
