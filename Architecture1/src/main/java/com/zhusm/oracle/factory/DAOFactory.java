package com.zhusm.oracle.factory;

/**
 * @description: DAO层工厂
 * @author: maker
 * @create: 2018/11/17
 */
public class DAOFactory {

    // 禁止产生实例化对象
    private DAOFactory() {
    }

    /**
     * 取得DAO接口实例化对象
     *
     * @param clzz 之类的class对象
     * @param <T>  范型类型
     * @return 接口实例化对象
     */
    public static <T> T getInstance(Class<T> clzz) {
        try {
            return clzz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
