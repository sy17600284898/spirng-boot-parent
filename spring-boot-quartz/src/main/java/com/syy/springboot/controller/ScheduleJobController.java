package com.syy.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.syy.springboot.model.JobAndTrigger;
import com.syy.springboot.model.QuartzJob;
import com.syy.springboot.service.ScheduleJobService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ScheduleJobController
 *
 * @author:zy
 * @Date: 2019-07-29 15:42
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@RestController
@RequestMapping("/scheduleJob")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService jobService;

    @PostMapping(value = "/addSchedule")
    public String addSchedule(QuartzJob quartzJob) {
        jobService.addSchedule(quartzJob);
        return "addSchedule job success";
    }

    /**
     * resumeSchedule
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @ApiOperation(value = "Resume a task")
    @GetMapping("/resumeSchedule")
    public String resumeSchedule(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.resumeSchedule(jobName, jobGroup);
        return "resumeSchedule job success";
    }

    /**
     * pauseSchedule
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @ApiOperation(value = "Pause a task")
    @GetMapping("/pauseSchedule")
    public String pauseSchedule(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.pauseSchedule(jobName, jobGroup);
        return "pauseSchedule job success";
    }

    /**
     * deleteSchedule
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @ApiOperation(value = "Delete a task")
    @GetMapping("deleteSchedule")
    public String deleteSchedule(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.deleteSchedule(jobName, jobGroup);
        return "deleteSchedule job success";
    }

    /**
     * updateSchedule
     *
     * @param quartzJob
     * @return
     */
    @ApiOperation(value = "Modify a task")
    @PostMapping("/updateSchedule")
    public String updateSchedule(QuartzJob quartzJob) {
        jobService.updateSchedule(quartzJob);
        return "update job success";
    }

    /**
     * executeImmediately
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    @ApiOperation(value = "Execute immediately")
    @PostMapping("executeImmediately")
    public String executeImmediately(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
        jobService.executeImmediately(jobName, jobGroup);
        return "executeImmediately job success";
    }


    /**
     * queryAllJob
     *
     * @return
     */
    @GetMapping(value = "/queryAllJob")
    @ApiOperation(value = "Get a list of tasks in all plans")
    public Map<String, Object> queryAllJob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTrigger = jobService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }

    /**
     * queryRunJob
     *
     * @return
     */
    @GetMapping(value = "/queryRunJob")
    @ApiOperation(value = "Get all running jobs")
    public List<Map<String, Object>> queryRunJob() {
        return jobService.queryRunJob();
    }
}
