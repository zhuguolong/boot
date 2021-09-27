package com.zgu.boot.user.controller;

import com.zgu.boot.common.CommonResponse;
import com.zgu.boot.user.entity.User;
import com.zgu.boot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
        System.out.println(Thread.currentThread().getName() + ": start...");
        for (int i = 0; i < num; i++) {
            userService.asyncTask(taskId);
        }
        System.out.println(Thread.currentThread().getName() + ": end...");

        return new CommonResponse(HttpStatus.OK.value(), Thread.currentThread().getName());
    }

}
