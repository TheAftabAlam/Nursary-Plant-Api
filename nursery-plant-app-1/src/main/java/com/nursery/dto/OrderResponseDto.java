package com.nursery.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponseDto {


    private float amount;
    private int invoiceNumber;
    private LocalDateTime date;
    private String OrderDescription;
    private int orderId;
    
    
    

}
