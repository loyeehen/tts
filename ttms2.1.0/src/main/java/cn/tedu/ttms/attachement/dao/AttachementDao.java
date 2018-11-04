package cn.tedu.ttms.attachement.dao;

import java.util.List;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.common.dao.BaseDao;

public interface AttachementDao extends BaseDao<Attachement>{
	//void insertObject(Attachement att);
	List<Attachement> findObjects();
	
	//根据摘要信息判定是否已经存在该文件
	int findObjectByDisgest(String fileDisgest);
	
	Attachement findObjectById(Integer id);
	
}
