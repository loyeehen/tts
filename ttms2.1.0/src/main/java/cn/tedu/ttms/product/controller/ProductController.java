package cn.tedu.ttms.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
	/**产品列表页面*/
	@RequestMapping("/ListUI")
	public String ListUI(){
		return "product/product_list";
	}
}
