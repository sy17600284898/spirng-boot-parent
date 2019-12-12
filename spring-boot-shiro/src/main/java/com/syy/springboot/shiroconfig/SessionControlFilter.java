package com.syy.springboot.shiroconfig;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * SessionControlFilter
 *
 * @author: shiyan
 * @version: 2019-12-11 15:52
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class SessionControlFilter extends AccessControlFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionControlFilter.class);
    /**
     * 踢出后到的地址
     */
    private String kickoutUrl;
    /**
     * 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
     */
    private boolean kickoutAfter = false;
    /**
     * 同一个帐号最大会话数 默认1
     */
    private int maxSession = 1;

    private SessionManager sessionManager;

    private Cache<String, Deque<Session>> cache;

    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public boolean isKickoutAfter() {
        return kickoutAfter;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Cache<String, Deque<Session>> getCache() {
        return cache;
    }

    public void setCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro_redis_cache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        String usernameTenant = (String) session.getAttribute("loginName");
        synchronized (this.cache) {
            if (cache == null) {
                throw new Exception("cache 为空");
            }
            //读取缓存   没有就存入
            Deque<Session> deque = cache.get(usernameTenant);
            //如果此用户没有session队列，也就是还没有登录过，缓存中没有
            //就new一个空队列，不然deque对象为空，会报空指针
            if (deque == null) {
                deque = new LinkedList<>();
            }
            //如果队列里没有此sessionId，且用户没有被踢出；放入队列
            boolean whetherPutDeQue = true;
            if (deque.isEmpty()) {
                whetherPutDeQue = true;
            } else {
                for (Session sessionInqueue : deque) {
                    if (sessionId.equals(sessionInqueue.getId())) {
                        whetherPutDeQue = false;
                        break;
                    }
                }
            }
            if (whetherPutDeQue) {
                deque.push(session);
            }
            LOGGER.debug("logged user:" + usernameTenant + ", deque size = " + deque.size());
            LOGGER.debug("deque = " + deque);
            //如果队列里的sessionId数超出最大会话数，开始踢人
            while (deque.size() > maxSession) {
                Session kickoutSession = null;
                if (kickoutAfter) {
                    kickoutSession = deque.removeFirst();
                    LOGGER.debug("踢出后登录的，被踢出的sessionId为: " + kickoutSession.getId());
                } else { //否则踢出前者
                    kickoutSession = deque.removeLast();
                    LOGGER.debug("踢出先登录的，被踢出的sessionId为： " + kickoutSession.getId());
                }
                if (kickoutSession != null) {
                    kickoutSession.stop();
                }
            }
            return true;
        }
    }
}