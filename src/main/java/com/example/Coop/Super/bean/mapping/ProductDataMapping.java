package com.example.Coop.Super.bean.mapping;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class ProductDataMapping {
    private int product_id;
    private String product_name;
    private int category;
    private String description;
    private String unit_qty;
    private double unit_price;
    private String available_stock;
    private byte[] product_img;
}
