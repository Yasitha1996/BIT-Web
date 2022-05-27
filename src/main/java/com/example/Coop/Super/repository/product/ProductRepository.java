package com.example.Coop.Super.repository.product;

import com.example.Coop.Super.bean.product.ProductDataBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.db.DBConnection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Scope("prototype")
public class ProductRepository {

    public DataTableResponse<ProductDataBean> getProductList(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DataTableResponse<ProductDataBean> response = new DataTableResponse<>();
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM product");
            rs = ps.executeQuery();
            while (rs.next()){
                ProductDataBean dataBean = new ProductDataBean();
                dataBean.setProduct_id(rs.getString("product_id"));
                dataBean.setProduct_name(rs.getString("product_name"));
                dataBean.setDescription(rs.getString("description"));
                dataBean.setUnit_price(rs.getString("unit_price"));
                dataBean.setUnit_qty(rs.getString("unit_qty"));
                dataBean.setAvailable_stock(rs.getString("available_stock"));
                response.data.add(dataBean);
            }
        }catch (Exception e){
            System.out.println("Product Exception:"+e);
        }
        return response;
    }
}
