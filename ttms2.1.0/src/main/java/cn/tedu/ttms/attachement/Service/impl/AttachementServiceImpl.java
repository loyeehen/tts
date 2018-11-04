package cn.tedu.ttms.attachement.Service.impl;

import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.Service.AttachementService;
import cn.tedu.ttms.attachement.dao.AttachementDao;
import cn.tedu.ttms.attachement.entity.Attachement;
@Service
public class AttachementServiceImpl implements AttachementService{
@Resource
private AttachementDao attdao;
	
	public void saveObject(//athType鍚庨潰鍔犲叆鐨�
			String title, Integer belongId, 
			Integer athType, MultipartFile mFile,String serverPath) {
		//灏嗘枃浠朵笂浼犵殑淇℃伅灏佽鍒癕ultipartFile涓�
		//涓婁紶鏂囦欢锛屼笂浼犳垚鍔熷悗淇濆瓨鍒版暟鎹簱
		//1,涓婁紶鏂囦欢
		//鑾峰緱鏂囦欢鍚嶅瓧
		String oFileName=mFile.getOriginalFilename();
		System.out.println("oFileName");
		byte[] fileBytes;
		File dest;//鐩爣鏂囦欢锛堜笂浼犲悗瀛樺偍鐨勬枃浠� 鍒板摢锛�
		String fileDisgest;//鐢ㄤ簬灏佽鎽樿淇℃伅
		try{
			//姝ゅ鏄厛涓婁紶锛屽悗淇濆瓨銆傚簲璇ュ厛淇濆瓨鍦ㄤ笂浼狅紝濡傛灉淇濆瓨鎴愬姛锛屼絾涓婁紶澶辫触锛屽垯鐢ㄦ暟鎹彲寰椾簨鍔℃挙鍥�
			//1.2鑾峰緱鏂囦欢瀛楄妭
			fileBytes=mFile.getBytes();
			fileDisgest=DigestUtils.md5Hex(fileBytes);
			int count=attdao.findObjectByDisgest(fileDisgest);
			if(count>0)throw new RuntimeException("鏂囦欢宸茬粡瀛樺湪!");
			//1.3鎵ц涓婁紶鍔ㄤ綔
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			String dateStr=sdf.format(new Date());
			String newFileName
				=UUID.randomUUID().toString()+".";
//			String newFileName
//			=UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(oFileName);
			String realPath="d:/uploads/"+dateStr+File.separator+newFileName;
//			dest=new File("d:/uploads/"+oFileName);
			dest=new File(realPath);
			File parent=dest.getParentFile();
			if(!parent.exists()){
				parent.mkdirs();
			}
			
			mFile.transferTo(dest);
		}catch(IOException e){//濡傛灉鏂囦欢宸茬粡瀛樺湪锛屾姤寮傚父锛屽垯涓嶆姤upload error 
			e.printStackTrace();
			throw new RuntimeException("upload error!");
		}
		//2.淇濆瓨鏂囦欢淇℃伅鍒版暟鎹簱
		Attachement att=new Attachement();
		att.setTitle(title);//
		att.setFileName(oFileName);
		att.setFilePath(dest.getPath());//鏂囦欢瀛樺偍鐨勭湡瀹炶矾寰�
		att.setAthType(athType);//
		att.setBelongId(belongId);//
		att.setContentType(mFile.getContentType());
		att.setFileDisgest(fileDisgest);
		att.setCreatedUser("admin");
		att.setModifiedUser("admin");		
		attdao.insertObject(att);		
	}
	
	public List<Attachement> findObjects(){
		return attdao.findObjects();
	}
	
	
	//鏂囦欢涓嬭浇
	public File findObjectById(Integer id){		
		if(id==null)
			throw new RuntimeException("it can not get id!");
		//1锛屾牴鎹甶d鏌ユ壘璁板綍
		Attachement a=attdao.findObjectById(id);		
		if(a==null)
			throw new RuntimeException("鏁版嵁搴撲腑娌℃湁瀵瑰簲鐨勮褰�!");
		//2.鑾峰緱鏂囦欢鐨勭湡瀹炶矾寰勶紝鏋勫缓鏂囦欢瀵硅薄鍏宠仈鐪熷疄璺姴
		File file=new File(a.getFilePath());
		//妫�娴嬫枃浠舵槸鍚﹀瓨鍦紝瀛樺湪鍒欎笅杞�
		if(!file.exists())
			throw new RuntimeException("file is not exists!");				
		return file;
	}	
}
