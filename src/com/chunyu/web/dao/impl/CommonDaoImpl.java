package com.chunyu.web.dao.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import com.chunyu.web.dao.CommonDao;
import com.sun.org.apache.bcel.internal.classfile.PMGClass;
import com.sun.org.apache.regexp.internal.recompile;


public class CommonDaoImpl implements CommonDao{
	private JdbcTemplate jdbcTemplate;
	private TransactionTemplate transactionTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	private static Connection con;

	/**
	 * 批量更新
	 * sql：支持单sql的批量更新，只有一条sql语句，也要封装好用字符串数组的形式传进来，要不然会产生编译时错误
	 */
	public void bulkupdate(final String[] sqls,final List<List<Object>> list) {
		jdbcTemplate.execute(new ConnectionCallback<T>() {
			public T doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				PreparedStatement pstmt=null;
				String sql=sqls[0];
				try {
				 pstmt=conn.prepareStatement(sql);
				 conn.setAutoCommit(false);//将Auto commit设置为FALSE，不让它自动提交
				for(int i=0;i<list.size();i++) {
					List<Object> innerList=list.get(i);
					for(int j=1;j<=innerList.size();j++) {
						pstmt.setObject(j, innerList.get(j-1));
					}
					pstmt.addBatch();//将每组参数添加到此PreparedStatement对象的批处理命令中
				}
				pstmt.executeBatch();
				conn.commit();//手动提交
				conn.setAutoCommit(true);//提交完成后恢复现场将Auto commit还原为true
				}catch(Exception e) {
					e.printStackTrace();
					if(!conn.isClosed()) {
						conn.rollback();//当异常发生时，进行回滚
						conn.setAutoCommit(true);
					}
				}finally {
					if(pstmt!=null) {
						pstmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}
				return null;
			}
		});
		
	}
		
	public List<List<String>> queryListData(String sql, Object[] o) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<Map<String, Object>> re = jdbcTemplate.queryForList(sql, o);
		for (Map<String, Object> m : re) {
			Set<String> ls = m.keySet();
			List<String> lsub = new ArrayList<String>();
			for (String ss : ls) {
				lsub.add(String.valueOf(m.get(ss)));
			}
			result.add(lsub);
		}
		return result;
	}

	/**
	 * 
	 * @Title: addOrUpdateForTransaction
	 * @Description: TODO sql_param and sql_user 添加事务管理
	 * @param sql
	 * @param sql1
	 * @param o
	 * @return
	 * @throws Exception
	 * @see com.wanmei.bidbq.web.repository.dao.CommonDao#addOrUpdateForTransaction(java.lang.String,
	 *      java.lang.String, java.lang.String[][])
	 */
	public int addOrUpdateForTransaction(String sql, String sql1, String sql2,
			List<List<Object>> userCondition, String[]... o) throws Exception {
		Integer state = -1;
		PlatformTransactionManager manager = transactionTemplate
				.getTransactionManager();
		TransactionStatus statu = manager
				.getTransaction(new DefaultTransactionDefinition());
		try {
			for (int i = 0; i < o[1].length; i++) {
				Object[] inserto = { o[1][i], o[2][i], i };
				jdbcTemplate.update(sql, inserto);
			}
			for (int i = 0; i < o[0].length; i++) {
				Object[] inserto = { o[0][i] };
				jdbcTemplate.update(sql1, inserto);
			}
			for (int i = 0; i < userCondition.size(); i++) {
				Object[] inserto = userCondition.get(i).toArray();
				jdbcTemplate.update(sql2, inserto);
			}
		} catch (Exception e) {
			statu.setRollbackOnly();
			throw e;
		}
		state = 2;
		manager.commit(statu);
		return state;
	}

	public int addOrUpdateForTransactionOneTable(String sql, String sqlselect,
			String[] o) throws Exception {
		Integer state = -1;
		PlatformTransactionManager manager = transactionTemplate
				.getTransactionManager();
		TransactionStatus statu = manager
				.getTransaction(new DefaultTransactionDefinition());
		try {
			for (int i = 0; i < o.length; i++) {

				Object[] inserto = { o[i] };
				if (jdbcTemplate.queryForObject(sqlselect, inserto,
						Integer.class) == 0)
					jdbcTemplate.update(sql, inserto);
			}
		} catch (Exception e) {
			statu.setRollbackOnly();
			throw e;
		}
		state = 2;
		manager.commit(statu);
		return state;
	}

	public int addOrUpdate(String sql, Object[] o) {
		return jdbcTemplate.update(sql, o);
	}

	public int queryForInt(String sql, Object[] o) {
		return jdbcTemplate.queryForObject(sql, o, Integer.class);
	}
	
	public Long queryForLong(String sql, Object[] o) {
		return jdbcTemplate.queryForObject(sql, o,Long.class);
	}

	public List queryList(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	public List queryList(String sql, Object[] o) {
		List list=jdbcTemplate.queryForList(sql, o);
		return list;
	}

	public SqlRowSet getRs(String sql) throws Exception {
		return jdbcTemplate.queryForRowSet(sql);
	}

	 
	public SqlRowSet getRs(String sql, Object[] o) throws Exception {
		return jdbcTemplate.queryForRowSet(sql, o);
	}

	/**
	 * 查询对象列表方法，切记返回结果的列名要与Bean的属性名完全一致！！！
	 */
	 
	public <T> List<T> queryObjList(String sql, Object[] args,
			final Class<T> clazz) {
		return jdbcTemplate.query(sql, args, new RowMapper<T>() {
			public T mapRow(ResultSet rs, int arg1) throws SQLException {
				T result = null;
				ResultSetMetaData rsmd = rs.getMetaData();
				List<String> columns = getColumnList(rsmd);
				try {
					result = clazz.newInstance();
					BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
					PropertyDescriptor[] pds = beanInfo
							.getPropertyDescriptors();
					for (PropertyDescriptor pd : pds) {
						Method setMethod = pd.getWriteMethod();
						if (setMethod != null) {
							String propertyName = pd.getName();
							if (columns.contains(propertyName.toLowerCase())) {
								BeanUtils.setProperty(result, propertyName,
										rs.getObject(propertyName));
							}
						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return result;
			}

		});
	}

	private List<String> getColumnList(ResultSetMetaData rsmd) {
		int columnCount = 0;
		List<String> columnList = null;
		try {
			columnCount = rsmd.getColumnCount();
			if (columnCount > 0) {
				columnList = new ArrayList<String>();
				for (int i = 1; i <= columnCount; i++) {
					String columnname = rsmd.getColumnName(i);
					columnList.add(columnname.toLowerCase());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnList;
	}

	 
	public <T> T queryObj(String sql, Object[] args, final Class<T> clazz) {
		return jdbcTemplate.query(sql, new ResultSetExtractor<T>() {
			public T extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				T result = null;
				if (rs.next()) {
					try {
						result = clazz.newInstance();
						BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
						PropertyDescriptor[] pds = beanInfo
								.getPropertyDescriptors();
						for (PropertyDescriptor pd : pds) {
							Method setMethod = pd.getWriteMethod();
							if (setMethod != null) {
								String propertyName = pd.getName();
								BeanUtils.setProperty(result, propertyName,
										rs.getObject(propertyName));
							}
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				return result;
			}

		});
	}

	
	public <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
		return jdbcTemplate.query(sql, args, rowMapper);
	}
//
//	public String returnJson(String sql, Object[] o) {
//		return jdbcTemplate.queryForObject(sql, String.class);
//	}
	
	
	//要求执行一次的sql要放在前面，后面是执行多次的sql,list对应的条件也是
	public void bulkupdate2(final String[] sqls,final List<List<Object>> list) {
		jdbcTemplate.execute(new ConnectionCallback<T>() {
			public T doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				PreparedStatement pstmt1=null;
				PreparedStatement pstmt2=null;
				String sql1=sqls[0];
				String sql2=sqls[1];
				try {
				 pstmt1=conn.prepareStatement(sql1);
				 pstmt2=conn.prepareStatement(sql2);
				conn.setAutoCommit(false);//将Auto commit设置为FALSE，不让它自动提交
				List<Object> innerList1=list.get(0);
				for(int k=0;k<innerList1.size();k++){
				  pstmt1.setObject(k+1, innerList1.get(k));
				}
				pstmt1.execute();
				for(int i=1;i<list.size();i++) {
					List<Object> innerList=list.get(i);
					for(int j=1;j<=innerList.size();j++) {
						pstmt2.setObject(j, innerList.get(j-1));
					}
					pstmt2.addBatch();//将每组参数添加到此PreparedStatement对象的批处理命令中
				}
				pstmt2.executeBatch();
				conn.commit();//手动提交
				conn.setAutoCommit(true);//提交完成后恢复现场将Auto commit还原为true
				}catch(Exception e) {
					e.printStackTrace();
					if(!conn.isClosed()) {
						conn.rollback();//当异常发生时，进行回滚
						conn.setAutoCommit(true);
					}
				}finally {
					if(pstmt1!=null){
						pstmt1.close();
					}
					if(pstmt2!=null) {
						pstmt2.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}
				return null;
			}
		});
	}

	public String queryForString(String sql, Object[] o) {
		jdbcTemplate.queryForObject(sql,o,String.class);
		return null;
	}		
		
	public void InsertAndQueryId(final String[] sqls,final int agencyId){
		 jdbcTemplate.execute(new ConnectionCallback<T>() {

			public T doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				T result=null;
				Statement st=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				try{
				conn.setAutoCommit(false);
				st=conn.createStatement();
				ps=conn.prepareStatement(sqls[1]);
				int row=st.executeUpdate(sqls[0],Statement.RETURN_GENERATED_KEYS);
				rs=st.getGeneratedKeys();
				if(rs.next()){
					 int goodId=rs.getInt(row);
					 ps.setObject(1,goodId);
					 ps.setObject(2,agencyId);
				}
				ps.execute();
				conn.commit();//手动提交
				conn.setAutoCommit(true);//提交完成后恢复现场将Auto commit还原为true
				}catch(Exception e){
					System.out.println("wrong");
					e.printStackTrace();
				}finally{
					if(conn!=null){
						conn.close();
					}
					if(rs!=null){
						rs.close();
					}
					if(st!=null){
						st.close();
					}
					if(ps!=null){
						ps.close();
					}
				}
				return null;
			}
		});
		}

	public void delete2(final String sql1, final Object[] o1, final String sql2, final Object[] o2) {
		jdbcTemplate.execute(new ConnectionCallback<T>() {
			public T doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				PreparedStatement pstmt1=null;
				PreparedStatement pstmt2=null;
				try {
				 pstmt1=conn.prepareStatement(sql1);
				 pstmt2=conn.prepareStatement(sql2);
				 conn.setAutoCommit(false);//将Auto commit设置为FALSE，不让它自动提交
				 for(int i=0;i<o1.length;i++){
					 pstmt1.setObject(i+1, o1[i]);
				}
				 for(int j=0;j<o2.length;j++){
					 pstmt2.setObject(j+1, o2[j]);
				 }
				 pstmt1.execute();
				 pstmt2.execute();
				conn.commit();//手动提交
				conn.setAutoCommit(true);//提交完成后恢复现场将Auto commit还原为true
				}catch(Exception e) {
					e.printStackTrace();
					if(!conn.isClosed()) {
						conn.rollback();//当异常发生时，进行回滚
						conn.setAutoCommit(true);
					}
				}finally {
					if(pstmt1!=null) {
						pstmt1.close();
					}
					if(pstmt2!=null) {
						pstmt2.close();
					}
					if(conn!=null) {
						conn.close();
					}
				}
				return null;
			}
		});
	}
	
}


	/*public <T> T InsertAndQueryId(final String[] sqls,final Object[] o,final Class<T> clazz) {
		
		jdbcTemplate.execute(new ConnectionCallback<T>() {
			public T doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				T result=null;
				PreparedStatement pstmt1=null;
				PreparedStatement pstmt2=null;
				ResultSet rs=null;
				String sql1=sqls[0];
				String sql2=sqls[1];
				try {
					result=clazz.newInstance();
				 pstmt1=conn.prepareStatement(sql1);
				 pstmt2=conn.prepareStatement(sql2);
				 conn.setAutoCommit(false);//将Auto commit设置为FALSE，不让它自动提交
					for(int j=0;j< o.length;j++) {
						pstmt1.setObject(j+1, o[j]);
					}
				pstmt1.execute();
				rs=pstmt2.executeQuery();
				if (rs.next()) {
					try {
						result = clazz.newInstance();
						BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
						PropertyDescriptor[] pds = beanInfo
								.getPropertyDescriptors();
						for (PropertyDescriptor pd : pds) {
							Method setMethod = pd.getWriteMethod();
							if (setMethod != null) {
								String propertyName = pd.getName();
								BeanUtils.setProperty(result, propertyName,
										rs.getObject(propertyName));
							}
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				conn.commit();//手动提交
				conn.setAutoCommit(true);//提交完成后恢复现场将Auto commit还原为true
				}catch(Exception e) {
					e.printStackTrace();
					if(!conn.isClosed()) {
						conn.rollback();//当异常发生时，进行回滚
						conn.setAutoCommit(true);
					}
				}finally {
					if(pstmt1!=null) {
						pstmt1.close();
					}
					if(pstmt2!=null){
						pstmt2.close();
					}
					if(conn!=null) {
						conn.close();
					}
					if(rs!=null){
						rs.close();
					}
				}
				return result;
			}
		});
		return null;
		
	}*/
