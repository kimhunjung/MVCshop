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
import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception{
		
		Product product = new Product();
		product.setFileName("aaa.jpg");
		product.setManuDate("123123");
		product.setPrice(100);
		product.setProdDetail("testdetail");
		product.setProdName("testName");
		
		productService.addProduct(product);
				
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct(10000);
		Assert.assertEquals("vaio vgn FS70B", product.getProdName());
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product = productService.getProduct(10000);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("vaio vgn FS70B",product.getProdName());
		Assert.assertEquals("소니 바이오 노트북 신동품",product.getProdDetail());
		
		//product.setProdDetail("수정 test");
		//product.setManuDate("1111");
		//product.setPrice(10000);
		//product.setFileName("abcd.jpg");
		product.setProdName("testName");
		productService.updateProduct(product);
		
		product = productService.getProduct(10000);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("소니 바이오 노트북 신동품", product.getProdDetail());
		
	}
	
	//@Test
	public void testGetProductListAll() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);

		
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3,list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
	}
	
	//@Test
	public void testGetProductListSearch() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
	 	search.setSearchKeyword("자전거");	
		
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1,list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
	}
	
	
}
