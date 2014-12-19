package com.mybatis.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.common.MybatisConfig;
import com.mybatis.mapper.UserDao;

public class MybatisDemo {

	public static void main(String[] args) {
		SqlSessionFactory sessionFactory = MybatisConfig.getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();
		UserDao userDao = session.getMapper(UserDao.class);
	}

}
