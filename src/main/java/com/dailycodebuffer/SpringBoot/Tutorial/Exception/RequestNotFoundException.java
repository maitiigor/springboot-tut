package com.dailycodebuffer.SpringBoot.Tutorial.Exception;

public class RequestNotFoundException extends Exception {
    
     public RequestNotFoundException(){
          super();
     }
     public RequestNotFoundException(String message){
          super(message);
     }
     public RequestNotFoundException(String message,Throwable cause){
          super(message,cause);
     }
     public RequestNotFoundException(Throwable cause){
          super(cause);
     }
     public RequestNotFoundException(String message, Throwable cause,SuppressWarnings enablWarnings){
          super(message,cause);
     }
}
