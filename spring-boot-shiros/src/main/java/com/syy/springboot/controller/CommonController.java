package com.syy.springboot.controller;

import com.syy.springboot.enums.ResultStatusCode;
import com.syy.springboot.result.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RequestMapping("/common")
@RestController
public class CommonController {
 
    /**
     * 未授权跳转方法
     * @return
     */
    @GetMapping("/unauth")
    public Result unauth(){
        SecurityUtils.getSubject().logout();
        return new Result(ResultStatusCode.UNAUTHO_ERROR);
    }
 
    /**
     * 被踢出后跳转方法
     * @return
     */
    @GetMapping("/kickout")
    public Result kickout(){
        return new Result(ResultStatusCode.INVALID_TOKEN);
    }
 
}
