# Food_Delivery_console


This is a console-based Food Delivery System implemented in Java. It allows users to interact as either an Admin or a Customer and perform relevant operations. The system demonstrates object-oriented programming principles and uses several design patterns including Factory, Template, and State. File I/O and serialization are used for persistent data storage.

ADMIN PANEL:
------------
1. Add food items
2. Remove food items
3. Update food items
4. Search food items by full or partial name
5. View all food items
6. Add delivery boys
7. Remove delivery boys
8. Update delivery boys
9. View all delivery boys

CUSTOMER PANEL:
---------------
1. View all available food items
2. Search food items by full or partial name
3. Add food items to cart
4. Remove food items from cart
5. View cart
6. Place order:
    - Select delivery time slot
    - Assign delivery boy (if available)
    - Select payment method (Cash, UPI, Card)
7. View final order summary with:
    - GST applied
    - Extra payment method charges
    - Assigned delivery boy and time slot

--------------------------------------------------
PROJECT STRUCTURE
--------------------------------------------------

com.aurionpro.model
-------------------
- Food.java              : Represents a food item
- LineItem.java          : Represents each item in the customer cart
- Order.java             : Represents an order
- Customer.java          : Represents a customer
- Admin.java             : Represents an admin
- DeliveryBoy.java       : Represents a delivery person

com.aurionpro.service
---------------------
- AdminService.java      : Admin functionalities
- CustomerService.java   : Customer functionalities

com.aurionpro.util
------------------
- FoodUtility.java       : Operations for searching, adding, updating food
- OrderUtility.java      : Operations for order placement, payment handling
- ProductStorage.java    : Handles serialization and deserialization of food and delivery boy data

com.aurionpro.exception
-----------------------
- ProductNotFoundException.java
- DeliveryBoyNotFoundException.java
- DataValidationException.java
- PaymentException.java

com.aurionpro.test
------------------
- FoodTest.java          : Main class for running the application

--------------------------------------------------
PAYMENT CHARGES
--------------------------------------------------

| Payment Method       | Extra Charge |
|----------------------|--------------|
| Cash on Delivery     | ₹15          |
| UPI                  | ₹13          |
| Card                 | ₹0           |

*GST is applied to the total food cost.*

--------------------------------------------------
HOW TO RUN
--------------------------------------------------

1. Run the `FoodTest.java` class.
2. Select user type: Admin or Customer.
3. Follow the console-based menu instructions.

Note: Data is stored using serialization to retain food Product list.

--------------------------------------------------
SEARCH FUNCTIONALITY
--------------------------------------------------

Both Admin and Customer can search food items by full or **partial name match** (case-insensitive).
