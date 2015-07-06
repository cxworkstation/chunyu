package com.chunyu.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.OrdersService;
import com.chunyu.web.utils.TypeConversion;
import com.chunyun.web.model.Orders;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
	@Resource(name="commonDao")
	private CommonDao commonDao;
	
	public int delete(int id) {
		//这个id是自增id，不是订单号
		String sql="delete from t_cy_orders where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}
	
	//用户id提供查看该用户订单的操作，黑名单用户可以被筛选出来
	public List<Orders> selectAllOrders(int offSet,int pageSize){
		String sql="select id,orderId,address,telephone,payMethod,message,totalMoney,fee,state,userId,isBulk,orderTime from t_cy_orders limit ?,?";
		Object[] o={offSet,pageSize};
		return commonDao.queryObjList(sql, o, Orders.class);
		
	}

	public long getCount() {
		String sql="select count(*) from t_cy_orders";
		return commonDao.queryForLong(sql, null);
	}

	//数据库里面goodsJson的名字不可变！且必须不为空
	public List<Map<String,String>> selectOrderGoods(int id) {
		String goodJson="";
		String sql="select goodsJson from t_cy_orders where id=?";
		Object[] o={id};
		List<Map<String,Object>> list=commonDao.queryList(sql, o);
		if(list.size()>0){
			 Map<String,Object> map=list.get(0);
			 goodJson=map.get("goodsJson").toString();
		}
		return TypeConversion.JsonToList(goodJson);
	}

	public int deliver(int id) {
		String sql="update t_cy_orders set state=1 where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public int checkState(int orderId) {
		String sql="select state from t_cy_orders where id=?";
		Object[] o={orderId};
		return commonDao.queryForInt(sql, o);
	}

	public long getCheckCount(String selectName, String keyword) {
		String sql="select count(*) from t_cy_orders where orderId like ?";
		if("2".equals(selectName)){
		 sql="select count(*) from t_cy_orders where telephone like ?";	
		}else if("3".equals(selectName)){
		sql="select count(*) from t_cy_orders where address like ?";		
		}
		Object[] o={"%"+keyword+"%"};
		return commonDao.queryForLong(sql, o);
	}

	public List<Orders> selectCheckOrders(int pageOffSet, int pageSize,
			String selectName, String keyword) {
		String sql="select id,orderId,address,telephone,payMethod,message,totalMoney,fee,state,userId,isBulk,orderTime from t_cy_orders where orderId like ? limit ?,?";
		if("2".equals(selectName)){
		 sql="select id,orderId,address,telephone,payMethod,message,totalMoney,fee,state,userId,isBulk,orderTime from t_cy_orders where telephone like ? limit ?,?";	
		}else if("3".equals(selectName)){
		sql="select id,orderId,address,telephone,payMethod,message,totalMoney,fee,state,userId,isBulk,orderTime from t_cy_orders where address like ? limit ?,?";		
		}
		Object[] o={"%"+keyword+"%",pageOffSet,pageSize};
		return commonDao.queryObjList(sql, o, Orders.class);
	}

	public int bulkdel(List<List<Object>> list) {
		String sql="delete from t_cy_orders where id=?";
		String[] sqls={sql};
	    commonDao.bulkupdate(sqls, list);
	    return 1;
	}

}
