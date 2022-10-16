package com.mycompany.app;
import java.util.concurrent.*; 

/**
 * Hello world!
 */
public class App
{

    private final String message = "Hello World!";

    public App() {}

    public static void main(String[] args) {
        try{
            long timeToSleep = 120L; 
             // Create a TimeUnit object 
            TimeUnit time = TimeUnit.SECONDS; 

            // System.out.println("Hello World!");
            System.out.println(new App().getMessage());
            time.sleep(timeToSleep);     
               // Get time to sleep     
        }
         catch (InterruptedException e) { 
            System.out.println("Interrupted "
                               + "while Sleeping"); 
        } 
      
        
    }

    private final String getMessage() {

        return message;

    }

}

