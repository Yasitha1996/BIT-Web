package com.example.Coop.Super.controller.customer;

import com.example.Coop.Super.bean.ResponseBean;
import com.example.Coop.Super.bean.customer.CustomerDataBean;
import com.example.Coop.Super.bean.customer.CustomerInputBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.repository.customer.CustomerRepository;
import com.example.Coop.Super.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@Scope("request")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/listCustomers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public @ResponseBody DataTableResponse<CustomerDataBean> searchTask(@RequestBody CustomerInputBean customer, ModelMap modelMap){
        //System.out.println("Customer Search");
        DataTableResponse<CustomerDataBean> response = new DataTableResponse<>();
            try {
                response = customerRepository.getCustomerList();
              System.out.println(response);
            }catch (Exception e){
                System.out.println("Exception: " + e);
            }
            return response;
    }

    @GetMapping("/customer")
    public ModelAndView viewHomePage() {
        ModelAndView model = new ModelAndView("customer");
        return model;
    }

    @RequestMapping(value = "/listCustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody DataTableResponse<CustomerDataBean> loadCustomerList() {
        System.out.println("Customer Search");
        DataTableResponse<CustomerDataBean> response = new DataTableResponse<>();
        try {
            response = customerRepository.getCustomerList();
            System.out.println(response);
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return response;
    }

    @PostMapping(value = "/deleteCustomer", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseBean deleteCustomer(@RequestParam String id) {
        System.out.println("CUSTOMER DELETE");
        ResponseBean responseBean = null;
        try {
            String message = customerService.deleteCustomer(id.trim());
            if (message.isEmpty()) {
                responseBean = new ResponseBean(true, "Customer deleted successfully!", null);
            } else {
                responseBean = new ResponseBean(false, null, message);
            }
        } catch (Exception e) {
            System.out.println(e);
            responseBean = new ResponseBean(false, null, "Failed to delete the customer");
        }
        return responseBean;
    }
}
