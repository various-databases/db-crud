package com.mybatis.mapper;

import com.mybatis.pojo.User;

public interface UserOperation {
	//@Insert("insert into user(`first`,`last`,`age`) values (#{first},#{last},#{age})")
	public boolean addUser(User user);

	//@Select("select * from user where id = #{id}")
	public User selectUserById(int id);

	//@Update("update user set first = #{first}," + " last = #{last},age = #{age} " + " where id = #{id}")
	public boolean updateUser(User user);

	//@Delete("delete from user where id = #{id}")
	public boolean delUser(int id);
}
