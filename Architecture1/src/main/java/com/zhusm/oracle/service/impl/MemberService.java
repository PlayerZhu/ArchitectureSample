package com.zhusm.oracle.service.impl;

import com.zhusm.oracle.dao.IMemberDAO;
import com.zhusm.oracle.dao.impl.MemberDAO;
import com.zhusm.oracle.dbc.DatabaseConnection;
import com.zhusm.oracle.factory.DAOFactory;
import com.zhusm.oracle.service.IMemberService;
import com.zhusm.oracle.vo.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: Member业务逻辑
 * @author: maker
 * @create: 2018/11/17
 */
public class MemberService implements IMemberService {

    private IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);

    @Override
    public boolean add(Member vo) throws Exception {
        // IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);
        if (dao.findById(vo.getMid()) == null) {
            if (dao.findByPhone(vo.getPhone()) == null) {
                if (vo.getAge() <= 0) {
                    vo.setAge(-1);
                }
                return dao.doCreate(vo);
            }
        }
        return false;
    }

    @Override
    public boolean edit(Member vo) throws Exception {
        // IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);
        if (vo.getAge() <= 0) {
            vo.setAge(-1);
        }
        Member member = dao.findByPhone(vo.getPhone());
        if (member == null) {
            return dao.doUpdate(vo);
        }
        if (member.getPhone().equals(vo.getPhone())) {
            return dao.doUpdate(vo);
        }
        return false;
    }

    @Override
    public boolean remove(Set<String> ids) throws Exception {
        if (ids == null || ids.size() <= 0) {
            return false;
        }
        // IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);
        return dao.doDeleteBatch(ids);
    }

    @Override
    public Member get(String id) throws Exception {
        // IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);
        return dao.findById(id);
    }

    @Override
    public List<Member> getMembers() throws Exception {
        // IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);
        return dao.findAll();
    }

    @Override
    public Map<String, Object> getMembers(int pageIndex, int pageSize) throws Exception {
        // IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);
        Map<String, Object> map = new HashMap<>();
        map.put("members", dao.findByPage(pageIndex, pageSize));
        map.put("count", dao.getAllCount());
        return map;
    }

    @Override
    public Map<String, Object> getMembers(String column, String keyWord, int pageIndex, int pageSize) throws Exception {
        // IMemberDAO dao = DAOFactory.getInstance(MemberDAO.class);
        Map<String, Object> map = new HashMap<>();
        if (column == null || keyWord == null || "".equals(keyWord)) {
            map.put("members", dao.findByPage(pageIndex, pageSize));
            map.put("count", dao.getAllCount());
        } else {
            map.put("members", dao.findByPage(column, keyWord, pageIndex, pageSize));
            map.put("count", dao.getAllCount(column, keyWord));
        }
        return map;
    }
}
