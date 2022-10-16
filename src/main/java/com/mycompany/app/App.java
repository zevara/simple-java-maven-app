package com.mycompany.app;

// /**
//  * Hello world!
//  */
// public class App
// {

//     private final String message = "Hello World!";

//     public App() {}

//     public static void main(String[] args) {
//         System.out.println(new App().getMessage());
//     }

//     private final String getMessage() {
//         return message;
//     }

// }

package com.codacuum.springboothelloworld.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RESTController;
@RESTController
public class HelloWorldController {
@GetMapping(“/hello-world”)
public String HelloWorld(){
return “Hello World”;
}
}