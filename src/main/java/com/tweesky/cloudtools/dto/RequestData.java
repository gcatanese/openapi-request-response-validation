package com.tweesky.cloudtools.dto;

public class RequestData {

    private String method;
    private String path;
    private String requestAsJson;
    private String responseAsJson;
    private Integer statusCode;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestAsJson() {
        return requestAsJson;
    }

    public void setRequestAsJson(String requestAsJson) {
        this.requestAsJson = requestAsJson;
    }

    public String getResponseAsJson() {
        return responseAsJson;
    }

    public void setResponseAsJson(String responseAsJson) {
        this.responseAsJson = responseAsJson;
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
