package org.example.tliaswebmanagement.pojo;


import jakarta.validation.constraints.NotNull;  // ✅ 这是参数校验的注解
import jakarta.validation.constraints.NotBlank;  // ✅ 非空字符串校验
import jakarta.validation.constraints.Size;      // ✅ 长度校验
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
    private Integer id;

    @NotBlank(message = "用户名不能为空")        // 非空且不为空白
    @Size(min = 2, max = 20, message = "用户名长度必须在2-20之间")  // 长度限制
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 50, message = "密码长度必须在6-50之间")
    private String password;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    private Short gender;

    private String image;

    @NotNull(message = "职位不能为空")
    private Short job;

    @NotNull(message = "入职日期不能为空")
    private LocalDate entrydate;

    @NotNull(message = "部门ID不能为空")
    private Integer deptId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

