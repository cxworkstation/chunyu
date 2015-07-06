package com.chunyu.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Resource(name="commonDao")
	private CommonDao commonDao;

	public List<List<Object>> getCateList() throws Exception{
		String sql="select id,name from t_cy_classify where level=1";
		SqlRowSet rs=commonDao.getRs(sql);
		List<List<Object>> list=new ArrayList<List<Object>> ();
		while(rs.next()) {
			List<Object> inList=new ArrayList<Object> ();
			int id=rs.getInt(1);
			String name=rs.getString(2);
			inList.add(id);
			inList.add(name);
			list.add(inList);
		}
		return list;
	}
	
	public boolean addCateImgs(Integer cateId,String pic1,String pic2,int ordernum) {
		String sql="insert into t_category_broadcast(category,img1,img2,ordernum) values(?,?,?,?)";
		Object[]o=new Object[]{cateId,pic1,pic2,ordernum};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public List<List<Object>> getCateImgList(Integer cateId) throws Exception{
		String sql="select id,img1,img2,goodsId from t_category_broadcast where category="+cateId+" order by ordernum";
		SqlRowSet rs=commonDao.getRs(sql);
		List<List<Object>> list=new ArrayList<List<Object>>();
		while(rs.next()) {
			List<Object> innerList=new ArrayList<Object>();
			innerList.add(rs.getObject(1));
			innerList.add(rs.getObject(2));
			innerList.add(rs.getObject(3));
			innerList.add(rs.getObject(4));
			list.add(innerList);
		}
		return list;
	}
	
	public boolean addGoodsToCate(int cateId,int goodsId) {
		String sql="update t_category_broadcast set goodsId=? where id=?";
		Object[] o=new Object[]{goodsId,cateId};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
}
