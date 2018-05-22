package com.basic.core.web;

import java.io.Serializable;

/**
 * @Author: wangzw
 * @Description: 返回前端数据
 * @Version: 1.0
 * @Date: 2017/4/11 15:36
 */
public class WebJson implements Serializable {

    private int code;

    private boolean success = true;

    private String msg;

    private Object data;

    public static WebJson success() {
        WebJson webJson = new WebJson();
        webJson.setCode(200);
        webJson.setMsg("操作成功！");
        return webJson;
    }

    public static WebJson success(Object data) {
        WebJson webJson = new WebJson();
        webJson.setCode(200);
        webJson.setMsg("操作成功！");
        webJson.setData(data);
        return webJson;
    }

    public static WebJson error(int code, String message) {
        WebJson webJson = new WebJson();
        webJson.setCode(code);
        webJson.setMsg(message);
        webJson.setSuccess(false);
        return webJson;
    }

    public static WebJson error(String message) {
        return error(500, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}