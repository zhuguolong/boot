package com.zgu.boot.user.service.impl;

import com.zgu.boot.user.entity.User;
import com.zgu.boot.user.mapper.UserMapper;
import com.zgu.boot.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Async(value = "primaryTaskExecutor")
    public void asyncTask(Long taskId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info(Thread.currentThread().getName() + ": " + taskId);
    }

    @Override
    public Map<String, Object> login(String userId, String password) {
        return null;
    }

    @Override
    public Map<String, Object> loginByPhone(String phone, String password) {
        return null;
    }

    @Override
    @Transactional
    public Map<String, Object> register(String phone, String password) {
        User user = userMapper.findUserByPhone(phone);
        return null;
    }
}
