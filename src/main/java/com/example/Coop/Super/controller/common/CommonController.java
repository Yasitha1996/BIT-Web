package com.example.Coop.Super.controller.common;

import com.example.Coop.Super.bean.customer.CustomerDataBean;
import com.example.Coop.Super.bean.status.StatusBean;
import com.example.Coop.Super.common.DataTableResponse;
import com.example.Coop.Super.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("request")
public class CommonController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/*/getStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody StatusBean loadStatus() {
        System.out.println("Status Search");
        StatusBean statusBean = new StatusBean();
        try {
            statusBean = commonService.getStatusCount();
            System.out.println(statusBean);
        }catch (Exception e){
            System.out.println("Exception: " + e);
        }
        return statusBean;
    }

}
