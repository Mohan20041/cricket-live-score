package com.cricketscore.cricket.controller;

import com.cricketscore.cricket.model.Match; 
import com.cricketscore.cricket.service.MatchService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "http://localhost:3000")
public class MatchController {
    
    @Autowired
    private MatchService matchService;
    
    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }
    
    @GetMapping("/status/{status}")
    public List<Match> getMatchesByStatus(@PathVariable String status) {
        return matchService.getMatchesByStatus(status);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable String id) {
        Optional<Match> match = matchService.getMatchById(id);
        return match.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshMatches() {
        matchService.updateMatchesFromCricApi();
        return ResponseEntity.ok("Matches refreshed successfully");
    }
}