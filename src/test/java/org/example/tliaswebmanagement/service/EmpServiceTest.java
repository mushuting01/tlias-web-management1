package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.PageBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmpServiceTest {

    @Autowired
    private EmpService empService;

    @Test
    public void testPage() {
        PageBean pageBean = empService.page(1, 10, null, null, null, null);
        assertNotNull(pageBean);
        assertTrue(pageBean.getTotal() > 0);
    }

    @Test
    public void testGetById() {
        PageBean pageBean = empService.page(1, 10, null, null, null, null);
        assertTrue(pageBean.getRows().size() > 0);
        Emp first = (Emp) pageBean.getRows().get(0);
        Emp emp = empService.getById(first.getId());
        assertNotNull(emp);
        assertEquals(first.getId(), emp.getId());
    }

    @Test
    @Transactional
    public void testSave() {
        Emp emp = new Emp();
        emp.setUsername("test_" + System.currentTimeMillis());
        emp.setName("测试员工");
        emp.setPassword("123456");
        emp.setGender((short) 1);
        emp.setDeptId(7);
        emp.setEntrydate(LocalDate.now());
        empService.save(emp);

        Emp saved = empService.getById(emp.getId());
        assertNotNull(saved);
        assertEquals("测试员工", saved.getName());
    }

    @Test
    @Transactional
    public void testUpdate() {
        PageBean pageBean = empService.page(1, 10, null, null, null, null);
        assertTrue(pageBean.getRows().size() > 0);
        Emp first = (Emp) pageBean.getRows().get(0);
        String newName = "更新测试_" + System.currentTimeMillis();
        first.setName(newName);
        empService.update(first);
        Emp updated = empService.getById(first.getId());
        assertEquals(newName, updated.getName());
    }

    @Test
    public void testLoginSuccess() {
        PageBean pageBean = empService.page(1, 10, null, null, null, null);
        assertTrue(pageBean.getRows().size() > 0);
        Emp first = (Emp) pageBean.getRows().get(0);

        Emp loginEmp = new Emp();
        loginEmp.setUsername(first.getUsername());
        loginEmp.setPassword(first.getPassword());
        Emp result = empService.login(loginEmp);
        assertNotNull(result);
    }

    @Test
    public void testLoginFail() {
        Emp emp = new Emp();
        emp.setUsername("不存在的账号");
        emp.setPassword("错误密码");
        Emp result = empService.login(emp);
        assertNull(result);
    }

    @Test
    public void testGetByIdNotFound() {
        Emp emp = empService.getById(Integer.MAX_VALUE);
        assertNull(emp);
    }
}
