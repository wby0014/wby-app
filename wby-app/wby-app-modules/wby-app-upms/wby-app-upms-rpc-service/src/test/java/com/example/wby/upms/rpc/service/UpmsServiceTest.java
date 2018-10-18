package com.example.wby.upms.rpc.service;

import com.example.wby.upms.rpc.api.UpmsApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试
 *
 * @author wubinyu
 * @date 2018/10/15 17:02.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UpmsServiceTest {

    @Autowired
    private UpmsApiService upmsApiService;


    @Test
    public void index() {
        String s = upmsApiService.selectUpmsApiInfo("测试数据");
        System.out.println(s);
    }
}
