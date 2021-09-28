package com.zgu.boot.user.service;

import java.util.Map;

public interface UserService {

    void asyncTask(Long taskId);

    Map<String, Object> login(String userId, String password);

    Map<String, Object> loginByPhone(String phone, String password);
}
