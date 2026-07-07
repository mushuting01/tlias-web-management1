package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeptServiceTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void testList() {
        List<Dept> list = deptService.list();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetById() {
        List<Dept> list = deptService.list();
        assertTrue(list.size() > 0);
        Dept first = list.get(0);
        Dept dept = deptService.getById(first.getId());
        assertNotNull(dept);
        assertEquals(first.getId(), dept.getId());
    }

    @Test
    @Transactional
    public void testAdd() {
        List<Dept> before = deptService.list();
        Dept dept = new Dept();
        dept.setName("测试部门");
        deptService.add(dept);
        List<Dept> after = deptService.list();
        assertEquals(before.size() + 1, after.size());
    }

    @Test
    @Transactional
    public void testUpdate() {
        List<Dept> list = deptService.list();
        assertTrue(list.size() > 0);
        Dept first = list.get(0);
        String newName = "更新测试_" + System.currentTimeMillis();
        first.setName(newName);
        deptService.update(first);
        Dept updated = deptService.getById(first.getId());
        assertEquals(newName, updated.getName());
    }

    @Test
    public void testGetByIdNotFound() {
        Dept dept = deptService.getById(Integer.MAX_VALUE);
        assertNull(dept);
    }
}
