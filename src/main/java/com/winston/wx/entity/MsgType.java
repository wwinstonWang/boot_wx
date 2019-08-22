package com.winston.wx.entity;

public enum  MsgType {
    Text("text"),
    Image("image"),
    Event("event"),
    Music("music"),
    Video("video"),
    Voice("voice"),
    News("news"),
    Location("location"),
    Link("link");
    private String msgType = "";

    MsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the msgType
     */
    @Override
    public String toString() {
        return msgType;
    }
}
