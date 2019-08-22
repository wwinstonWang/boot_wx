package com.winston.wx.service;

import com.winston.wx.util.Net;
import com.winston.wx.util.WeChatUtil;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 1 第一步：用户同意授权，获取code
 *  https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx91906c255d94a295&redirect_uri=http://2566d12z03.zicp.vip/Test&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
 *
 * 2 第二步：通过code换取网页授权access_token
 *
 * 3 第三步：刷新access_token（如果需要）
 *
 * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 *
 * 5 附：检验授权凭证（access_token）是否有效
 */
@Service
public class WebAuthorizeService {
    @Data
    public static class AccessToken{
        String access_token;
        Long expires_in;
        String refresh_token;
        String openid;
        String scope;
    }

    @Data
    public static class UserInfo{
        String openid;
        String nickname;
        Byte sex;
        String province;
        String city;
        String country;
        String headimgurl;
        String privilege;
        String unionid;
    }

    public AccessToken getAccessToken(Map<String,String> codeMap){
        if(!codeMap.containsKey("state") || !codeMap.get("state").equals("STATE"))
            return null;
        String code=codeMap.get("code");
        try {
            AccessToken accessToken = Net.getRestTemplate().getForObject(String.format(WeChatUtil.WEB_ACCESSTOKEN_URL, code), AccessToken.class);
            return accessToken;
        }catch (Exception e){
            return null;
        }
    }

    public AccessToken refreshToken(String refreshToken){
        try {
            AccessToken newToken = Net.getRestTemplate().getForObject(String.format(WeChatUtil.WEB_REFRESHTOKEN_URL, refreshToken), AccessToken.class);
            return newToken;
        }catch (Exception e){
            return null;
        }
    }


    public UserInfo getUserInfo(String accessToken,String openid){
        try {
            UserInfo userInfo = Net.getRestTemplate().getForObject(String.format(WeChatUtil.WEB_GETUSERINFO_URL, accessToken,openid), UserInfo.class);
            return userInfo;
        }catch (Exception e){
            return null;
        }
    }

}
