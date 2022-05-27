package com.example.Coop.Super.controller.product;

import com.example.Coop.Super.bean.product.ProductDataBean;
import com.example.Coop.Super.bean.product.ProductInputBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/listProducts", headers = {"content-type=application/json"})
    public @ResponseBody DataTableResponse<ProductDataBean> searchProduct(@RequestBody ProductInputBean product){
        //System.out.println("Product Search");
        DataTableResponse<ProductDataBean> response = new DataTableResponse<>();
        try {
            response = productRepository.getProductList();
            //System.out.println(response);
        }catch (Exception e){
            System.out.println("Product Exception:"+e);
        }
        return response;
    }

    @GetMapping("/product")
    public ModelAndView viewHomePage() {
        ModelAndView model = new ModelAndView("product");
        return model;
    }

    @RequestMapping(value = "/listProduct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody DataTableResponse<ProductDataBean> loadProduct() {
        System.out.println("Product Search");
        DataTableResponse<ProductDataBean> response = new DataTableResponse<>();
        try {
            response = productRepository.getProductList();
            System.out.println(response);
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return response;
    }
}
