package com.example.yarosh.services.workout;

import com.example.yarosh.dto.WorkoutDTO;
import com.example.yarosh.entity.Workout;

import java.util.List;

public interface WorkoutService {
    WorkoutDTO postWorkout(WorkoutDTO workoutDTO);

    List<WorkoutDTO> getWorkouts();

    Iterable<Workout> getGraphWorkouts();

    Workout getWorkoutById(Long id);
}
