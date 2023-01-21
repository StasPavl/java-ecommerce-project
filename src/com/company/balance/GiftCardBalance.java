package com.company.balance;

import java.util.UUID;

public class GiftCardBalance extends Balance{

    public GiftCardBalance(UUID customerId, Double balance) {
        super(customerId, balance);
    }

    @Override
    public Double addBalance(Double additionalBalance) {

        double promotionAmount = additionalBalance * 10 / 100;

        setBalance(getBalance()+ additionalBalance + promotionAmount);

        return getBalance();
    }
}
