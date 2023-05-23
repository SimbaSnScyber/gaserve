package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpResponseBody {

    @JsonProperty("httpResponseCode")
    private String httpResponseCode;

    @JsonProperty("httpResponseMessage")
    private String httpResponseMessage;

    public void setHttpResponseCode(String httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    public void setHttpResponseMessage(String httpResponseMessage) {
        this.httpResponseMessage = httpResponseMessage;
    }
}
