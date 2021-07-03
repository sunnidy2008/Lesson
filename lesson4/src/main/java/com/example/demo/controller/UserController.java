package com.example.demo.controller;

import com.example.demo.dao.UserJpa;
import com.example.demo.entity.User;
import com.example.demo.service.LogService;
import com.example.demo.service.UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: DELL
 * @Date: 2021-01-08 15:33
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserJpa userJpa;

    @Resource
    private UserService userService;

    @Resource
    private LogService logService;

    @GetMapping("findAll")//查找所有数据
    public List<User> findAll(){
        return this.userJpa.findAll();
    }

    @GetMapping("get")//按主键查找数据
    public User get(@RequestParam("id")Long id){
        return this.userJpa.findById(id).get();
    }

    @GetMapping("save")//保存数据
    public void save(@RequestBody User user){
        userJpa.save(user);
    }

    @GetMapping("delete")//按主键删除数据
    public void delete(@RequestParam("id")Long id){
        userJpa.deleteById(id);
    }

    @GetMapping("update")//更新数据
    public void update(@RequestParam("id")Long id,@RequestParam("name")String name){
        User user = this.userJpa.findById(id).get();//先更加id将数据load出来
        user.setName(name);//变更数据
        this.userJpa.save(user);//保存数据
    }

    @Transactional
    @GetMapping("deleteByUserName")//按用户名删除数据
    public void deleteByUserName(@RequestParam("name")String name){
        this.userJpa.deleteByName(name);
    }

    @GetMapping("findByAge")//按年龄查找数据
    public List<User> findByAge(@RequestParam("age")Integer age){
        return this.userJpa.findByAge(age);
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("save1")
    public String save1() throws Exception {
        System.out.println("save12");
        User user = new User();
        user.setDptId(1L);
        user.setName("a");
        user.setAge(18L);
        user.setEmail("a@a.com");
        user.setHeadImg("headImg1");

        this.userJpa.save(user);
        System.out.println(1/0);
        //模拟发生了异常
        throw new Exception("test");
//        return "ok";
    }




    @GetMapping("save2")
    public String save2() throws Exception {
        //模拟业务操作
        this.userService.saveBiz();
        return "ok";
    }




}
