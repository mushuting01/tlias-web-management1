package org.example.tliaswebmanagement.service.impl;

import org.example.tliaswebmanagement.mapper.DeptMapper;
import org.example.tliaswebmanagement.mapper.EmpMapper;
import org.example.tliaswebmanagement.pojo.Dept;
import org.example.tliaswebmanagement.pojo.DeptLog;
import org.example.tliaswebmanagement.service.DeptLogService;
import org.example.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门业务逻辑实现类
 * 实现DeptService接口中定义的所有方法，处理部门相关的业务逻辑
 *
 * 调用链：Controller → DeptService（接口） → DeptServiceImpl（实现） → DeptMapper（数据访问）
 *
 * @Service 注解：将该类标记为Spring的Service组件，
 * Spring会自动创建该类的实例并注入到IOC容器中管理，
 * 当Controller需要DeptService时，Spring会自动注入DeptServiceImpl实例
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }


    //@Transactional(rollbackFor = Exception.class)//spring事务管理
    @Transactional
    @Override
    public void delete(Integer id) {
        try{
            deptMapper.deleteByID(id);//根据ID删除部门数据

            empMapper.deleteByDeptId(id);//根据部门ID删除该部门下的员工
        }finally{
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作,此次解散的是" + id + "号部门");
            deptService.insert(deptLog);

        }




    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);

        // 记录操作日志
        DeptLog deptLog = new DeptLog();
        deptLog.setCreateTime(LocalDateTime.now());
        deptLog.setDescription("新增了部门：" + dept.getName());
        deptService.insert(deptLog);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);

        // 记录操作日志
        DeptLog deptLog = new DeptLog();
        deptLog.setCreateTime(LocalDateTime.now());
        deptLog.setDescription("修改了" + dept.getId() + "号部门，新名称为：" + dept.getName());
        deptService.insert(deptLog);
    }
}
