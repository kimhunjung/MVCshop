package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;
@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void addPurchase(Purchase purchase) throws Exception{
		sqlSession.insert("PurchaseMapper.addPurchase",purchase);
	}
	
	public Purchase getPurchase(int tranNo) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.getPurchase",tranNo);
	}
	
	public Purchase getPurchase2(int ProdNo) throws Exception{
		return sqlSession.selectOne("PurchaseMapper.getPurchase2",ProdNo);
	}
	
	public List<Purchase> getPurchaseList(Search search,String buyerId) throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("search", search);
		map.put("buyerId",buyerId);
		
		return sqlSession.selectList("PurchaseMapper.getPurchaseList",map);
	}
	
	
	public List<Purchase> getSaleList(Search search) throws Exception{
		
		
		return sqlSession.selectList("PurchaseMapper.getSaleList",search);
		
	}
	
	
	public void updatePurchase(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updatePurchase",purchase);
	}
	
	public void updatePurchase2(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updatePurchase2",purchase);
	}
	
	public void updateTranCode(Purchase purchase) throws Exception{
		sqlSession.update("PurchaseMapper.updateTranCode",purchase);
	}
	
	
	public int getTotalCount(Search search) throws Exception{
		
		return sqlSession.selectOne("PurchaseMapper.getTotalCount",search);
	}
	
	
	public int getTotalCount2(Search search,String buyerId) throws Exception{
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("search", search);
		map.put("buyerId",buyerId);
		
		return sqlSession.selectOne("PurchaseMapper.getTotalCount2",map);
	}
	
}
