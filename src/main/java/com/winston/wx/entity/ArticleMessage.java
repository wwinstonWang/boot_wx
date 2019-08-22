package com.winston.wx.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("item")
@Data
public class ArticleMessage {
    @XStreamAlias("Title")
    @XStreamCDATA
    private String Title;

    @XStreamAlias("Description")
    @XStreamCDATA
    private String Description;

    @XStreamAlias("PicUrl")
    @XStreamCDATA
    private String PicUrl;

    @XStreamAlias("Url")
    @XStreamCDATA
    private String Url;
}
