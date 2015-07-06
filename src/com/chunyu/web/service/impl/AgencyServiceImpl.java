package com.chunyu.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.AgencyService;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Agency;
import com.chunyun.web.model.Goods;

@Service("agencyService")
public class AgencyServiceImpl implements AgencyService {
	@Resource(name="commonDao")
	private CommonDao commonDao;

	public long getCount() {
		String sql="select count(*) from t_cy_agency";
		return commonDao.queryForLong(sql, null);
	}

	public List<Agency> queryAllAngency(int pageOffSet, int pageSize) {
		String sql="select id,name,introduction from t_cy_agency limit ?,?";
		Object[] o={pageOffSet,pageSize};
		return commonDao.queryObjList(sql, o, Agency.class);
	}

	public int addAgency(Agency agency) {
		String sql="insert into t_cy_agency(name,introduction,image,manageTime) values(?,?,?,?)";
		Object[] o={agency.getName(),agency.getIntroduction(),agency.getImage(),agency.getManageTime()};
		return commonDao.addOrUpdate(sql, o);
	}

	//经营户里面商品可能没删除，要冗余删除缓存处理(逻辑忽略了有商品时候经营户内商品删除失败)
	public int delete(int id) {
		//删除经营户也要删除经营户下的所有商品
		int result=0;
		String sql0="select goodId from t_cy_agency_good where agencyId=?";
		String sql1="delete from t_cy_agency where id=?";
		String sql2="delete from t_cy_goods where id=?";
		String sql3="delete from t_cy_agency_good where agencyId=?";
		 Object[] o={id};
		List<Map<String,Object>> listMap=commonDao.queryList(sql0, o);
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> innerList0=new ArrayList<Object>();
		innerList0.add(id);
		list.add(innerList0);
		if(listMap.size()>0){
			for(int i=0;i<listMap.size();i++){
				List<Object> innerList=new ArrayList<Object>();
				Integer goodId=(Integer) listMap.get(i).get("goodId");
				innerList.add(goodId);
				list.add(innerList);
			}
		String[] sqls={sql1,sql2};
	    commonDao.bulkupdate2(sqls, list);
	     result=commonDao.addOrUpdate(sql3, o);
	     System.out.println(result);
		}else{
			 result=commonDao.addOrUpdate(sql1, o);
			return result;
		}
	    return result;
	}

	public void addGoods(Goods goods, int agencyId) {
		String uploadTime=TypeConversion.DateToString(new Date());
		String sql1="insert into t_cy_goods(name,introduction,uploadTime,image,inArea,isfree,isAgency,onlyReservation,isCount,hasBuyNum,commentNum) values('"+goods.getName()+"','"+goods.getIntroduction()+"','"+uploadTime+"','"+goods.getImage()+"',"+goods.getInArea()+","+goods.getIsfree()+","+goods.getIsAgency()+",0,0,0,0)";
		String sql2="insert into t_cy_agency_good(goodId,agencyId) values(?,?)";
		String[] sqls={sql1,sql2};
		commonDao.InsertAndQueryId(sqls,agencyId);
		
	}

	public List<Map<String, Object>> selectAgencyGood(int agencyId) {
		String sql="select b.id,a.id goodsId,a.name,a.introduction,a.uploadTime from (select id,goodId from t_cy_agency_good where agencyId=?) as b left join t_cy_goods as a on a.id=b.goodId";
		Object[] o={agencyId};
		return commonDao.queryList(sql,o);
	}

	public List<Map<String,Object>> getImgAddress(int id) {
		String sql="select image from t_cy_goods where id=?";
		Object[] o={id};
		return commonDao.queryList(sql, o);
	}

	public int deleteGood(int id,int goodsId) {
		String sql1="delete from t_cy_goods where id=?";
		Object[] o={goodsId};
		String sql2="delete from t_cy_agency_good where id=?";
		Object[] o1={id};
		commonDao.delete2(sql1,o,sql2,o1);
		return 0;
	}

	public long getCheckCount(String selectName, String keyword) {
		String sql="select count(*) from t_cy_agency where name like ?";
		if("2".equals(selectName)){
			sql="select count(*) from t_cy_agency_good where goodId in (select id from t_cy_goods where name like ?)";
		}
		Object[] o={"%"+keyword+"%"};
		return commonDao.queryForLong(sql, o);
	}

	public List<Agency> queryCheckAngency(int pageOffSet, int pageSize,
			String selectName, String keyword) {
		String sql="select id,name,introduction from t_cy_agency where name like ? limit ?,?";
		if("2".equals(selectName)){
			sql="select id,name,introduction from t_cy_agency where id = (select agencyId from t_cy_agency_good where goodId in (select id from t_cy_goods where name like ?)) limit ?,?";
		}
		Object[] o={"%"+keyword+"%",pageOffSet,pageSize};
		return commonDao.queryObjList(sql, o, Agency.class);
	}

	
}
