package com.chunyu.web.service;

import java.util.List;

import com.chunyun.web.model.Classify;

public interface ClassifyService {

	//得到商品的分类
	public List<String> getGoodsClassify(int goodsId) throws Exception;
	
	//查询所有分类信息
	List<Classify> getAllClassify(int offSet,int pageSize);
	
	//删除分类
	int delete(int id);
	
	//查询总记录条数
    long getCount();

	//添加新的分类
	int add(String name,int parentId,int level,int onlyReservation);

	//获得当前分类的level
    int getLevel(int classifyId);

    //查找下一级分类
	List<Classify> checkNext(int id);

	//通过当前id得到parentId
	int getParentId(int id);

	//通过id查找这个分类
	List<Classify> selectById(int parentId);

	//得到该Id下一级id的数量
	int getNextCount(int id);

	//设为免费区
	void changeFree(int id);

	//查询所有一级分类的id和name
	List<Classify> getLevel1();

	//得到子级的分类
	List<Classify> getSubClassify(int classifyId);

	//查看当前分类是不是预定区（当前分类就是下一级分类的子分类）
	int checkParentClassify(int id);

}
