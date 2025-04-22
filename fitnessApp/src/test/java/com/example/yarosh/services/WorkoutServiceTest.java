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

import com.example.yarosh.dto.WorkoutDTO;
import com.example.yarosh.entity.Workout;
import com.example.yarosh.repository.WorkoutRepository;
import com.example.yarosh.services.workout.WorkoutServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WorkoutServiceTest {

    @Mock
    private WorkoutRepository workoutRepository;

    @InjectMocks
    private WorkoutServiceImpl workoutService;

    private Workout workout;
    private WorkoutDTO workoutDTO;

    @BeforeEach
    public void setUp() throws ParseException {
        workout = new Workout();
        workout.setId(1L);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        workout.setDate(dateFormat.parse("2023-01-01"));
        workout.setType("Running");
        workout.setDuration(60);
        workout.setCaloriesBurned(500);

        workoutDTO = new WorkoutDTO();
        workoutDTO.setId(2L);
        workoutDTO.setDate(dateFormat.parse("2023-01-01"));
        workoutDTO.setType("Running");
        workoutDTO.setDuration(60);
        workoutDTO.setCaloriesBurned(500);
    }

    @Test
    public void testPostWorkout() {
        when(workoutRepository.save(any(Workout.class))).thenReturn(workout);

        WorkoutDTO result = workoutService.postWorkout(workoutDTO);

        assertNotNull(result);
        assertEquals(workoutDTO.getDate(), result.getDate());
        verify(workoutRepository, times(1)).save(any(Workout.class));
    }

    @Test
    public void testGetWorkouts() {
        when(workoutRepository.findAll()).thenReturn(Arrays.asList(workout));

        List<WorkoutDTO> result = workoutService.getWorkouts();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(workoutRepository, times(1)).findAll();
    }

    @Test
    public void testGetWorkoutById() {
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(workout));

        Workout result = workoutService.getWorkoutById(1L);

        assertNotNull(result);
        assertEquals(workout.getId(), result.getId());
        verify(workoutRepository, times(1)).findById(1L);
    }
}
