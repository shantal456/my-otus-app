package com.example.my_otus_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


import java.util.Objects;

/**
 * ErrorResponse
 */
@lombok.Builder(toBuilder = true)
@lombok.RequiredArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)

@JsonTypeName("error_response")
public class ErrorResponse {

  private String message;

  private String requestId;

  private Integer code;

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorResponse requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  @JsonProperty("request_id")
  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public ErrorResponse code(Integer code) {
    this.code = code;
    return this;
  }

  @JsonProperty("code")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.message, errorResponse.message) &&
        Objects.equals(this.requestId, errorResponse.requestId) &&
        Objects.equals(this.code, errorResponse.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, requestId, code);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Login500Response {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

