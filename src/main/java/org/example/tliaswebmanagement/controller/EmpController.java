package org.example.tliaswebmanagement.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.PageBean;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 * 负责接收前端发送的员工相关HTTP请求，调用Service层处理业务逻辑，并返回JSON格式的响应数据
 *
 * @RestController = @Controller + @ResponseBody
 * 表示该类是一个REST风格的控制器，所有方法的返回值会自动转换为JSON格式写入响应体
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService; // Spring自动注入EmployeeService实现类

    /**
     * 员工分页查询接口
     * 请求示例：GET /emps?page=1&pageSize=10  → 查询第1页，每页10条
     *
     * @param page     页码（从1开始），不传默认为第1页
     * @param pageSize 每页显示的记录数，不传默认为10条
     * @return Result.success(pageBean)，包含总记录数和当前页数据列表
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")  Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        // 记录日志，便于调试时查看前端传来的分页参数
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,name,gender,begin,end);
        // 调用Service层获取分页结果，Service会返回封装好的PageBean对象
        PageBean pageBean = empService.page(page, pageSize,name,gender,begin,end);
        // 将分页结果包装为统一响应格式返回给前端
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result save(@Valid @RequestBody Emp emp){//RequestBody是把 HTTP 请求体中的 JSON 数据 自动转换成 Java 对象。

        log.info("新增员工，emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }


    @PutMapping
    public Result update(@Valid @RequestBody Emp emp){//@Valid 的作用就是： 告诉 Spring，对这个参数进行校验！
        // Spring 会自动检查 emp 对象的字段
        // 如果 @NotBlank 校验失败，会抛出 MethodArgumentNotValidException
        // 被 GlobalExceptionHandler 捕获，返回友好的错误信息
        log.info("更新员工信息emp:{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
