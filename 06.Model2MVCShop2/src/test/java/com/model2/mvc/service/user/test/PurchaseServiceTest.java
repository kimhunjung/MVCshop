package com.model2.mvc.service.user.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

import junit.framework.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

//service.addPurchase(purchaseVO);
//Map<String,Object> map=service.getPurchaseList(search, userVO.getUserId());
//service.updateTranCode(purchaseVO);
//service.updatePurcahse(purchaseVO);
/*
    private User buyer;
	private String divyAddr;
	private String divyDate;
	private String divyRequest;
	private Date orderDate;
	private String paymentOption;
	private Product purchaseProd;
	private String receiverName;
	private String receiverPhone;
	private String tranCode;
	private int tranNo;	
 */
	//@Test
	public void testAddPurchase() throws Exception{
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user= new User();
		user.setUserId("user01");
		product.setProdNo(10000);
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setDivyAddr("test");
		purchase.setDivyRequest("test");
		purchase.setTranCode("1");
		purchase.setDivyRequest("requestTest");
		purchase.setPaymentOption("csh");
		purchaseService.addPurchase(purchase);
		
	}
	
	//@Test
	public void testGetPurchaseList() throws Exception{
		Search search = new Search();
		String buyerId = "user01";
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		Map<String,Object> map = purchaseService.getPurchaseList(search, buyerId);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception{
		//3로 변환,purchaseProd.prodNo
		Purchase purchase = new Purchase();
		Product product = new Product();
		product.setProdNo(10004);
		purchase.setPurchaseProd(product);
		purchase.setTranCode("3");
		purchaseService.updatePurchase(purchase);
		Assert.assertEquals("3", purchase.getTranCode());
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		//2로변환,tranNo
		Purchase purchase = new Purchase();
		purchase.setTranNo(10010);	//구매시 설정
		purchase.setTranCode("2");
		purchaseService.updateTranCode(purchase);
		Assert.assertEquals("2", purchase.getTranCode());
	}
	
}