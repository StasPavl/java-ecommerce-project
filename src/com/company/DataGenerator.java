package com.company;

import com.company.balance.Balance;
import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;
import com.company.category.Category;
import com.company.category.Electronic;
import com.company.category.Furniture;
import com.company.category.SkinCare;
import com.company.discount.AmountBasedDiscount;
import com.company.discount.Discount;
import com.company.discount.RateBasedDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataGenerator {

    public static void createCustomer(){

        Address address1Customer1 = new Address("7925", "Jones Branch Dr", "apr 329","22009","VA");
        Address address2Customer1 = new Address("79", "Jones Carl rd", "House","22009","VA");

        Address address1Customer2 = new Address("25", "Lee Hwy", "apr 9","22009","VA");


        List<Address> customer1AddressList = new ArrayList<>();
        customer1AddressList.add(address1Customer1);
        customer1AddressList.add(address2Customer1);


        Customer customer1 = new Customer(UUID.randomUUID(),"ozzy","ozzy@cydeo.com",customer1AddressList);

        Customer customer2 = new Customer(UUID.randomUUID(),"stan","lichfb@gmail.com");

        StaticConstance.CUSTOMER_LIST.add(customer1);
        StaticConstance.CUSTOMER_LIST.add(customer2);
    }
    public static void createCategory(){
        Category category1 = new Electronic(UUID.randomUUID(),"Electronic");
        Category category2 = new Furniture(UUID.randomUUID(),"Furniture");
        Category category3 = new SkinCare(UUID.randomUUID(),"SkinCare");

        StaticConstance.CATEGORY_LIST.add(category1);
        StaticConstance.CATEGORY_LIST.add(category2);
        StaticConstance.CATEGORY_LIST.add(category3);
    }
    public static void createProduct(){
        Product product1 =
                new Product(UUID.randomUUID(),"PS5",230.72, 7,7,StaticConstance.CATEGORY_LIST.get(0).getId());
        Product product2 = new Product(UUID.randomUUID(),"XBOX", 120.34,15,15,StaticConstance.CATEGORY_LIST.get(0).getId());
        Product product3 = new Product(UUID.randomUUID(),"Chair", 30.87,85,85,StaticConstance.CATEGORY_LIST.get(1).getId());
        Product product4 = new Product(UUID.randomUUID(),"Milk", 2.87,85,85,UUID.randomUUID());

        StaticConstance.PRODUCT_LIST.add(product1);
        StaticConstance.PRODUCT_LIST.add(product2);
        StaticConstance.PRODUCT_LIST.add(product3);
        StaticConstance.PRODUCT_LIST.add(product4);
    }
    public static void createBalance(){

        Balance customerBalance = new CustomerBalance(StaticConstance.CUSTOMER_LIST.get(0).getId(), 450.00);
        Balance giftCardBalance = new GiftCardBalance(StaticConstance.CUSTOMER_LIST.get(1).getId(), 500.00);

        StaticConstance.CUSTOMER_BALANCE_LIST.add(customerBalance);
        StaticConstance.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);

    }
    public static void createDiscount() {

        Discount amountBasedDiscount = new AmountBasedDiscount(UUID.randomUUID(), "Buy 250$ free 50$",250.00,50.00);
        Discount rateBasedDiscount = new RateBasedDiscount(UUID.randomUUID(),"Buy 500$ free 15%", 500.00, 15.00);

        StaticConstance.DISCOUNT_LIST.add(amountBasedDiscount);
        StaticConstance.DISCOUNT_LIST.add(rateBasedDiscount);
    }

}
