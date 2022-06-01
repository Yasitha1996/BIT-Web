package com.example.Coop.Super.bean;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseBean {
    private boolean flag;
    private String successMessage;
    private String errorMessage;
}
