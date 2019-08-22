package com.winston.wx.entity;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XStreamAlias("xml")
public class OutputMessage {

    @XStreamAlias("ToUserName")
    @XStreamCDATA
    private String ToUserName;

    @XStreamAlias("FromUserName")
    @XStreamCDATA
    private String FromUserName;

    @XStreamAlias("CreateTime")
    private Long CreateTime;

    @XStreamAlias("Content")
    @XStreamCDATA
    private String Content;

    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String MsgType = "text";

    private ImageMessage Image;
    private VoiceMessage Voice;
    private VideoMessage Video;
    private MusicMessage Music;

    @XStreamAlias("ArticleCount")
    private Integer ArticleCount;
    @XStreamAlias("Articles")
    private List<ArticleMessage> articles=new ArrayList<>();

}
