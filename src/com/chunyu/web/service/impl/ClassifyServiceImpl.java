package com.chunyu.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.dao.impl.CommonDaoImpl;
import com.chunyu.web.service.ClassifyService;
import com.chunyun.web.model.Classify;

@Service("classifyService")
public class ClassifyServiceImpl implements ClassifyService {

	@Resource(name="commonDao")
	private CommonDao commonDao;

	public List<String> getGoodsClassify(int goodsId) throws Exception{
	   String sql="select id,name from t_cy_classify where id in(select classifyId from t_cy_classify_good " +
	   		"where goodId="+goodsId+") order by id asc";
	   
	   SqlRowSet rs=commonDao.getRs(sql);
	   List<String> list=new ArrayList<String> ();
	   while(rs.next()) {
		   list.add(rs.getString(2));
	   }
	   return list;
	}
	
	public List<Classify> getAllClassify(int offSet,int pageSize) {
		String sql="select id,name,level,isCount,onlyReservation from t_cy_classify where parentId=0 limit ?,?";
		Object[] o={offSet,pageSize};
		return commonDao.queryObjList(sql, o, Classify.class);
	}
	
	public long getCount(){
		String sql="select count(*) from t_cy_classify where parentId=0";
		return commonDao.queryForLong(sql, null);
	}

	public int delete(int id) {
        String sql="delete from t_cy_classify where id=?";
        Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public int add(String name,int parentId,int level,int onlyReservation) {
		String sql="insert into t_cy_classify(name,parentId,level,isCount,onlyReservation) values(?,?,?,?,?)";
		Object[] o={name,parentId,level,0,onlyReservation};
		return commonDao.addOrUpdate(sql, o);
	}

	public int getLevel(int id) {
		String sql="select level from t_cy_classify where id=?";
		Object[] o={id};
		return commonDao.queryForInt(sql, o);
	}

	public List<Classify> checkNext(int id) {
		String sql="select id,name,level,isCount,onlyReservation from t_cy_classify where parentId=?";
		Object[] o={id};
		return commonDao.queryObjList(sql, o, Classify.class);
	}

	public int getParentId(int id) {
		String sql="select parentId from t_cy_classify where id=?";
		Object[] o={id};
		return commonDao.queryForInt(sql, o);
	}

	public List<Classify> selectById(int parentId) {
		String sql="select id,name,level,isCount from t_cy_classify where id=?";
		Object[] o={parentId};
		return commonDao.queryObjList(sql, o, Classify.class);
	}

	public int getNextCount(int id) {
		String sql="select count(*) from t_cy_classify where parentId=?";
		Object[] o={id};
		return commonDao.queryForInt(sql, o);
	}

	public void changeFree(int id) {
		String sql1="select goodId from t_cy_classify_good where classifyId=?";
		Object[] o={id};
		List<Map<String,Object>> list=commonDao.queryList(sql1, o);
        String sql2="update t_cy_classify set isCount=1 where id=?";
        String sql3="update t_cy_goods set isCount=1 where id=?";
        String[] sqls={sql2,sql3};
        List<List<Object>> lists=new ArrayList<List<Object>>();
        List<Object> innerList1=new ArrayList<Object>();
        innerList1.add(id);
        lists.add(innerList1);
        if(list.size()>0){
	        for(int i=0;i<list.size();i++){
	        	List<Object> innerList=new ArrayList<Object>();
	            innerList.add(list.get(i).get("goodId"));
	            lists.add(innerList);
	        }
        }
       commonDao.bulkupdate2(sqls, lists);
	}
	

	public List<Classify> getLevel1() {
		String sql="select id,name from t_cy_classify where level=1";
		return commonDao.queryObjList(sql, null, Classify.class);
	}

	public List<Classify> getSubClassify(int classifyId) {
		String sql="select id,name from t_cy_classify where parentId=?";
		Object[] o={classifyId};
		return commonDao.queryObjList(sql, o, Classify.class);
	}

	public int checkParentClassify(int parentId) {
		String sql0="select onlyReservation from t_cy_classify where id=?";
		Object[] obj={parentId};
		return commonDao.queryForInt(sql0, obj);
	}
}	
