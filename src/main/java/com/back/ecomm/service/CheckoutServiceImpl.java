package com.back.ecomm.service;

import com.back.ecomm.dao.CustomerRepository;
import com.back.ecomm.dto.Purchase;
import com.back.ecomm.dto.PurchaseResponse;
import com.back.ecomm.entity.Customer;
import com.back.ecomm.entity.Order;
import com.back.ecomm.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl  implements  CheckoutService {

    private CustomerRepository customerRepository;

    private CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve order info from dto
        Order order =  purchase.getOrder();
        List<Integer> num = new ArrayList<>();
        num.add(33);


        //generatye tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //populate order with orderITems
        Set<OrderItem> orderItemSet = purchase.getOrderItems();
        orderItemSet.forEach(item -> order.add(item));

        //populate order with billingAddress and shipingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //populate customer with order
        Customer customer = purchase.getCustomer();
        //check if this id sn exiting customer
        String customerEmail = customer.getEmail();

        Customer existentCustomer = customerRepository.findByEmail(customerEmail);
        if(customerEmail != null){
            customer = existentCustomer;
        }
        customer.add(order);

        //save to the database
        customerRepository.save(customer);

        // return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber(){
        //generate random UUID version
        return UUID.randomUUID().toString();
    }
}
