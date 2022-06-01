package com.example.Coop.Super.service.product;

import com.example.Coop.Super.bean.mapping.ProductDataMapping;
import com.example.Coop.Super.bean.product.ProductInputBean;
import com.example.Coop.Super.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Scope("prototype")
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public String addProduct(ProductInputBean productInputBean){
        String msg = "";
        ProductDataMapping productDataMapping = new ProductDataMapping();
        try {
            this.setProductData(productInputBean, productDataMapping);
            msg = productRepository.addProduct(productDataMapping);
            System.out.println(msg);
            if (!msg.isEmpty()){
            //if(msg!=""){
                msg = "Error occured in Service if";
            }
        }catch (Exception e){
            System.out.println("Exception :"+e);
            msg = "Error occured in service exception";
        }
        return msg;
    }

    public void setProductData(ProductInputBean bean, ProductDataMapping dataMapping){
        dataMapping.setProduct_name(bean.getProduct_name());
        dataMapping.setAvailable_stock(bean.getAvailable_stock());
        dataMapping.setDescription(bean.getDescription());
        dataMapping.setUnit_price(bean.getUnit_price());
        dataMapping.setUnit_qty(bean.getUnit_qty());
        dataMapping.setCategory(Integer.parseInt(bean.getCategory()));
        if (bean.getProduct_img() != null && !bean.getProduct_img().isEmpty()) {
            dataMapping.setProduct_img(convertBase64toByte(bean.getProduct_img()));
        }
    }

    private byte[] convertBase64toByte(String base64String){
        byte[] valueDecoded = new byte[0];
        try {
            valueDecoded = Base64.getDecoder().decode(base64String.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
        }
        return valueDecoded;
    }
}
