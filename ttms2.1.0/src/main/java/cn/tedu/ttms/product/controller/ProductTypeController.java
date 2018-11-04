package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.Service.ProductTypeService;
import cn.tedu.ttms.product.entity.ProductType;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {
	@Resource
	private ProductTypeService productTypeService;
	
	@RequestMapping("/ListUI")
	public String ListUI(){
		return "product/type_list";		
	}
	
	@RequestMapping("/editUI")
	public String editUI(){
		return "product/type_edit";
	}
	
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		List<Map<String,Object>> list
			=productTypeService.findObjects();
		System.out.println(list);
		System.out.println("doFindObjects");
		return new JsonResult(list);
	}
	
	@RequestMapping("/doFindTreeNodes")
	@ResponseBody
	public JsonResult doFindTreeNodes(){
		List<Map<String,Object>> list
			=productTypeService.findTreeNodes();
//		System.out.println(list);
		
		return new JsonResult(list);
		
	}
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType type){
		System.out.println("type="+type);
		 /**
		 * 登录时获得用户信息
		 *HttpSession session
		 *User user =session.getAttribute("user")
		 * type.setCreateUser(user.getUserNmae())
		 * */
		productTypeService.saveObject(type);	
		return new JsonResult();
		
	}
	
//	@RequestMapping("/doFindObjectById.do")
//	@ResponseBody
//	public JsonResult doFindObjectById(Integer id){
//		System.out.println("id="+id);
//		Map<String,Object> map=productTypeService.findObjectById(id);
//		System.out.println("map+"+map);
//		return new JsonResult(map);	
//	}
	@RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(Integer id){
    	Map<String,Object> map=
    	productTypeService.findObjectById(id);
    	return new JsonResult(map);
	}
	
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(ProductType type){
		productTypeService.updateObject(type);
		return new JsonResult();	
	}
	
	@RequestMapping("/doDeleteObjectById")
	@ResponseBody
	public JsonResult doDeleteObjectById(Integer id){
		System.out.println("deId"+id);
		productTypeService.deleteObjectById(id);
		return new JsonResult();
	}		
}
