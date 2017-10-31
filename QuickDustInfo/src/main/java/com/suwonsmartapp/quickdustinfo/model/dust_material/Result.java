package com.suwonsmartapp.quickdustinfo.model.dust_material;

public class Result {

    private Integer code;
    private String requestUrl;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", requestUrl='" + requestUrl + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}