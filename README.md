# Zooplus
Prerequisites:

1. JAVA 8
2. Maven version 3 and above

Deployment Steps :

1. Build the modules first using : mvn clean compile install command.
2. Run the below command to start spring boot application.
   java -jar target/ZooplusApp-1.0-SNAPSHOT.jar com.zooplus.ZooplusStarterApp


Swagger URL:
http://localhost:8080/v2/api-docs

Steps to perform action:
1. Create Customer: 
    http://localhost:8080/createCustomer
    reuqest: {
    "customerName":"German"
    }

2. Create Order:
   http://localhost:8080/createOrder
   request: {
    "customerId":1,
    "amount":10,
    "currency":"EUR"
    }
    
3. Make Payment:
   http://localhost:8080/makePayment
   request: {
    "orderId":1,
    "amount":90,
    "currency":"EUR",
    "payToolType":"CREDIT_CARD"
  }
4. Get Order Details:
   http://localhost:8080/getOrderDetails/1
   response: {
    "errorMsg": null,
    "status": "SUCCESS",
    "orderId": 1,
    "customerId": 1,
    "origAmount": 10,
    "orderBal": 80,
    "orderAmtCurr": "EUR",
    "orderStatus": "SETTLED"
    }
5. Get Customer Details:
   http://localhost:8080/getCustomerDetails/1
   request: {
    "customerId":1
   }
   response: {
    "errorMsg": null,
    "status": "SUCCESS",
    "customerName": "German",
    "customerId": 1,
    "customerBal": 80,
    "customerBalCurr": "EUR"
  }
 6. Get Order Balance
    http://localhost:8080/getOrderBalance/1
    request: {
    "customerId":1
    }
    response: 80 EUR

7. Get Customer Balance:
   http://localhost:8080/getCustomerBalance/1
    response: 80 EUR

8. Get Payment Details for order:
   http://localhost:8080/getPaymentDetails/order/1
   response: List of Payments:
  [
    {
        "errorMsg": null,
        "status": "SUCCESS",
        "orderId": 1,
        "amount": 90,
        "currency": "EUR",
        "paymentStatus": "FULLY_PAID",
        "orderBal": 80,
        "customerRunningBal": 80,
        "payToolType": "CREDIT_CARD",
        "created_at": "2021-12-31T12:48:36.466+00:00",
        "updated_at": "2021-12-31T12:48:36.466+00:00"
    }
  ] 
 
