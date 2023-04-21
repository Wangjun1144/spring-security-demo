package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UsersMapper extends BaseMapper<Users>{
    @Select("select * from users")
    List<Users> findall();

    @Select("SELECT * FROM users WHERE id = #{id}")
    Users getUserById(@Param("id") Long id);

    @Select("SELECT * FROM users WHERE name = #{name}")
    List<Users> getUserList(@Param("name") String name);

    @Insert("INSERT INTO users(name, age) VALUES (#{name},#{age})")
    void addUsers(Users users);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void deleteUserById(@Param("id") Long id);

    @Update("UPDATE users SET name=#{name}, age=#{age} WHERE id=#{id}")
    void updateUser(Users users);
}
