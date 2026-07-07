package org.example.tliaswebmanagement.exception;


import org.example.tliaswebmanagement.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * 用于全局捕获 Controller 层抛出的异常，统一返回 Result 格式的 JSON 响应，
 * 避免因未处理的异常导致前端收到不友好的错误页面。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获所有类型的异常（Exception 是所有异常的父类）
     *
     * @param ex 被捕获的异常对象
     * @return 统一的错误响应 Result
     *
     * 说明：
     * 1. ex.printStackTrace() 会在控制台打印完整堆栈信息，方便开发调试
     * 2. 返回给前端的 msg 是通用提示，不暴露具体错误细节，防止敏感信息泄露
     * 3. 后期可以根据异常类型细化处理，如捕获 SQLException、NullPointerException 等
     */
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex) {
        // 打印异常堆栈到控制台，便于开发排查
        ex.printStackTrace();
        // 返回统一格式的错误信息，不暴露具体异常内容给前端
        return Result.error("对不起，操作失败，请联系管理员");
    }

}
