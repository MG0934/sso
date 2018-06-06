package top.mooxy.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import top.mooxy.core.domain.User;
import top.mooxy.core.enums.JWTCheckEnum;
import top.mooxy.core.store.SsoLoginStore;

/**
* @author Mxy
* @time 2018年5月29日 上午10:18:27
* @description: 校验工具类
*/
public class CheckUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckUtil.class);
	
	private static final String CHECKID = "ID";
	
	private static final String CHECKPWD = "AUTH";
	
	/**
	 * 校验JWT
	 * 
	 * @param jwt
	 * @return
	 */
	public static Integer checkJwt(HttpServletRequest request,HttpServletResponse response,String jwt,String isClient){
		
		JWTCheckEnum checkJwt = JWTUtil.checkJwt(jwt,isClient);
		
		User userFromRedis = SsoLoginStore.get(jwt);
		
		if(userFromRedis==null){
			logger.info("【登录状态检查】:账户异常：缓存用户不存在，重新登录！");
			return JWTCheckEnum.RELET.getCode();
			
		}
		
		//判断密码携带内容是否发生改变
		if(!checkPassword(jwt,userFromRedis)){
			logger.info("【登录状态检查】:账户异常：密码校验未通过，重新登录！");
			return JWTCheckEnum.EXPORT.getCode();
			
		}
		
		//正常
		if(checkJwt.getCode()==JWTCheckEnum.NORMAL.getCode()){			
			return JWTCheckEnum.NORMAL.getCode();
		}
		
		//需要续期
		if(checkJwt.getCode()==JWTCheckEnum.RELET.getCode()){
			
			logger.info("【登录状态检查】:账户即将逾期，进行续租！");
			
			SsoLoginHelper.logout(request, response);
			
			String newtoken = SsoLoginHelper.login(response, userFromRedis);
			
			request.setAttribute("newToken",newtoken);
			
			logger.info("【登录状态检查】:账户即将逾期，续租完成！");
			
			return JWTCheckEnum.RELET.getCode();
		}
		
		//超时，清除旧数据，进行重新签名
		if(checkJwt.getCode()==JWTCheckEnum.EXPORT.getCode()){
			
			SsoLoginHelper.logout(request, response);
			logger.info("【登录状态检查】:账户超时，重新登录！");
			return JWTCheckEnum.EXPORT.getCode();
		}
		
		return JWTCheckEnum.EXPORT.getCode();
	}
	
	/**
	 * 携带加密pwd校验，如果发生改变存在恶意篡改或本地密码已发生变化，不可直接进行续期操作
	 * 
	 * @param jwt
	 * @param user
	 * @return
	 */
	public static boolean checkPassword(String jwt,User user){
		
		Claims parJwt = JWTUtil.ParJwt(jwt);
		
		if(parJwt==null){
			
			return false;
			
		}
		
		String id = (String) parJwt.get(CHECKID);
		
		String pwd = (String) parJwt.get(CHECKPWD);
		
		if(id!=null && !"".equals(id) && pwd!=null && !"".equals(pwd)){
			
			if(id.equals(user.getId()) && pwd.equals(user.getPassword())){
				return true;
			}
		}
			return false;
	}
}
