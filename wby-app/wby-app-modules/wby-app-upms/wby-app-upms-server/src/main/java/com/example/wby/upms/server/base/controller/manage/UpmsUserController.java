package com.example.wby.upms.server.base.controller.manage;

import com.example.wby.common.base.BaseController;
import com.example.wby.common.util.ObjectUtils;
import com.example.wby.redis.util.RedisCacheUtils;
import com.example.wby.upms.rpc.api.UpmsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsApiService upmsApiService;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @ApiOperation(value = "测试hello")
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String helloMethod() {
        return "hello world";
    }

    @ApiOperation(value = "测试获取数据库数据")
    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    public String getMysqlData(String param) {
        String mysql_key = (String) redisCacheUtils.get("mysql_key");
        if (ObjectUtils.isNotEmpty(mysql_key)) {
            logger.info("返回缓存中的值");
            return mysql_key;
        }
        String s = upmsApiService.selectUpmsApiInfo(param);
        redisCacheUtils.set("mysql_key", s);
        return s;
    }

    @ApiOperation(value = "用户首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/user/index.jsp";
    }


    @ApiOperation(value = "用户权限")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") int id, ModelMap modelMap) {
        String s = upmsApiService.selectUpmsApiInfo("1");
        modelMap.put("user", s);
        return "/manage/user/permission.jsp";
    }


    @ApiOperation(value = "新增用户")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/user/create.jsp";
    }


}
