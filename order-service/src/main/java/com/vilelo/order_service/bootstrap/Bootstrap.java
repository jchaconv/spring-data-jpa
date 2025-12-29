package com.vilelo.order_service.bootstrap;

import com.vilelo.order_service.domain.Customer;
import com.vilelo.order_service.domain.OrderHeader;
import com.vilelo.order_service.repositories.CustomerRepository;
import com.vilelo.order_service.repositories.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    /*@Autowired
    OrderHeaderRepository orderHeaderRepository;*/

    @Autowired
    BootstrapOrderService bootstrapOrderService;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        //readOrderData();
        bootstrapOrderService.readOrderData();

        Customer customer = new Customer();
        customer.setCustomerName("Testing version");
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("==== Version is: " + savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);
        System.out.println("==== Version is: " + savedCustomer2.getVersion());

        savedCustomer2.setCustomerName("Testing version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        System.out.println("==== Version is: " + savedCustomer3.getVersion());

        customerRepository.deleteById(savedCustomer.getId());
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
