package top.mooxy.core.utils;

import top.mooxy.core.enums.ResultEnum;

/**
 * @author Mxy
 * @param <T>
 * @time 2018年5月28日 上午9:25:08
 * @description: 统一结果处理
 */
public class Result<T> {
	
	// 错误码
	private Integer code;
	
	// 提示信息
	private String msg;
	
	// 具体的内容
	private T data;

	public Result() {
		super();
	}

	public Result(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	// 省略getter和setter方法
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * 成功
	 * 
	 * @param data
	 * @return
	 */
	public static<T> Result<T> success(T data){
		return new Result<T>(ResultEnum.SUCCESS.getStatus(), ResultEnum.SUCCESS.getDescription(), data);
	}
	
	/**
	 * 失败
	 * 
	 * @param data
	 * @return
	 */
	public static<T> Result<T> error(T data){
		return new Result<T>(ResultEnum.ERROR.getStatus(), ResultEnum.ERROR.getDescription(), data);
	}
	
	/**
	 * 异常
	 * 
	 * @param result
	 * @param data
	 * @return
	 */
	
	public static<T> Result<T> exception(ResultEnum result , T data){
		return new Result<T>(result.getStatus(),result.getDescription(), data);
	}
}
