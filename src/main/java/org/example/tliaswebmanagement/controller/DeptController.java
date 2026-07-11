package org.example.tliaswebmanagement.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Dept;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 * 负责接收前端发送的部门相关HTTP请求，调用Service层处理业务逻辑，并返回JSON格式的响应数据
 *
 * @RestController = @Controller + @ResponseBody
 * 表示该类是一个REST风格的控制器，所有方法的返回值会自动转换为JSON格式写入响应体
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //"凡是 URL 以 /depts 开头的 HTTP 请求，统统交给这个 Controller 处理"
    //@RequestMapping("/depts",method = RequestMethod.GET)//括号里的参数：URL 路径 = "/depts"
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        //调用service删除部门
        deptService.delete(id);

        return Result.success();
    }


    //新增部门
    @PostMapping
    public Result add(@Valid @RequestBody Dept dept){

        log.info("新增部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据ID查询部门
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询部门:{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    public Result update(@Valid @RequestBody Dept dept){
        log.info("修改部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
