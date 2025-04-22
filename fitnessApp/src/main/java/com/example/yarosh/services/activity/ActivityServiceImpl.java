package com.example.yarosh.services.activity;

import com.example.yarosh.dto.ActivityDTO;
import com.example.yarosh.entity.Activity;
import com.example.yarosh.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    @Transactional
    public ActivityDTO postActivity(ActivityDTO dto) {

        Activity activity = new Activity();

        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());

        return activityRepository.save(activity).getActivityDTO();
    }

    @Override
    public List<ActivityDTO> getActivities() {

        List<Activity> activities = activityRepository.findAll();

        return activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList());
    }

    @Override
    public List<Activity> getGraphActivities() {

        return activityRepository.findAll();
    }

    @Override
    public Activity getActivityById(Long id) {

        return activityRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
