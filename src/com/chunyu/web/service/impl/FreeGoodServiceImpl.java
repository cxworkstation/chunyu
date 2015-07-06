package com.chunyu.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.FreeGoodService;
import com.chunyun.web.model.Goods;

@Service("freeGoodService")
public class FreeGoodServiceImpl implements FreeGoodService {
	
	@Resource(name="commonDao")
	private CommonDao commonDao;

	public long getCount() {
		String sql="select count(*) from t_cy_goods where isfree=1";
		return commonDao.queryForLong(sql, null);
	}

	public List<Goods> selectFreeGoods(int pageOffSet, int pageSize) {
		String sql="select id,name,commentNum,hasBuyNum,uploadTime from t_cy_goods where isfree=1 limit ?,?";
		Object[] o={pageOffSet,pageSize};
		return commonDao.queryObjList(sql, o, Goods.class);
	}

	public int delete(int id) {
		String sql="delete from t_cy_goods where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public int update(int id) {
		String sql="update t_cy_goods set isfree=0 where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public int bulkdel(String[] ids) {
		String sql="delete from t_cy_goods where id=?";
		String[] sqls={sql};
		List<List<Object>> list=new ArrayList<List<Object>>();
		for(int i=0;i<ids.length;i++){
			List<Object> innerlist=new ArrayList<Object>();
			int id=Integer.parseInt(ids[i]);
			innerlist.add(id);
			list.add(innerlist);
		}
		commonDao.bulkupdate(sqls, list);
		return 1;
	}

	public long getRecordNumCheck(String checkName, String keyword) {
		String sql="select count(*) from t_cy_goods where isfree=1 and name like ?";
		Object[] o={"%"+keyword+"%"};
	    return commonDao.queryForLong(sql, o);
	}

	public List<Goods> queryCheck(int pageOffSet, int pageSize,
			String checkName, String keyword) {
		String sql="select id,name,commentNum,hasBuyNum,uploadTime from t_cy_goods where isfree=1 and name like ? limit ?,?";
		Object[] o={"%"+keyword+"%",pageOffSet,pageSize};
		return commonDao.queryObjList(sql, o, Goods.class);
	}
}
