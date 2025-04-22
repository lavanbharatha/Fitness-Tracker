package com.example.yarosh.repository;

import com.example.yarosh.entity.Workout;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("select sum(w.duration) from Workout w")
    Integer getTotalDuration();

    @Query("select sum(w.caloriesBurned) from Workout w")
    Integer getTotalWorkoutCaloriesBurned();

    @Query("select w from Workout w order by w.date DESC")
    List<Workout> findLast7Workouts(Pageable pageable);
}
