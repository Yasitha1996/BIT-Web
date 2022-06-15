package com.example.Coop.Super.bean.login;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginBean {

    private String username;
    private String password;

}
