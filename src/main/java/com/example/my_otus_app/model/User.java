package com.example.my_otus_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

/**
 * User
 */
@lombok.Builder(toBuilder = true)
@lombok.RequiredArgsConstructor
@lombok.AllArgsConstructor
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown=true)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-06-28T19:59:09.358007400+03:00[Europe/Moscow]")
public class User {

  private String id;

  private String firstName;

  private String secondName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate birthdate;

  private String biography;

  private String city;

  public User id(String id) {
    this.id = id;
    return this;
  }


  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }


  @JsonProperty("first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User secondName(String secondName) {
    this.secondName = secondName;
    return this;
  }

  @JsonProperty("second_name")
  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public User birthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  @JsonProperty("birthdate")
  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public User biography(String biography) {
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

  public User city(String city) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.firstName, user.firstName) &&
        Objects.equals(this.secondName, user.secondName) &&
        Objects.equals(this.birthdate, user.birthdate) &&
        Objects.equals(this.biography, user.biography) &&
        Objects.equals(this.city, user.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, secondName, birthdate, biography, city);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    secondName: ").append(toIndentedString(secondName)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    biography: ").append(toIndentedString(biography)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
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

