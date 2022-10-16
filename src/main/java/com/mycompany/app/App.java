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
        try {
            System.out.println(new App().getMessage());
            Thread.sleep(120000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("running properly.....");
 
         }

    private final String getMessage() {
        return message;
    }

}