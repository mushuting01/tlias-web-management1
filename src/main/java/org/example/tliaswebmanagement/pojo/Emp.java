package org.example.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工实体类
 * 对应数据库中的员工（emp）表，用于封装员工数据
 *
 * Lombok注解说明：
 *   @Data               - 自动生成 getter/setter、toString、equals、hashCode 方法
 *   @NoArgsConstructor  - 自动生成无参构造方法
 *   @AllArgsConstructor - 自动生成全参构造方法
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;             // 员工ID，主键，自增
    private String username;        // 登录用户名，唯一
    private String password;        // 登录密码
    private String name;            // 员工真实姓名
    private Short gender;           // 性别：1-男，2-女
    private String image;           // 员工头像图片的URL地址
    private Short job;              // 职位：1-班主任，2-讲师，3-学工主管，4-教研主管，5-咨询师
    private LocalDate entrydate;    // 入职日期
    private Integer deptId;         // 所属部门ID，外键关联dept表的id
    private LocalDateTime createTime; // 记录创建时间
    private LocalDateTime updateTime; // 记录最后修改时间
}

