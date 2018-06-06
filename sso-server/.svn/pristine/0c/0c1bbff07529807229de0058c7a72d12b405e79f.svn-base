package top.mooxy.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.mooxy.server.dao.UserInfoRepository;
import top.mooxy.server.domain.UserInfo;
import top.mooxy.server.service.UserInfoService;

/**
* @author Mxy
* @time 2018年5月28日 上午10:42:11
* @description: 
*/
@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo findByusername(String username) {
		return userInfoRepository.findByusername(username);
	}

	@Override
	public UserInfo findByid(String id) {
		return userInfoRepository.findByid(id);
	}
	
}
