package com.company.checkout;

import com.company.Customer;
import com.company.StaticConstance;
import com.company.balance.Balance;
import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;

import java.util.UUID;

public class MixPaymentCheckoutServiceImpl implements CheckOutService{
    @Override
    public boolean checkout(Customer customer, Double totalAmount) {
        try {


            GiftCardBalance giftCardBalance = findGiftCardBalance(customer.getId());

            final double giftBalance = giftCardBalance.getBalance() - totalAmount;
            if (giftBalance > 0) {
                giftCardBalance.setBalance(giftBalance);
                return true;
            } else {
                CustomerBalance customerBalance = findCustomerBalance(customer.getId());
                final double mixBalance = giftCardBalance.getBalance() + customerBalance.getBalance() - totalAmount;
                if (mixBalance > 0) {
                    giftCardBalance.setBalance(0d);
                    customerBalance.setBalance(mixBalance);
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    private CustomerBalance findCustomerBalance(UUID customerId) {
        for (Balance customerBalance : StaticConstance.CUSTOMER_BALANCE_LIST) {
            if (customerBalance.getCustomerId().toString().equals(customerId.toString())){
                return (CustomerBalance) customerBalance;
            }
        }
        CustomerBalance customerBalance = new CustomerBalance(customerId,0d);
        StaticConstance.CUSTOMER_BALANCE_LIST.add(customerBalance);
        return customerBalance;
    }

    private GiftCardBalance findGiftCardBalance(UUID customerId) {
        for (Balance giftCardBalance : StaticConstance.GIFT_CARD_BALANCE_LIST) {
            if (giftCardBalance.getCustomerId().toString().equals(customerId.toString())){
                return (GiftCardBalance) giftCardBalance;
            }
        }
        GiftCardBalance giftCardBalance = new GiftCardBalance(customerId,0d);
        StaticConstance.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);
        return giftCardBalance;
    }
}
