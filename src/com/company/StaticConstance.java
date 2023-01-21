package com.company;

import com.company.balance.Balance;
import com.company.category.Category;
import com.company.discount.Discount;
import com.company.order.Order;

import java.util.ArrayList;
import java.util.List;

public class StaticConstance {

    public static final List<Customer> CUSTOMER_LIST = new ArrayList<>();

    public static final List<Category> CATEGORY_LIST = new ArrayList<>();
    public static final List<Product> PRODUCT_LIST = new ArrayList<>();

    public static final List<Balance> CUSTOMER_BALANCE_LIST = new ArrayList<>();
    public static final List<Balance> GIFT_CARD_BALANCE_LIST = new ArrayList<>();
    public static final List<Discount> DISCOUNT_LIST = new ArrayList<>();

    public static final List<Order> ORDER_LIST = new ArrayList<>();


}
