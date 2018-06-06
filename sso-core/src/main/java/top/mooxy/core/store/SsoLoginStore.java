package top.mooxy.core.store;
import top.mooxy.core.conf.RedirectParams;
import top.mooxy.core.domain.User;
import top.mooxy.core.utils.JedisUtil;

/**
 * local login store
 *
 * @author mooxy
 */
public class SsoLoginStore {

    /**
     * get
     *
     * @param sessionId
     * @return
     */
    public static User get(String sessionId) {

        String redisKey = redisKey(sessionId);
        Object objectValue = JedisUtil.getObjectValue(redisKey);
        if (objectValue != null) {
        	User user = (User) objectValue;
            return user;
        }
        return null;
    }

    /**
     * remove
     *
     * @param sessionId
     */
    public static void remove(String sessionId) {
        String redisKey = redisKey(sessionId);
        JedisUtil.del(redisKey);
    }

    /**
     * put
     *
     * @param sessionId
     * @param xxlUser
     */
    public static void put(String sessionId, User user) {
        String redisKey = redisKey(sessionId);
        JedisUtil.setObjectValue(redisKey, user);
    }

    private static String redisKey(String sessionId){
        return RedirectParams.SSO_SESSIONID.concat("#").concat(sessionId);
    }

}
