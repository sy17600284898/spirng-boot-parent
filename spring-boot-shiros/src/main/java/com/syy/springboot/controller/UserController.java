package com.syy.springboot.controller;

import com.syy.springboot.service.UserService;
import com.syy.springboot.system.vo.Grid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
 
    @Autowired
    private UserService userService;

    @RequiresPermissions("sys:user:view")
    @GetMapping("findList")
    public Grid findList(){
        return userService.findList();
    }
}