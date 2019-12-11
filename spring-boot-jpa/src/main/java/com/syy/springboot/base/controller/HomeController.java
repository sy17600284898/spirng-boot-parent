package com.syy.springboot.base.controller;

import com.syy.springboot.base.entity.UserInfo;
import com.syy.springboot.base.utils.StateParameter;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ASUS
 */
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @GetMapping("/view")
    public String index(HttpServletResponse response) {
        UserInfo ui = this.getCurrentUser();
        if (ui == null) {
            try {
                response.sendRedirect("../login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("初始化home页面....");
        return "/base/home";
    }

    @PostMapping(value = "/welcome")
    public String welcome() {
        logger.info("welcome....");
        return "/base/welcome";
    }

    /**
     * 获取客户权限资源树列表;
     *
     * @return
     */
    @PostMapping("/getTree")
    @RequiresPermissions("userInfo:add")
    public ModelMap getTree() {
        UserInfo ui = this.getCurrentUser();
        if (ui == null) {
            logger.info("当前登录用户：null");
        }
        logger.info("当前登录用户：" + ui.getName());
        JSONArray json = this.getCurrentUserPermission(ui);
        return getModelMap(StateParameter.SUCCESS, json, "操作成功");
    }

}