package com.campuscircle.app.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public  class MessageResult implements Serializable {

    private int Code;

    private String Message;

    private String Data;

    public MessageResult() {
        this.Code = -1;
        this.Message = "解析异常";
    }

    public MessageResult(int code) {
        this.Code = code;
        this.Message = "解析异常";
    }

    public MessageResult(int code, String message, String obj) {
        this.Code = code;
        this.Message = message;
        this.Data = obj;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        this.Data = data;
    }

    /**
     * @param result
     * @return
     */
    public static MessageResult parse(String result) {
        MessageResult message = new MessageResult();
        try {
            message = JSON.parseObject(result, MessageResult.class);
            return message;
        } catch (Exception e) {
            String str = e.getMessage();
        }
        return message;
    }
}
