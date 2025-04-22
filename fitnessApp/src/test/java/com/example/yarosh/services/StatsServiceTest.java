package com.example.yarosh.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.yarosh.dto.GraphDTO;
import com.example.yarosh.dto.StatsDTO;
import com.example.yarosh.entity.Activity;
import com.example.yarosh.entity.Workout;
import com.example.yarosh.repository.ActivityRepository;
import com.example.yarosh.repository.GoalRepository;
import com.example.yarosh.repository.WorkoutRepository;
import com.example.yarosh.services.stats.StatsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {

    @Mock
    private GoalRepository goalRepository;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private WorkoutRepository workoutRepository;

    @InjectMocks
    private StatsServiceImpl statsService;

    private Activity activity;
    private Workout workout;

    @BeforeEach
    public void setUp() {
        activity = new Activity();
        activity.setId(1L);
        activity.setSteps(1000);
        activity.setDistance(1.0);
        activity.setCaloriesBurned(100);

        workout = new Workout();
        workout.setId(1L);
        workout.setDuration(60);
        workout.setCaloriesBurned(500);
    }

    @Test
    public void testGetStats() {
        when(goalRepository.countAchievedGoals()).thenReturn(5L);
        when(goalRepository.countNotAchievedGoals()).thenReturn(3L);

        when(activityRepository.getTotalSteps()).thenReturn(10000);
        when(activityRepository.getTotalDistance()).thenReturn(50.0);
        when(activityRepository.getTotalActivityCaloriesBurned()).thenReturn(2000);

        when(workoutRepository.getTotalDuration()).thenReturn(600);
        when(workoutRepository.getTotalWorkoutCaloriesBurned()).thenReturn(4000);

        StatsDTO result = statsService.getStats();

        assertNotNull(result);
        assertEquals(5L, result.getAchievedGoals());
        assertEquals(3L, result.getNotAchievedGoals());
        assertEquals(10000, result.getSteps());
        assertEquals(50.0, result.getDistance());
        assertEquals(6000, result.getTotalCaloriesBurned());
        assertEquals(600, result.getDuration());

        verify(goalRepository, times(1)).countAchievedGoals();
        verify(goalRepository, times(1)).countNotAchievedGoals();
        verify(activityRepository, times(1)).getTotalSteps();
        verify(activityRepository, times(1)).getTotalDistance();
        verify(activityRepository, times(1)).getTotalActivityCaloriesBurned();
        verify(workoutRepository, times(1)).getTotalDuration();
        verify(workoutRepository, times(1)).getTotalWorkoutCaloriesBurned();
    }

    @Test
    public void testGetGraphStats() {
        Pageable pageable = PageRequest.of(0, 7);

        when(workoutRepository.findLast7Workouts(pageable)).thenReturn(Arrays.asList(workout));
        when(activityRepository.findLast7Activities(pageable)).thenReturn(Arrays.asList(activity));

        GraphDTO result = statsService.getGraphStats();

        assertNotNull(result);
        assertEquals(1, result.getWorkouts().size());
        assertEquals(1, result.getActivities().size());

        verify(workoutRepository, times(1)).findLast7Workouts(pageable);
        verify(activityRepository, times(1)).findLast7Activities(pageable);
    }
}
