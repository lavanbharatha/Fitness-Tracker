package com.example.yarosh.services.activity;

import com.example.yarosh.dto.ActivityDTO;
import com.example.yarosh.entity.Activity;

import java.util.List;

public interface ActivityService {
    ActivityDTO postActivity(ActivityDTO dto);

    List<ActivityDTO> getActivities();

    List<Activity> getGraphActivities();

    Activity getActivityById(Long id);

}
