package com.zhusm.oracle.service.proxy;

import com.zhusm.oracle.dbc.DatabaseConnection;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 服务层代理
 * @author: maker
 * @create: 2018/11/17
 */
public class ServiceProxy implements InvocationHandler {

    // 需要真实操作类对象
    private Object target;

    public <T> T bind(Class<T> clazz) {
        try {
            this.target = clazz.getDeclaredConstructor().newInstance();
            return (T) Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
                    this.target.getClass().getInterfaces(), this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object object = null;
            String methodName = method.getName();
            if (methodName.startsWith("add")
                    || methodName.startsWith("edit")
                    || methodName.startsWith("remove")) {
                try {
                    DatabaseConnection.getConnection().setAutoCommit(false);
                    object = method.invoke(this.target, args);
                    DatabaseConnection.getConnection().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    DatabaseConnection.getConnection().rollback();
                }
            } else {
                object = method.invoke(this.target, args);
            }
            return object;
        } catch (Exception e) {
            throw e;
        } finally {
            DatabaseConnection.close();
        }
    }
}
