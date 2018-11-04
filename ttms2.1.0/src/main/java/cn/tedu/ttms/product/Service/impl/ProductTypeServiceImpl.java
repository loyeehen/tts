package cn.tedu.ttms.product.Service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.product.Service.ProductTypeService;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{	
	@Resource 
	private ProductTypeDao productTypeDao;
	
	public List<Map<String, Object>> findObjects(){
		//因为没有传参数，不需要做分析，直接返回
		return productTypeDao.findObjects();
	}

	public List<Map<String, Object>> findTreeNodes() {
		List<Map<String,Object>> map
			=productTypeDao.findTreeNodes();
		System.out.println("map:"+map);
		return map;
	}
	
	public void saveObject(ProductType type) {
		int rows=productTypeDao.insertObject(type);
		if(rows==-1)
			throw new SaveRuntimeException("save ER");		
	}

	//根据id 查找分类信息
	public Map<String, Object> findObjectById(Integer id) {
		if(id==null)
		throw new RuntimeException("can not get id");
		Map<String,Object> map=productTypeDao.findObjectById(id);
		return map;
	}
		
	//更新产品分类信息
	public void updateObject(ProductType type) {
		int rows=productTypeDao.updateObject(type);
		if(rows==-1)
			throw new UpdateRuntimeException("更新失败");		
	}

	public void deleteObjectById(Integer id) {
		//1,根据id获得子元素的个数
		int count=productTypeDao.hasChilds(id);
		if(count>0)
			throw new UpdateRuntimeException("请先删除子元素");
		//2，执行删除动作
		int rows=productTypeDao.deleteObjectById(id);
		if(rows==-1)
			throw new UpdateRuntimeException("删除失败");			
	}
	
}
