package com.zhusm.oracle.dao;

import com.zhusm.oracle.vo.Member;

import java.util.List;
import java.util.Set;

/**
 * Member数据层操作标准
 */
public interface IMemberDAO extends IBaseDAO<String, Member> {

    /**
     * 根据phone查找member
     *
     * @param phone 要查找的member的phone
     * @return 成功返回member对象，否则返回null
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public Member findByPhone(String phone) throws Exception;

}
