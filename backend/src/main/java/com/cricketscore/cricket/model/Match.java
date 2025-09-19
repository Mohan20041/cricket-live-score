package com.cricketscore.cricket.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "matches")
public class Match {
    @Id
    private String id;
    private String matchId; // From CricAPI
    private String team1;
    private String team2;
    private String matchType;
    private String matchStatus; // upcoming, live, completed
    private String date;
    private String venue;
    private String score;
    private String scorecard; // Detailed score
    private Date lastUpdated;
    
    // Default constructor
    public Match() {
        this.lastUpdated = new Date();
    }
    
    // Constructor with parameters
    public Match(String matchId, String team1, String team2, String matchType, 
                 String matchStatus, String date, String venue) {
        this();
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.matchType = matchType;
        this.matchStatus = matchStatus;
        this.date = date;
        this.venue = venue;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScorecard() {
        return scorecard;
    }

    public void setScorecard(String scorecard) {
        this.scorecard = scorecard;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}