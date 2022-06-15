package com.example.Coop.Super.validator.product;

import com.example.Coop.Super.bean.product.ProductInputBean;
import com.example.Coop.Super.service.common.validation.Validation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class ProductValidator {

    public String getValidateResult(ProductInputBean productInputBean, String requestType) {
        String msg = "";
        try {

            if(requestType.equals("add")) {
                if (productInputBean.getProduct_name() == null || productInputBean.getProduct_name().isEmpty()) {
                    msg = "Product name cannot be empty.";
                } else if (productInputBean.getCategory()==null || productInputBean.getCategory().isEmpty()) {
                    msg = "Product category cannot be empty";
                }else if (productInputBean.getAvailable_stock()==null || productInputBean.getAvailable_stock().isEmpty()) {
                    msg = "Available Stock cannot be empty";
                }else if (productInputBean.getUnit_qty()==null || productInputBean.getUnit_qty().isEmpty()) {
                    msg = "Unit quantity cannot be empty";
                }else if (productInputBean.getUnit_price()==null || productInputBean.getUnit_price().isEmpty()) {
                    msg = "Unit price cannot be empty";
                }else if (productInputBean.getDescription()==null || productInputBean.getDescription().isEmpty()) {
                    msg = "Product description cannot be empty";
                }else if (productInputBean.getProduct_img()==null || productInputBean.getProduct_img().isEmpty()) {
                    msg = "Product image cannot be empty";
                }
            } else if (requestType.equals("update")) {
                if (productInputBean.getProduct_name() == null || productInputBean.getProduct_name().isEmpty()) {
                    msg = "Product name cannot be empty.";
                }else if (productInputBean.getUnit_qty()==null || productInputBean.getUnit_qty().isEmpty()) {
                    msg = "Unit quantity cannot be empty";
                }else if (productInputBean.getUnit_price()==null || productInputBean.getUnit_price().isEmpty()) {
                    msg = "Unit price cannot be empty";
                }else if (productInputBean.getDescription()==null || productInputBean.getDescription().isEmpty()) {
                    msg = "Product description cannot be empty";
                }else if (productInputBean.getAvailable_stock()==null || productInputBean.getAvailable_stock().isEmpty()) {
                    msg = "Available Stock cannot be empty";
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }


        return msg;

    }

   /* @Autowired
    Validation validation;

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductInputBean.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        try {
            SortedMap<String, Field> allFields = new TreeMap<>();
            //get fields from dto
            for (Field field : o.getClass().getDeclaredFields()) {
                allFields.put(field.getName(), field);
            }

            if (o.getClass().equals(ProductInputBean.class)) {
                Field[] requiredFields = this.getRequiredFields(allFields, (ProductInputBean) o);
                //validate fields
                for (Field field : requiredFields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();

                        if (fieldName.equals("product_name")) {
                            //validate the null and empty in taskCode
                            String productName = ((ProductInputBean) o).getProduct_name();
                            if (validation.isEmptyFieldValue(productName)) {
                                errors.rejectValue(fieldName, "Product name cannot be empty.", "Product name cannot be empty.");
                            }

                        } else if (fieldName.equals("category")) {
                            //validate the null and empty in description
                            String category = ((ProductInputBean) o).getCategory();
                            if (validation.isEmptyFieldValue(category)) {
                                errors.rejectValue(fieldName, "Category cannot be empty." , "Category cannot be empty.");
                            }

                        } else if (fieldName.equals("description")) {
                            //validate the null and empty in status
                            String description = ((ProductInputBean) o).getDescription();
                            if (validation.isEmptyFieldValue(description)) {
                                errors.rejectValue(fieldName, "Description cannot be empty.", "Description cannot be empty.");
                            }
                        } else if (fieldName.equals("unit_qty")) {
                            //validate the null and empty in status
                            String unit_qty = ((ProductInputBean) o).getUnit_qty();
                            if (validation.isEmptyFieldValue(unit_qty)) {
                                errors.rejectValue(fieldName, "Unit quantity cannot be empty.", "Unit quantity cannot be empty.");
                            }
                        } else if (fieldName.equals("unit_price")) {
                            //validate the null and empty in status
                            String unit_price = ((ProductInputBean) o).getUnit_price();
                            if (validation.isEmptyFieldValue(unit_price)) {
                                errors.rejectValue(fieldName, "Unit price cannot be empty.", "Unit price cannot be empty.");
                            }
                        } else if (fieldName.equals("available_stock")) {
                            //validate the null and empty in status
                            String available_stock = ((ProductInputBean) o).getAvailable_stock();
                            if (validation.isEmptyFieldValue(available_stock)) {
                                errors.rejectValue(fieldName, "Available stock cannot be empty.", "Available stock cannot be empty.");
                            }
                        }else if (fieldName.equals("product_img")) {
                            //validate the null and empty in status
                            String product_img = ((ProductInputBean) o).getProduct_img();
                            if (validation.isEmptyFieldValue(product_img)) {
                                errors.rejectValue(fieldName, "Product image cannot be empty.", "Product image cannot be empty.");
                            }
                        }

                }
            } else {
                errors.reject("Invalid Bean");
            }
        } catch (Exception ex) {
            logger.error("Exception : ", ex);
            errors.reject("System error");
        }
    }

    private Field[] getRequiredFields(SortedMap<String, Field> allFields, ProductInputBean o) {
        return new Field[]{
                allFields.get("product_name"),
                allFields.get("category"),
                allFields.get("available_stock"),
                allFields.get("description"),
                allFields.get("unit_qty"),
                allFields.get("unit_price"),
                allFields.get("product_img"),
        };
    }*/
}
