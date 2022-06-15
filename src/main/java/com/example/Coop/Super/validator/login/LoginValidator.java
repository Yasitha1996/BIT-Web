package com.example.Coop.Super.validator.login;

import com.example.Coop.Super.bean.login.LoginBean;
import com.example.Coop.Super.bean.product.ProductInputBean;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    public String getValidateResult(LoginBean loginBean) {
        String msg = "";

        try {
            if (loginBean.getUsername() == null || loginBean.getUsername().isEmpty()) {
                msg = "Username cannot be empty.";
            } else if (loginBean.getPassword()==null || loginBean.getPassword().isEmpty()) {
                msg = "Password cannot be empty";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return msg;
    }
}
