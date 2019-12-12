package com.syy.springboot.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * SessionListener
 *
 * @author: shiyan
 * @version: 2019-12-06 15:45
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@WebListener
@Slf4j
public class SessionListener implements HttpSessionListener {
    /**
     * 记录session的数量
     */
    private int onlineCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineCount++;
        log.info("【HttpSessionListener监听器】 sessionCreated, onlineCount:" + onlineCount);
        se.getSession().getSessionContext().getSession("c2abc1c8-1dde-4c21-83a3-378854073d3c");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (onlineCount > 0) {
            onlineCount--;
        }
        log.info("【HttpSessionListener监听器】 sessionDestroyed, onlineCount:" + onlineCount);
        se.getSession().getServletContext().setAttribute("onlineCount", onlineCount);
    }
}
