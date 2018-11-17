package com.zhusm.oracle.service;

import com.zhusm.oracle.vo.Member;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMemberService {

    /**
     * 实现用户数据添加，要实现功能如下<br>
     * <li>1.使用IMemberDAO.findById()判断要添加的用户是否存在</li>
     * <li>2.使用IMemberDAO.findByPhone()判断要添加的phone是否存在</li>
     * <li>3.判断年龄是否大于0，不大于0设置为-1</li>
     * <li>5.使用IMemberDAO.doCreate()保存数据</li>
     *
     * @param vo 要添加的member对象
     * @return 增加成功返回true，否则返回false
     * @throws Exception
     */
    public boolean add(Member vo) throws Exception;

    /**
     * 实现用户数据更新，要实现功能如下<br>
     * <li>1.使用IMemberDAO.findByPhone()判断更新的电话是否存在(包括自己)</li>
     * <li>2.使用IMemberDAO.doUpdate()更新数据</li>
     * <li>3.判断年龄是否大于0，不大于0设置为-1</li>
     *
     * @param vo 要更新的member对象
     * @return 更新成功返回true，否则返回false
     * @throws Exception
     */
    public boolean edit(Member vo) throws Exception;

    /**
     * 实现用户数据删除，要实现功能如下<br>
     * <li>1.判断集合师傅有数据</li>
     * <li>2.调用IMemberDAO.doDeleteBatch()方法删除数据</li>
     * <li>1.</li>
     * <li>1.</li>
     *
     * @param ids 所有要删除数据的id集合
     * @return 删除成功返回true，集合为空或无数据或删除失败则返回false
     * @throws Exception
     */
    public boolean remove(Set<String> ids) throws Exception;

    /**
     * 获取单个用户数据，要实现功能如下<br>
     * <li>调用IMemberDAO.findById()方法获取member对象</li>
     *
     * @param id 要查询的用户id
     * @return 成功返回member对象，失败返回null
     * @throws Exception
     */
    public Member get(String id) throws Exception;

    /**
     * 获取全部用户数据
     *
     * @return 所有用户数据以list返回，无数据则list长度为0
     * @throws Exception
     */
    public List<Member> getMembers() throws Exception;

    /**
     * 分页获取用户数据，要实现功能如下<br>
     * <li>1.调用IMemberDAO.findByPage()方法获取member对象集合</li>
     * <li>2.调用IMemberDAO.getAllCount()方法获取统计总数</li>
     *
     * @param pageIndex 当前页索引
     * @param pageSize  当前页条数
     * @return 返回Map类型数据，格式如下<br>
     * <li>1.key=count,value=用户集合(List&lt;Member&gt;类型)</li>
     * <li>2.key=members,value=数量(Long类型)</li>
     * @throws Exception
     */
    public Map<String, Object> getMembers(int pageIndex, int pageSize) throws Exception;

    /**
     * 分页获取模糊查询的用户数据，要实现功能如下<br>
     * <li>1.调用IMemberDAO.findByPage()方法获取member对象集合</li>
     * <li>2.调用IMemberDAO.getAllCount()方法获取统计总数</li>
     *
     * @param column    要模糊查询的列
     * @param keyWord   要模糊查询的关键字
     * @param pageIndex 当前页索引
     * @param pageSize  当前页条数
     * @return 返回Map类型数据，格式如下<br>
     * <li>1.key=count,value=用户集合(List&lt;Member&gt;类型)</li>
     * <li>2.key=members,value=数量(Long类型)</li>
     * @throws Exception
     */
    public Map<String, Object> getMembers(String column, String keyWord, int pageIndex, int pageSize) throws Exception;
}
