package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tliaswebmanagement.pojo.Dept;

import java.util.List;

/**
 * 部门数据访问层（Mapper接口）
 * 使用MyBatis操作数据库中部门（dept）表的数据
 *
 * @Mapper 注解：将该接口标记为MyBatis的Mapper接口，
 * Spring会为它生成代理实现类并注入到IOC容器中，
 * 运行时通过动态代理自动执行对应的SQL语句
 */
@Mapper
public interface DeptMapper {

    //查询全部部门数据
    @Select("select * from dept")
    List<Dept> list();

    //根据ID删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteByID(Integer id);

    //新增部门
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    //根据ID查询部门
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);

    //修改部门
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
