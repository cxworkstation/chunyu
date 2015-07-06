package com.chunyu.web.dao;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface CommonDao {
	//批量更新
	public void bulkupdate(String[]sqls,List<List<Object>> list);
	/**
	 * 
	 * @Title: queryList
	 * @Description: TODO 返回指定sql语句结果
	 * @param sql
	 *            查询语句
	 * @return
	 * @throws
	 */
	public List queryList(String sql);

	public List<List<String>> queryListData(String sql, Object[] o);

	/**
	 * 
	 * @Title: queryList
	 * @Description: TODO 返回指定sql语句结果
	 * @param sql
	 * @param o
	 *            参数集
	 * @return
	 * @throws
	 */
	public List queryList(String sql, Object[] o);

	public <T> List<T> queryObjList(String sql, Object[] args, Class<T> clazz);

	/**
	 * 
	 * @Title: queryForInt
	 * @Description: TODO 返回条数信息
	 * @param sql
	 * @param o
	 * @return
	 * @throws
	 */
	public int queryForInt(String sql, Object[] o);

	/**
	 * 
	 * @Title: addOrUpdate
	 * @Description: TODO 添加、修改、删除
	 * @param sql
	 * @param o
	 * @return
	 * @throws
	 */
	public int addOrUpdate(String sql, Object[] o);

	public SqlRowSet getRs(String sql) throws Exception;

	public int addOrUpdateForTransaction(String sql, String sql1, String sql2,
			List<List<Object>> userCondition, String[]... o) throws Exception;

	public int addOrUpdateForTransactionOneTable(String sql, String sqlselect,
			String[] o) throws Exception;

	public <T> T queryObj(String sql, Object[] args, Class<T> clazz);

	public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper);

	SqlRowSet getRs(String sql, Object[] o) throws Exception;
	
	//queryForXXX这类的都是返回单行单列一个数据
	//public String returnJson(String sql, Object[] o);
	
	public void bulkupdate2(final String[] sqls,final List<List<Object>> list);
	
	public Long queryForLong(String sql, Object[] o);
	
	
	//插入并返回刚刚插入的自增id字段值
	public void InsertAndQueryId(final String[] sqls,int agencyId);
	
	public void delete2(final String sql1,final Object[] o1,final String sql2,final Object[] o2);
}
