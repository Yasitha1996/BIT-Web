package com.example.Coop.Super.bean.customer;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class CustomerInputBean {
    private String id;
    private String fname;
    private String lname;
    private String address;
    private String contactNo;
    private int totalTrans;
}
