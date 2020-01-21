package com.syy.springboot.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tool {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
//        String lineToHump = lineToHump("f_parent_no_leader");
//        System.out.println(lineToHump);// fParentNoLeader
//        System.out.println(humpToLine(lineToHump));// f_parent_no_leader
//        System.out.println(humpToLine2(lineToHump));// f_parent_no_leader

            List<User> uList=new ArrayList<User>();
            uList.add(new User(1, "xxx", 1, 44));
            uList.add(new User(2, "zzz", 1, 11));
            uList.add(new User(3, "aaa", 1, 11));
            uList.add(new User(4, "bbb", 1, 11));

            List<User> uList1=new ArrayList<User>();
            uList1.add(new User(1, "xxx", 1, 11));
//            HashSet h3 = new HashSet(uList);
//            HashSet h4 = new HashSet(uList1);
//            h3.removeAll(h4);
//            uList.clear();
//            uList.addAll(h3);
//            System.out.println(uList);
        List<User> collect = uList.stream()
                .filter(t -> uList1.stream()
                        .anyMatch(t2 -> t2.getName() == t.getName()))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}