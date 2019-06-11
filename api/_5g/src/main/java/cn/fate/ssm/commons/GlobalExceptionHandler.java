package cn.fate.ssm.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器
 * @ControllerAdvice 对@Controller注解增强
 * @author admin
 * @date 2019-06-06 15:28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 异常处理器
     *
     * @ExceptionHandler(Exception.class) 针对某种异常的处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultData handlerException(Exception e) {
        log.error(e.toString());
        return ResultData.error();
    }
}
