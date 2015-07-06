package com.chunyu.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.GoodsService;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.GoodPrice;
import com.chunyun.web.model.Goods;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	
	@Resource(name="commonDao")
	private CommonDao commonDao; 

	public void setPriceState(int id,int flag) {
		String sql="update t_cy_good_price set isShow=? where id=?";
		Object[]o=new Object[]{flag,id};
		commonDao.addOrUpdate(sql, o);
	}
	
	public boolean getValidPrice(int goodsId) throws Exception{
		String sql="select id from t_cy_good_price where isShow=1 and goodId="+goodsId;
		SqlRowSet rs=commonDao.getRs(sql);
		return rs.next();
	}
	
	public boolean updateSubImage(int subImgId,String img0,String img1,String img2,String img3,String subImg0,
			String subImg1,String subImg2,String subImg3) {
		String sql="update t_goods_subImg set img0=?,img1=?,img2=?,img3=?,subImg0=?,subImg1=?,subImg2=?,subImg3=? where id=?";
		Object[]o=new Object[]{img0,img1,img2,img3,subImg0,subImg1,subImg2,subImg3,subImgId};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public List<List<Object>> getGoodsSubImgList(int goodsId) throws Exception {
		String sql="select id,img0,img1,img2,img3,subImg0,subImg1,subImg2,subImg3 from t_goods_subImg where goodsId="+goodsId;
		SqlRowSet rs=commonDao.getRs(sql);
		List<List<Object>> list=new ArrayList<List<Object>>();
		if(rs.next()) {
			List<Object> innerList=new ArrayList<Object> ();
			innerList.add(rs.getInt(1));
			innerList.add(rs.getString(2));
			innerList.add(rs.getString(3));
			innerList.add(rs.getString(4));
			innerList.add(rs.getString(5));
			innerList.add(rs.getString(6));
			innerList.add(rs.getString(7));
			innerList.add(rs.getString(8));
			innerList.add(rs.getString(9));
			list.add(innerList);
		}
		return list;
	}
	
	public int getLastGoodsId() throws Exception{
		String sql="select max(id) from t_cy_goods";
		SqlRowSet rs=commonDao.getRs(sql);
		int id=0;
		if(rs.next()) {
			id=rs.getInt(1);
		}
		return id;
	}
	public List<Goods> query(int pageOffSet,int pageSize) {
		String sql="select id,name,commentNum,hasBuyNum,isfree,uploadTime,isAgency,isCount,inArea,onlyReservation from t_cy_goods order by uploadTime desc limit ?,? ";
		Object[] o={pageOffSet,pageSize};
	     List<Goods> goods=commonDao.queryObjList(sql, o, Goods.class);
		return goods;
	}


	public boolean add(Goods goods) {
		boolean flag=false;
	    String sql="insert into t_cy_goods(name,introduction,uploadTime,image,inArea,isfree,isAgency,onlyReservation,isCount,hasBuyNum,commentNum) values(?,?,?,?,?,?,?,?,?,?,?)";
	    String uploadTime=TypeConversion.DateToString(new Date());
	    Object[] o=new Object[]{goods.getName(),goods.getIntroduction(),
	    uploadTime,goods.getImage(),goods.getInArea(),goods.getIsfree(),goods.getIsAgency(),goods.getOnlyReservation(),0,0,0};
	    int r=commonDao.addOrUpdate(sql, o);
	    if(r>0) {
	    	flag=true;
	    }
		return flag;
	}

	public int getRecordNum() {
		String sql="select count(*) from t_cy_goods";
		return commonDao.queryForInt(sql, null);
	}


	public List<Goods> selectAll(int id) {
		String sql="select id,name,introduction,image,inArea,isfree,isAgency,onlyReservation,hasBuyNum,commentNum from t_cy_goods where id="+id;
	    return commonDao.queryObjList(sql,null, Goods.class);
	}

	public Goods getGoodsById(int id) {
		String sql="select id,name,introduction,image,inArea,isfree,isAgency,onlyReservation,hasBuyNum from t_cy_goods where id="+id;
		List<Goods> list=commonDao.queryObjList(sql, null, Goods.class);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public boolean update(Goods goods) {
      String sql="update t_cy_goods set name=?,image=?,introduction=?,inArea=?,isfree=?,isAgency=?,hasBuyNum=?," +
    		"onlyReservation=?,uploadTime=? where id=?";
	  String newTime=TypeConversion.DateToString(new Date());
      Object[] o={goods.getName(),goods.getImage(),goods.getIntroduction(),goods.getInArea(),goods.getIsfree(),goods.getIsAgency(),
    		goods.getHasBuyNum(),goods.getOnlyReservation(),newTime,goods.getId()};
      int result= commonDao.addOrUpdate(sql, o);
      if(result>0) {
    	  return true;
      }
      return false;
	}


	public int addImage(String subImage,int id) {
		String sql="update t_cy_goods set subImage=? where id=?";
		Object[] o={subImage,id};
		return commonDao.addOrUpdate(sql, o);
	}
	
	public boolean addSubImage(String img0,String img1,String img2,String img3,String subImg0,String subImg1,String subImg2,
			   String subImg3,int id) {
		String sql="insert into t_goods_subImg(goodsId,img0,img1,img2,img3,subImg0,subImg1,subImg2,subImg3) " +
				"value(?,?,?,?,?,?,?,?,?)";
		Object[]o=new Object[]{id,img0,img1,img2,img3,subImg0,subImg1,subImg2,subImg3};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}


	public List<GoodPrice> selectAllPrice(int goodId) {
		String sql="select id,goodId,size,price,weight,isShow from t_cy_good_price where goodId=?";
		Object[] o={goodId};
		return commonDao.queryObjList(sql, o, GoodPrice.class);
	}


	public boolean setPrice(int goodId, String size, float price,float weight,boolean flag) {
		String str="0";
		if(!flag) {
			str="1";
		}
		String sql="insert into t_cy_good_price(goodId,size,price,weight,isShow) values(?,?,?,?,?)";
		Object[] o={goodId,size,price,weight,str};
		int result=commonDao.addOrUpdate(sql, o);
		if(result>0) {
			return true;
		}
		return false;
	}

//删除 1.删除商品，2.删除分类和商品，3.删除商品和栏目,4.删除评论
	public int delete(int id) {
		String sql0="delete from t_cy_goods where id=?";
		String sql1="delete from t_cy_classify_good where goodId=?";
		String sql2="delete from t_cy_column_good where goodId=?";
		String sql3="delete from t_cy_comments where goodId=?";
		Object[] o={id};
		int result0=commonDao.addOrUpdate(sql0, o);
		commonDao.addOrUpdate(sql1, o);
		commonDao.addOrUpdate(sql2, o);
		commonDao.addOrUpdate(sql3, o);
		return result0;
	}


	public boolean delSizeAttr(int id) {
		String sql="delete from t_cy_good_price where id="+id;
		int result=commonDao.addOrUpdate(sql,null);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	public void addClassify(int id, int classify1, int classify2, int classify3) {
		//判断分类是否有一个属于预定分类
	    String sql0="select onlyReservation from t_cy_classify where id=?";
	    Object[] obj={classify3};
	    int onlyReservation=commonDao.queryForInt(sql0, obj);
	    if(onlyReservation==1){
	    String sql1="update t_cy_goods set onlyReservation=1 where id=?";
	    Object[] obj1={id};
	    int updateReservation=commonDao.addOrUpdate(sql1, obj1);
	    //updateReservation用来判断是否更新预定区商品成功，如果没成功要求重新更新
	    }
		String sql="insert into t_cy_classify_good(classifyId,goodId) values(?,?)";
		String[] sqls={sql};
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> innerList1=new ArrayList<Object>();
		innerList1.add(classify1);
		innerList1.add(id);
		List<Object> innerList2=new ArrayList<Object>();
		innerList2.add(classify2);
		innerList2.add(id);
		List<Object> innerList3=new ArrayList<Object>();
		innerList3.add(classify3);
		innerList3.add(id);
		list.add(innerList1);
		list.add(innerList2);
		list.add(innerList3);
		commonDao.bulkupdate(sqls, list);
	}
	public void addColumn(int id,int[] columnIds) {
		int len=columnIds.length;
	    String sql="insert into t_cy_column_good(goodId,columnId) values(?,?)";
	    String[] sqls={sql};
	    List<List<Object>> list=new ArrayList<List<Object>>();
	    for(int i=0;i<len;i++){
	    List<Object> innerList=new ArrayList<Object>();
	    innerList.add(id);
	    innerList.add(columnIds[i]);
	    list.add(innerList);
	    }
	    commonDao.bulkupdate(sqls, list);
    }


	public int selectRecordinClassify(int id) {
		String sql="select count(*) from t_cy_classify_good where goodId=?";
		Object[] o={id};
		int result=commonDao.queryForInt(sql, o);
		return result;
	}


	public int selectRecordinColumn(int id) {
		String sql="select count(*) from t_cy_column_good where goodId=?";
		Object[] o={id};
		int result=commonDao.queryForInt(sql, o);
		return result;
	}


	public int addUeditorContent(int id, String content) {
		String sql="insert into t_cy_ueditor(goodId,content) values(?,?)";
		Object[] o={id,content};
		return commonDao.addOrUpdate(sql, o);
	}


	public List<Map<String,Object>> checkDetail(int goodId) {
		String sql="select content from t_cy_ueditor where goodId=?";
		Object[] o={goodId};
		return commonDao.queryList(sql, o);
	}


	public int getDetailCount(int id) {
		String sql="select count(*) from t_cy_ueditor where goodId=?";
		Object[] o={id};
		return commonDao.queryForInt(sql, o);
	}


	public int updateEditor(String content,int id) {
		String sql="update t_cy_ueditor set content=? where goodId=?";
		Object[] o={content,id};
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
			String sql="select count(*) from t_cy_goods where name like ?";
			Object[] o={"%"+keyword+"%"};
		    return commonDao.queryForLong(sql, o);
	}


	public List<Goods> queryCheck(int pageOffSet, int pageSize,
			String checkName, String keyword) {
		String sql="select id,name,commentNum,hasBuyNum,isfree,uploadTime,isAgency from t_cy_goods where name like ? limit ?,?";
		Object[] o={"%"+keyword+"%",pageOffSet,pageSize};
		return commonDao.queryObjList(sql, o, Goods.class);
	}
	
	public boolean delClassify(int id) {
		String sql="delete from t_cy_classify_good where goodId="+id;
		int result=commonDao.addOrUpdate(sql, null);
		if(result>0) {
			return true;
		}
		return false;
	}

}
