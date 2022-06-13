package com.example.Coop.Super.repository.reports;

import com.example.Coop.Super.bean.product.ProductDataBean;
import com.example.Coop.Super.bean.reports.SalesReportBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.db.DBConnection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@Scope("prototype")
public class SalesReportRepository {

    public List<SalesReportBean> getDataList(String category) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SalesReportBean> list = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT distinct( c.product_id) as product_id, sum(c.quantity) as sales_qty, " +
                    "p.product_name, p.available_stock, p.unit_price, " +
                    "ctr.category_name, (sum(c.quantity)*p.unit_price) as total_rev " +
                    "FROM product p left outer join cart c on p.product_id=c.product_id " +
                    "left outer join order_p o on c.order_id=o.order_id " +
                    "left outer join category ctr on p.category=ctr.category_id " +
                    "where o.status='3' and ctr.category_name = '" + category +
                    "' group by c.product_id order by product_id");
            rs = ps.executeQuery();
            while (rs.next()) {
                SalesReportBean salesReportBean = new SalesReportBean();
                salesReportBean.setProduct_id(rs.getString("product_id"));
                salesReportBean.setProduct_name(rs.getString("product_name"));
                salesReportBean.setSales_qty(rs.getString("sales_qty"));
                salesReportBean.setAvailable_stock(rs.getString("available_stock"));
                salesReportBean.setUnit_price(rs.getDouble("unit_price"));
                salesReportBean.setTotalRevenue(rs.getDouble("total_rev"));
                list.add(salesReportBean);
            }
        } catch (Exception e) {
            System.out.println("Sales Report Exception:" + e);
        }
        return list;
    }
}
