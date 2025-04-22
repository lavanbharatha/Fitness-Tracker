package com.example.yarosh.controller;


import com.example.yarosh.dto.ActivityDTO;
import com.example.yarosh.entity.Activity;
import com.example.yarosh.services.activity.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO dto) {

        ActivityDTO createActivity = activityService.postActivity(dto);

        if (createActivity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!!!");
        }
    }

    @GetMapping("/activities")
    public ResponseEntity<?> getActivities() {
        try {
            return ResponseEntity.ok(activityService.getActivities());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!!!");
        }
    }

    @QueryMapping("getGraphActivities")
    public Iterable<Activity> getGraphActivities(){
        return activityService.getGraphActivities();
    }

    @QueryMapping
    public Activity getActivityById(@Argument Long id){
        return activityService.getActivityById(id);
    }
}
