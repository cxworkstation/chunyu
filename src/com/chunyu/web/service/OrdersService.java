package com.chunyu.web.service;

import java.util.List;
import java.util.Map;

import com.chunyun.web.model.Orders;

public interface OrdersService {

	//删除订单
	int delete(int id);
	
	//查询所有的订单
	List<Orders> selectAllOrders(int offSet,int pageSize);

	//查询所有记录条数
	long getCount();

	//查询处理商品的json串用于展示
	List<Map<String,String>> selectOrderGoods(int id);

	//订单发货操作（即：改变订单状态）
	int deliver(int id);

	//查询订单状态
	int checkState(int orderId);

	//条件查询的订单
	List<Orders> selectCheckOrders(int pageOffSet, int pageSize,
			String selectName, String keyword);

	//查询记录数
	long getCheckCount(String selectName, String keyword);

	//批量删除
	int bulkdel(List<List<Object>> list);

}
