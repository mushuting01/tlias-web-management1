package org.example.tliaswebmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 部门操作日志实体类
 * 用于记录部门相关操作（如解散部门）的日志信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private Integer id;             // 日志ID，主键，自增
    private LocalDateTime createTime; // 操作时间
    private String description;     // 操作描述
}
