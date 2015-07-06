package com.chunyu.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.GoodClassifyService;

@Service("goodClassifyService")
public class GoodClassifyServiceImpl implements GoodClassifyService {
	
	@Resource(name="commonDao")
	private CommonDao commonDao;

	public long getCount() {
		String sql="select count(*) from (select c.goodId,b.name classify,b.level from t_cy_classify_good as c  left join  t_cy_classify as b on b.id=c.classifyId) as d left join t_cy_goods as a on a.id=d.goodId where a.isAgency=0";
		int c= commonDao.queryForInt(sql, null);
		c=c/3;
		return c;
	}

	public List<Map<String,Object>> selectAllGoods(int pageOffSet, int pageSize) {
		String sql="select a.id goodId,a.name,a.introduction,d.classify,d.level from (select c.goodId,b.name classify,b.level from  t_cy_classify_good as c  left join  t_cy_classify as b on b.id=c.classifyId) as d left join t_cy_goods as a on a.id=d.goodId where a.isAgency=0 limit ?,?";
		Object[] o={pageOffSet,pageSize*3};
		return commonDao.queryList(sql, o);
	}

	public List<Map<String,Object>> getGoodIds(int pageOffSet, int pageSize) {
		String sql="select distinct goodId from t_cy_classify_good limit ?,?";
		Object[] o={pageOffSet,pageSize};
		return commonDao.queryList(sql, o);
	}

	public int resubmit(int id) {
		String sql="delete from t_cy_classify_good where goodId=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}



}
