package com.vilelo.order_service.bootstrap;

import com.vilelo.order_service.domain.OrderHeader;
import com.vilelo.order_service.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //readOrderData();
        OrderHeader orderHeader = orderHeaderRepository.findById(5L).get();
        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());
            System.out.println("====== categories: " + ol.getProduct().getCategories().size());
            //from here we can see the Lazy initialize error
            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });

        });
    }

    /*
    @Transactional
    public void readOrderData() {
        OrderHeader orderHeader = orderHeaderRepository.findById(5L).get();
        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());
            System.out.println("====== categories: " + ol.getProduct().getCategories().size());
            //from here we can see the Lazy initialize error
            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });

        });
    }

     */

}
