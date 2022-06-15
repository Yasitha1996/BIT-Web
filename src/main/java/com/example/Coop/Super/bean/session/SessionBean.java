package com.example.Coop.Super.bean.session;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class SessionBean {

    private String sessionId;
    private String username;
}
