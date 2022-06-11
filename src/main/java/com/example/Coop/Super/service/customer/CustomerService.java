package com.example.Coop.Super.service.customer;

import com.example.Coop.Super.bean.customer.CustomerInputBean;
import com.example.Coop.Super.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public void listCustomer(){
    }

    public String deleteCustomer(String id) throws Exception {
        String message = "";
        try {

            message = customerRepository.deleteCustomer(id);

        } catch (Exception e) {
            message = "Error occurred";
            System.out.println(e);
        }
        return message;
    }
}
