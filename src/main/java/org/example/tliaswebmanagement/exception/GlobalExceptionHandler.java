package org.example.tliaswebmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 *
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * 用于全局捕获 Controller 层抛出的异常，统一返回 Result 格式的 JSON 响应，
 * 避免因未处理的异常导致前端收到不友好的错误页面。
 *
 * 异常处理优先级：越具体的异常越先匹配
 *   BusinessException → MethodArgumentNotValidException → ConstraintViolationException → Exception
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     * 业务逻辑中主动抛出，如：throw new BusinessException("部门名称已存在")
     */
    @ExceptionHandler(BusinessException.class)
    public Result businessException(BusinessException ex) {
        log.error("业务异常：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获 @Valid @RequestBody 参数校验失败异常
     * 当实体类上的 @NotBlank/@NotNull 等注解校验不通过时抛出
     * 提取第一个错误字段的 message 返回给前端
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "参数校验失败";
        log.error("参数校验失败：{}", message);
        return Result.error(message);
    }

    /**
     * 捕获 @RequestParam / @PathVariable 参数校验失败异常
     * 需要在 Controller 类上加 @Validated 才会触发
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationException(ConstraintViolationException ex) {
        log.error("参数校验失败：{}", ex.getMessage());
        return Result.error("参数校验失败：" + ex.getMessage());
    }

    /**
     * 兜底：捕获所有未处理的异常
     * 不暴露具体错误细节给前端，防止敏感信息泄露
     */
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex) {
        log.error("系统异常：", ex);
        return Result.error("系统异常，请联系管理员");
    }

}
