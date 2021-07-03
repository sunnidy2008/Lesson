package com.example.demo.service;

import com.example.demo.dao.UserJpa;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Auther: DELL
 * @Date: 2021-01-10 11:49
 * @Description:
 */
@Service
public class UserService {
    @Resource
    private UserJpa userJpa;
    @Resource
    private LogService logService;

    @Transactional(rollbackFor = Exception.class)
    public void saveBiz() throws Exception {
        System.out.println("save2");
        User user = new User();
        user.setDptId(1L);
        user.setName("biz");
        user.setAge(18L);
        user.setEmail("biz@biz.com");
        user.setHeadImg("biz");

        this.userJpa.save(user);

        //模拟保存日志
        this.logService.saveLog();
        //模拟发生了异常
        throw new Exception("test1");
    }
}
