package com.syy.springboot.util;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class SortList<E> {

    public void Sort(List<E> list, final String method) {
        //排序
        Collections.sort(list, (Comparator) (a, b) -> {
            int ret = 0;
            try {
                Method m1 = ((E) a).getClass().getMethod(method, null);
                Method m2 = ((E) b).getClass().getMethod(method, null);
                ret = m1.invoke(((E) a), null).toString().compareTo(m2.invoke(((E) b), null).toString());
            } catch (NoSuchMethodException ne) {
                log.info(String.valueOf(ne));
            } catch (IllegalAccessException ie) {
                log.info(String.valueOf(ie));
            } catch (InvocationTargetException it) {
                log.info(String.valueOf(it));
            }
            return ret;
        });
    }
}