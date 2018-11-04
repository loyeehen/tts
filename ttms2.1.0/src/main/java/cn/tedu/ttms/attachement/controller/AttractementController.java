package cn.tedu.ttms.attachement.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.tedu.ttms.attachement.Service.AttachementService;
import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.common.web.JsonResult;

@Controller
@RequestMapping("/att")
public class AttractementController {
	@Resource
	private AttachementService attachementService;
	
	@RequestMapping("/uploadUI")
	public String uploadUI(){
//		return "atta/upload";//文件上传页面	 报错，地址不对
		return "attachement/upload";//文件上传页面	
	}
	
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			String title, 
			Integer belongId, 
			Integer athType, 
			MultipartFile mFile,
			HttpServletRequest request){
		String serverPath//找到在服务器上的路径
			=request.getServletContext().getRealPath("/");
		System.out.println("serverPath"+serverPath);
		//<input type="file" name="mFile"..
		//<input type="file" name="upFile"..
		/**
		 * mFile和input中file的name名字相同
		 * 若不同，则@RequestParam("upFile")MultipartFile mFile 
		 * */				
		attachementService.saveObject(title, belongId, athType, mFile,serverPath);		
		return new JsonResult();
		
	}
	@RequestMapping("/dofindObjects")
	@ResponseBody
	public JsonResult dofindObjects(){
		List<Attachement> list
			=attachementService.findObjects();
		return new JsonResult(list);
	}
	
	//文件下载
	@RequestMapping("/dodownObject")
	@ResponseBody
	public byte[] dodownObject(Integer id,HttpServletResponse res) throws IOException{
		System.out.println("id:"+id);
		File file=attachementService.findObjectById(id);
		System.out.println("file"+file);
		//设置响应消息头（下载时的固定写法）
		res.setContentType("application/octet-stream");
		res.setHeader("Content-disposition","attachement;filename="+file.getName());	
		//根据文件真实路径构建一个path对象
		Path path=Paths.get(file.getPath());		
		//将文件的字节返回（springmvc会自动将字节写入）
		return Files.readAllBytes(path);
	}

}
