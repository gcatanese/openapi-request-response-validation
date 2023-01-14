package com.tweesky.cloudtools.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestData {

    private Map<String, Object> request;
    private String collectionId;
    private String method;
    private String path;
    private List<LinkedHashMap<String, String>> headers;
    private String requestAsJson;
    private String responseAsJson;
    private Integer statusCode;

    public RequestData() {
    }

    public RequestData(Map<String, Object> request) {
        this.request = request;

        this.collectionId = getAsString("collectionId");
        this.method = getAsString("method");
        this.path = getAsString("path");
        this.headers = (List<LinkedHashMap<String, String>>) request.get("headers");
        this.requestAsJson = getAsString("requestAsJson");
        this.responseAsJson = getAsString("responseAsJson");
        this.statusCode = getAsInteger("statusCode");

    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

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

    public List<LinkedHashMap<String, String>> getHeaders() {
        return headers;
    }

    public void setHeader(List<LinkedHashMap<String, String>> headers) {
        this.headers = headers;
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

    String getAsString(String key) {
        String value = "";

        if(request.get(key) != null) {
            value = request.get(key).toString();
        }

        return value;
    }
    Integer getAsInteger(String key) {
        Integer value = null;

        if(request.get(key) != null) {
            value = Integer.valueOf(request.get(key).toString());
        }

        return value;
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
