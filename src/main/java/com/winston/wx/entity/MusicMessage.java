package com.winston.wx.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("Music")
@Data
public class MusicMessage{
    @XStreamAlias("Title")
    @XStreamCDATA
    private String Title;

    @XStreamAlias("Description")
    @XStreamCDATA
    private String Description;

    @XStreamAlias("MusicUrl")
    @XStreamCDATA
    private String MusicUrl;
    @XStreamAlias("HQMusicUrl")

    @XStreamCDATA
    private String HQMusicUrl;

    //必须字段
    @XStreamAlias("ThumbMediaId")
    @XStreamCDATA
    private String ThumbMediaId;
}
