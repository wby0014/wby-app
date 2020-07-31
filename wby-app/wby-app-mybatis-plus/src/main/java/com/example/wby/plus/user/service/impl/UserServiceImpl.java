package com.example.wby.plus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wby.plus.user.entity.User;
import com.example.wby.plus.user.mapper.UserMapper;
import com.example.wby.plus.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wby
 * @since 2020-07-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
