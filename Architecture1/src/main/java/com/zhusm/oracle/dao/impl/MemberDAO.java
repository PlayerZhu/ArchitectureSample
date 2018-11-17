package com.zhusm.oracle.dao.impl;

import com.zhusm.oracle.dao.IMemberDAO;
import com.zhusm.oracle.vo.Member;
import com.zhusm.oracle.dao.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @description: IMemberDAO实现
 * @author: maker
 * @create: 2018/11/17
 */
public class MemberDAO extends AbstractDAO implements IMemberDAO {

    @Override
    public boolean doCreate(Member vo) throws Exception {
        String sql = "INSERT INTO member(mid,name,age,phone,birthday,note) VALUES(?,?,?,?,?,?)";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, vo.getMid());
        this.statement.setString(2, vo.getName());
        this.statement.setInt(3, vo.getAge());
        this.statement.setString(4, vo.getPhone());
        this.statement.setDate(5, new java.sql.Date(vo.getBirthday().getTime()));
        this.statement.setString(6, vo.getNote());
        return this.statement.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Member vo) throws Exception {
        String sql = "UPDATE member t SET t.name=?,t.age=?,t.phone=?,t.birthday=?,t.note=? WHERE t.mid=?";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, vo.getName());
        this.statement.setInt(2, vo.getAge());
        this.statement.setString(3, vo.getPhone());
        this.statement.setDate(4, new java.sql.Date(vo.getBirthday().getTime()));
        this.statement.setString(5, vo.getNote());
        this.statement.setString(6, vo.getMid());
        return this.statement.executeUpdate() > 0;
    }

    @Override
    public boolean doDeleteBatch(Set<String> ids) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM member t WHERE t.mid in(");
        Iterator<String> iterator = ids.iterator();
        while (iterator.hasNext()) {
            sb.append("'").append(iterator.next()).append("'").append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(")");
        this.statement = this.connection.prepareStatement(sb.toString());
        return this.statement.executeUpdate() == ids.size();
    }

    @Override
    public Member findById(String id) throws Exception {
        String sql = "SELECT mid,name,age,phone,birthday,note FROM member WHERE mid=?";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, id);
        ResultSet rs = this.statement.executeQuery();
        return resultSetToMember(rs);
    }

    @Override
    public Member findByPhone(String phone) throws Exception {
        String sql = "SELECT mid,name,age,phone,birthday,note FROM member WHERE phone=?";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, phone);
        ResultSet rs = this.statement.executeQuery();
        return resultSetToMember(rs);
    }

    private Member resultSetToMember(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setPhone(rs.getString(4));
            vo.setBirthday(rs.getDate(5));
            vo.setNote(rs.getString(6));
            return vo;
        }
        return null;
    }

    private List<Member> resultSetToMembers(ResultSet rs) throws SQLException {
        List<Member> members = new ArrayList<>();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setPhone(rs.getString(4));
            vo.setBirthday(rs.getDate(5));
            vo.setNote(rs.getString(6));
            members.add(vo);
        }
        return members;
    }

    @Override
    public List<Member> findAll() throws Exception {
        String sql = "SELECT mid,name,age,phone,birthday,note FROM member";
        this.statement = this.connection.prepareStatement(sql);
        ResultSet rs = this.statement.executeQuery();
        return resultSetToMembers(rs);
    }

    @Override
    public List<Member> findByPage(Integer pageIndex, Integer pageSize) throws Exception {
        String sql = "SELECT * FROM ( " +
                "   SELECT mid,name,age,phone,birthday,note,ROWNUM rn " +
                "   FROM member WHERE ROWNUM <= ? ) t" +
                " WHERE t.rn > ?";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setInt(1, pageIndex * pageSize);
        this.statement.setInt(2, (pageIndex - 1) * pageSize);
        ResultSet rs = this.statement.executeQuery();
        return resultSetToMembers(rs);
    }

    @Override
    public List<Member> findByPage(String column, String keyWord, Integer pageIndex, Integer pageSize) throws Exception {
        String sql = "SELECT * FROM ( " +
                "   SELECT mid,name,age,phone,birthday,note,ROWNUM rn " +
                "   FROM member WHERE " + column + " like ? AND ROWNUM <= ? ) t" +
                " WHERE t.rn > ?";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, "%" + keyWord + "%");
        this.statement.setInt(2, pageIndex * pageSize);
        this.statement.setInt(3, (pageIndex - 1) * pageSize);
        ResultSet rs = this.statement.executeQuery();
        return resultSetToMembers(rs);
    }

    @Override
    public Long getAllCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM member";
        this.statement = this.connection.prepareStatement(sql);
        ResultSet rs = this.statement.executeQuery();
        while (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }

    @Override
    public Long getAllCount(String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM member WHERE " + column + " LIKE ?";
        this.statement = this.connection.prepareStatement(sql);
        this.statement.setString(1, "%" + keyWord + "%");
        ResultSet rs = this.statement.executeQuery();
        while (rs.next()) {
            return rs.getLong(1);
        }
        return 0L;
    }
}
