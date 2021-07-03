package com.example.demo.dao;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: DELL
 * @Date: 2021-01-09 10:26
 * @Description:
 */
@Repository//表示这个是一个操作数据库的Repository类
public interface UserJpa extends JpaRepository<User,Long> {

    @Modifying//告诉jpa这个是update或delete方法，会对数据库中的数据产生变更
    @Query(value = "delete from user where name=:name",nativeQuery = true)
    public int deleteByName(@Param("name") String name);

    @Query(value = "select * from user where age>?1",nativeQuery = true)
    public List<User> findByAge(Integer Age);

}
