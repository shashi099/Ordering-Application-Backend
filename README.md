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









