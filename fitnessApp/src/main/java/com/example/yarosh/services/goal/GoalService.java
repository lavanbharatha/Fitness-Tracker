package com.example.yarosh.services.goal;

import com.example.yarosh.dto.GoalDTO;
import com.example.yarosh.entity.Goal;

import java.util.List;

public interface GoalService {
    GoalDTO postGoal(GoalDTO goalDTO);
    
    List<GoalDTO> getGoals();
    
    GoalDTO updateStatus(Long id);

    Iterable<Goal> getGraphGoals();

    Goal getGoalById(Long id);
}
