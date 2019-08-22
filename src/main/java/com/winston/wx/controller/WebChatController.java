package com.winston.wx.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winston.wx.util.SerializeXmlUtil;
import com.winston.wx.util.WeChatUtil;
import com.thoughtworks.xstream.XStream;
import com.winston.wx.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Calendar;

@RestController
@RequestMapping("/weChat")
public class WebChatController {
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("")
    public String validate(String signature, String timestamp, String nonce, String echostr) {
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp, nonce, WeChatUtil.TOKEN};
        Arrays.sort(arr);
        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }

        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (WeChatUtil.getSha1(sb.toString()).equals(signature)) {
            //接入成功
            return echostr;
        }
        //接入失败
        return null;
    }

    @PostMapping(value = "")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //获取数据流字符串
        String xmlMsg=StreamUtils.copyToString(req.getInputStream(), Charset.forName("utf-8"));
        System.out.println(xmlMsg);

        // 将POST流转换为XStream对象
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutputMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);

        // 将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg);
        String msgType=inputMsg.getMsgType();

        OutputMessage outputMsg = new OutputMessage();
        outputMsg.setFromUserName(inputMsg.getToUserName());
        outputMsg.setToUserName(inputMsg.getFromUserName());
        outputMsg.setCreateTime(Calendar.getInstance().getTimeInMillis()/1000);
        outputMsg.setMsgType(msgType);

        if(msgType.equals(MsgType.Text.toString())){
            outputMsg.setContent("智能回复内容");
        }else if(msgType.equals(MsgType.Image.toString())){
            ImageMessage images = new ImageMessage();
            images.setMediaId(inputMsg.getMediaId());
            outputMsg.setImage(images);
        }else if(msgType.equals(MsgType.Voice.toString())){
            VoiceMessage voice = new VoiceMessage();
            voice.setMediaId(inputMsg.getMediaId());
            outputMsg.setVoice(voice);
        }else if(msgType.equals(MsgType.Video.toString())){
            VideoMessage video = new VideoMessage();
            video.setMediaId(inputMsg.getMediaId());
            outputMsg.setVideo(video);
        }else if(msgType.equals(MsgType.Music.toString())){
            MusicMessage music = new MusicMessage();
            music.setThumbMediaId("");
            outputMsg.setMusic(music);
        }else if(msgType.equals(MsgType.Event.toString())){
            //事件类型 subscribe unsubscribe CLICK LOCATION SCAN  VIEW(点击菜单跳转链接时的事件推送)
            String event=inputMsg.getEvent();
            //判断关注事件
            if ("subscribe".equals(event)) {
                outputMsg.setContent("欢迎关注![愉快]");
                //设置消息的响应类型
                outputMsg.setMsgType("text");
            } else if ("CLICK".equals(event)) {
                //获取菜单的key值
                String key = inputMsg.getEventKey();
                String outContent="默认";
                if ("classinfo".equals(key)) {
                    outContent = "上海Java基础班第05期于2018/05/10开班\n" +
                            "广州Java基础班第24期于2018/04/02开班";
                } else if ("address".equals(key)) {
                    outContent = "北京校区：北京昌平区沙河镇万家灯火装饰城2楼8077号\n" +
                            "广州校区：广州市天河区棠下涌东路大地工业区D栋六楼\n" +
                            "上海校区：上海市青浦区华新镇华隆路1777号E通世界商务园华新园A座4楼402";
                }
                //设置消息的响应类型
                outputMsg.setMsgType("text");
                outputMsg.setContent(outContent);
            }
        }
        byte[] contents=xs.toXML(outputMsg).getBytes(Charset.forName("utf-8"));
        res.getOutputStream().write(contents,0,contents.length);
    }

}
