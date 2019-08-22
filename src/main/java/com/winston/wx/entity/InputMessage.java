package com.winston.wx.entity;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * POST的XML数据包转换为消息接受对象
 *
 * <p>
 * 由于POST的是XML数据包，所以不确定为哪种接受消息，<br/>
 * 所以直接将所有字段都进行转换，最后根据<tt>MsgType</tt>字段来判断取何种数据
 * </p>
 *
 */
@Data
@XStreamAlias("xml")
public class InputMessage implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @XStreamAlias("ToUserName")
    private String ToUserName;
    @XStreamAlias("FromUserName")
    private String FromUserName;
    @XStreamAlias("CreateTime")
    private Long CreateTime;
    @XStreamAlias("MsgType")
    private String MsgType = "text";
    @XStreamAlias("MsgId")
    private Long MsgId;
    // 文本消息
    @XStreamAlias("Content")
    private String Content;
    // 图片消息
    @XStreamAlias("PicUrl")
    private String PicUrl;
    // 位置消息
    @XStreamAlias("Location_X")
    private String LocationX;
    @XStreamAlias("Location_Y")
    private String LocationY;
    @XStreamAlias("Scale")
    private Long Scale;
    @XStreamAlias("Label")
    private String Label;
    // 链接消息
    @XStreamAlias("Title")
    private String Title;
    @XStreamAlias("Description")
    private String Description;
    @XStreamAlias("Url")
    private String URL;
    // 语音信息
    @XStreamAlias("MediaId")
    private String MediaId;
    @XStreamAlias("Format")
    private String Format;
    @XStreamAlias("Recognition")
    private String Recognition;
    // 事件
    @XStreamAlias("Event")
    private String Event;
    @XStreamAlias("EventKey")
    private String EventKey;
    @XStreamAlias("Ticket")
    private String Ticket;

    //点击菜单跳转链接时的事件推送
    @XStreamAlias("MenuId")
    private Long MenuId;

    //要求位置事件
    @XStreamAlias("Latitude")
    private String Latitude;
    @XStreamAlias("Longitude")
    private String Longitude;
    @XStreamAlias("Precision")
    private Double Precision;
}
