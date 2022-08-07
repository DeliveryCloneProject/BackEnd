package me.delivery.common.exception;

public class DeliveryExceotion extends RuntimeException{

    public DeliveryExceotion(){};

    public DeliveryExceotion(String message){
        super(message);
    }
}
