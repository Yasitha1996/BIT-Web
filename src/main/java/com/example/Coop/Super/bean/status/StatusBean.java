package com.example.Coop.Super.bean.status;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class StatusBean {
    private String packing;
    private String delivering;
    private String completed;
    private String total;

}
