package com.example.yarosh.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.yarosh.dto.ActivityDTO;
import com.example.yarosh.entity.Activity;
import com.example.yarosh.repository.ActivityRepository;
import com.example.yarosh.services.activity.ActivityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    private Activity activity;
    private ActivityDTO activityDTO;

    @BeforeEach
    public void setUp() throws ParseException {
        activity = new Activity();
        activity.setId(1L);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        activity.setDate(dateFormat.parse("2023-01-01"));
        activity.setSteps(1000);
        activity.setDistance(1.0);
        activity.setCaloriesBurned(100);

        activityDTO = new ActivityDTO();
        activityDTO.setId(2L);
        activityDTO.setDate(dateFormat.parse("2023-01-01"));
        activityDTO.setSteps(1000);
        activityDTO.setDistance(1.0);
        activityDTO.setCaloriesBurned(100);
    }

    @Test
    public void testPostActivity() {
        when(activityRepository.save(any(Activity.class))).thenReturn(activity);

        ActivityDTO result = activityService.postActivity(activityDTO);

        assertNotNull(result);
        assertEquals(activity.getDate(), result.getDate());
        verify(activityRepository, times(1)).save(any(Activity.class));
    }

    @Test
    public void testGetActivities() {
        when(activityRepository.findAll()).thenReturn(Arrays.asList(activity));

        List<ActivityDTO> result = activityService.getActivities();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(activityRepository, times(1)).findAll();
    }

    @Test
    public void testGetActivityById() {
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));

        Activity result = activityService.getActivityById(1L);

        assertNotNull(result);
        assertEquals(activity.getId(), result.getId());
        verify(activityRepository, times(1)).findById(1L);
    }
}
