package com.example.my_otus_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;

import java.util.Objects;

/**
 * LoginRequest
 */
@lombok.Builder(toBuilder = true)
@lombok.RequiredArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)

@JsonTypeName("login_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-28T19:59:09.358007400+03:00[Europe/Moscow]")
public class LoginRequest {

  private String id;

  private String password;

  public LoginRequest id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Идентификатор пользователя
   * @return id
  */

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LoginRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */
  

  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginRequest loginRequest = (LoginRequest) o;
    return Objects.equals(this.id, loginRequest.id) &&
        Objects.equals(this.password, loginRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

