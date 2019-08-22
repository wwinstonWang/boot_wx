package com.winston.wx.controller;

import com.winston.wx.entity.WeMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Test")
public class TestController {
    @GetMapping("")
    public Object getData(HttpServletRequest req, HttpServletResponse res){
        List<WeMessage> list=new ArrayList<>();

        WeMessage weMessage=null;
        for(int i=0;i<10;i++)
        {
            weMessage=new WeMessage();
            weMessage.setToUserName("w "+i);
            weMessage.setFromUserName("ton "+i);
            list.add(weMessage);
        }

        return weMessage;
    }
}
