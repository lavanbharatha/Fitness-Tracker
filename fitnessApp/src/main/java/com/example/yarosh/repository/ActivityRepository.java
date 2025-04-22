package com.example.yarosh.repository;

import com.example.yarosh.entity.Activity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("select sum(a.steps) from Activity a")
    Integer getTotalSteps();

    @Query("select sum(a.distance) from Activity a")
    Double getTotalDistance();

    @Query("select sum(a.caloriesBurned) from Activity a")
    Integer getTotalActivityCaloriesBurned();

    @Query("select a from Activity a order by a.date desc")
    List<Activity>  findLast7Activities(Pageable pageable);
}
