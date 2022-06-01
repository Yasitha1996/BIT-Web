package com.example.Coop.Super.repository.product;

import com.example.Coop.Super.bean.mapping.ProductDataMapping;
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

    public String addProduct(ProductDataMapping productDataMapping){
        String msg = "Error";
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("INSERT INTO product (available_stock, description, product_img, product_name, unit_price, unit_qty, category) VALUES (?,?,?,?,?,?,?)");

            ps.setString(1, productDataMapping.getAvailable_stock());
            ps.setString(2, productDataMapping.getDescription());
            ps.setBytes(3, productDataMapping.getProduct_img());
            ps.setString(4, productDataMapping.getProduct_name());
            ps.setString(5, productDataMapping.getUnit_price());
            ps.setString(6, productDataMapping.getUnit_qty());
            ps.setInt(7, productDataMapping.getCategory());

            rs = ps.executeUpdate();

            if (rs > 0) {
                msg = "";
            }

        } catch (Exception e){
            System.out.println("Product Exception:"+e);
            msg = "Failed to add product";
        }
        return msg;
    }
}
