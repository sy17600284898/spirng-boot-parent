package com.syy.springboot.controller;

import com.syy.springboot.base.controller.BaseController;
import com.syy.springboot.base.utils.StateParameter;
import com.syy.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: FirstController
 * @Auther: zhangyingqi
 * @Date: 2018/8/23 17:53
 * @Description: 测试页面跳转
 */
@Controller
@RequestMapping(value="/first")
public class FirstController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/view")
    public String view(HttpServletRequest request){
        logger.info("进入测试页面");
        return "/student/firstPage";
    }

    @PostMapping(value="/back")
    @ResponseBody
    public ModelMap back(HttpServletRequest request){
        logger.info("进入json测试页面");
        return getModelMap(StateParameter.SUCCESS, null, "请求成功");
    }

}
