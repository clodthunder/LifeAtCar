package com.lskj.ct.lifeatcar.response;

/**
 * Created by thunder on 2018/2/18.
 */

public class ResponseWrap<T> {
    //请求响应码
    private int code;
    //返回的信息
    private String msg;
    //返回的数据
    private T data;

    public ResponseWrap() {
    }

    public ResponseWrap(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseWrap{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
