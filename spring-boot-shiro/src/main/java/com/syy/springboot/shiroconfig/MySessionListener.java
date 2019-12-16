package com.syy.springboot.shiroconfig;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MySessionListener implements SessionListener {

    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
        System.out.println("Login+1==" + sessionCount.get());
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
        System.out.println("Login-1==" + sessionCount.get());
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
        System.out.println("Login expired-1==" + sessionCount.get());
    }
}