package com.example.Coop.Super.bean.product;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ProductDataBean {

    private String product_id;
    private String product_name;
    private String description;
    private String unit_qty;
    private String unit_price;
    private String available_stock;

}
