package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController{
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping("/addPurchase.do")
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase , Model model , HttpSession session) throws Exception{
		
		System.out.println("/addPurchase.do");
		User user = (User)session.getAttribute("user");
		Product product = (Product)session.getAttribute("product");
		
		product.setProTranCode("1");
		purchase.setTranCode("1");
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchaseService.addPurchase(purchase);
		
		model.addAttribute("purchaseVO", purchase);
		
		return "forward:/product/addComplete.jsp";
	}
	
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase(@RequestParam("tranNo") String tranNo , Model model ) throws Exception{
		
		System.out.println("/getPurchase.do");
		
		Purchase purchase=new Purchase();
		
		purchase=purchaseService.getPurchase(Integer.parseInt(tranNo));
		
		model.addAttribute("purchase", purchase);
		

		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView(@RequestParam("tranNo") String tranNo , Model model ) throws Exception{
		
		System.out.println("/updatePurchaseView.do");
		
		Purchase purchase = purchaseService.getPurchase(Integer.parseInt(tranNo));
		
		model.addAttribute("purchase", purchase);

		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping("/updatePurchase2.do")
	public String updatePurchase2(@ModelAttribute("purchase") Purchase purchase , Model model ) throws Exception{
		
		System.out.println("/updatePurchase2.do");
		
		purchaseService.updatePurchase2(purchase);
		
		return "redirect:/getPurchase.do?tranNo="+purchase.getTranNo();
	}
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase(@ModelAttribute("search") Search search , Model model , HttpServletRequest request ,HttpSession session) throws Exception{
		
		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		String userId=((User)session.getAttribute("user")).getUserId();
		Map<String, Object> map = purchaseService.getPurchaseList(search, userId);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
	
	@RequestMapping("/listSale.do")
	public String listSale(@ModelAttribute("search") Search search , Model model , HttpServletRequest request ) throws Exception{
		
		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = purchaseService.getSaleList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listSale.jsp";
	}
	
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode(@RequestParam("tranNo") String tranNO , @RequestParam("tranCode") String tranCode ) throws Exception{
		
		System.out.println("/updateTranCode");
		
		Purchase purchase = new Purchase();
		
		purchase.setTranNo(Integer.parseInt(tranNO));
		purchase.setTranCode(tranCode);
		
		purchaseService.updateTranCode(purchase);
		
		return "forward:/listPurchase.do";
	}
	
	@RequestMapping("/updateTranCodeByProd.do")
	public String updateTranCodeByProd(@RequestParam("pranNo") String pranNO , @RequestParam("tranCode") String tranCode ) throws Exception{
		
		System.out.println("/updateTranCodeByProd");
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		product.setProdNo(Integer.parseInt(pranNO));
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		
		purchaseService.updatePurchase(purchase);
		
		return "forward:/listProduct.do?menu=manage";
	}
	
	
}

