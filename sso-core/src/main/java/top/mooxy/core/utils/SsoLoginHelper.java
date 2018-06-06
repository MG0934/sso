package top.mooxy.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import top.mooxy.core.domain.User;
import top.mooxy.core.enums.CookieEnum;
import top.mooxy.core.store.SsoLoginStore;

/**
* @author Mxy
* @time 2018年5月25日 上午11:38:34
* @description: 完成登录相关处理
*/
public class SsoLoginHelper {
	
	private static final String KEY = "top.mooxy.buchang";

	//登录密码进行加密
	public static String encryptPassword(String password){
		
		return Encrypt.md5(Encrypt.sha(password)+Encrypt.md5(KEY));
		
	}
	
	//进行登录
    public static String login(HttpServletResponse response,
                             User user) {
    	//jwt字符串
    	String jwt = null;
    	
    	//复制加密后的密码
    	String encryptPassword = encryptPassword(user.getPassword());

    	Claims claims = Jwts.claims();
    	
    	claims.put("ID", user.getId());
    	
    	claims.put("AUTH", encryptPassword);

    	jwt = JWTUtil.createJwt(claims);
    	
    	user.setPassword(encryptPassword);
    	
    	//保存在user
    	SsoLoginStore.put(jwt, user);
    	
        //保存到cookie
        CookieUtil.set(response, CookieEnum.TOKENKEY.getContent(), jwt, false);
        
        return jwt;
    }
    
    //登出
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		
		SsoLoginStore.remove(CookieUtil.getValue(request, CookieEnum.TOKENKEY.getContent()));
		
        CookieUtil.remove(request, response, CookieEnum.TOKENKEY.getContent());
	}
}
