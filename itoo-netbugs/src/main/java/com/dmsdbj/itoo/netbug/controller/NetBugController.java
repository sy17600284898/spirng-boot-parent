package com.dmsdbj.itoo.netbug.controller;

import com.dmsdbj.itoo.netbug.service.jinMainLine;
import com.dmsdbj.itoo.netbug.service.jinProject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ASUS
 * @Class_NAME netBugController
 * @Auther 张明慧
 * @Date 2019/3/9 11:04
 */
@Api(tags = {"今目标爬取接口"})
@RequestMapping("/netBug")
@RestController
public class NetBugController {

    /**
     * 根据jsessionID爬取主线
     *
     * @param jsessionID jsessionID
     * @author 张明慧
     * @since 0.0.2 2019年3月9日11:16:09
     */
    @ApiOperation(value = "根据jsessionID爬取数据", notes = "请输入jsessionID爬取主线信息")
    @GetMapping(value = {"/getMainLine/{jsessionID}"})
    public String getMainLine(@ApiParam(value = "jsessionID", required = true) @PathVariable String jsessionID,
                              HttpServletRequest request, HttpServletResponse response) {
        jinMainLine.getMainLine(jsessionID, request, response);
        return null;
    }


    /**
     * 根据jsessionID爬取项目
     *
     * @param jsessionID jsessionID
     * @author 张明慧
     * @since 0.0.2 2019年3月9日11:16:06
     */
    @ApiOperation(value = "根据jsessionID爬取数据", notes = "请输入jsessionID爬取项目信息")
    @GetMapping(value = {"/getProject/{jsessionID}"})
    public String getProject(@ApiParam(value = "jsessionID", required = true) @PathVariable String jsessionID, HttpServletRequest request, HttpServletResponse response) {
        jinProject.getProject(jsessionID, request, response);
        return null;
    }
}
