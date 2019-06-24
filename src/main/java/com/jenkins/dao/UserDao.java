package com.jenkins.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jenkins.vo.User;
@Mapper
@Repository
public interface UserDao {
	@Select("select * from sys_user  where username=#{username}")
	User findByUsername(String username);

	@Select("select * from sys_user  where username=#{username} and password=#{password}")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Select("select * from sys_user  where id=#{id}")
	User findById(@Param("id") int id);

	@Select("update sys_user  set loginIp =#{loginIp} where username =#{username}")
	int updateLoginIpById( String loginIp,  String username);


	@Select("update sys_user  set password =#{password} where username =#{username}")
	int updatePasswordById(String password, String username);
	@Insert("insert into sys_user(username) values(#{username}) where id=#{id} ")
	void save(User user);

}
