package com.back.ecomm.dto;

import com.back.ecomm.entity.Address;
import com.back.ecomm.entity.Customer;
import com.back.ecomm.entity.Order;
import com.back.ecomm.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address  shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
