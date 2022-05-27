package com.example.Coop.Super.service.common;

import com.example.Coop.Super.bean.status.StatusBean;
import com.example.Coop.Super.repository.common.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class CommonService {

    @Autowired
    CommonRepository commonRepository;

    public StatusBean getStatusCount(){

        StatusBean statusBean =new StatusBean();
        statusBean.setPacking(commonRepository.getPackingCount());
        statusBean.setDelivering(commonRepository.getDeliveringCount());
        statusBean.setCompleted(commonRepository.getCompletedCount());
        statusBean.setTotal(commonRepository.getTotalRev());

        if (statusBean.getPacking() == null || statusBean.getPacking().isEmpty()) {
            statusBean.setPacking("0");
        }

        if (statusBean.getDelivering() == null || statusBean.getDelivering().isEmpty()) {
            statusBean.setDelivering("0");
        }

        if (statusBean.getCompleted() == null || statusBean.getCompleted().isEmpty()) {
            statusBean.setCompleted("0");
        }

        if (statusBean.getTotal() == null || statusBean.getTotal().isEmpty()) {
            statusBean.setTotal("0");
        }

        return statusBean;
    }
}
