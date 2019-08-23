package com.winston.wx.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.winston.wx.entity.AccessToken;
import com.winston.wx.entity.State;
import com.winston.wx.util.Net;
import com.winston.wx.util.WeChatUtil;
import lombok.Data;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CustomerService {
    @Data
    public static class Customer{
        String kf_account;
        String nickname;
        String password;

        public void setPassword(String password) {
            this.password = DigestUtils.md5DigestAsHex(password.getBytes());
        }
    }

    public State add(String accessToken,Customer customer){
        try {

            State state = Net.getRestTemplate().postForEntity(String.format(WeChatUtil.CUSTOMER_ADD_URL,accessToken),customer,State.class).getBody();
            return state;
        }catch (Exception e){
            return null;
        }
    }

    public State modify(String accessToken,Customer customer){
        try {

            State state = Net.getRestTemplate().postForEntity(String.format(WeChatUtil.CUSTOMER_MODIFY_URL,accessToken),customer,State.class).getBody();
            return state;
        }catch (Exception e){
            return null;
        }
    }

    public State del(String accessToken,Customer customer){
        try {

            State state = Net.getRestTemplate().postForEntity(String.format(WeChatUtil.CUSTOMER_DEL_URL,accessToken),customer,State.class).getBody();
            return state;
        }catch (Exception e){
            return null;
        }
    }

    public State uploadHeadImg(String accessToken,String kfAccount,String tempFilePath){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
        MultiValueMap<String,Object> param = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String,Object>> formEntity = new HttpEntity<>(param,headers);
        FileSystemResource resource = new FileSystemResource(tempFilePath);
        param.add("file",resource);
        try {

            State state = Net.getRestTemplate().postForEntity(String.format(WeChatUtil.CUSTOMER_UPLOADHEADIMG_URL,accessToken,kfAccount),formEntity,State.class).getBody();
            return state;
        }catch (Exception e){
            return null;
        }
    }

    public JsonNode getAll(String accessToken){
        try {
            JsonNode jsonNode = Net.getRestTemplate().getForObject(String.format(WeChatUtil.CUSTOMER_GET_URL, accessToken), JsonNode.class);
            return jsonNode;
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        AccessTokenService accessTokenService=new AccessTokenService();
        AccessToken accessToken= accessTokenService.refreshToken();
        Customer customer=new Customer();
        customer.setKf_account("mupiaoliu");
        customer.setNickname("winston");
        customer.setPassword("123456");

        CustomerService customerService=new CustomerService();
        State state=  customerService.add(accessToken.getAccess_token(),customer);
        System.out.println("Hello.");
    }

}
