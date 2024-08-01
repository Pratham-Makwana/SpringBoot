package com.pratham.LearnSpring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChallengeService {

    private Long nextId = 1L;
    private static List<Challenge> challenges = new ArrayList<>();

    public ChallengeService() {

    }


    public List<Challenge> getAllChallenge() {
        return challenges;
    }

    public Boolean addChallenge(Challenge challenge) {
        if (challenge != null) {
            challenge.setId(nextId++);
            challenges.add(challenge);
            return true;
        }
        return false;
    }


    public Challenge getChallenge(String month) {
        for (Challenge challenge : challenges) {
            if (challenge.getMonth().equalsIgnoreCase(month)) {
                return challenge;
            }
        }
        return null;
    }

    public static boolean updateChallenge(Long id, Challenge updateChallenge) {
        for (Challenge challenge : challenges) {
            if (challenge.getId().equals(id)) {
                challenge.setMonth(updateChallenge.getMonth());
                challenge.setDescription(updateChallenge.getDescription());
                return true;
            }
        }
        return false;
    }


    public static boolean deleteChallenge(Long id) {
        return challenges.removeIf(challenge -> challenge.getId().equals(id));
//        for (Challenge challenge: challenges) {
//            if (challenge.getId().equals(id)){
//                challenges.remove(id);
//            }
//        }
    }


}
