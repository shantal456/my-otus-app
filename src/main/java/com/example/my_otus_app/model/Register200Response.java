package com.example.my_otus_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * Register200Response
 */
@lombok.Builder(toBuilder = true)
@lombok.RequiredArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)

@JsonTypeName("register_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-28T19:59:09.358007400+03:00[Europe/Moscow]")
public class Register200Response {

  private String userId;

  public Register200Response userId(String userId) {
    this.userId = userId;
    return this;
  }

 @JsonProperty("user_id")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Register200Response register200Response = (Register200Response) o;
    return Objects.equals(this.userId, register200Response.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Register200Response {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

