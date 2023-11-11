package com.globant.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "number",
        "name",
        "deposits",
        "withdrawals",
        "id"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @JsonProperty("number")
    private String number;
    @JsonProperty("name")
    private String name;
    @JsonProperty("deposits")
    private List<Movement> deposits = new ArrayList<>();
    @JsonProperty("withdrawals")
    private List<Movement> withdrawals = new ArrayList<>();
    @JsonProperty("id")
    private String id;

    public Account(String number, String name) {
        this.number = number;
        this.name = name;
    }
}