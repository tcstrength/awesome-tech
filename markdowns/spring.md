# Spring Framework

## References

* [Vietnamese tutorial](https://loda.me/spring-boot/2)
* [English tutorial](https://www.tutorialspoint.com/spring_boot/index.htm)

## Table of Contents

* [What is Spring](#what-is-spring)
* [Why to learn Spring](#why-to-learn-spring)
* [Applications of Spring](#applications-of-spring)

## What is Spring

Spring is an open source java framework that provides a comprehensive infrastructure support for developing a robus Java application very easily and rapidly.

## Why to learn Spring

The Spring is very lightweight, the basic version of Spring is just around 2MB.

Milions of developers around the World using Spring to develop their Java Applications, a good support over other framework.

## The Spring framework architecture

The Spring Framework organized their features into more than 20 modules. Everytime you want to use a particular module, just import. By using this approach, we can save a small space of application size.

This framework follows Dependency Injection, so, you can easily inject anything you want to your projects.

![Spring architecture](images/spring-architecture.png)

## The Spring Boot

Spring Boot is one of the most important of Spring Framework, the Spring Boot help you developing your web serives more easily by using many more annotation.

When using Spring Boot, you just care about the paths, the services, databases, and one important annotations. The routes and mapping values is Spring Boot responsibility.

Here is a very simple example of Customer Controller

```java
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

   @Autowired
   private CustomerService customerService;

   @GetMapping(value = "{id}")
   public Customer getCustomer(@PathVariable("id") int customerId) {
      return customerService.getCustomer(customerId);
   }

   @GetMapping
   public List<Customer> getCustomers() {
      return customerService.getCustomers();
   }
   @PostMapping
   public Customer addCustomer(@RequestBody Customer customer) {
      return customerService.addCustomer(customer);
   }

   @PutMapping(value = "{id}")
   public Customer updateCustomer(@PathVariable("id") int customerId, @RequestBody Customer customer) {
      return customerService.updateCustomer(customerId, customer);
   }

   @DeleteMapping(value = "{id}")
   public void deleteCustomer(@PathVariable("id") int customerId) {
      customerService.deleteCustomer(customerId);
   }
}
```

The service

```java
public class CustomerService {
    private int customerKey = 0;
    private List<Customer> customers = new CopyOnWriteArrayList<>();

    public Customer addCustomer(Customer customer) {
        customer.setCustomerId(++customerKey);
        customers.add(customer);
        return customer;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer(int customerId) {
        return customers
                .stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .get();
    }

    public Customer updateCustomer(int customerId, Customer customer) {
        var c = getCustomer(customerId);
        c.setCustomerName(customer.getCustomerName());
        return c;
    }

    public void deleteCustomer(int customerId) {
        customers.stream().forEach(c -> {
            if (c.getCustomerId() == customerId) {
                customers.remove(c);
            }
        });
    }
}
```
