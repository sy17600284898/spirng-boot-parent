package com.syy.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syy.springboot.mapper.JobAndTriggerMapper;
import com.syy.springboot.model.JobAndTrigger;
import com.syy.springboot.model.QuartzJob;
import com.syy.springboot.service.ScheduleJobService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author:
 * @Date: 2019-07-29 15:24
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    private Logger logger = LoggerFactory.getLogger(ScheduleJobService.class);
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    @PostConstruct
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 增加一个job
     * addSchedule
     */
    @Override
    public void addSchedule(QuartzJob quartzJob) {
        try {
            String jobName = quartzJob.getJobName(),
                    jobGroup = quartzJob.getJobGroup(),
                    cronExpression = quartzJob.getCronExpression(),
                    jobDescription = quartzJob.getJobDescription(),
                    createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            // 定义调度触发规则
            // 使用cornTrigger规则
            CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(schedBuilder).build();

            // 把作业和触发器注册到任务调度中
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobName);
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改 一个job的 时间表达式
     * updateSchedule
     *
     * @param info
     */
    @Override
    public void updateSchedule(QuartzJob info) {
        String jobName = info.getJobName(),
                jobGroup = info.getJobGroup(),
                cronExpression = info.getCronExpression(),
                jobDescription = info.getJobDescription(),
                createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(cronScheduleBuilder).build();
            // 重启触发器
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);
            scheduler.scheduleJob(jobDetail, triggerSet, true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除任务一个job
     * deleteSchedule
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    @Override
    public void deleteSchedule(String jobName, String jobGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            logger.info("delete job, triggerKey:{},jobGroup:{}, jobName:{}", triggerKey, jobGroupName, jobName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停一个job
     * pauseSchedule
     *
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public void pauseSchedule(String jobName, String jobGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            scheduler.pauseTrigger(triggerKey);
            logger.info("pause job success, triggerKey:{},jobGroup:{}, jobName:{}", triggerKey, jobGroupName, jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复一个job
     * resumeSchedule
     *
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public void resumeSchedule(String jobName, String jobGroupName) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            scheduler.resumeTrigger(triggerKey);
            logger.info("resume job success,triggerKey:{},jobGroup:{}, jobName:{}", triggerKey, jobGroupName, jobName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即执行一个job
     * executeImmediately
     *
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public void executeImmediately(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     * queryAllJob
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryAllJob() {
        List<Map<String, Object>> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>(16);
                    map.put("jobName", jobKey.getName());
                    map.put("jobGroupName", jobKey.getGroup());
                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    map.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("jobTime", cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 获取所有正在运行的job
     * queryRunJob
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryRunJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<>(16);
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    @Override
    public PageInfo getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<>(list);
        return page;
    }
}
