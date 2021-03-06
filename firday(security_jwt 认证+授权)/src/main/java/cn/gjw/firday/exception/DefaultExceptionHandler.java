package cn.gjw.firday.exception;

import cn.gjw.firday.result.ResponseCode;
import cn.gjw.firday.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Joetao
 * 异常处理类
 * controller层异常无法捕获处理，需要自己处理
 * Created at 2018/8/27.
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 处理所有自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Results handleCustomException(CustomException e) {
        System.out.println("handleCustomException");
        //log.error(e.getResultJson().getMsg().toString());
        return e.getResults();
    }

    /**
     * 处理参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Results handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println("MethodArgumentNotValidException");
//        log.error(e.getBindingResult().getFieldError().getField() + e.getBindingResult().getFieldError().getDefaultMessage());
        return Results.failure(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage());
    }
}
