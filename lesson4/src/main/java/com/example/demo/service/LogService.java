package com.example.demo.service;

import com.example.demo.dao.UserJpa;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2021-01-10 11:53
 * @Description:
 */
@Service
public class LogService {
    @Resource
    private UserJpa userJpa;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLog(){
        User user = new User();
        user.setDptId(1L);
        user.setName("log");
        user.setAge(18L);
        user.setEmail("log@log.com");
        user.setHeadImg("log");

        this.userJpa.save(user);
        System.out.println("log");
    }
}
