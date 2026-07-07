package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.Dept;

import java.util.List;

/**
 * 部门业务逻辑接口
 * 定义部门相关的业务操作方法规范，由DeptServiceImpl提供具体实现
 *
 * 面向接口编程的优势：
 *   1. 降低耦合度：Controller只需依赖接口，不关心具体实现
 *   2. 便于扩展：可以通过不同的实现类实现不同的业务逻辑
 *   3. 便于测试：可以轻松使用Mock实现进行单元测试
 */
public interface DeptService {
    //查询全部部门数据
    List<Dept> list();


    //根据ID删除部门
    void delete(Integer id);


    //新增部门
    void add(Dept dept);

    //根据ID查询部门
    Dept getById(Integer id);

    //修改部门
    void update(Dept dept);
}
