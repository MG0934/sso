package top.mooxy.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.mooxy.core.exception.SsoException;

/**
* @author Mxy
* @time 2018年5月25日 上午11:44:42
* @description: 校验基础过滤器
*/
public class BaseCheckFilter  implements Filter{
	
	public static final Logger logger = LoggerFactory.getLogger(BaseCheckFilter.class);
	
	public String ssoServer ;
	
	public String isClient;
	
	public String ssoClient;
	
	public HttpServletRequest servletRequest = null;
	
	public HttpServletResponse servletResponse = null;
	
	public boolean returnStatus = false;
	
	//获取请求的路径
    String uri = null;
	//获取请求的方法
    String url = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		isClient = filterConfig.getInitParameter("is_client");
		
		ssoServer = filterConfig.getInitParameter("sso_server");
		
		ssoClient = filterConfig.getInitParameter("sso_client");
		
		if(isClient==null || "".equals(isClient) || (!"false".equals(isClient) && !"true".equals(isClient))){
			throw new SsoException("isClient配置错误，请在用户端使用true，服务端使用false！");
		}
		
	}




	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		servletRequest = (HttpServletRequest)request;
		
		servletResponse = (HttpServletResponse)response;
		
		//获取请求的路径
        uri = servletRequest.getRequestURI();
		//获取请求的方法
        url = servletRequest.getRequestURL().toString(); 
        
        //判断是否是login方法和doLogin方法，进行放行
        
        if(uri.startsWith("/ssos/static/")){
        	chain.doFilter(request, response);
        	return;
        }
        
        if(uri.contains("/ssos/web/sysLogOut")){
        	chain.doFilter(request, response);
        	returnStatus=true;
        }

        if(uri.contains("/ssos/web/login")){
        	chain.doFilter(request, response);
        	returnStatus=true;
        }
        
        if(uri.contains("/ssos/web/doLogin")){
        	chain.doFilter(request, response);
        	returnStatus=true;
        }
	}
}
