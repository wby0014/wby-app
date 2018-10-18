package com.example.wby.upms.rpc.service.impl;


import com.example.wby.upms.mapper.UpmsApiDao;
import com.example.wby.upms.rpc.api.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UpmsApiService实现
 */
@Service
public class UpmsApiServiceImpl implements UpmsApiService {

    private static final Logger logger = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    UpmsApiDao upmsApiDao;


    /**
     * @param info
     * @return
     */
    @Override
    public String selectUpmsApiInfo(String info) {
        logger.info(">>>>>>>查询用户信息<<<<<<<");
        String apiInfo = upmsApiDao.getApiInfo();
        return apiInfo + '@' + info;
    }


}