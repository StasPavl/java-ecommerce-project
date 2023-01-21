package com.company.checkout;


import com.company.Customer;
import com.company.StaticConstance;
import com.company.balance.Balance;
import com.company.balance.CustomerBalance;

import java.util.UUID;

public class CustomerBalanceCheckoutServiceImpl  implements CheckOutService{
    @Override
    public boolean checkout(Customer customer, Double totalAmount) {
        CustomerBalance customerBalance = findCustomerBalance(customer.getId());
        double finalBalance = customerBalance.getBalance() - totalAmount;

        if (finalBalance>0){
            customerBalance.setBalance(finalBalance);
            return true;
        }else{
            return false;
        }

    }
    private static CustomerBalance findCustomerBalance(UUID customerId){
        for (Balance customerBalance : StaticConstance.CUSTOMER_BALANCE_LIST) {
            if (customerBalance.getCustomerId().toString().equals(customerId.toString())){
                return (CustomerBalance) customerBalance;
            }
        }
        CustomerBalance customerBalance = new CustomerBalance(customerId,0d);
        StaticConstance.CUSTOMER_BALANCE_LIST.add(customerBalance);
        return customerBalance;
    }
}
