package com.model2.mvc.service.user.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;


//==> 회원관리 DAO CRUD 구현
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public UserDaoImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	public void addUser(User user) throws Exception {
	
		sqlSession.insert("UserMapper.addUser", user);
		
	}

	public User getUser(String userId) throws Exception {
		//User userTemp = new User();
		//userTemp=sqlSession.selectOne("UserMapper.getUser", userId);
		//System.out.println(userTemp);
		//sqlSession.commit();
		//sqlSession.close();
		//return userTemp;
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	public void updateUser(User user) throws Exception {
		sqlSession.update("UserMapper.updateUser", user);
		//sqlSession.commit();
		//sqlSession.close();
	}

	public List<User> getUserList(Search search) throws Exception {
		//List<User> userList = sqlSession.selectList("UserMapper.getUserList", search);
		//sqlSession.close();
		//return userList;
		return sqlSession.selectList("UserMapper.getUserList", search);
	}

	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		//int total=sqlSession.selectOne("UserMapper.getTotalCount", search);
		//sqlSession.close();
		//return total;
		return sqlSession.selectOne("UserMapper.getTotalCount", search);
	}
}