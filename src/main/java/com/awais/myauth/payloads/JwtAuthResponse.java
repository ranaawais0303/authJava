package com.awais.myauth.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String token;

    ///for user
//    private UserDto user;
}
