package com.chunyu.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.HotGoodService;
import com.chunyun.web.model.Goods;

@Service("hotGoodService")
public class HotGoodServiceImpl implements HotGoodService {

	@Resource(name="commonDao")
	private CommonDao commonDao;

	public List<Goods> selectRangeGood(int data) {
		String sql="select id,name,hasBuyNum,commentNum,isfree,isAgency,introduction from t_cy_goods order by hasBuyNum desc limit 0,?";
		Object[] o={data};
		return commonDao.queryObjList(sql, o, Goods.class);
	}


}
