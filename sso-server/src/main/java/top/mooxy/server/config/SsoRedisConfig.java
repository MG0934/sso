package top.mooxy.server.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import top.mooxy.core.utils.JedisUtil;

/**
 * @author xuxueli 2018-04-03 20:41:07
 */
@Configuration
public class SsoRedisConfig implements InitializingBean {

    @Value("${redis.address}")
    private String redisAddress;

    @Override
    public void afterPropertiesSet() throws Exception {
        JedisUtil.init(redisAddress);
    }

}
