package com.ticketing.support.dto;


public class CompanyRegistrationResponse{

    private String message;

    public CompanyRegistrationResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}

