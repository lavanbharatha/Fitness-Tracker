package com.example.yarosh.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GoalDTO {

    private int id;

    private String description;

    private Date startDate;

    private Date endDate;

    private boolean achieved;

}
