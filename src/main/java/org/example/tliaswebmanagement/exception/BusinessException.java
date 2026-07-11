package org.example.tliaswebmanagement.exception;


// 自定义业务异常
public class BusinessException extends RuntimeException {

    // 错误码
    private Integer code;

    // 错误信息
    private String message;

    // 构造方法：只传错误信息
    public BusinessException(String message) {
        super(message);
        this.code = 0;
        this.message = message;
    }

    // 构造方法：传错误码和错误信息
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    // getter方法
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}