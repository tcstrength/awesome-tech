[__Back__](../README.md)

# REST

## What is REST

REST is like a protocol that governs data communication (TCP, UDP, HTTP, ...), the protocols also have their set of rules for example TCP, if you want to transfer data via TCP, you must program your application following the TCP standard contains source port, dest port, checksum, data, ... under transport layer but REST uses HTTP.

TCP | REST
-|-
Transport layer (low level) | HTTP (high level)
Must know destination address including ip & port | The URL end point.
Transfer packets | Send requests, receive responses
Maybe synchronous or asynchronous | Only synchronous, to achive asyncrhonous, using async/await or multithreading.

## Resource play a key role in REST

Anything we do with REST is using resource, everything we can call or view is resource for example image, video, plaintext, audio...

REST using HTTP verbs to control a resource and URN (a subsets of URI) to identify that resource.

For example we have a product resource:

- __GET__ /product/1 we use GET method to ___get___ details of product having ___id 1___
- __POST__ /product with body including product details: we use POST method to ___create___ a new product following the body information.
- __PUT__ /product with body including product details: we use PUT method to ___update___ an existing product following the body information
- __DELETE__ /product/1 to ___delete___ a product have ___id 1___

As we discused above, the product is resource, we use /product to identify that resource and use GET, POST, PUT, DELETE operations to control that resource.

In a banking system, we have many resources like customers, cards, and products, each of them must be controlled by one URN and a resource maybe embeded into another resource like /customers/1/cards but at a time we only use one URL to control one resource. How we can get cards without get customers information, impossible!, at least two requests.

All we following the REST rules, your web service will be RESTful.