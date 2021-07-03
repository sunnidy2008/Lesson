package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)//使用SpringRunner加载上下文
public class Lesson9ApplicationTests {
    @Test//这个是一个test单元测试文件
    public void test1() {
        int num = new Integer(1);
        Assert.assertEquals(num,1);
    }

    @Test
    public void test2() {
        List<String> list1 = Arrays.asList("a", "b");
        List<String> list2 = Arrays.asList("a", "b");
        List<String> list3 = list2;

        Assert.assertEquals(list1,list2);//通过
        Assert.assertEquals(list3,list2);//通过
        Assert.assertSame(list3,list2);//通过
        Assert.assertSame(list1,list2);//不通过，因为list1和list2分别指向不同的内存地址
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test3() throws URISyntaxException {
        String result = testRestTemplate.getForObject("/demo/test1", String.class);
        Assert.assertEquals(result,"hello junit test");
    }


//    @Test
//    @Transactional
//    public void test4() {
//        User user = new User();
//        user.setName("abc");
//        user.setAge(19);
//        user.setBirthday(new Date());
//        userJpa.save(user);
//        Assert.assertTrue(user.getId()!=null);
//        Assert.assertNotNull(user.getId());
//    }
}
