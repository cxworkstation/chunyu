package com.chunyu.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chunyu.web.dao.CommonDao;
import com.chunyu.web.service.CommentsService;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService{
	
	@Resource(name="commonDao")
	private CommonDao commonDao;

	public long getCounts() {
		String sql="select count(*) from t_cy_comments";
		return commonDao.queryForLong(sql, null);
	}

	public List<Map<String, Object>> queryComments(int pageOffSet, int pageSize) {
		String sql="select d.id,d.name,d.content,b.username,d.commentTime  from (select c.id,a.name,c.content,c.userId,c.commentTime from t_cy_comments as c left join t_cy_goods as a on a.id=c.goodId) as d left join t_cy_users as b on b.id=d.userId limit ?,?";
		Object[] o={pageOffSet,pageSize};
		return commonDao.queryList(sql, o);
	}

	public int delete(int id) {
		String sql="delete from t_cy_comments where id=?";
		Object[] o={id};
		return commonDao.addOrUpdate(sql, o);
	}

	public long getCheckCounts(String selectName, String keyword) {
		String sql="select count(*) from (select c.id,a.name,c.content,c.userId,c.commentTime from t_cy_comments as c , (select id,name from t_cy_goods where name like ?) as a where a.id=c.goodId ) as d left join t_cy_users as b on b.id=d.userId";
		if("2".equals(selectName)){
		 sql="select count(*) from (select c.id,a.name,c.content,c.userId,c.commentTime from t_cy_comments as c left join t_cy_goods as a on a.id=c.goodId) as d right join (select id,username from t_cy_users where username like ?)as b on b.id=d.userId ";
		}
		Object[] o={"%"+keyword+"%"};
		return commonDao.queryForLong(sql, o);
	}

	public List<Map<String, Object>> queryCheckComments(int pageOffSet,
			int pageSize, String selectName, String keyword) {
		String sql="select d.id,d.name,d.content,b.username,d.commentTime from (select c.id,a.name,c.content,c.userId,c.commentTime from t_cy_comments as c , (select id,name from t_cy_goods where name like ?) as a where a.id=c.goodId ) as d left join t_cy_users as b on b.id=d.userId limit ?,?";
		if("2".equals(selectName)){
		 sql="select d.id,d.name,d.content,b.username,d.commentTime from (select c.id,a.name,c.content,c.userId,c.commentTime from t_cy_comments as c left join t_cy_goods as a on a.id=c.goodId) as d right join (select id,username from t_cy_users where username like ?)as b on b.id=d.userId limit ?,?";
		}
		Object[] o={"%"+keyword+"%",pageOffSet,pageSize};
		return commonDao.queryList(sql, o);
	}

}
