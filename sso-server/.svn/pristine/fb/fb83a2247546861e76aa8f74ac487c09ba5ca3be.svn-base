package top.mooxy.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import top.mooxy.core.utils.Result;

/**
* @author Mxy
 * @param <T>
* @time 2018年5月28日 上午9:22:54
* @description: 全局异常处理器
*/
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = RuntimeException.class)  
	@ResponseBody
    public Result<Object> businessExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
		
		return Result.error("错误");  
    }  
	
}
