package com.syy.springboot.config.mysql;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author shiyan
 * @Date 2018-06-23
 * @Description 动态数据源上下文管理
 */
public class DynamicDataSourceContextHolder {

    /**
     * 存放当前线程使用的数据源类型信息
     */
    private static final ThreadLocal<String> CONTEXTHOLDER = new ThreadLocal<>();
    /**
     * 存放数据源id
     */
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 设置数据源
     *
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        CONTEXTHOLDER.set(dataSourceType);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSourceType() {
        return CONTEXTHOLDER.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSourceType() {
        CONTEXTHOLDER.remove();
    }

    /**
     * 判断当前数据源是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean isContainsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
