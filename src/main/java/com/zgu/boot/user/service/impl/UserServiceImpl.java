package com.zgu.boot.user.service.impl;

import com.zgu.boot.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

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
}
