package com.zhusm.oracle.factory;

import com.zhusm.oracle.service.proxy.ServiceProxy;

/**
 * @description: 业务逻辑层工厂
 * @author: maker
 * @create: 2018/11/17
 */
public class ServiceFactory {

    // 禁止实例化
    private ServiceFactory() {
    }

    /**
     * 取得IService接口实例化对象
     *
     * @param clzz 之类的class对象
     * @param <T>  范型类型
     * @return 接口实例化对象
     */
    public static <T> T getInstance(Class<T> clzz) {
        try {
            return new ServiceProxy().bind(clzz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
