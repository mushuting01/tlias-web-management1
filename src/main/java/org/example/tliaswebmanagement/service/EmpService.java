package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工业务逻辑接口
 * 定义员工相关的业务操作方法规范，由EmpServiceImpl提供具体实现
 *
 * 面向接口编程的优势：
 *   1. 降低耦合度：Controller只需依赖接口，不关心具体实现
 *   2. 便于扩展：可以通过不同的实现类实现不同的业务逻辑
 *   3. 便于测试：可以轻松使用Mock实现进行单元测试
 */
public interface EmpService {
    /**
     * 分页查询员工数据
     *
     * @param page     当前页码（从1开始）
     * @param pageSize 每页条数
     * @return PageBean 包含总记录数(total)和当前页数据列表(rows)
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);


    //新增员工
    void save(Emp emp);


    //根据id查询员工
    Emp getById(Integer id);


    //更新员工
    void update(Emp emp);

    //实现员工登录
    Emp login(Emp emp);
}

