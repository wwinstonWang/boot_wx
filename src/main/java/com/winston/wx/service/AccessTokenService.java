package com.winston.wx.service;

import com.winston.wx.entity.AccessToken;
import com.winston.wx.util.Net;
import com.winston.wx.util.WeChatUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

@Service
public class AccessTokenService implements CommandLineRunner {

    AccessToken accessToken;


    @Override
    public void run(String... args) throws Exception {
        Executors.newCachedThreadPool().execute(() -> {
            try {
                accessToken =refreshToken();
                //获取到access_token 休眠7000秒,大约2个小时左右
                Thread.sleep(7000 * 1000);
            }catch (Exception e){
                try {
                    Thread.sleep(1000 * 10); //发生异常休眠1秒
                } catch (Exception e1) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 创建菜单
     * @param menu
     */
    public String createMenu(String menu){
        String result= Net.getRestTemplate().postForEntity(String.format(WeChatUtil.CREATE_MENU_URL,accessToken.getAccess_token()),menu,String.class).getBody();
        System.out.println(result);
        return result;
    }

    /**
     * 刷新token
     */
    public AccessToken refreshToken(){
        accessToken= Net.getRestTemplate().getForObject(String.format(WeChatUtil.GET_ACCESSTOKEN_URL, WeChatUtil.appID, WeChatUtil.appsecret), AccessToken.class);
        return accessToken;
    }

    public static void main(String[] args) throws IOException {
        AccessTokenService tokenService=new AccessTokenService();
        AccessToken token=tokenService.refreshToken();
        System.out.println(token.getAccess_token());


        //创建菜单
        String menu=StreamUtils.copyToString(ClassLoader.getSystemClassLoader().getResourceAsStream("menu.json"), Charset.forName("utf-8"));
        String result=tokenService.createMenu(menu);
    }

}
