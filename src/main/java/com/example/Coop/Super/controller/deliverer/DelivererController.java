package com.example.Coop.Super.controller.deliverer;

import com.example.Coop.Super.bean.ResponseBean;
import com.example.Coop.Super.bean.deliverer.DelivererDataBean;
import com.example.Coop.Super.bean.deliverer.DelivererInputBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.repository.deliverer.DelivererRepository;
import com.example.Coop.Super.service.deliverer.DelivererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class DelivererController {

    @Autowired
    DelivererRepository delivererRepository;

    @Autowired
    DelivererService delivererService;

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

    @PostMapping(value = "/deleteDeliverer", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseBean deleteDeliverer(@RequestParam String del_id) {
        System.out.println("Deliverer Delete");
        ResponseBean responseBean = null;
        try {
            String message = delivererService.deleteDeliverer(del_id.trim());
            if (message.isEmpty()) {
                responseBean = new ResponseBean(true, "Deliverer deleted successfully!", null);
            } else {
                responseBean = new ResponseBean(false, null, message);
            }
        } catch (Exception e) {
            System.out.println(e);
            responseBean = new ResponseBean(false, null, "Failed to delete the deliverer");
        }
        return responseBean;
    }
}
