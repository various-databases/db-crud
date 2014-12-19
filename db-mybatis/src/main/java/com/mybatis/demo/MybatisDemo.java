package com.mybatis.demo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.common.MybatisConfig;

public class MybatisDemo {

	public static void main(String[] args) {
		SqlSessionFactory sessionFactory = MybatisConfig.getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();
	}

}
