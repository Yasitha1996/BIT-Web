package com.example.Coop.Super.bean.deliverer;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class DelivererDataBean {

    private String del_id;
    private String firstname;
    private String lastname;
    private String licenseNo;
    private String contactNo;
    private String delivering;
    private String delivered;
}
