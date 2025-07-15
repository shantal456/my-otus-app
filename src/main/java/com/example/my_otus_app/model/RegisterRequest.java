package com.example.my_otus_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

/**
 * RegisterRequest
 */
@lombok.Builder(toBuilder = true)
@lombok.RequiredArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)

@JsonTypeName("register_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-28T19:59:09.358007400+03:00[Europe/Moscow]")
public class RegisterRequest {

  private String firstName;

  private String secondName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthdate;

  private String biography;

  private String city;

  private String password;

  public RegisterRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  */
  
  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public RegisterRequest secondName(String secondName) {
    this.secondName = secondName;
    return this;
  }

  /**
   * Get secondName
   * @return secondName
  */

  @JsonProperty("second_name")
  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public RegisterRequest birthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Дата рождения
   * @return birthdate
  */
  @Valid 
  @JsonProperty("birthdate")
  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public RegisterRequest biography(String biography) {
    this.biography = biography;
    return this;
  }


  @JsonProperty("biography")
  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }

  public RegisterRequest city(String city) {
    this.city = city;
    return this;
  }

  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public RegisterRequest password(String password) {
    this.password = password;
    return this;
  }

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
    RegisterRequest registerRequest = (RegisterRequest) o;
    return Objects.equals(this.firstName, registerRequest.firstName) &&
        Objects.equals(this.secondName, registerRequest.secondName) &&
        Objects.equals(this.birthdate, registerRequest.birthdate) &&
        Objects.equals(this.biography, registerRequest.biography) &&
        Objects.equals(this.city, registerRequest.city) &&
        Objects.equals(this.password, registerRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, secondName, birthdate, biography, city, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterRequest {\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    secondName: ").append(toIndentedString(secondName)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    biography: ").append(toIndentedString(biography)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

