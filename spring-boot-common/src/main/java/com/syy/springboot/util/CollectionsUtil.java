package com.syy.springboot.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Test
 *
 * @author: shiyan
 * @version: 2019-12-09 09:55
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class CollectionsUtil {

    public static <T> List<T> ComparatorUtil(List<T> list) {
        //sort by Amount and Id
//        Collections.sort(list, (o1, o2) -> {
//            if (o1.getAmount().compareTo(o2.getAmount()) == 0) {
//                if (o1.getId() < o2.getId()) {
//                    return o2.getId();
//                }
//                return o1.getId();
//            }
//            return o2.getAmount().compareTo(o1.getAmount()) > 0 ? 1 : -1;
//        });

        //key 下标  value 对象
//        Map<Integer, User> collect = IntStream.range(0, list.size()).boxed().collect(Collectors.toMap(i -> i+1,
//                i -> list.get(i)));
        return list;
    }


}

