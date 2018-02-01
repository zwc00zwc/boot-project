package jobs.config;

/**
 * Created by alan.zheng on 2018/2/1.
 */
public class WeiXinToken {
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String _token) {
        token = _token;
    }
}
