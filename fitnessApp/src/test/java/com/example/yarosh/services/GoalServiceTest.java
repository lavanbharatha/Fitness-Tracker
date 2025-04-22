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

import com.example.yarosh.dto.GoalDTO;
import com.example.yarosh.entity.Goal;
import com.example.yarosh.repository.GoalRepository;
import com.example.yarosh.services.goal.GoalServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GoalServiceTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GoalServiceImpl goalService;

    private Goal goal;
    private GoalDTO goalDTO;

    @BeforeEach
    public void setUp() throws ParseException {
        goal = new Goal();
        goal.setId(1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        goal.setStartDate(dateFormat.parse("2023-01-01"));
        goal.setEndDate(dateFormat.parse("2023-12-31"));
        goal.setDescription("Test Goal");
        goal.setAchieved(false);

        goalDTO = new GoalDTO();
        goalDTO.setId(2);
        goalDTO.setStartDate(dateFormat.parse("2023-01-01"));
        goalDTO.setEndDate(dateFormat.parse("2023-12-31"));
        goalDTO.setDescription("Test Goal DTO");
        goalDTO.setAchieved(false);
    }

    @Test
    public void testPostGoal() {
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);

        GoalDTO result = goalService.postGoal(goalDTO);

        assertNotNull(result);
        assertEquals(goal.getDescription(), result.getDescription());
        verify(goalRepository, times(1)).save(any(Goal.class));
    }

    @Test
    public void testGetGoals() {
        when(goalRepository.findAll()).thenReturn(Arrays.asList(goal));

        List<GoalDTO> result = goalService.getGoals();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(goalRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateStatus() {
        when(goalRepository.findById(1)).thenReturn(Optional.of(goal));
        goal.setAchieved(true);
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);

        GoalDTO result = goalService.updateStatus(1L);

        assertNotNull(result);
        assertTrue(result.isAchieved());
        verify(goalRepository, times(1)).findById(1);
        verify(goalRepository, times(1)).save(any(Goal.class));
    }

    @Test
    public void testGetGoalById() {
        when(goalRepository.findById(1)).thenReturn(Optional.of(goal));

        Goal result = goalService.getGoalById(1L);

        assertNotNull(result);
        assertEquals(goal.getId(), result.getId());
        verify(goalRepository, times(1)).findById(1);
    }

    @Test
    public void testGetGoalByIdNotFound() {
        when(goalRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            goalService.getGoalById(1L);
        });

        verify(goalRepository, times(1)).findById(1);
    }
}
