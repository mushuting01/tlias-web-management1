package org.example.tliaswebmanagement.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 分页查询结果的封装类
 * 用于统一前后端分页数据交互格式
 *
 * 前端收到的JSON示例：
 * {
 *   "code": 1,
 *   "msg": "success",
 *   "data": {
 *     "total": 100,          ← 总记录数，前端用来算共有多少页
 *     "rows": [              ← 当前页的数据列表
 *       { "id": 1, "name": "张三", ... },
 *       { "id": 2, "name": "李四", ... }
 *     ]
 *   }
 * }
 *
 * Lombok注解说明：
 *   @Data   - 自动生成 getter/setter、toString、equals、hashCode
 *   @NoArgsConstructor  - 无参构造（Spring等框架反序列化时需要）
 *   @AllArgsConstructor - 全参构造（new PageBean(count, empList) 时使用）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    private Long total; // 总记录数（数据库里emp表一共有多少条）
    private List rows;  // 当前页的数据列表（List<Emp>，泛型以实际运行时为准）

}
