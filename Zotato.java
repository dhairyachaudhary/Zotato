/*
Name: Dhairya Chaudhary
Roll Number: 2019035
AP Assignment 2 (Zotato App)
 */

import java.util.*;

class FoodItem {

    /*
    This class represents a food item found in the menu of a restaurant that can be ordered by a customer.
    Each food item has a unique registration ID associated with it.

    ATTRIBUTES:
        * name- Name of the food item [Type: String]. Can be edited.
        * price- Price of the food item [Type: Double]. Can be edited.
        * quantity- Quantity of the food item that is available at a particular restaurant [Type: Integer]. Can be edited.
        * category- Category to which the food item belongs [Type: String]. Can be edited.
        * offer- Offer available on the food item [Type: Integer]. Can be edited.
        * availableAt- The restaurant where the given item is available [Type: Restaurant].This is final (can't be edited).
        * regID- Unique registration ID allotted to each food item [Type: Integer]. This is final (can't be edited).
        * regCounter- It is used to implement the unique regID [Type: Integer]. It is static and increments by 1 for each new food item created.

    METHODS:
        * Contains accessors for all attributes and mutators for those that need to be modified during the course of the program.
        * Contains the class constructor
     */

    private String name;
    private double price;
    private int quantity;
    private String category;
    private int offer;
    private final Restaurant availableAt;

    private final int regID;
    private static int regIDCounter;

    {
        //Initialization Block
        this.regID = ++regIDCounter;
    }

    public FoodItem(String name, int price, int quantity, String category, int offer, Restaurant availableAt) {
        //Class Constructor
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.offer = offer;
        this.availableAt = availableAt;
    }

    //Accessors
    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getCategory() {
        return this.category;
    }

    public int getOffer() {
        return this.offer;
    }

    public int getRegID() {
        return this.regID;
    }

    public Restaurant getAvailableAt() {
        return availableAt;
    }

    //Mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }
}

class Restaurant {

    /*
    This class represents a restaurant registered with the application.
    It contains details about the restaurant and a menu that contains food items available there.
    This is the parent class of all the sub-categories of restaurants

    ATTRIBUTES:
        * name- Name of the restaurant [Type: String].
        * address - Address of the restaurant  [Type: String].
        * menu - Contains all the items available in the restaurant mapped to their unique registration ID [Type: HashMap<Integer, FoodItem>]. We can add and edit menu items.
        * type- Category to which the restaurant  belongs [Type: String]. Can be edited.
        * discount- Overall bill discount offered by the restaurant [Type: Integer].
        * rewardPoints- Points earned by the restaurant from various orders [Type: Double].
        * ordersTaken- Counts the number of orders taken by the restauarnt [Type: Integer]. Incremented each time a new order is placed.
        * owner- The owner of the restaurant [Type: RestaurantOwner].

    METHODS:
        * Contains accessors for all attributes and mutators for those that need to be modified during the course of the program.
        * incOrdersTaken()
        * showMenu()
        * printMenu()
        * print()
        * addToMenu(FoodItem newEntry)
        * menuItem(int atPlace)
     */

    private String name;
    private String address;
    private HashMap<Integer, FoodItem> menu = new HashMap<>();
    private double rewardPoints;
    private int discount;
    private String type;
    private int ordersTaken;
    private RestaurantOwner owner;

    {
        //Initialization block
        ordersTaken = 0;
        discount = 0;
    }

    //Accessors

    protected String getName() {
        return this.name;
    }

    protected double getRewardPoints() {
        return this.rewardPoints;
    }

    protected String getType() {
        return this.type;
    }

    protected String getAddress() {
        return this.address;
    }

    protected int getOrdersTaken() {
        return this.ordersTaken;
    }

    protected int getDiscount() {
        return this.discount;
    }

    //Mutators

    protected void setName(String name) {
        this.name = name;
    }

    protected void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setOwner(RestaurantOwner owner) {
        this.owner = owner;
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    protected void setDiscount(int discount) {
        this.discount = discount;
    }

    public void incOrdersTaken() {
        this.ordersTaken++;
    }

    public void showMenu() {
        //prints all the items in the menu of the restaurant along with their attributes
        System.out.println("Choose Item by Code");
        for (Map.Entry<Integer, FoodItem> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + " " + this.getName() + " - " + entry.getValue().getName() + " " + (int)entry.getValue().getPrice() + " " + entry.getValue().getQuantity() + " " + entry.getValue().getOffer() + "% Off " + entry.getValue().getCategory());
        }
    }

    public void printMenu() {
        //Call the getMenu() function of the RestaurantOwner class
        this.owner.getMenu();
    }

    public void print() {
        //Call the print() function of the RestaurantOwner class
        this.owner.print();
    }

    public void addToMenu(FoodItem newEntry) {
        //Adds new food item to the menu
        this.menu.put(newEntry.getRegID(), newEntry);
    }

    public FoodItem menuItem(int atPlace) {
        //Returns the food item corresponding to a particular Registration ID
        return this.menu.get(atPlace);
    }
}

class FastFoodRestaurant extends Restaurant {
    /*
    Child class of the Restaurant class. Contains the constructor for a Fast Food Restaurant.
     */
    public FastFoodRestaurant(String name, String address) {
        this.setName(name);
        this.setAddress(address);
        this.setType("Fast Food");
    }
}

class AuthenticRestaurant extends Restaurant {
    /*
    Child class of the Restaurant class. Contains the constructor for an Authentic Restaurant.
     */
    public AuthenticRestaurant(String name, String address) {
        this.setName(name);
        this.setAddress(address);
        this.setType("Authentic");
    }
}

class RegularRestaurant extends Restaurant {
    /*
    Child class of the Restaurant class. Contains the constructor for a Regular Restaurant.
     */
    public RegularRestaurant(String name, String address) {
        this.setName(name);
        this.setAddress(address);
        this.setType("Regular");
    }
}

class RestaurantOwner implements User {
    /*
    This class represents a restaurant owner who has a restaurant registered with the application.
    A restaurant owner implements the User interface.

    ATTRIBUTES:
        * restaurantOwned- The restaurant owned by the owner [Type: Restaurant]

    METHODS:
        * Contains an accessor for restaurantOwned.
        * Contains a class constructor. The restaurant owner class composes the restaurant class.
        * getMenu()
        * getRewards()
        * print()
        * newItem()
        * editItem()
        * discountBill(int discount)
     */

    private final Restaurant restaurantOwned;

    public RestaurantOwner(String restaurantName, String type, String address) {
        //Class Constructor
        if (type.equals("Fast Food")) {
            this.restaurantOwned = new FastFoodRestaurant(restaurantName, address);
        } else if (type.equals("Authentic")) {
            this.restaurantOwned = new AuthenticRestaurant(restaurantName, address);
        } else {
            this.restaurantOwned = new RegularRestaurant(restaurantName, address);
        }
        Zotato.registerRestaurant(this.restaurantOwned);
        restaurantOwned.setOwner(this);
    }

    Scanner input = new Scanner(System.in).useDelimiter("\n");

    //Accessors

    protected Restaurant getRestaurantOwned() {
        return restaurantOwned;
    }

    @Override
    public void getMenu() {
        //The menu of the restaurant owner.
        //Lists the options available to the restaurant owner.
        //Different menus for authentic/fast food and regular restaurants
        //Runs an infinite loop of displaying options and performing functions till the user chooses to exit

        boolean status = true;
        String message1 = "Welcome " + this.restaurantOwned.getName() + "\n" +
                "1) Add item\n" +
                "2) Edit item\n" +
                "3) Print Rewards\n" +
                "4) Discount on bill value\n" +
                "5) Exit";

        String message2 = "Welcome " + this.restaurantOwned.getName() + "\n" +
                "1) Add item\n" +
                "2) Edit item\n" +
                "3) Print Rewards\n" +
                "4) Exit";

        while (status) {
            if (this.restaurantOwned.getType().equals("Fast Food") || this.restaurantOwned.getType().equals("Authentic")) {
                System.out.println(message1);
                int nextInstruction = input.nextInt();

                if (nextInstruction == 1) {
                    newItem();
                } else if (nextInstruction == 2) {
                    editItem();
                } else if (nextInstruction == 3) {
                    getRewards();
                } else if (nextInstruction == 4) {
                    System.out.print("Enter offer on total bill value - ");
                    discountBill(input.nextInt());
                } else if (nextInstruction == 5) {
                    status = false;
                }
            } else {
                System.out.println(message2);
                int nextInstruction = input.nextInt();

                if (nextInstruction == 1) {
                    newItem();
                } else if (nextInstruction == 2) {
                    editItem();
                } else if (nextInstruction == 3) {
                    getRewards();
                } else if (nextInstruction == 4) {
                    status = false;
                }
            }
        }
    }

    @Override
    public void getRewards() {
        //Prints the rewards present with the restaurant owned. Used for rating purposes.
        System.out.println("Reward Points - " + (int)this.getRestaurantOwned().getRewardPoints());
    }

    @Override
    public void print() {
        //Prints the restaurant and it's details
        System.out.println(this.restaurantOwned.getName() + ", " + this.restaurantOwned.getAddress() + ", " + this.restaurantOwned.getOrdersTaken());
    }

    @Override
    public void search() {
        this.print();
    }

    public void newItem() {
        //Used by the restaurant owner to add a new item to the menu of the restaurant owned
        System.out.println("Enter food item details");
        System.out.println("Food name:");
        String name = input.next();
        System.out.println("Item price:");
        int price = input.nextInt();
        System.out.println("Item quantity:");
        int quantity = input.nextInt();
        System.out.println("Item category:");
        String category = input.next();
        System.out.println("Offer:");
        int offer = input.nextInt();

        FoodItem newItem = new FoodItem(name, price, quantity, category, offer, this.restaurantOwned);
        this.restaurantOwned.addToMenu(newItem);

        System.out.println(newItem.getRegID() + " " + newItem.getName() + " " + (int)newItem.getPrice() + " " + newItem.getQuantity() + " " + newItem.getOffer() + "% Off  " + newItem.getCategory());
    }

    public void editItem() {
        //Used by the restaurant owner to edit attributes of an item in the menu of the restaurant owned
        this.restaurantOwned.showMenu();
        FoodItem itemPicked = this.restaurantOwned.menuItem(input.nextInt());
        System.out.println("Choose an attribute to edit:\n" +
                "1) Name\n" +
                "2) Price\n" +
                "3) Quantity\n" +
                "4) Category\n" +
                "5) Offer");
        int attributePicked = input.nextInt();

        if (attributePicked == 1) {
            System.out.print("Enter the new Name - ");
            String newName = input.next();
            itemPicked.setName(newName);
        } else if (attributePicked == 2) {
            System.out.print("Enter the new Price - ");
            int newPrice = input.nextInt();
            itemPicked.setPrice(newPrice);
        } else if (attributePicked == 3) {
            System.out.print("Enter the new Quantity - ");
            int newQuantity = input.nextInt();
            itemPicked.setQuantity(newQuantity);
        } else if (attributePicked == 4) {
            System.out.print("Enter the new Category - ");
            String newCategory = input.next();
            itemPicked.setCategory(newCategory);
        } else {
            System.out.print("Enter the new Offer - ");
            int newOffer = input.nextInt();
            itemPicked.setOffer(newOffer);
        }
        System.out.println(itemPicked.getRegID() + " " + this.restaurantOwned.getName() + " - " + itemPicked.getName() + " " + (int)itemPicked.getPrice() + " " + itemPicked.getQuantity() + " " + itemPicked.getOffer()+ "% Off "+ itemPicked.getCategory());
    }

    public void discountBill(int discount) {
        //Adds an overall discount to the bill. Unavailable for regular restaurants.
        this.getRestaurantOwned().setDiscount(discount);
    }
}

class Cart {
    /*
    This class represents the cart of a customer.
    The customer class composes the cart class

    ATTRIBUTES:
        * myCart- All the items present in the cart mapped to their quantity [Type: HashMap<FoodItem, Integer>]
        * cartTotal- Sum total of (item quantity)*(item price)*(item discount) of all the items in the cart [Type: Double]. This is updated each time a new item is added to cart.
        * placeFrom- The restaurant to which the items in myCart belong [Type: Restaurant].
        * owner- The owner of the cart [Type: Customer]. This is final (cannot be edited).
        * bill- This is the value we get after applying restaurant discounts and customer discounts to cartTotal [Type: Double].
        * grandTotal- This is the sum of the bill and the delivery charges that the customer has to pay [Type: Double]. This amount will be subtracted from the customer's rewards and wallet at checkout.

    METHODS:
        * Contains an accessor for restaurantOwned.
        * Contains a class constructor. The restaurant owner class composes the restaurant class.
        * getMenu()
        * getRewards()
        * print()
        * newItem()
        * editItem()
        * discountBill(int discount)
     */

    private HashMap<FoodItem, Integer> myCart = new HashMap<>();
    private double cartTotal;
    private int cartItems;
    private Restaurant placeFrom;
    private final Customer owner;
    private double bill;
    private double grandTotal;

    {
        //Initialization Block
        this.cartTotal = 0;
        this.cartItems = 0;
        this.bill = 0;
        this.cartTotal = 0;
    }

    public Cart(Customer owner) {
        //Class Constructor
        this.owner = owner;
    }

    public void addItem(FoodItem toBeAdded, int quantity) {
        //Adds new item to myCart
        myCart.put(toBeAdded, quantity);
        cartItems = cartItems + quantity;
        toBeAdded.setQuantity(toBeAdded.getQuantity() - quantity);
        this.placeFrom = toBeAdded.getAvailableAt();
        cartTotal = cartTotal + (toBeAdded.getPrice() * quantity * (100 - toBeAdded.getOffer()) / 100);
    }

    public void printCart() {
        /*
         * Prints all the items present in cart along with attributes and quantity
         * Mentions the delivery charges that the user will have to pay
         */

        System.out.println("Items in Cart - ");
        for (Map.Entry<FoodItem, Integer> entry : myCart.entrySet()) {
            System.out.println(entry.getKey().getRegID() + " " + entry.getKey().getAvailableAt().getName() + " - " + entry.getKey().getName() + " - " + (int)entry.getKey().getPrice() + " - " + entry.getValue() + " - " + entry.getKey().getOffer() + "% Off");
        }
        System.out.println("Delivery Charge - " + owner.getDeliveryCharge() + "/-");
    }

    public double checkOut() {
        /*
         * Calculates the bill and the grandTotal
         * returns grandTotal
         */

        bill = cartTotal * (100 - placeFrom.getDiscount()) / 100;

        if (placeFrom.getType().equals("Authentic")) {
            if (bill > 100) {
                bill = bill - 50;
            }
        }

        if (bill > owner.getActivationPrice()) {
            bill = bill - owner.getCustomerDiscount();
        }

        grandTotal = bill + owner.getDeliveryCharge();
        return grandTotal;
    }

    public void makeChanges() {
        /*
         * Checks if customer is eligible to checkout, if not it empties the cart
         * Confirms purchase with the user
         * Deducts money from the customer's account
         * Calculates and adds reward points to both the customer account and restaurant owner's restaurant
         * Increments the orders taken parameter of the restaurant where the items are from
         * Adds the transaction and delivery charges to the company's account
         * Adds the order details to the old orders of the user
         * Resets the cart
         */

        if (grandTotal <= owner.getWallet() + owner.getReward()) {

            if (grandTotal <= owner.getReward()) {
                owner.setReward(owner.getReward() - grandTotal);
            } else {
                owner.setWallet(owner.getWallet() + owner.getReward() - grandTotal);
                owner.setReward(0);
            }

            double rewardPoints;

            if (placeFrom.getType().equals("Authentic")) {
                rewardPoints = 25* (int)(bill/200);
            } else if (placeFrom.getType().equals("Fast Food")) {
                rewardPoints = 10 * (int)(bill/150);
            } else {
                rewardPoints = 5 * (int)(bill/100);
            }

            owner.setReward(owner.getReward() + rewardPoints);
            placeFrom.setRewardPoints(placeFrom.getRewardPoints() + rewardPoints);

            placeFrom.incOrdersTaken();

            Zotato.setTransactionCharges(Zotato.getTransactionCharges() + (bill / 100));
            Zotato.setDeliveryCharges(Zotato.getDeliveryCharges() + owner.getDeliveryCharge());

            System.out.println(this.cartItems + " items successfully bought for INR " + grandTotal + "/-");
            Order thisOrder = new Order(placeFrom, grandTotal, owner.getDeliveryCharge());
            thisOrder.addItems(myCart);
            owner.addOrder(thisOrder);
        } else {
            System.out.println("Cart Total exceeds wallet and rewards\n" + "Emptying Cart");
        }

        myCart.clear();
        cartTotal = 0;
        cartItems = 0;
    }
}

class Order {
    /*
    This class represents an order made by the customer. It is used to keep track of the old orders.
    The cart class composes the Order class.

    ATTRIBUTES:
        * items- All the items present in the cart when the order was checked out [Type: HashMap<FoodItem, Integer>]. Each item is mapped to the quantity.
        * orderedFrom- The restaurant the order was placed from [Type: Restaurant].
        * totalBill- The value of the bill when the order was checked out [Type: Double].
        * deliveryCharge- The delivery charge paid by the user [Type: Integer].

        All the attributes of Cart class are final and cannot be edited.

    METHODS:
        * Contains a class constructor which is called from the cart class.
        * addItems()
        * print()
     */

    private final HashMap<FoodItem, Integer> items = new HashMap<>();
    private final Restaurant orderedFrom;
    private final double totalBill;
    private final int deliveryCharge;

    public Order(Restaurant orderedFrom, double totalBill, int deliveryCharge) {
        //Class Constructor
        this.orderedFrom = orderedFrom;
        this.totalBill = totalBill;
        this.deliveryCharge = deliveryCharge;
    }

    public void addItems(HashMap<FoodItem, Integer> oldCart) {
        //Used to add items from myCart of Cart class to items of Order class
        for (Map.Entry<FoodItem, Integer> entry : oldCart.entrySet()) {
            items.put(entry.getKey(), entry.getValue());
        }
    }

    public void print() {
        //Prints details of the order
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey().getName() + ", " + entry.getValue() + " for Rs. " + (int)entry.getKey().getPrice());
        }
        System.out.println("From " + this.orderedFrom.getName() + " and delivery charge Rs. " + this.deliveryCharge);
        System.out.println("Total Rs. " + this.totalBill);
    }
}

class Customer implements User {
    /*
    This class represents a customer registered with the application.
    A Customer implements the User interface.

    ATTRIBUTES:
        * name- Name of the customer [Type: String].
        * address - Address of the customer  [Type: String].
        * wallet - The money present in the customer's wallet [Type: Double].
        * reward- reward points of the customer [Type: Double]. Updated each time an order is placed.
        * deliveryCharge- the charges associated with the Customer [Type: Integer]. Depends on type of customer.
        * type- denotes the type of the restaurant [Type: String].
        * customerCart- Cart of the customer where items are added [Type: Cart].
        * customerDiscount- The discount availed by the customer [Type: Integer]. Depends on type of customer.
        * activationPrice - The bill amount above which customer discount is activated [Type: Integer].
        * oldOrders - contains details about the 10 previous orders [Type: ArrayList<Order>].

    METHODS:
        * Contains an accessor for restaurantOwned.
        * Contains a class constructor. The restaurant owner class composes the restaurant class.
        * getMenu()
        * getRewards()
        * print()
        * addItem()
        * checkout()
        * addItem(FoodItem toBeAdded, int quantity)
        * addOrder(Order toAdd)
        * printRecent()
        * printRestaurant()
     */

    private String name;
    private String address;
    private double wallet;
    private double reward;
    private int deliveryCharge;
    private String type;
    private Cart customerCart;
    private int customerDiscount;
    private int activationPrice;

    private ArrayList<Order> oldOrders = new ArrayList<>();

    {
        //Initialization block
        this.wallet = 1000;
        this.reward = 0;
        Zotato.registerCustomer(this);
        customerCart = new Cart(this);
    }

    Scanner input = new Scanner(System.in);

    //Accessors

    protected String getName() {
        return this.name;
    }

    protected double getWallet() {
        return this.wallet;
    }

    protected double getReward() {
        return this.reward;
    }

    protected int getDeliveryCharge() {
        return this.deliveryCharge;
    }

    protected String getType() {
        return this.type;
    }

    protected int getCustomerDiscount() {
        return customerDiscount;
    }

    protected int getActivationPrice() {
        return activationPrice;
    }

    //Mutators

    protected void setName(String name) {
        this.name = name;
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    protected void setReward(double reward) {
        this.reward = reward;
    }

    protected void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setWallet(double wallet) {
        this.wallet = wallet;
    }

    protected void setCustomerDiscount(int customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    protected void setActivationPrice(int activationPrice) {
        this.activationPrice = activationPrice;
    }

    @Override
    public void getMenu() {
        //The menu of the Customer.
        //Lists the options available to the customer.
        //Runs an infinite loop of displaying options and performing functions till the user chooses to exit

        boolean status = true;
        String message = "Welcome " + this.name + "\n" +
                "Customer Menu\n" +
                "1) Select Restaurant\n" +
                "2) checkout cart\n" +
                "3) Reward won\n" +
                "4) print the recent orders\n" +
                "5) Exit";

        while (status) {
            System.out.println(message);
            int nextInstruction = input.nextInt();

            if (nextInstruction == 1) {
                pickRestaurant();
            }

            if (nextInstruction == 2) {
                checkout();
            }

            if (nextInstruction == 3) {
                getRewards();
            }

            if (nextInstruction == 4) {
                printRecent();
            }

            if (nextInstruction == 5) {
                status = false;
            }
        }
    }

    @Override
    public void getRewards() {
        //Prints the reward points earned by the customer
        System.out.println("Reward Points - " + (int)this.getReward());
    }

    @Override
    public void print() {
        //prints customer details
        if (this.type.equals("Special") || this.type.equals("Elite")){
            System.out.println(this.name + "("+this.type+"), " + this.address + ", " + this.wallet + "/-");
        } else {
            System.out.println(this.name + ", " + this.address + ", " + this.wallet + "/-");
        }
    }

    @Override
    public void search() {
        this.print();
    }

    private void addItem(FoodItem toBeAdded, int quantity) {
        //Adds item to cart
        this.customerCart.addItem(toBeAdded, quantity);
    }

    private void checkout() {
        //Checks our cart
        this.customerCart.printCart();
        System.out.println("Total order value - " + this.customerCart.checkOut() + "/-");
        System.out.println("1) Proceed to checkout");
        int check = input.nextInt();
        if (check == 1) {
            if (oldOrders.size() == 10) {
                oldOrders.remove(0);
            }
            this.customerCart.makeChanges();
        }
    }

    public void addOrder(Order toAdd) {
        //Adds an order to oldOrders (used when user checks our cart)
        oldOrders.add(toAdd);
    }

    public void printRecent() {
        //Prints 10 previous orders
        System.out.println("Showing last 10 Orders");

        int i = 0;

        while (i != oldOrders.size()) {
            System.out.println("Order " + (i + 1));
            oldOrders.get(i).print();
            i++;
        }
    }

    public void pickRestaurant() {
        //Allows user to choose a restaurant to choose from, view its menu and pick items from it
        System.out.println("Choose Restaurant");
        Zotato.printRestaurants();
        int picked = input.nextInt();
        Restaurant chosen = Zotato.restaurantAt(picked - 1);
        chosen.showMenu();
        int itemPicked = input.nextInt();
        System.out.println("Enter Item Quantity ");
        int quantity = input.nextInt();
        if (quantity <= chosen.menuItem(itemPicked).getQuantity()) {
            addItem(chosen.menuItem(itemPicked), quantity);
            System.out.println("Items added to Cart");
        } else {
            System.out.println("Given quantity not present");
        }
    }
}

class EliteCustomer extends Customer {
    /*
    Child class of the Customer class. Contains the constructor for an Elite Customer.
    */

    public EliteCustomer(String name, String address) {
        this.setName(name);
        this.setAddress(address);
        this.setDeliveryCharge(0);
        this.setType("Elite");
        this.setCustomerDiscount(50);
        this.setActivationPrice(200);
    }
}

class SpecialCustomer extends Customer {
    /*
    Child class of the Customer class. Contains the constructor for a Special Customer.
    */

    public SpecialCustomer(String name, String address) {
        this.setName(name);
        this.setAddress(address);
        this.setDeliveryCharge(20);
        this.setType("Special");
        this.setCustomerDiscount(25);
        this.setActivationPrice(200);
    }
}

class RegularCustomer extends Customer {
    /*
    Child class of the Customer class. Contains the constructor for a Regular Customer.
    */

    public RegularCustomer(String name, String address) {
        this.setName(name);
        this.setAddress(address);
        this.setDeliveryCharge(40);
        this.setType("Regular");
        this.setCustomerDiscount(0);
        this.setActivationPrice(0);
    }
}

public class Zotato {
    /*
    This is the class used to call the main method
    It additionally contains company account details. These are static since there is only one company.
    It also has the database of all the customers and restaurants registered with the company.

    ATTRIBUTES:
        * registeredRestaurants- All the restaurants registered with the company [Type: HashMap<Integer, Restaurant>]
        * registeredCustomers- All the customers registered with the company [Type: HashMap<Integer, Customer>].
        * transactionCharges- Contains the delivery transaction collected by the company [Type: Double]. It is 1% of the total bill value for every order.
        * deliveryCharges- Contains the delivery charges collected by the company [Type: Integer]. It is different for different types of customers.

    METHODS:
        * Contains an accessor for restaurantOwned.
        * Contains a class constructor. The restaurant owner class composes the restaurant class.
        * getMenu()
        * getRewards()
        * print()
        * newItem()
        * editItem()
        * discountBill(int discount)
     */

    private static final HashMap<Integer, Restaurant> registeredRestaurants = new HashMap<>();
    private static final HashMap<Integer, Customer> registeredCustomers = new HashMap<>();
    private static double transactionCharges;
    private static int deliveryCharges;

    public Zotato() {
        //
    }

    //Accessors

    public static double getTransactionCharges() {
        return transactionCharges;
    }

    public static int getDeliveryCharges() {
        return deliveryCharges;
    }

    //Mutators

    public static void setTransactionCharges(double transCharges) {
        transactionCharges = transCharges;
    }

    public static void setDeliveryCharges(int delCharges) {
        deliveryCharges = delCharges;
    }

    //Database

    RestaurantOwner o1 = new RestaurantOwner("Diggin", "Fast Food", "Chanakyapuri");
    RestaurantOwner o2 = new RestaurantOwner("Brewbakes", "Other", "IIIT Delhi");
    RestaurantOwner o3 = new RestaurantOwner("Social", "Authentic", "Nehru Place");
    RestaurantOwner o4 = new RestaurantOwner("Big Chill", "Fast Food", "DLF Saket");
    RestaurantOwner o5 = new RestaurantOwner("UCH", "Other", "Connaught Place");
    RestaurantOwner o6 = new RestaurantOwner("The All American Diner", "Authentic", "Lodhi Road");
    RestaurantOwner o7 = new RestaurantOwner("iHOP", "Other", "CityWalk");
    RestaurantOwner o8 = new RestaurantOwner("Mc Donald's", "Fast Food", "Kalkaji");
    RestaurantOwner o9 = new RestaurantOwner("Nandos", "Other", "Nehru Place");
    RestaurantOwner o10 = new RestaurantOwner("Irish House", "Authentic", "Epicuria");
    RestaurantOwner o11 = new RestaurantOwner("Laidback Cafe", "Authentic", "Saket");
    RestaurantOwner o12 = new RestaurantOwner("Baskin-Robbins", "Other", "CR Park");
    RestaurantOwner o13 = new RestaurantOwner("Dunkin Donuts", "Fast Food", "GK2");
    RestaurantOwner o14 = new RestaurantOwner("Theobroma", "Other", "CityWalk");

    Customer c1 = new SpecialCustomer("Rishit", "Chandigarh");
    Customer c2 = new EliteCustomer("Ishika", "South Extension");
    Customer c3 = new EliteCustomer("Dhairya", "Kalkaji");
    Customer c4 = new SpecialCustomer("Karan", "Connaught Place");
    Customer c5 = new RegularCustomer("Gaurav", "Saket");
    Customer c6 = new SpecialCustomer("Aairah", "Sarita Vihar");
    Customer c7 = new RegularCustomer("Bassam", "Dubai");
    Customer c8 = new RegularCustomer("Aaloke", "CR Park");

    Scanner input = new Scanner(System.in);

    public static void registerRestaurant(Restaurant r) {
        //Adds a new restaurant to the restaurants registered with the app
        registeredRestaurants.put(registeredRestaurants.size(), r);
    }

    public static void registerCustomer(Customer c) {
        //Adds a new customer to the customers registered with the app
        registeredCustomers.put(registeredCustomers.size(), c);
    }

    public void companyStatus() {
        //Prints the details about the resources collected by the company
        double balance = deliveryCharges + transactionCharges;
        System.out.println("Total Company Balance - INR " + balance + "/-");
        System.out.println("Total Delivery Charges Collected - INR " + deliveryCharges + "/-");
    }

    public static void printRestaurants() {
        //Prints all the restaurants registered with the application and their attributes
        for (Map.Entry<Integer, Restaurant> entry : registeredRestaurants.entrySet()) {
            System.out.print(entry.getKey() + 1 + ") ");
            if (entry.getValue().getType().equals("Fast Food") || entry.getValue().getType().equals("Authentic")) {
                System.out.print(entry.getValue().getName() + " (" + entry.getValue().getType() + ")" + "\n");
            } else {
                System.out.print(entry.getValue().getName() + "\n");
            }
        }
    }

    public void printCustomers() {
        //Prints all the customers registered with the application and their attributes
        for (Map.Entry<Integer, Customer> entry : registeredCustomers.entrySet()) {
            System.out.print(entry.getKey() + 1 + ". ");
            if (entry.getValue().getType().equals("Elite") || entry.getValue().getType().equals("Special")) {
                System.out.print(entry.getValue().getName() + " (" + entry.getValue().getType() + ")" + "\n");
            } else {
                System.out.print(entry.getValue().getName() + "\n");
            }
        }
    }

    private void OwnerLoginMenu() {
        //Displays the login menu for a restaurant owner
        System.out.println("Choose Restaurant");

        printRestaurants();

        int picked = input.nextInt();
        registeredRestaurants.get(picked - 1).printMenu();
    }

    public static Restaurant restaurantAt(int positionAt) {
        //Gets the restaurant at a position in the list of registered restaurants
        return registeredRestaurants.get(positionAt);
    }

    private void CustomerLoginMenu() {
        //Displays the login menu for a customer
        printCustomers();

        int picked = input.nextInt();
        registeredCustomers.get(picked - 1).getMenu();
    }

    public void detailsMenu() {
        //Menu to print details of the users (customers or restaurant owners)
        System.out.println("1) Customer List\n" +
                "2) Restaurant List");

        int picked = input.nextInt();

        if (picked == 1) {
            printCustomers();
            int choice = input.nextInt();
            registeredCustomers.get(choice - 1).print();
        } else {
            printRestaurants();
            int choice = input.nextInt();
            registeredRestaurants.get(choice - 1).print();
        }
    }

    public void parentMenu() {
        //Prints the parent menu
        boolean status = true;
        String message = "Welcome to Zotato:\n" +
                "1) Enter as Restaurant Owner\n" +
                "2) Enter as Customer\n" +
                "3) Check User Details\n" +
                "4) Company Account details\n" +
                "5) Exit";

        while (status) {
            System.out.println(message);
            int nextInstruction = input.nextInt();

            if (nextInstruction == 1) {
                OwnerLoginMenu();
            }

            if (nextInstruction == 2) {
                CustomerLoginMenu();
            }

            if (nextInstruction == 3) {
                detailsMenu();
            }

            if (nextInstruction == 4) {
                companyStatus();
            }

            if (nextInstruction == 5) {
                status = false;
            }
        }
    }

    public static void main(String[] args) {
        //Main method
        Zotato app = new Zotato();
        app.parentMenu();
    }
}
