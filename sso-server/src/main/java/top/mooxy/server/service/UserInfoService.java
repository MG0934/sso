package top.mooxy.server.service;

import top.mooxy.server.domain.UserInfo;

/**
* @author Mxy
* @time 2018年5月28日 上午10:41:36
* @description: 用户信息业务层
*/
public interface UserInfoService {

	UserInfo findByusername(String username);
	
	UserInfo findByid(String id);
}
