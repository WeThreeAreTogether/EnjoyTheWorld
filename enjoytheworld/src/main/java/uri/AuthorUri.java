package uri;

/**
 * Created by YanTi on 2016/7/25.
 */
public class AuthorUri {
    /**
     * 头
     */
    public static String HEAD="http://baobab.wandoujia.com/api/";
    /**
     * 尾
     */
    public static String TAIL="udid=d5c5ee1d70a64c7093dd616bda3a557ab483f172&vc=121&vn=2.3.5&deviceModel=Custom%20Phone%20-%205.0.0%20-%20API%2021%20-%20768x1280&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market&system_version_code=21";
    /**
     * 第一次进入首页
     */
    public static String FIRST="v3/tabs/pgcs?";
    /**
     * %d：header下的id
     * %s： 按时间排序--date
     *     按分享排序--shareCount
     */
    public static String ITEM="v3/pgc/videos?pgcId=%d&strategy=%s&";
//    public static String HEAD="";
//    public static String HEAD="";
//    public static String HEAD="";
}
