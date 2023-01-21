package com.company;

import com.company.balance.Balance;
import com.company.balance.CustomerBalance;
import com.company.balance.GiftCardBalance;
import com.company.category.Category;
import com.company.discount.Discount;
import com.company.order.Order;
import com.company.order.OrderService;
import com.company.order.OrderServiceImpl;

import javax.swing.text.html.CSS;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;



public class Main {
    public static void main(String[] args) {

        DataGenerator.createCustomer();
        DataGenerator.createCategory();
        DataGenerator.createProduct();
        DataGenerator.createBalance();
        DataGenerator.createDiscount();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Customer: ");

        for (int i = 0; i < StaticConstance.CUSTOMER_LIST.size(); i++) {
            System.out.println("Type "+ i + " for customer: " + StaticConstance.CUSTOMER_LIST.get(i).getUsername());
        }
        Customer customer = StaticConstance.CUSTOMER_LIST.get(scanner.nextInt());
        Cart cart = new Cart(customer);

//Start making menu

        while(true){
            System.out.println("What would you like to do? Just type id for selection");
            for (int i = 0; i < prepareMenuOptions().length; i++) {
                System.out.println(i + "-" + prepareMenuOptions()[i]);
            }
            int menuSelection = scanner.nextInt();

            switch (menuSelection){
                case 0: //list categories
                    for (Category category : StaticConstance.CATEGORY_LIST){
                        System.out.println("Category Code: " + category.generateCategoryCode() + " category name: " + category.getName());
                    }
                    break;

                case 1://list products
                    try{

                        for (Product product : StaticConstance.PRODUCT_LIST){
                            System.out.println("Product name " + product.getName() + "Product category name: " + product.getCategoryName());
                        }
                    }catch (Exception e){
                        System.out.println("Product couldnt printed because category not found for product name: " + e.getMessage().split(",")[1]);
                    }
                    break;

                case 2://list discounts
                    for (Discount discount : StaticConstance.DISCOUNT_LIST) {
                        System.out.println("Discount name: " + discount.getName());
                    }
                    break;

                case 3://see balance
                    CustomerBalance cBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance gBalance = findGiftCardBalance(customer.getId());
                    double totalBalance = cBalance.getBalance() + gBalance.getBalance();
                    System.out.println("Total Balance: " + totalBalance);
                    System.out.println("Customer Balance: " + cBalance.getBalance());
                    System.out.println("Gift Card Balance: " + gBalance.getBalance());
                    break;
                case 4://add balance
                    System.out.println("Which Account would you like to add?");
                    CustomerBalance customerBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance giftCardBalance = findGiftCardBalance(customer.getId());
                    System.out.println("Type 1 for Customer Balance: " + customerBalance.getBalance());
                    System.out.println("Type 2 for Gift Card Balance: " + giftCardBalance.getBalance());

                    int balanceAccountSelection = scanner.nextInt();
                    System.out.println("How much you would like to add?");
                    Double additionalAmount = scanner.nextDouble();

                    switch (balanceAccountSelection){
                        case 1:
                            customerBalance.addBalance(additionalAmount);
                            System.out.println("New Customer Balance: " + customerBalance.getBalance());
                            break;
                        case 2:
                            giftCardBalance.addBalance(additionalAmount);
                            System.out.println("New Gift Card Balance: " + giftCardBalance.getBalance());
                            break;
                    }

                    break;
                case 5://place an order
                    Map<Product,Integer> map = new HashMap<>();
                    cart.setProductMap(map);
                    while(true){
                        System.out.println("which product you want to add to your car. For exit product selection type: exit");

                        for(Product product : StaticConstance.PRODUCT_LIST){
                            try {
                                System.out.println("id: " + product.getId() + " price: " + product.getPrice() +
                                        " product category: " + product.getCategoryName() + "stock: " + product.getRemainingStock() +
                                        "product delivery due" + product.getDeliveryDueDate());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        String productId = scanner.next();

                        if (productId.equals("exit")){
                            break;
                        }

                        try {
                            Product product = findProductById(productId);
                            if (!putItemToCartIfStockAvailable(cart,product)){
                                System.out.println("Stock is insufficient.please try again");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Product doesnt exist.please try again");
                            continue;
                        }


                        System.out.println("Do you want to add more product. Type Y for adding more, N for exit");

                        String decision = scanner.next();
                        if(!decision.equals("Y")){
                            break;
                        }
                    }
                    if (cart.getProductMap().isEmpty()){
                        break;
                    }
                    System.out.println("Seems there are discount options. Do you want to see and apply to your cart if it is applicable. For no discount type No");

                    for (Discount discount : StaticConstance.DISCOUNT_LIST){
                        System.out.println("discount id " + discount.getDiscountId() + " discount name: " + discount.getName());
                    }
                    String discountId = scanner.next();
                    if (!discountId.equals("No")){
                        try{
                            Discount discount = findDiscountById(discountId);
                            if (discount.decideDiscountIsApplicableToCart(cart)){
                                cart.setDiscountsId(discount.getDiscountId());
                            }
                        }catch(Exception e){
                            System.out.println(e.getMessage());

                        }
                    }

                    OrderService orderService = new OrderServiceImpl();
                    String result = orderService.placeOrder(cart);

                    if (result.equals("Order has been placed successfully")){
                        System.out.println("Order is successful");
                        updateProductStock(cart.getProductMap());
                        cart.setProductMap(new HashMap<>());
                        cart.setDiscountsId(null);
                    }else{
                        System.out.println(result);
                    }
                    break;

                case 6://see cart
                    System.out.println("Your Cart");
                    if (!cart.getProductMap().keySet().isEmpty()){
                        for (Product product : cart.getProductMap().keySet()) {
                            System.out.println("Product name "+ product.getName() + " count: " + cart.getProductMap().get(product));
                        }
                    }else{
                        System.out.println("Your cart is empty");
                    }
                    break;
                case 7://see you order details
                    printOrdersByCustomerId(customer.getId());
                    break;
                case 8://see your address
                    printAddressByCustomerId(customer);
                    break;
                case 9: //close app
                    System.exit(1);
                    break;
            }

            System.out.println();
            System.out.println();

        }

    }

    private static void printAddressByCustomerId(Customer customer) {


        try{
            for (Address address : customer.getAddress()) {
                System.out.println("Street Name: " + address.getStreetName() + "Building number: " + address.getStreetNumber()+ " ZipCode: " + address.getZipCode()+ "State: "+ address.getState());

            }
        }catch (Exception e){

            System.out.println("Customer didnt provide the Address");
        }

    }

    private static void printOrdersByCustomerId(UUID customerId) {
        for (Order order : StaticConstance.ORDER_LIST) {
            if (order.getCustomerId().toString().equals(customerId.toString())){
                System.out.println("Order status: " + order.getOrderStatus() + " order amount " + order.getPaidAmount() + " order date" + order.getOrderDate());
            }
        }
    }

    private static void updateProductStock(Map<Product, Integer> map) {
        for (Product product : map.keySet()) {
            product.setRemainingStock(product.getRemainingStock() -map.get(product));
        }

    }

    private static boolean putItemToCartIfStockAvailable(Cart cart, Product product) {
        System.out.println("Please provide product count");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        Integer cartCount = cart.getProductMap().get(product);

        if (cartCount != null && product.getRemainingStock() > cartCount + count){
            cart.getProductMap().put(product,cartCount + count);
            return true;
        }else if(product.getRemainingStock() >= count){
            cart.getProductMap().put(product,count);
            return true;
        }
        return false;


    }

    public static CustomerBalance findCustomerBalance(UUID customerId){

        for (Balance customerBalance : StaticConstance.CUSTOMER_BALANCE_LIST) {
            if (customerBalance.getCustomerId().toString().equals(customerId.toString())){
                return (CustomerBalance)customerBalance;
            }
        }
        CustomerBalance customerBalance = new CustomerBalance(customerId,0d);
        StaticConstance.CUSTOMER_BALANCE_LIST.add(customerBalance);
        return customerBalance;
    }

    public static GiftCardBalance findGiftCardBalance(UUID customerId){
        for (Balance giftCardBalance : StaticConstance.GIFT_CARD_BALANCE_LIST) {
            if (giftCardBalance.getCustomerId().toString().equals(customerId.toString())){
                return (GiftCardBalance) giftCardBalance;
            }
        }
        GiftCardBalance giftCardBalance = new GiftCardBalance(customerId,0d);
        StaticConstance.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);
        return giftCardBalance;
    }

    private static String[] prepareMenuOptions(){
        return new String[] {"List Categories","List Products","List Discount", "See Balance", "Add Balance",
                "Place an order","See Cart", "See order details", "See you address", "Close App"};
    }

    private static Product findProductById(String productId) throws Exception{
        for (Product product : StaticConstance.PRODUCT_LIST){
            if (product.getId().toString().equals(productId)){
                return product;
            }
        }
        throw new Exception("Product not found");
    }

    private static Discount findDiscountById(String discountId) throws Exception{
        for (Discount discount : StaticConstance.DISCOUNT_LIST) {
            if (discount.getDiscountId().toString().equals(discountId)){
                return discount;
            }
        }
        throw new Exception("Discount couldnt applied because couldnt found");
    }

}
