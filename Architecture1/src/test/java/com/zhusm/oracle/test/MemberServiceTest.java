package com.zhusm.oracle.test;

import com.zhusm.oracle.factory.ServiceFactory;
import com.zhusm.oracle.service.IMemberService;
import com.zhusm.oracle.service.impl.MemberService;
import com.zhusm.oracle.vo.Member;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberServiceTest {

    private static String mid;
    private static String phone;

    static {
        Random random = new Random();
        int n = random.nextInt(1000);
        mid = "testID-" + n;
        phone = "testPhone-" + n;
    }

    @Test
    public void test1Add() {
        IMemberService service = ServiceFactory.getInstance(MemberService.class);
        Member vo = new Member();
        vo.setMid(mid);
        vo.setName("某人");
        vo.setPhone(phone);
        vo.setAge(48);
        vo.setBirthday(new Date());
        vo.setNote("中国老好人");
        try {
            assertTrue(service.add(vo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2Edit() {
        IMemberService service = ServiceFactory.getInstance(MemberService.class);
        Member vo = new Member();
        vo.setMid(mid);
        vo.setName("张三");
        vo.setPhone(phone);
        vo.setAge(35);
        vo.setBirthday(new Date());
        vo.setNote("坏人一个");
        try {
            assertTrue(service.edit(vo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7Remove() {
        IMemberService service = ServiceFactory.getInstance(MemberService.class);
        try {
            Set<String> sets = new HashSet<>();
            sets.add(mid);
            assertTrue(service.remove(sets));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3Get() {
        IMemberService service = ServiceFactory.getInstance(MemberService.class);
        try {
            assertNotNull(service.get(mid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4GetMembersAll() {
        IMemberService service = ServiceFactory.getInstance(MemberService.class);
        try {
            assertTrue(service.getMembers().size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5GetMembersByPage() {
        IMemberService service = ServiceFactory.getInstance(MemberService.class);
        try {
            Map<String, Object> map = service.getMembers(1, 10);
            long count = (Long) map.get("count");
            List<Member> members = (List<Member>) map.get("members");
            assertTrue(count > 0 && members.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6GetMembersByKeyword() {
        IMemberService service = ServiceFactory.getInstance(MemberService.class);
        try {
            Map<String, Object> map = service.getMembers("name", "三", 1, 10);
            long count = (Long) map.get("count");
            List<Member> members = (List<Member>) map.get("members");
            assertTrue(count > 0 && members.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}