package com.example.wby.upms.rpc.api;

/**
 * @author wubinyu
 * @date 2018/10/18 19:53.
 */
public class UpmsApiServiceMock implements UpmsApiService {

    public String selectUpmsApiInfo(String info) {
        throw new RuntimeException("service is no vailable!");
    }
}
