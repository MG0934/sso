package top.mooxy.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import top.mooxy.core.filter.BaseCheckFilter;
import top.mooxy.core.filter.WebCheckFilter;

/**
 * @author Mxy
 * @time 2018年5月25日 下午3:17:38
 * @description:
 */
@Configuration
public class BaseCheckFilterConfig {
	
	@Value("${sso.server}")
	private String ssoServer;
	
	@Value("${sso.isClient}")
	private String isClient;

	@Bean
	public FilterRegistrationBean BaseCheckFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new WebCheckFilter());
		registration.addInitParameter("is_client", isClient);
		registration.addInitParameter("sso_server", ssoServer);
		registration.setName("webCheckFilter");
		registration.setOrder(1);
		registration.addUrlPatterns("/web/*");
		return registration;
	}

}
