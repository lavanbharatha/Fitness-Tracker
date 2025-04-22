package com.example.yarosh.repository;

import com.example.yarosh.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {

    @Query("SELECT count(g) from Goal g where g.achieved = true")
    Long countAchievedGoals();

    @Query("SELECT count(g) from Goal g where g.achieved = false")
    Long countNotAchievedGoals();
}
