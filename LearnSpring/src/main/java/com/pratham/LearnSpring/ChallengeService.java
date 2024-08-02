package com.pratham.LearnSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    private Long nextId = 1L;
    //private static List<Challenge> challenges = new ArrayList<>();


    @Autowired
    private ChallengeRepository challengeRepository;

    public ChallengeService() {

    }


    public List<Challenge> getAllChallenge() {
        return challengeRepository.findAll();
    }

    public Boolean addChallenge(Challenge challenge) {
        if (challenge != null) {
            challenge.setId(nextId++);
            challengeRepository.save(challenge);
            return true;
        }
        return false;
    }


    public Challenge getChallenge(String month) {
        Optional<Challenge> challenge = challengeRepository.findByMonthIgnoreCase(month);
        return challenge.orElse(null);

    }

    public boolean updateChallenge(Long id, Challenge updateChallenge) {
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if (challenge.isPresent()) {
            Challenge challengeToUpdate = challenge.get();
            challengeToUpdate.setMonth(updateChallenge.getMonth());
            challengeToUpdate.setDescription(updateChallenge.getDescription());
            challengeRepository.save(challengeToUpdate);
            return true;
        }
        return false;
    }


    public Boolean deleteChallenge(Long id) {
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if (challenge.isPresent()) {
            challengeRepository.deleteById(id);
            ;
            return true;
        }
        return false;

    }


}
