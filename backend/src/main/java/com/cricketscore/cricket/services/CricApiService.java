package com.cricketscore.cricket.service;

import com.cricketscore.cricket.model.Match; // Changed from com.cricket.model
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.*;
import java.util.logging.Logger;

@Service
public class CricApiService {
    
    private static final Logger logger = Logger.getLogger(CricApiService.class.getName());
    
    @Value("${cricapi.key}")
    private String apiKey;
    
    @Value("${cricapi.base-url}")
    private String baseUrl;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Match> getMatches() {
        try {
            String url = baseUrl + "/matches?apikey=" + apiKey;
            
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                List<Map<String, Object>> matchesData = (List<Map<String, Object>>) responseBody.get("matches");
                
                List<Match> matches = new ArrayList<>();
                for (Map<String, Object> matchData : matchesData) {
                    Match match = new Match();
                    match.setMatchId(matchData.get("unique_id").toString());
                    match.setTeam1((String) matchData.get("team-1"));
                    match.setTeam2((String) matchData.get("team-2"));
                    match.setMatchType((String) matchData.get("type"));
                    match.setMatchStatus((String) matchData.get("matchStarted") != null && 
                                        Boolean.parseBoolean(matchData.get("matchStarted").toString()) ? 
                                        "live" : "upcoming");
                    match.setDate((String) matchData.get("date"));
                    match.setVenue((String) matchData.get("venue"));
                    
                    // For live matches, try to get the score
                    if ("live".equals(match.getMatchStatus())) {
                        String score = getMatchScore(match.getMatchId());
                        match.setScore(score);
                    }
                    
                    matches.add(match);
                }
                return matches;
            }
        } catch (Exception e) {
            logger.severe("Error fetching matches from CricAPI: " + e.getMessage());
        }
        return Collections.emptyList();
    }
    
    public String getMatchScore(String matchId) {
        try {
            String url = baseUrl + "/cricketScore?apikey=" + apiKey + "&unique_id=" + matchId;
            
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                return (String) responseBody.get("score");
            }
        } catch (Exception e) {
            logger.severe("Error fetching score for match " + matchId + ": " + e.getMessage());
        }
        return "Score not available";
    }
}