package com.zgu.boot.user.service.impl;

import com.zgu.boot.user.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Async(value = "primaryTaskExecutor")
    public void asyncTask(Long taskId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + taskId);
    }
}
