package com.mycompany.app;
import java.lang.Thread;  

/**
 * Hello world!
 */
public class App
{

    private final String message = "Hello World!";

    public App() {}

    public static void main(String[] args) {
        System.out.println(new App().getMessage());
        Thread.sleep(1200000);
         }

    private final String getMessage() {
        return message;
    }

}