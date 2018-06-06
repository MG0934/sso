package top.mooxy.core.conf;

/**
* @author Mxy
* @time 2018年5月25日 下午4:43:16
* @description: 
*/
public class RedirectParams {
	/**
	 * 重定向登录地址
	 */
	public static final String LOGIN = "/web/login";
	
	/**
	 * 重定向登录地址
	 */
	public static final String LOGOUT = "/web/sysLogOut";
	
	/**
	 * 登录
	 */
	public static final String DOLOGIN = "/web/doLogin";
	
	/**
	 * 重定向到首页
	 */
	public static final String INDEX = "/web/";
	
	/**
	 * web续租参数
	 */
	public static final String RELET="/web/relet?diviceType=pwd&token=";
	
	/**
	 * 重定向参数
	 */
	public static final String REDIRECT_URL = "redirect_url";
	
	/**
	 * cookie名
	 */
	public static final String SSO_SESSIONID = "sso_sessionid";
}
