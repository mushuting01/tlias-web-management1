package org.example.tliaswebmanagement.pojo;

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
    private Integer id;             // 部门ID，主键，自增
    private String name;            // 部门名称（如：学工部、教研部、咨询部等）
    private LocalDateTime createTime; // 记录创建时间
    private LocalDateTime updateTime; // 记录最后修改时间
}

