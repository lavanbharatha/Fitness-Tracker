package com.example.yarosh.services.goal;

import com.example.yarosh.dto.GoalDTO;
import com.example.yarosh.entity.Goal;
import com.example.yarosh.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    @Override
    @Transactional
    public GoalDTO postGoal(GoalDTO goalDTO) {
        Goal goal = new Goal();

        goal.setDescription(goalDTO.getDescription());
        goal.setEndDate(goalDTO.getEndDate());
        goal.setStartDate(goalDTO.getStartDate());
        goal.setAchieved(false);

        return goalRepository.save(goal).getGoalDTO();
    }

    @Override
    public List<GoalDTO> getGoals() {
        List<Goal> goals = goalRepository.findAll();

        return goals.stream().map(Goal::getGoalDTO).collect(Collectors.toList());
    }

    @Override
    public GoalDTO updateStatus(Long id) {
        Optional<Goal> goal = goalRepository.findById(Math.toIntExact(id));

        if (goal.isPresent()) {
            Goal updatedGoal = goal.get();
            updatedGoal.setAchieved(true);
            return goalRepository.save(updatedGoal).getGoalDTO();
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Iterable<Goal> getGraphGoals() {
        return goalRepository.findAll();
    }

    @Override
    public Goal getGoalById(Long id) {
        return goalRepository.findById(Math.toIntExact(id)).orElseThrow(EntityNotFoundException::new);
    }
}
