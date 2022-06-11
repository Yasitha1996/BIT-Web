package com.example.Coop.Super.bean.reports;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalesReportBean {

    private String product_id;
    private String product_name;
    private String sales_qty;
    private String available_stock;
    private double unit_price;
    private double totalRevenue;
    private double netRevenue;


}
