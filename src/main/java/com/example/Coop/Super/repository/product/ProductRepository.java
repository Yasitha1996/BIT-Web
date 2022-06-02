package com.example.Coop.Super.repository.product;

import com.example.Coop.Super.bean.mapping.ProductDataMapping;
import com.example.Coop.Super.bean.product.ProductDataBean;
import com.example.Coop.Super.bean.product.ProductInputBean;
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

    public String deleteProduct(String productId){
        String msg = "Error";
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("DELETE FROM product WHERE product_id =?");

            ps.setString(1, productId);

            rs = ps.executeUpdate();

            if (rs > 0) {
                msg = "";
            }

        } catch (Exception e){
            System.out.println("Product Exception:"+e);
            msg = "Failed to delete product";
        }
        return msg;
    }

    public String updateProduct(ProductInputBean productInputBean){
        String msg = "Error";
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("UPDATE product SET available_stock = ?, product_name = ?, unit_price = ?, unit_qty = ?, description = ? WHERE product_id=?");

            ps.setString(1, productInputBean.getAvailable_stock());
            ps.setString(2, productInputBean.getProduct_name());
            ps.setString(3, productInputBean.getUnit_price());
            ps.setString(4, productInputBean.getUnit_qty());
            ps.setString(5, productInputBean.getDescription());
            ps.setInt(6, Integer.parseInt(productInputBean.getProduct_id()));

            rs = ps.executeUpdate();

            if (rs > 0) {
                msg = "";
            }

        } catch (Exception e){
            System.out.println("Product Exception:"+e);
            msg = "Failed to update product";
        }
        return msg;
    }

    public ProductDataMapping getProduct(String productId){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDataMapping response = new ProductDataMapping();
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM product WHERE product_id=?");

            ps.setInt(1, Integer.parseInt(productId));

            rs = ps.executeQuery();
            while (rs.next()){
                response.setProduct_id(Integer.parseInt(rs.getString("product_id")));
                response.setProduct_name(rs.getString("product_name"));
                response.setDescription(rs.getString("description"));
                response.setUnit_price(rs.getString("unit_price"));
                response.setUnit_qty(rs.getString("unit_qty"));
                response.setAvailable_stock(rs.getString("available_stock"));
            }
        }catch (Exception e){
            System.out.println("Product Exception:"+e);
        }
        System.out.println("Repo Response: " + response);
        return response;
    }
}
