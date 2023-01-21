package com.company.order;

import com.company.Cart;
import com.company.StaticConstance;
import com.company.checkout.CheckOutService;
import com.company.checkout.CustomerBalanceCheckoutServiceImpl;
import com.company.checkout.MixPaymentCheckoutServiceImpl;
import com.company.discount.Discount;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class OrderServiceImpl implements OrderService{
    @Override
    public String placeOrder(Cart cart) {
        double amountAfterDiscount = cart.calculateCartTotalAmount();

        if (cart.getDiscountsId() != null){
            Discount discount = null;
            try {
                discount = findDiscountById(cart.getDiscountsId());
                amountAfterDiscount = discount.calculateCartAmountAfterDiscountApplied(amountAfterDiscount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which payment option you would like to choose, Type 1 : Customer balance, Type 2 : Mix (gift card + customer balance)");
        int paymentOption = scanner.nextInt();
        boolean checkoutResult = false;

        switch (paymentOption){
            case 1:
                CheckOutService customerBalanceCheckoutService = new CustomerBalanceCheckoutServiceImpl();
                checkoutResult = customerBalanceCheckoutService.checkout(cart.getCustomer(),amountAfterDiscount);
                break;
            case 2:
                CheckOutService mixPaymentCheckoutService = new MixPaymentCheckoutServiceImpl();
                checkoutResult = mixPaymentCheckoutService.checkout(cart.getCustomer(),amountAfterDiscount);
                break;
        }
        if (checkoutResult){
            Order order = new Order(UUID.randomUUID(), LocalDateTime.now(), cart.calculateCartTotalAmount(), amountAfterDiscount, cart.calculateCartTotalAmount() - amountAfterDiscount,cart.getCustomer().getId(),"Placed", cart.getProductMap().keySet());
            StaticConstance.ORDER_LIST.add(order);
            return "Order has been placed successfully";

        }else{
            return "Balance is insufficient. Please add money to your one of Balances and try again.";
        }
    }
    private Discount findDiscountById (UUID discountId) throws Exception{
        for (Discount discount : StaticConstance.DISCOUNT_LIST) {
            if (discount.getDiscountId().toString().equals(discountId.toString())){
                return discount;
            }
        }
        throw new Exception("Discount couldnt found");
    }
}
