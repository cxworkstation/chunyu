package com.chunyu.web.service;

import java.util.List;

import com.chunyun.web.model.Users;

public interface UsersService {
	
	//查询所有用户
    List<Users> getAllUsers(int offSet,int pageSize);
	
    //删除用户
	int delete(int id);

	//查询技术条数
	long getCount();

	//设置成为黑名单用户
	int toBlackUser(int id);

	//查询条件下的用户list
	List<Users> getCheckUsers(int pageOffSet, int pageSize, String typeName,
			String keyword);

	//查询条件下的记录数
	long getCheckCount(String typeName, String keyword);

	//批量删除
	int bulkdel(List<List<Object>> list);
}
