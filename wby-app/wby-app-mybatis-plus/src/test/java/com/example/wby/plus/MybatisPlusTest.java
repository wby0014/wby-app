package com.example.wby.plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wby.plus.user.entity.User;
import com.example.wby.plus.user.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description mybatis-plus测试
 * @Date 2020/7/3 17:02
 * @Author wuby31052
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    private static Logger logger = LoggerFactory.getLogger(MybatisPlusTest.class);

    @Autowired
    private UserServiceImpl userService;

    /**
     * 测试插入方法
     */
    @Test
    public void testInsert() {
        //单条记录插入
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(18);

        userService.save(user);
        //批量插入
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userService.saveBatch(userList);
        //保存或更新
        userService.saveOrUpdate(user);
        userService.saveOrUpdateBatch(userList);
    }

    /**
     * 测试更新方法
     */
    @Test
    public void testUpdate() {
        //修改
        User user = new User();
        user.setId(1);
        user.setName("把名字改成李四");
        userService.updateById(user);
    }

    /**
     * 测试删除方法
     */
    @Test
    public void testDelete() {
        //使用通用方法删除
//        userService.removeById(1);
        //还可以使用自定义的删除，删除name="张三"的记录
        userService.remove(new QueryWrapper<User>().eq("name", "张三"));
    }

    /**
     * 测试查询方法
     */
    @Test
    public void testSelect() {
        //单个查询
        User userById = userService.getById(1);
        logger.info("user={}", userById.toString());
        //批量查询
        List<User> list = userService.list();
        //自定义查询,id=1的记录
        User user = userService.getOne(new QueryWrapper<User>().eq("id", 1));
    }

    /**
     * 测试分页
     */
    @Test
    public void testPage() {
        int pageNo = 1;
        int pageSize = 10;
        //解释一下自定义的查询，可以看到每种方法都有若干的自定义查询方法，可以定义类似这种new QueryWrapper<User>().like，.eq,.like什么的就是等于、模糊查询的意思
        IPage<User> userIPage = userService.page(new Page<>(pageNo, pageSize), new QueryWrapper<User>().like("name", "李"));
        //分页的记录
        List<User> records = userIPage.getRecords();
        //总记录数
        long total = userIPage.getTotal();
        //总页数
        long pages = userIPage.getPages();
    }


}
