package top.mooxy.core.enums;

/**
* @author Mxy
* @time 2018年5月25日 下午2:53:45
* @description: 
*/
public enum CookieEnum {
	
	TOKENKEY("sso_token_key","单点登录认证key");
	
	private String content;
	
	@SuppressWarnings("unused")
	private String desc;

	CookieEnum(String content, String desc) {
		this.content = content;
		this.desc = desc;
	}

	public String getContent() {
		return content;
	}
}
