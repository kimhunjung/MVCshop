package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit'] ?:3 }")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize'] ?:2 }")
	int pageSize;
	
		
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product") Product product, Model model) throws Exception {
		
		System.out.println("/addProduct.do");
		
		if(product.getManuDate()!=null) {
			product.setManuDate(product.getManuDate().replaceAll("-",""));
		}
		
		productService.addProduct(product);
		
		
		model.addAttribute("vo",product);
		
		return "forward:/product/addProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prod_no") String prod_No , Model model , HttpServletRequest request , HttpServletResponse response , HttpSession session) throws Exception{
		
		history(request,response,prod_No);
		
		System.out.println("/getProduct.do");
		
		Product product = productService.getProduct(Integer.parseInt(prod_No));
		
		session.setAttribute("product",product);
		
		model.addAttribute("vo",product);
		
		return "forward:/product/getProduct.jsp";
	}
	
	@RequestMapping("listProduct.do")
	public String listProduct(@ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listProduct");
		
		if(search.getCurrentPage() == 0 ) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String,Object> map = productService.getProductList(search);
		
		Page resultPage = new Page(search.getCurrentPage(),((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage",resultPage);
		model.addAttribute("search",search);
		
		return "forward:/product/listProduct.jsp?menu="+request.getParameter("menu");
	}
	
	
	@RequestMapping("updateProduct.do")
	public String updateProduct(@ModelAttribute("product") Product product , Model model) throws Exception{
		
		System.out.println("product머가들어갔을까요"+product);
		System.out.println("/updateProduct.do");
		
		if(product.getManuDate()!=null) {
			product.setManuDate(product.getManuDate().replaceAll("-",""));
		}
		
		productService.updateProduct(product);
		
		model.addAttribute("productVO", product);
		
		return "forward:/product/updateProductComplete.jsp";
	}
	
	@RequestMapping("updateProductView.do")
	public String updateProductView(@RequestParam("PROD_NO") String prod_No , Model model ) throws Exception{
				
		System.out.println("/updateProductView.do");
		
		Product product = productService.getProduct(Integer.parseInt(prod_No));
		
		model.addAttribute("productVO", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	
	@RequestMapping("addPurchaseView.do")
	public String addPurchaseView(@RequestParam("PROD_NO") String prod_No , Model model , HttpSession session) throws Exception{
		
		System.out.println("/addPurchaseView.do");
		
		Product product = productService.getProduct(Integer.parseInt(prod_No));
		
		model.addAttribute("productVO", product);
		
		//세션 추가 안해도 EL 로 접근가능?
		
		return "forward:/purchase/addPurchaseViews.jsp"; 
	}
	
	
	@RequestMapping("updatePurchase.do")
	public String updatePurchase(@ModelAttribute("product") Product product) throws Exception{
		
		System.out.println("updatePurchase.do");
		
		productService.updateProduct(product);
		
		String prod_Name = product.getProdName();
		
		return "redirect:/getProduct.do?="+prod_Name;
	}
	
	
	private void history(HttpServletRequest request , HttpServletResponse response,String prod_No) {
		
	Cookie[] cookies = request.getCookies();
			
			for(int i=0;i<cookies.length;i++) {
				if(cookies.length==2) {
					Cookie cookie=cookies[1];
					System.out.println("쿠키"+cookie.getValue());
					if(cookie.getName().contentEquals("history") && cookies.length==2) {
						cookie=cookies[1];
						cookie = new Cookie("history",cookie.getValue()+","+prod_No);
						cookie.setMaxAge(-1);
						response.addCookie(cookie);
					}else {
						cookie=cookies[0];
						cookie = new Cookie("history",prod_No);
					}
				}else if(cookies.length==1){
					Cookie cookie=cookies[0];
					cookie = new Cookie("history",prod_No);
					cookie.setMaxAge(-1);
					response.addCookie(cookie);
				}
			}
		}
	
}
