package com.zhusm.oracle.dao;

import com.zhusm.oracle.vo.Member;

import java.util.List;
import java.util.Set;

/**
 * Member数据层操作标准
 */
public interface IBaseDAO<K, V> {

    /**
     * 保存VO对象数据
     *
     * @param vo 要保存数据的VO对象
     * @return 保存成功返回true，否则返回false
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public boolean doCreate(V vo) throws Exception;

    /**
     * 更新VO对象数据，根据id更新
     *
     * @param vo 要更新数据的VO对象
     * @return 更新成功返回true，否则返回false
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public boolean doUpdate(V vo) throws Exception;

    /**
     * 根据id删除VO对象数据
     *
     * @param ids VO对象表id无重复集合
     * @return 删除成功返回true，否则返回false
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public boolean doDeleteBatch(Set<K> ids) throws Exception;

    /**
     * 根据id查找VO对象
     *
     * @param id 要查找的VO对象的id
     * @return 成功返回VO对象对象，否则返回null
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public Member findById(K id) throws Exception;

    /**
     * 查询全部VO对象数据
     *
     * @return 成功返回VO对象列表，否则返回的size为0的list
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public List<V> findAll() throws Exception;

    /**
     * 分页查询VO对象数据
     *
     * @param pageIndex 当前页索引
     * @param pageSize  每页条数
     * @return 成功返回VO对象列表，否则返回size为0的list
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public List<V> findByPage(Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 分页模糊查询VO对象数据
     *
     * @param column    要查询的数据列
     * @param keyWord   要查询的关键字，为空表示全部查询
     * @param pageIndex 当前页索引
     * @param pageSize  每页显示条数
     * @return 成功返回VO对象列表，否则返回size为0的list
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public List<V> findByPage(String column, String keyWord, Integer pageIndex, Integer pageSize) throws Exception;

    /**
     * 统计全部VO对象数量
     *
     * @return Oracle COUNT()函数统计结果
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public Long getAllCount() throws Exception;

    /**
     * 统计模糊查询的VO对象数量
     *
     * @param column  要查询的数据列
     * @param keyWord 要查询的关键字，为空表示查询全部
     * @return Oracle COUNT()函数统计结果
     * @throws Exception 数据库未连接，数据库操作错误
     */
    public Long getAllCount(String column, String keyWord) throws Exception;
}
