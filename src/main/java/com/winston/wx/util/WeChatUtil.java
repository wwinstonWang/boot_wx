package com.winston.wx.util;


import java.security.MessageDigest;

public class WeChatUtil {
    public static final String TOKEN  = "winston";

    public static final String appID = "wx91906c255d94a295";
    public static final String appsecret = "3df989691782ef2955a833ff0b7e756d";

    //创建菜单接口地址
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";


    // token 接口(GET)
    public static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    // 素材上传(POST)URL
    private static final String UPLOAD_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/upload";
    // 素材下载:不支持视频文件的下载(GET)
    private static final String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

    //网页授权部分
    public static final String WEB_ACCESSTOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appID+"&secret=SECRET&code=%S&grant_type=authorization_code";
    public static final String WEB_REFRESHTOKEN_URL="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+appID+"&grant_type=refresh_token&refresh_token=%s";
    public static final String WEB_GETUSERINFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    //客服接口
    public static final String CUSTOMER_ADD_URL="https://api.weixin.qq.com/customservice/kfaccount/add?access_token=%s";
    public static final String CUSTOMER_MODIFY_URL="https://api.weixin.qq.com/customservice/kfaccount/update?access_token=%s";
    public static final String CUSTOMER_DEL_URL="https://api.weixin.qq.com/customservice/kfaccount/del?access_token=%s";
    public static final String CUSTOMER_UPLOADHEADIMG_URL="http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=%s&kf_account=%s";
    public static final String CUSTOMER_GET_URL="https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=%s";

    public static String getSha1(String str) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }


}
