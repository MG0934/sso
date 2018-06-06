package top.mooxy.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import top.mooxy.server.domain.UserInfo;

/**
* @author Mxy
* @time 2018年5月28日 上午10:33:06
* @description: 
*/
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
	
	UserInfo findByusername(String username);

	UserInfo findByid(String id);
}
