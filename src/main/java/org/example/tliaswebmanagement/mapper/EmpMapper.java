package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tliaswebmanagement.pojo.Emp;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工数据访问层（Mapper接口）
 * 使用MyBatis操作数据库中员工（emp）表的数据
 *
 * @Mapper 注解：将该接口标记为MyBatis的Mapper接口，
 * Spring会为它生成代理实现类并注入到IOC容器中，
 * 运行时通过动态代理自动执行对应的SQL语句
 */
@Mapper
public interface EmpMapper {

    // ==================== 原手动分页方式（已注释，改用PageHelper插件） ====================

    //    /**
    //     * 查询员工总记录数
    //     * SQL: SELECT COUNT(*) FROM emp
    //     * 返回emp表中所有记录的总数，用于前端计算总页数
    //     *
    //     * @return 员工总条数
    //     */
    //    @Select("select count(*) from emp")
    //    public Long count();
    //
    //    /**
    //     * 分页查询员工列表
    //     * SQL: SELECT * FROM emp LIMIT start, pageSize
    //     *
    //     * MySQL LIMIT语法：LIMIT 偏移量, 条数
    //     *   偏移量(start)：从第几条开始取（从0开始计数）
    //     *   条数(pageSize)：一次取多少条
    //     *   例如：LIMIT 0,10 → 第1~10条  LIMIT 10,10 → 第11~20条
    //     *
    //     * MyBatis会自动将方法参数与SQL中的 #{参数名} 绑定：
    //     *   #{start}     → start参数的值（偏移量）
    //     *   #{pageSize}  → pageSize参数的值（每页条数）
    //     *
    //     * @param start    查询起始位置（偏移量 = (页码-1) × 每页条数）
    //     * @param pageSize 每页显示条数
    //     * @return 当前页的员工数据列表
    //     */
    //    @Select("select * from emp limit #{start},#{pageSize}")
    //    public List<Emp> page(Integer start, Integer pageSize);

    // ==================== PageHelper 分页方式（新） ====================

    /**
     * 查询全部员工（不含分页，分页由 PageHelper 拦截器自动处理）
     *
     * PageHelper 工作原理：
     *   1. Service层调用 PageHelper.startPage(page, pageSize) 设置分页参数
     *   2. PageHelper 通过 MyBatis 拦截器拦截本条 SQL
     *   3. 自动将 "select * from emp" 追加为 "... LIMIT offset, pageSize"
     *   4. 同时自动执行 "select count(*)" 获取总记录数
     *   5. 结果封装为 Page 对象（继承 ArrayList，自带分页信息）
     *
     *   调用前必须执行：PageHelper.startPage(页码, 每页条数)
     *
     * @return 全部员工列表（实际运行时被 PageHelper 截断为当前页数据）
     */
    //@Select("select * from emp ")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


    //批量删除操作
    void delete(List<Integer> ids);


    //新增员工
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            " values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    //根据id查询员工
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);


    //更新员工，使用动态SQL
    void update(Emp emp);


    //根据用户名和密码查询员工
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);



    //根据部门ID删除该部门下的员工
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}

