package com.example.Coop.Super.service.deliverer;

import com.example.Coop.Super.repository.deliverer.DelivererRepository;
import com.example.Coop.Super.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class DelivererService {

    @Autowired
    DelivererRepository delivererRepository;

    public String deleteDeliverer(String del_id) throws Exception {
        String message = "";
        try {

            message = delivererRepository.deleteDeliverer(del_id);

        } catch (Exception e) {
            message = "Error occurred";
            System.out.println(e);
        }
        return message;
    }
}
