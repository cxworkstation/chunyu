package com.chunyu.web.service;

import java.util.List;
import java.util.Map;

import com.chunyun.web.model.GoodPrice;
import com.chunyun.web.model.Goods;

public interface GoodsService {
	//改变标价状态
	void setPriceState(int id,int flag);
	//查询是否存在标准价格规则
	boolean getValidPrice(int GoodsId) throws Exception;
	
	//删除该商品分类
	boolean delClassify(int id);
	//查询商品的附属图片
	List<List<Object>> getGoodsSubImgList(int goodsId) throws Exception;
	//更新商品附属图
	boolean updateSubImage(int subImgId,String img0,String img1,String img2,String img3,String subImg0,
			String subImg1,String subImg2,String subImg3);
	
	//得到最后一个插入的商品Id
	int getLastGoodsId() throws Exception;
	
	
	//添加商品
   boolean add(Goods goods);
   
    //查询所有商品
   List<Goods> query(int pageOffSet,int pageSize);
   
    //查询记录总数
   int getRecordNum();
   
   //根据id查询商品
   List<Goods> selectAll(int id);
   Goods getGoodsById(int id);
   
   //修改商品
   boolean update(Goods goods);
   
   //添加多张图片
   int addImage(String subImage,int id);
   boolean addSubImage(String img0,String img1,String img2,String img3,String subImg0,String subImg1,String subImg2,
		   String subImg3,int id);

   //查询所有的价格设置
   List<GoodPrice> selectAllPrice(int id);

   //设置价值
   boolean setPrice(int id, String size, float price,float weight,boolean flag);

   //删除商品
   int delete(int id);
   
   //删除商品规格
   boolean delSizeAttr(int id);

   //为商品添加分类
   void addClassify(int id, int classify1, int classify2, int classify3);
	
   //查询所属分类是否已经添加记录
   int selectRecordinClassify(int id);
	
   //查询所属栏目是否已经添加记录
   int selectRecordinColumn(int id);

   //为商品添加栏目
   void addColumn(int id,int[] columnIds);

  //添加编辑器内容 
  int addUeditorContent(int id, String content);

  //查看编辑器内容
  List<Map<String,Object>> checkDetail(int id);

  //查询编辑器是不是已经每个商品一个了
  int getDetailCount(int id);

  //修改编辑器内容
  int updateEditor(String content,int id);

  //批量删除
  int bulkdel(String[] ids);

  //条件查询
  List<Goods> queryCheck(int pageOffSet, int pageSize, String checkName,
		String keyword);

  //条件查询获得记录
  long getRecordNumCheck(String checkName, String keyword);


}
