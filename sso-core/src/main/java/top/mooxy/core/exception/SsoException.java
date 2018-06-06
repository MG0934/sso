package top.mooxy.core.exception;

/**
* @author Mxy
* @time 2018年5月28日 上午10:31:17
* @description: 异常处理
*/
public class SsoException extends RuntimeException{
	
    private static final long serialVersionUID = 42L;

    public SsoException(String msg) {
        super(msg);
    }

    public SsoException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SsoException(Throwable cause) {
        super(cause);
    }
}
