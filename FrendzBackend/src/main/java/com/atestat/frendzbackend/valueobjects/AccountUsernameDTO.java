package com.atestat.frendzbackend.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUsernameDTO {
    String username;
    String platform;
}
