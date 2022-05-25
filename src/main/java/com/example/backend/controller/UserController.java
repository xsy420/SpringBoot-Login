package com.example.backend.controller;

import com.example.backend.annotation.MultiRequestBody;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.TokenUtil;
import com.example.backend.common.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.utils.UserUtil.*;
import static com.example.backend.common.AjaxResult.*;
import static com.example.backend.utils.EntityUtil.*;

@RestController
@RequestMapping("/api/v1/user")
@Api(tags = {"用户"})
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public AjaxResult login(
            @ApiParam(name = "用户实例") @RequestBody User user
    ) {
        try {
            isUser(user);
        } catch ( Exception e ) {
            return error(e.getMessage());
        }

        String username = user.getUsername();
        String password = user.getPassword();

        User userByUsername = userService.findUserByUsername(username);

        if (null == userByUsername) {
            return warning("请前往注册");
        }
        if (!userByUsername.getPassword().equals(password)) {
            return warning("用户名或密码不一致");
        } else {
            return success("登录成功")
                    .put("token", TokenUtil.generateToken(username, password));
        }
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册")
    public AjaxResult register(
            @MultiRequestBody User user,
            @MultiRequestBody String check_password
            ) {
        try {
            isUser(user);
            isPassword(check_password);
        } catch ( Exception e ) {
            return error(e.getMessage());
        }

        String username = user.getUsername();
        String password = user.getPassword();

        if (!check_password.equals(password)) {
            return warning("两次密码不一致");
        }

        User userByUsername = userService.findUserByUsername(username);

        if (null != userByUsername) {
            return warning("用户已存在");
        } else {
            userService.insert(user);
            return success("注册成功");
        }
    }

    @PutMapping("/upload")
    public AjaxResult update(
            @RequestBody User newUser
    ) {
        String username = newUser.getUsername();
        String password = newUser.getPassword();

        try {
            isUser(newUser);
            if (StringUtils.isNotEmpty(password)) isPassword(password);
        } catch ( Exception e ) {
            return error(e.getMessage());
        }

        User oldUser = userService.findUserByUsername(username);

        if (null == oldUser) {
            return warning("用户不存在");
        }
        else {
            UpdateAllNotEmpty(oldUser, newUser);
            userService.updateUser(oldUser);
            return success("修改成功");
        }
    }

    @DeleteMapping("/delete")
    public AjaxResult delete(
            @RequestParam(value = "username") String username
    ) {
        try {
            isUsername(username);
        } catch ( Exception e ) {
            return error(e.getMessage());
        }

        User userByUsername = userService.findUserByUsername(username);

        if (null == userByUsername) {
            return warning("用户不存在");
        } else {
            return success("删除成功")
                    .put("rows", userService.deleteUserByUsername(username));
        }
    }
}
