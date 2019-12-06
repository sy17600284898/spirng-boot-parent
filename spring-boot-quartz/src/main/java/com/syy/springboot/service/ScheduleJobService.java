package com.syy.springboot.service;

import com.github.pagehelper.PageInfo;
import com.syy.springboot.model.QuartzJob;

import java.util.List;
import java.util.Map;

/**
 * ScheduleJobService
 *
 * @author: Lenovo
 * @date: 2019-7-28 11:40:39
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public interface ScheduleJobService {

    /**
     * addSchedule
     *
     * @param info
     */
    void addSchedule(QuartzJob info);

    /**
     * resumeSchedule
     *
     * @param jobName
     * @param jobGroup
     */
    void resumeSchedule(String jobName, String jobGroup);

    /**
     * pauseSchedule
     *
     * @param jobName
     * @param jobGroup
     */
    void pauseSchedule(String jobName, String jobGroup);

    /**
     * deleteSchedule
     *
     * @param jobName
     * @param jobGroup
     */
    void deleteSchedule(String jobName, String jobGroup);


    /**
     * updateSchedule
     *
     * @param quartzJob
     */
    void updateSchedule(QuartzJob quartzJob);

    /**
     * executeImmediately
     *
     * @param jobName
     * @param jobGroup
     */
    void executeImmediately(String jobName, String jobGroup);


    /**
     * queryAllJob
     *
     * @return
     */
    List<Map<String, Object>> queryAllJob();

    /**
     * queryRunJob
     *
     * @return
     */
    List<Map<String, Object>> queryRunJob();

    PageInfo getJobAndTriggerDetails(Integer pageNum, Integer pageSize);
}
