package com.winston.wx.entity;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@XStreamAlias("Articles")
@Data
public class ArticlesMessage {
    @XStreamAlias("Articles")
    private List<ArticleMessage> articles=new ArrayList<>();

    public static void main(String[] args) {
        XStream xStream = new XStream();
        xStream.processAnnotations(ArticlesMessage.class);
        xStream.processAnnotations(ArticleMessage.class);

        ArticlesMessage messages=new ArticlesMessage();
        ArticleMessage message;
        for(int i=0;i<5;i++){
            message=new ArticleMessage();
            message.setTitle("W" +i);
            messages.articles.add(message);
        }

        String out=xStream.toXML(messages);
        System.out.println(out);
    }
}
