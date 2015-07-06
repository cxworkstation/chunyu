package com.chunyu.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.ColumnsService;

@Service("columnsService")
public class ColumnsServiceImpl implements ColumnsService {

	@Resource(name="commonDao")
	private CommonDao commonDao;

	public long getCount() {
		String sql="select count(*) from t_cy_column_good";
		return commonDao.queryForLong(sql, null);
	}

	public List<Map<String,Object>> selectAllColumnGoods(int pageOffSet, int pageSize) {
		String sql="select a.id,a.columnId,b.name,b.introduction,a.goodId from t_cy_column_good as a  left join t_cy_goods as b on a.goodId=b.id limit ?,?";
		Object[] o={pageOffSet,pageSize};
		return commonDao.queryList(sql, o);
	}

	public int delete(int id) {
		String sql="delete from t_cy_column_good where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public List<Map<String,Object>> selectColumnId(int goodId) {
		String sql="select columnId from t_cy_column_good where goodId=?";
		Object[] o={goodId};
		return  commonDao.queryList(sql,o);
	}

	public int deleteRecord(int goodId) {
		String sql="delete from t_cy_column_good where goodId=?";
		Object[] o={goodId};
		return commonDao.addOrUpdate(sql, o);
	}

	public int bulkdel(String str) {
		String sql="delete from t_cy_column_good where id=?";
		String[] sqls={sql};
		String[] ids=str.split("_");
		List<List<Object>> list=new ArrayList<List<Object>>();
		for(int i=0;i<ids.length;i++){
			List<Object> innerList=new ArrayList<Object>();
			int id=Integer.parseInt(ids[i]);
			innerList.add(id);
			list.add(innerList);
		}
		commonDao.bulkupdate(sqls, list);
		return 1;
	}

	public long getCheckCount(String selectName, String keyword) {
		String sql="select count(*) from t_cy_column_good as a , (select id,name,introduction from t_cy_goods where name like ?)as b where a.goodId =b.id";
		Object[] o={"%"+keyword+"%"};
		return commonDao.queryForLong(sql, o);
	}

	public List<Map<String, Object>> selectCheckColumnGoods(int pageOffSet,
			int pageSize, String selectName, String keyword) {
		String sql="select a.id,a.columnId,b.name,b.introduction,a.goodId from t_cy_column_good as a ,(select id,name,introduction from t_cy_goods where name like ?)as b where a.goodId =b.id limit ?,?";
		Object[] o={"%"+keyword+"%",pageOffSet,pageSize};
		return commonDao.queryList(sql, o);
	}

	
	
}
