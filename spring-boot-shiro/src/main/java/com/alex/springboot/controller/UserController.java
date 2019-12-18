package com.alex.springboot.controller;

import com.alex.springboot.service.UserService;
import com.alex.springboot.system.vo.Grid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresPermissions("sys:user:view")
    @RequestMapping("findList")
    public Grid findList(){
        return userService.findList();
    }

}
