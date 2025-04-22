package com.example.yarosh.services.stats;

import com.example.yarosh.dto.GraphDTO;
import com.example.yarosh.dto.StatsDTO;

public interface StatsService {
    StatsDTO getStats();
    GraphDTO getGraphStats();
}
