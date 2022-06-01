package com.example.Coop.Super.bean.product;

import com.example.Coop.Super.common.DataTableRequest;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInputBean extends DataTableRequest {

    private String product_id;
    private String product_name;
    private String category;
    private String description;
    private String unit_qty;
    private String unit_price;
    private String available_stock;
    private String product_img;
}
