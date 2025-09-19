package com.cricketscore.cricket.service;

import com.cricketscore.cricket.model.Match; // Changed from com.cricket.model
import com.cricketscore.cricket.repository.MatchRepository; // Changed from com.cricket.repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private CricApiService cricApiService;
    
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
    
    public List<Match> getMatchesByStatus(String status) {
        return matchRepository.findByMatchStatusOrderByDateDesc(status);
    }
    
    public Optional<Match> getMatchById(String id) {
        return matchRepository.findById(id);
    }
    
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }
    
    public void deleteMatch(String id) {
        matchRepository.deleteById(id);
    }
    
    public void updateMatchesFromCricApi() {
        List<Match> apiMatches = cricApiService.getMatches();
        
        for (Match apiMatch : apiMatches) {
            Optional<Match> existingMatch = matchRepository.findByMatchId(apiMatch.getMatchId());
            
            if (existingMatch.isPresent()) {
                Match matchToUpdate = existingMatch.get();
                matchToUpdate.setTeam1(apiMatch.getTeam1());
                matchToUpdate.setTeam2(apiMatch.getTeam2());
                matchToUpdate.setMatchStatus(apiMatch.getMatchStatus());
                matchToUpdate.setScore(apiMatch.getScore());
                matchToUpdate.setLastUpdated(new java.util.Date());
                
                matchRepository.save(matchToUpdate);
            } else {
                matchRepository.save(apiMatch);
            }
        }
        
        // Clean up old matches that are no longer in the API
        List<Match> allDbMatches = matchRepository.findAll();
        for (Match dbMatch : allDbMatches) {
            boolean foundInApi = apiMatches.stream()
                .anyMatch(apiMatch -> apiMatch.getMatchId().equals(dbMatch.getMatchId()));
            
            if (!foundInApi) {
                matchRepository.delete(dbMatch);
            }
        }
    }
}