package com.company.checkout;


import com.company.Customer;

public interface CheckOutService {
boolean checkout(Customer customer, Double totalAmount);
}
