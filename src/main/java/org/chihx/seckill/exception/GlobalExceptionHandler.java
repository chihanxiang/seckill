package org.chihx.seckill.exception;

import org.chihx.seckill.Result.CodeMsg;
import org.chihx.seckill.Result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常拦截
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    // 拦截所有异常
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHander(HttpServletRequest req, Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException)e;
            return Result.Error(ex.getCm());
        } else if (e instanceof BindException) {
            BindException ex = (BindException)e;
            List<ObjectError> allErrors = ex.getAllErrors();
            ObjectError error = allErrors.get(0);
            String message = error.getDefaultMessage();
            return Result.Error(CodeMsg.BIND_EXCEPTION.fillArgs(message));
        } else {
            return Result.Error(CodeMsg.SERVER_ERROR);
        }
    }
}
