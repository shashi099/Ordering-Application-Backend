# Ordering-Application-Backend

## PHASE 1 - USER REGISTRATION

1. Create User Entity
2. Create Users Table
3. Create Repository
4. Create Service
5. Create Controller
6. POST /users/register
7. Store User in MySQL

---------------

* Entity Class
* JPA Annotations
* Dependency Injection
* Repository Layer
* Service Layer
* Controller Layer
* POST APIs
* RequestBody
* Validation


-------------------------
### **NEXT STEP** 
* ✅ Check duplicate email 
* ✅ Password Encryption (BCrypt)
* ✅ Login API
* ✅ Find User by Email
* ✅ Custom Repository Methods
* ✅ Response DTO
* ✅ Clean API Responses

#### And now we have IMPLEMENTED:

* ✅ User Registration
* ✅ Email Validation
* ✅ Duplicate Email Check
* ✅ Password Encryption
* ✅ Login API
* ✅ Custom Repository Methods
* ✅ Request DTO
* ✅ Response DTO
* ✅ Dependency Injection
* ✅ PostgreSQL Integration

## Refactor project like a real production-grade Spring Boot application

* ✅ Custom Exceptions
* ✅ Global Exception Handler
* ✅ Common API Response Format
* ✅ Proper HTTP Status Codes
* ✅ Better Error Messages

#### Old Flow -> New Flow

`Service -> RuntimeException -> Spring Default Error Response`

`Service -> Custom Exception -> Global Exception Handler -> Standard API Response`

### Create Standard API Response
`dto
└── ApiResponse.java`

**Architecture After Refactoring**

Controller -> Service -> Repository -> Database -> Exceptions -> GlobalExceptionHandler -> ApiResponse

* ✅ Production-ready response format
* ✅ Reusable exception handling
* ✅ Proper status codes
* ✅ Clean service code
* ✅ No RuntimeException everywhere


# Restaurant Management Module

* Restaurant Entity
* Restaurant DTOs
* Restaurant Repository
* Restaurant Service
* Restaurant Controller


* POST /restaurants
* GET /restaurants
* GET /restaurants/{id}
* PUT /restaurants/{id}
* DELETE /restaurants/{id}


# Menu Management

Each restaurants has menu items:


**Menu_Items**
----------

#### Database

* menuId
* restaurant_id
* food_name
* price
* description
* image

# Cart Management

**User Can**

* ✅ Add item to cart
* ✅ View cart
* ✅ Update quantity
* ✅ Remove item
* ✅ Clear cart
* ✅ Calculate total amount


**Create Cart Entity**

**cart**
1. id
2. user_id
3. menu_item_id
4. quantity

###### User ------ Cart ------ MenuItem

**Meaning:**
1. One User can have many Cart Items
2. One MenuItem can appear in many carts

**Now Current Scenario**
* ✅ Cart Entity
* ✅ Cart Repository
* ✅ Custom Finder Methods
* ✅ AddToCartRequest DTO
* ✅ CartService
* ✅ CartController
* ✅ POST /cart/add
* ✅ Duplicate Cart Prevention
* ✅ Quantity Auto Increment

**Cart Module Progress**

* ✅ Cart Entity
* ✅ Cart Repository
* ✅ Add To Cart API
* ✅ Prevent Duplicate Items
* ✅ Get User Cart API
* ✅ Cart Item DTO
* ✅ Cart Response DTO
* ✅ Total Amount Calculation
* ✅ Subtotal Calculation

**Cart Module Progress**

* ✅ Calculate Total Amount
* ✅ Update Quantity API
* ✅ Quantity Validation
* ✅ DTO-Based Request Handling

**Cart Module Progress**

* ✅ Cart Entity
* ✅ Cart Repository
* ✅ Add To Cart API
* ✅ Get User Cart API
* ✅ Calculate Total Amount
* ✅ Update Quantity API
* ✅ Quantity Validation
* ✅ Remove Single Cart Item API


# Module 5: Order Management

* ✅ Order Entity
* ✅ OrderStatus Enum
* ✅ Entity Relationships
* ✅ Database Design
* ✅ Order Creation Flow Design

# Order Placement Flow

User Clicks Checkout => Get User => Read Cart Items => Validate Cart Not Empty => Calculate Total Amount => Create Order => Set Status = PLACED => Save Order => Clear Cart =>Return Success Response

**Completed** ✅

* ✅ OrderRepository
* ✅ CreateOrderRequest DTO
* ✅ OrderService
* ✅ OrderServiceImpl
* ✅ OrderController
* ✅ POST /orders/place
* ✅ Read Cart Data
* ✅ Calculate Total
* ✅ Create Order
* ✅ Order Status = PLACED
* ✅ Auto Cart Clear

# Order Item
TO Track the **OrderItem for User** after deletion of the cart items
**Who is can see the MYORDER section** 

**Complete Flow**
`Get User => Get Restaurant => Get Cart Items => Calculate Total => Create Order => Save Order => Create OrderItems => Save OrderItems => Clear Cart => Return Order Response`

# Current Architecture

```
User
 │
 ├── Order #101
 │      │
 │      ├── Pizza x2
 │      └── Burger x1
 │
 ├── Order #102
 │      │
 │      ├── Fries x1
 │      └── Coke x2
 │
 └── Order #103

```