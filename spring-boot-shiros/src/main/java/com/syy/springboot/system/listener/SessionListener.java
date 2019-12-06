package com.syy.springboot.system.listener;

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
public class SessionListener implements HttpSessionListener {
    private int onlineCount = 0;//记录session的数量

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlineCount++;
        System.out.println("【HttpSessionListener监听器】 sessionCreated, onlineCount:" + onlineCount);
        se.getSession().getSessionContext().getSession("c2abc1c8-1dde-4c21-83a3-378854073d3c");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (onlineCount > 0) {
            onlineCount--;
        }
        System.out.println("【HttpSessionListener监听器】 sessionDestroyed, onlineCount:" + onlineCount);
        se.getSession().getServletContext().setAttribute("onlineCount", onlineCount);
    }
}
