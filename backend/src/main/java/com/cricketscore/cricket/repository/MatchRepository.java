package com.cricketscore.cricket.repository;

import com.cricketscore.cricket.model.Match; 
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {
    List<Match> findByMatchStatus(String status);
    Optional<Match> findByMatchId(String matchId);
    List<Match> findByMatchStatusOrderByDateDesc(String status);
    void deleteByMatchId(String matchId);
}