package com.chunyu.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.UsersService;
import com.chunyun.web.model.Users;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Resource(name="commonDao")
	private CommonDao commonDao;
	
	public List<Users> getAllUsers(int offSet,int pageSize){
		String sql="select id,username,telephone,email,isBlackUser,registTime from t_cy_users limit ?,?";
		Object[] o={offSet,pageSize};
		return commonDao.queryObjList(sql, o, Users.class);
	}

	//删除用户但是不删除用户的评论或者订单
	public int delete(int id) {
		String sql="delete from t_cy_users where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public long getCount() {
		String sql="select count(*) from t_cy_users";
		return commonDao.queryForLong(sql, null);
	}

	public int toBlackUser(int id) {
		String sql="update t_cy_users set isBlackUser=1 where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public long getCheckCount(String typeName, String keyword) {
		String sql="select count(*) from t_cy_users where username like ?";
		if("2".equals(typeName)){
			sql="select count(*) from t_cy_users where telephone like ?";
		}
		Object[] o={"%"+keyword+"%"};
		return commonDao.queryForLong(sql, o);
	}

	public List<Users> getCheckUsers(int pageOffSet, int pageSize,
			String typeName, String keyword) {
		String sql="select id,username,telephone,email,isBlackUser,registTime from t_cy_users where username like ? limit ?,?";
		if("2".equals(typeName)){
			sql="select id,username,telephone,email,isBlackUser,registTime from t_cy_users where telephone like ? limit ?,?";
		}
		Object[] o={"%"+keyword+"%",pageOffSet,pageSize};
		return commonDao.queryObjList(sql, o, Users.class);
	}

	public int bulkdel(List<List<Object>> list) {
		String sql="delete from t_cy_users where id=?";
		String[] sqls={sql};
		commonDao.bulkupdate(sqls, list);
		return 1;
	}
}
