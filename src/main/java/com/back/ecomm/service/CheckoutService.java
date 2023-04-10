package com.back.ecomm.service;

import com.back.ecomm.dto.Purchase;
import com.back.ecomm.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
