
package com.globant.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "last_name",
        "email",
        "password",
        "account",
        "id"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty("name")
    private String name;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("account")
    public Account account;
    @JsonProperty("id")
    private String id;

}