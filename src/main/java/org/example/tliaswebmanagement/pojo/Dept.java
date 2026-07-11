package org.example.tliaswebmanagement.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 部门实体类
 * 对应数据库中的部门（dept）表，用于封装部门数据
 *
 * Lombok注解说明：
 *   @Data               - 自动生成 getter/setter、toString、equals、hashCode 方法
 *   @NoArgsConstructor  - 自动生成无参构造方法
 *   @AllArgsConstructor - 自动生成全参构造方法
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 2, max = 50, message = "部门名称长度必须在2-50之间")
    private String name;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

