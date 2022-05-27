package com.example.Coop.Super.controller.deliverer;

import com.example.Coop.Super.bean.deliverer.DelivererDataBean;
import com.example.Coop.Super.bean.deliverer.DelivererInputBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.repository.deliverer.DelivererRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DelivererController {

    @Autowired
    DelivererRepository delivererRepository;

    @GetMapping(value = "/listDeliverers", headers = {"content-type=application/json"})
    public @ResponseBody DataTableResponse<DelivererDataBean> serachDeliervers(@RequestBody DelivererInputBean deliverer) {
         //System.out.println("Deliverer Search");
        DataTableResponse<DelivererDataBean> response = new DataTableResponse<>();
        try {
            response = delivererRepository.getDelivererList();
            //System.out.println(response);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return response;
    }

    @GetMapping("/deliverer")
    public ModelAndView viewHomePage() {
        ModelAndView model = new ModelAndView("deliverer");
        return model;
    }

    @RequestMapping(value = "/listDeliverer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody DataTableResponse<DelivererDataBean> loadDeliverers() {
        System.out.println("Deliverer Search");
        DataTableResponse<DelivererDataBean> response = new DataTableResponse<>();
        try {
            response = delivererRepository.getDelivererList();
            System.out.println(response);
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return response;
    }
}
