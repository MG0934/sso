package top.mooxy.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import top.mooxy.core.conf.MappingParams;
import top.mooxy.core.conf.RedirectParams;
import top.mooxy.core.domain.User;
import top.mooxy.core.exception.SsoException;
import top.mooxy.core.utils.MD5Utils;
import top.mooxy.core.utils.SsoLoginHelper;
import top.mooxy.server.domain.UserInfo;
import top.mooxy.server.service.UserInfoService;

/**
 * @author Mxy
 * @time 2018年5月25日 上午11:11:32
 * @description: web登录控制器
 */
@Controller
@RequestMapping(MappingParams.WEB)
public class WebReqController {
	
	@Autowired
	private  UserInfoService userInfoService;
	
	@RequestMapping(MappingParams.INDEX)
	public String index(Model model, HttpServletRequest request) {
		
		User user = (User) request.getAttribute("user");
		
        if (user == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", user);
            return "index";
        }
        
	}

	/**
	 * 获取登录页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(MappingParams.LOGIN)
	public String login(Model model, HttpServletRequest request) {
		
		model.addAttribute("errorMsg", request.getParameter("errorMsg"));
		
		model.addAttribute(RedirectParams.REDIRECT_URL, request.getParameter(RedirectParams.REDIRECT_URL));
		
		return "login";
	}

	/**
	 * 登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param redirect_url
	 *            重定向路径
	 * @return
	 */
	@RequestMapping(MappingParams.DOLOGIN)
	public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {

		String errorMsg = null;

		UserInfo existUser = null;
		
		try {
			if (StringUtils.isBlank(username)) {
				throw new SsoException("用户名不能为空！");
			}
			if (StringUtils.isBlank(password)) {
				throw new SsoException("密码不能为空！");
			}
			existUser = userInfoService.findByusername(username);
			
			if (existUser == null) {
				throw new SsoException("用户不存在！");
			}
			
			password = MD5Utils.encrypt(username, password);
			
			if (!existUser.getPassword().equals(password)) {
				throw new SsoException("用户名或密码错误！");
			}
		} catch (SsoException e) {
			errorMsg = e.getMessage();
		}

		if (StringUtils.isNotBlank(errorMsg)) {
			redirectAttributes.addAttribute("errorMsg", errorMsg);
			redirectAttributes.addAttribute(RedirectParams.REDIRECT_URL, request.getParameter(RedirectParams.REDIRECT_URL));
			return "redirect:"+RedirectParams.LOGIN;
		}

		//登录成功的话
        User user = new User();
        user.setId(existUser.getId());
        user.setUsername(existUser.getUsername());
        //对密码进行处理
        user.setPassword(existUser.getPassword());

        SsoLoginHelper.login(response, user);

        // success redirect
        String redirectUrl = request.getParameter(RedirectParams.REDIRECT_URL);
        
        if (StringUtils.isNotBlank(redirectUrl)) {
            return "redirect:" + redirectUrl;
        } else {
            return "redirect:"+RedirectParams.INDEX;
        }
	}
	
	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping(MappingParams.LOGINOUT)
	public String loginOut(HttpServletRequest request,HttpServletResponse response) {
	
        SsoLoginHelper.logout(request, response);
        
        String redirect_url = request.getParameter(RedirectParams.REDIRECT_URL);
        
        if(StringUtils.isNotBlank(redirect_url)){
        	
        	return "redirect:"+RedirectParams.LOGIN+"?"+RedirectParams.REDIRECT_URL+"="+redirect_url;
        }
		
        return "redirect:"+RedirectParams.LOGIN;
	}
}
