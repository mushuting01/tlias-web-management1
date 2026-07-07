package org.example.tliaswebmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.mapper.EmpMapper;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.PageBean;
import org.example.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工业务逻辑实现类
 * 实现EmpService接口中定义的所有方法，处理员工相关的业务逻辑
 *
 * 调用链：Controller → EmpService（接口） → EmpServiceImpl（实现） → EmpMapper（数据访问）
 *
 * @Service 注解：将该类标记为Spring的Service组件，
 * Spring会自动创建该类的实例并注入到IOC容器中管理，
 * 当Controller需要EmpService时，Spring会自动注入EmpServiceImpl实例
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper; // Spring自动注入Mapper代理对象

    // ==================== 原手动分页逻辑（已注释） ====================
    //    @Override
    //    public PageBean page(Integer page, Integer pageSize) {
    //        // 1. 查询总记录数：用于前端计算总页数
    //        Long count = empMapper.count();
    //
    //        // 2. 计算偏移量并执行分页查询
    //        //    start = (page - 1) * pageSize
    //        Integer start = (page - 1) * pageSize;
    //        List<Emp> empList = empMapper.page(start, pageSize);
    //
    //        // 3. 封装PageBean对象
    //        PageBean pageBean = new PageBean(count, empList);
    //        return pageBean;
    //    }

    // ==================== PageHelper 分页方式（新） ====================

    /**
     * 使用 PageHelper 插件实现分页查询
     *
     * PageHelper 核心用法（仅需 3 步）：
     *   1. PageHelper.startPage(page, pageSize) — 设置页码和每页条数
     *   2. empMapper.list() — 执行查询（SQL 里不需要写 LIMIT，PageHelper 自动追加）
     *   3. 将 Page 对象转为 PageBean — 提取总记录数和当前页数据
     *
     * Page 对象（com.github.pagehelper.Page）继承自 ArrayList：
     *   - page.getTotal()   → 总记录数
     *   - page.getResult()  → 当前页数据列表
     *   - page.getPages()   → 总页数
     *
     * @param page     当前页码（从1开始）
     * @param pageSize 每页条数
     * @return PageBean 包含总记录数和当前页数据列表
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // 1. 开启分页：PageHelper 会将 page 和 pageSize 存入 ThreadLocal
        //    后续的 MyBatis 查询会被拦截器自动追加 LIMIT 和 COUNT
        PageHelper.startPage(page, pageSize);

        // 2. 执行查询：Mapper 里的 SQL 是 "select * from emp"（无 LIMIT）
        //    PageHelper 拦截器会自动改成 "select * from emp LIMIT offset, pageSize"
        //    同时自动执行 COUNT 查询，结果封装到 Page 对象中
        List<Emp> empList = empMapper.list(name, gender, begin, end);

        // 3. 从 Page 对象中提取分页信息，封装为 PageBean
        //    Page 是 PageHelper 返回的特殊 List，强转后可以拿到分页信息
        com.github.pagehelper.Page<Emp> pageResult = (com.github.pagehelper.Page<Emp>) empList;
        PageBean pageBean = new PageBean(pageResult.getTotal(), pageResult.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());// 记录创建时间
        emp.setUpdateTime(LocalDateTime.now());// 记录修改时间
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}