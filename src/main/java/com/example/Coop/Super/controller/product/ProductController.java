package com.example.Coop.Super.controller.product;

import com.example.Coop.Super.bean.ResponseBean;
import com.example.Coop.Super.bean.product.ProductDataBean;
import com.example.Coop.Super.bean.product.ProductInputBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.repository.product.ProductRepository;
import com.example.Coop.Super.service.product.ProductService;
import com.example.Coop.Super.validator.RequestBeanValidation;
import com.example.Coop.Super.validator.product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductValidator productValidator;

    @Autowired
    MessageSource messageSource;

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
    public ModelAndView viewHomePage(@ModelAttribute("product") ProductInputBean productInputBean, ModelMap modelMap, Locale locale) {
//        ModelAndView model = new ModelAndView("product");
//        return model;
        return new ModelAndView("product", new ModelMap());
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

    @PostMapping(value = "/addProduct", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseBean addProduct(@ModelAttribute("product") ProductInputBean productInputBean, Locale locale) {
        System.out.println("Add Product");

        ResponseBean responseBean = null;
        try {
            String msg = productValidator.getValidateResult(productInputBean);
            if (msg != null && !msg.isEmpty()) {
                responseBean = new ResponseBean(false, null, msg);
            } else {
                String message = productService.addProduct(productInputBean);
                if (message.isEmpty()) {
                    responseBean = new ResponseBean(true, "Product added successfully!", null);
                } else {
                    responseBean = new ResponseBean(false, null, message);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception  :  " + e);
            responseBean = new ResponseBean(false, null, "Failed to add the product");
        }
        return responseBean;
    }

    @ModelAttribute
    public void getProductBean(Model map) throws Exception {
        ProductInputBean productInputBean = new ProductInputBean();
        map.addAttribute("product", productInputBean);
    }

    /*@Override
    public BindingResult validateRequestBean(Object object) {
        DataBinder dataBinder = new DataBinder(object);
        dataBinder.setValidator(productValidator);
        dataBinder.validate();
        return dataBinder.getBindingResult();
    }*/
}
