package com.atestat.frendzbackend.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddAccountRequest {
    private String username;
    private String platform;
}
