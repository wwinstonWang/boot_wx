package com.winston.wx.entity;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Map;

@Deprecated
@Data
@XmlRootElement(name="xml")
public class WeMessage {
    @XmlElement(name="ToUserName")
    String ToUserName;
    String FromUserName;
    long CreateTime;
    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     * Event 事件推送
     */
    String MsgType;
    String Content;
    long MsgId;
    String Event;

    String EventKey;


    public static WeMessage parse(Map<String,String> map){
        WeMessage weMessage=new WeMessage();
        if(map.containsKey("ToUserName"))
            weMessage.setToUserName(map.get("ToUserName"));

        if(map.containsKey("FromUserName"))
            weMessage.setFromUserName(map.get("FromUserName"));
        if(map.containsKey("CreateTime"))
            weMessage.setCreateTime(Long.parseLong(map.get("CreateTime")));
        if(map.containsKey("MsgType"))
            weMessage.setMsgType(map.get("MsgType"));
        if(map.containsKey("Content"))
            weMessage.setContent(map.get("Content"));
        if(map.containsKey("MsgId"))
            weMessage.setMsgId(Long.parseLong(map.get("MsgId")));
        if(map.containsKey("Event"))
            weMessage.setEvent(map.get("Event"));
        if(map.containsKey("EventKey"))
            weMessage.setEventKey(map.get("EventKey"));

        return weMessage;
    }
}
