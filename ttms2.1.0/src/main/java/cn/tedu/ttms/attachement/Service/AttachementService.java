package cn.tedu.ttms.attachement.Service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.entity.Attachement;

public interface AttachementService {
	/**
	 * 
	 * @param title 附件标题
	 * @param belongId 附件归属id(例如具体的某个产品的id)
	 * @param athType 附属归类类型（产品附件）
	 * @param mFile 上传的文件对象
	 */
	void saveObject(
			String title,
			Integer belongId,
			Integer athType,
			MultipartFile mFile,
			String serverPath);
	//上传的文件对象，文件封装在里面
	List<Attachement> findObjects();
	
	File findObjectById(Integer id);

}
