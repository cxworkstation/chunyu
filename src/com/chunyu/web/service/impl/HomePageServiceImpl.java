package com.chunyu.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.HomePageService;
import com.chunyun.web.model.BroadCast;
import com.chunyun.web.model.Module;

@Service("homePageService")
public class HomePageServiceImpl implements HomePageService{

	@Resource(name="commonDao")
	private CommonDao commonDao;
	
	/***********轮播图操作******************/
	public boolean addBroad(String chiefPic,String pic1,String pic2,String pic3,String pic4,int orderVal) {
		String sql="insert into t_homepage_broadcast(chiefPic,pic1,pic2,pic3,pic4,flag) " +
				"values(?,?,?,?,?,?)";
		Object[]o=new Object[]{chiefPic,pic1,pic2,pic3,pic4,orderVal};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}

	public boolean addOrUpdateBroadImg(Integer id,String path,Integer index) {
		String sql=null;
		Object[]o=null;
		if(id==null||id==0) {
		   sql="insert into t_homepage_broadcast(pic,orderId) values(?,?)";
		   o=new Object[]{path,index};
		}else {
			sql="update t_homepage_broadcast set pic=? where id=?";
			o=new Object[]{path,id};
		}
		
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public int getLastestBroadImgId() {
		String sql="select max(id) from t_homepage_broadcast";
		return commonDao.queryForInt(sql,null);
	}
	public List<BroadCast> getBroadList() {
		String sql="select id,pic,goodId from t_homepage_broadcast order by 'index'";
		List<BroadCast> list=commonDao.queryObjList(sql, null, BroadCast.class);
		return list;
	}

	public boolean delBroad(int id) {
		String sql="delete from t_homepage_broadcast where id=?";
		Object[] o=new Object[]{id};
		int result=commonDao.addOrUpdate(sql,o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public BroadCast getBroadById(int id) {
		String sql="select id,chiefPic,pic1,pic2,pic3,pic4,flag from t_homepage_broadcast where id="+id;
		BroadCast broad=commonDao.queryObj(sql, null, BroadCast.class);
		return broad;
	}
	
	public boolean updateBroad(BroadCast broad) {
		String sql="update t_homepage_broadcast set chiefPic=?,pic1=?,pic2=?,pic3=?,pic4=?,flag=? where id=?";
		return false;
	}

	public boolean addBroadToGoods(int id,int good,int goodsId) {
		String col="good"+good;
		String sql="update t_homepage_broadcast set "+col+"=? where id=?";
		Object[]o=new Object[]{goodsId,id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public int getGoodsId(int id,int good) throws Exception{
		String col="good"+good;
		String sql="select "+col+" from t_homepage_broadcast where id="+id;
		SqlRowSet rs=commonDao.getRs(sql);
		int goodsId=0;
		if(rs.next()) {
			goodsId=rs.getInt(1);
		}
		return goodsId;
	}
	
	/*************公告操作****************/
	public boolean addNotice(String title,int flag) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String dateStr=sdf.format(new Date());
		Date date=null;
		try {
			date=sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sql="insert into t_homepage_notice(title,flag,addtime) values(?,?,?)";
		Object[] o=new Object[]{title,flag,date};
		int result =commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public List<List<Object>>getNotices() throws Exception {
		String sql="select id,title,content,flag from t_homepage_notice";
		SqlRowSet rs=commonDao.getRs(sql);
		SqlRowSetMetaData meta=rs.getMetaData();
		int colCount=meta.getColumnCount();
		List<List<Object>> list=new ArrayList<List<Object>> ();
		while(rs.next()) {
			List<Object> innerList=new ArrayList<Object> ();
			for(int i=1;i<=colCount;i++) {
				innerList.add(rs.getObject(i));
			}
			list.add(innerList);
		}
		return list;
	}
	
	public boolean addContent(int id,String content) {
		String sql="update t_homepage_notice set content=? where id=?";
		Object[]o=new Object[]{content,id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public String getContentById(int noticeId) throws Exception{
		String sql="select content from t_homepage_notice where id="+noticeId;
		SqlRowSet rs=commonDao.getRs(sql);
		String content="";
		if(rs.next()) {
			content=rs.getString(1);
		}
		return content;
	}
	/************模块操作*****************/
	public boolean addModulePic(String pic1,String pic2,String pic3,String pic4,String pic5,String pic6,String module) {
		String sql="insert into t_homepage_module(pic1,pic2,pic3,pic4,pic5,pic6,module) values(?,?,?,?,?,?,?)";
		Object[] o=new Object[]{pic1,pic2,pic3,pic4,pic5,pic6,module};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateModulePic(Module m) {
		String sql="update t_homepage_module set pic1=?,pic2=?,pic3=?,pic4=? where id=?";
		Object[]o=new Object[]{m.getPic1(),m.getPic2(),m.getPic3(),m.getPic4(),m.getId()};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public Module getModule(String module) {
		String sql="select id,pic1,pic2,pic3,pic4,pic5,pic6,module from t_homepage_module where module='"+module+"'";
		Module m=commonDao.queryObj(sql, null, Module.class);
		return m;
	}
	
	public boolean addModuleToGoods(int id,int col,int goodsId) {
		String colName="goodsId"+col;
		String sql="update t_homepage_module set "+colName+"=? where id=?";
		Object[]o=new Object[]{goodsId,id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public int getModuleGoodsId(int id,int col) throws Exception{
		String sql="select goodsId"+col+" from t_homepage_module where id="+id;
		SqlRowSet rs=commonDao.getRs(sql);
		int goodId=0;
		if(rs.next()) {
			goodId=rs.getInt(1);
		}
		return goodId;
	}
	
	public boolean delModel(int id) {
		String sql="delete from t_homepage_module where id="+id;
		int result=commonDao.addOrUpdate(sql,null);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	/*********类别操作**************/
	public boolean addClassifyPic(String pic,String classify,String postion,String mode) {
		String sql="insert into t_homepage_classify(img,classify,position,mode) values(?,?,?,?)";
		Object[] o=new Object[]{pic,classify,postion,mode};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public List<List<Object>> getClassify(String classify) throws Exception{
		String sql="select id,img,position,mode from t_homepage_classify where classify=?";
		Object[] o=new Object[]{classify};
		SqlRowSet rs=commonDao.getRs(sql, o);
		List<List<Object>> list=new ArrayList<List<Object>> ();
		while(rs.next()) {
			List<Object> innerList=new ArrayList<Object>();
			innerList.add(rs.getInt(1));
			innerList.add(rs.getString(2));
			innerList.add(rs.getString(3));
			innerList.add(rs.getString(4));
			list.add(innerList);
		}
		return list;
	}
	
	public boolean updateClassifyPic(String pic,int id) {
		String sql="update t_homepage_classify set img=? where id=?";
		Object[]o=new Object[]{pic,id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public boolean delClassifyPic(int id) {
		String sql="delete from t_homepage_classify where id=?";
		Object[]o=new Object[]{id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public boolean delClassifyPic(String position) {
		String sql="delete from t_homepage_classify where position=?";
		Object[]o=new Object[]{position};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public int getClassifyGoodsId(String pic,String classify) throws Exception{
		String sql="select goodsId from t_homepage_classify where position='"+pic+"' and classify='"+classify+"'";
		SqlRowSet rs=commonDao.getRs(sql);
		int goodsId=0;
		if(rs.next()) {
          	goodsId=rs.getInt(1);		
		}
		return goodsId;
	}
	
	public boolean addClassifyToGoods(String classify,String pic,int goodsId) {
		String sql="update t_homepage_classify set goodsId=? where position=? and classify=?";
		Object[]o=new Object[]{goodsId,pic,classify};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public boolean delDesc(List<List<Object>> descList) {
		String[] sqls=new String[]{"delete from t_homepage_classify where position=?"};
		try {
		 commonDao.bulkupdate(sqls, descList);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean delClassifyGoods(String classify) {
		String sql="update t_homepage_classify set goodsId=0 where classify=?";
		Object[]o=new Object[]{classify};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public boolean addHealthPic(String pic) {
		String sql="insert into t_homepage_health(img) values(?)";
		Object[]o=new Object[]{pic};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public List<List<Object>> getHealthPic() throws Exception{
		String sql="select id,img from t_homepage_health";
		SqlRowSet rs=commonDao.getRs(sql);
		List<List<Object>> list=new ArrayList<List<Object>> ();
		if(rs.next()) {
			List<Object> innerList=new ArrayList<Object>();
			innerList.add(rs.getObject(1));
			innerList.add(rs.getObject(2));
			list.add(innerList);
		}
		return list;
	}
	
	public boolean updateHealthPic(int id,String pic) {
		String sql="update t_homepage_health set img=? where id=?";
		Object[]o=new Object[]{pic,id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public boolean addHealthContent(int id,String content) {
		String sql="update t_homepage_health set content=? where id=?";
		Object[]o=new Object[]{content,id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public String getHealthContent(int id) throws Exception {
		String sql="select content from t_homepage_health where id="+id;
		SqlRowSet rs=commonDao.getRs(sql);
		String content=null;
		if(rs.next()) {
			content=rs.getString(1);
		}
		return content;
	}
	
	
	public boolean addEspecialImg(String img,int position) {
		String sql="insert into t_homepage_especial(img,position) values(?,?)";
		Object[]o=new Object[]{img,position};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public List<List<Object>>getEspecial() throws Exception {
		String sql="select id,img,goodsId from t_homepage_especial order by position";
		SqlRowSet rs=commonDao.getRs(sql);
		List<List<Object>> list=new ArrayList<List<Object>>();
		while(rs.next()) {
			List<Object> innerList=new ArrayList<Object>();
			innerList.add(rs.getObject(1));
			innerList.add(rs.getObject(2));
			innerList.add(rs.getObject(3));
			list.add(innerList);
		}
		return list;
	}
	
	public boolean updateEspecialImg(String img,int id) {
		String sql="update t_homepage_especial set img=? where id=?";
		Object[]o=new Object[]{img,id};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public boolean addGoodsToEsPecial(int especialId,int id) {
		String sql="update t_homepage_especial set goodsId=? where id=?";
		Object[] o=new Object[]{id,especialId};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public int getEspecialGoodsId(int id) throws Exception {
		String sql="select goodsId from t_homepage_especial where id="+id;
		Object[]o=new Object[]{id};
		SqlRowSet rs=commonDao.getRs(sql);
		int goodsId=0;
		if(rs.next()) {
			goodsId=rs.getInt(1);
		}
		return goodsId;
	}
}
