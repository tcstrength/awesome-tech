# Spring Interceptor vs Filter

In Spring Framework, we have two components that can use to pre-processing __before__:

* Sending request to controller
* Sending response to client

But they a little bit differences:

* The Interceptors can only be used to pre-processing.
* The Filters are more powerful, they can block/allow exchanging request/response.

## Interceptors

The interceptors in each componenets might have differ behaviors, for example:

* The HandshakeInterceptor will have two required methods if you want to implements them
  * beforeHandshake
  * afterHandshake
* But in a HandlerInterceptor, they have three methods
  * preHandle
  * postHandle
  * afterCompletion

Therefore, just search your component with the following keyword "__[Your Component]__ interceptor"

## Filters

```java
@Component
public class SimpleFilter implements Filter {
   @Override
   public void destroy() {}

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) 
      throws IOException, ServletException {
      System.out.println("Remote Host:"+request.getRemoteHost());
      System.out.println("Remote Address:"+request.getRemoteAddr());
      filterchain.doFilter(request, response);
   }

   @Override
   public void init(FilterConfig filterconfig) throws ServletException {}
}
```
