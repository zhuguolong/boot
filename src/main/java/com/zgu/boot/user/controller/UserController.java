package com.zgu.boot.user.controller;

import com.zgu.boot.common.CommonResponse;
import com.zgu.boot.user.entity.User;
import com.zgu.boot.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户 id 登录
     * @param userId 用户id
     * @param password 密码
     * @return
     */
    @PostMapping("/login/id")
    public CommonResponse login(String userId, String password) {
        Map<String, Object> resMap = userService.login(userId, password);
        return new CommonResponse(HttpStatus.OK.value(), "登陆成功！", resMap);
    }

    /**
     * 手机号码登录
     * @param phone 手机号码
     * @param password 密码
     * @return
     */
    @PostMapping("/login/phone")
    public CommonResponse loginByPhone(String phone, String password) {
        Map<String, Object> resMap = userService.loginByPhone(phone, password);
        return new CommonResponse(HttpStatus.OK.value(), "登陆成功！", resMap);
    }

    @GetMapping("/info/{id}")
    public Object findUserInfo(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setUserName("张山");
        return new CommonResponse(HttpStatus.OK.value(), "查询成功！", user);
    }

    @GetMapping("/async/{num}/{taskId}")
    public CommonResponse asyncTask(@PathVariable Long num, @PathVariable Long taskId) {
        LOG.info("{}: start...", Thread.currentThread().getName());
        for (int i = 0; i < num; i++) {
            userService.asyncTask(taskId);
        }
        LOG.info("{}: end...", Thread.currentThread().getName());
        return new CommonResponse(HttpStatus.OK.value(), Thread.currentThread().getName());
    }

}
