package com.cricketscore.cricket.scheduler;

import com.cricketscore.cricket.service.MatchService; // Changed from com.cricket.service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchUpdateScheduler {
    
    @Autowired
    private MatchService matchService;
    
    // Update every 2 minutes for live matches
    @Scheduled(fixedRate = 120000)
    public void updateMatches() {
        matchService.updateMatchesFromCricApi();
    }
    
    // Update every hour for all matches
    @Scheduled(fixedRate = 3600000)
    public void updateAllMatches() {
        matchService.updateMatchesFromCricApi();
    }
}