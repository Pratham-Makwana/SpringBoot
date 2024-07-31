package com.pratham.LearnSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChallengeController {
    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenge")
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenge();
    }

    @PostMapping("/challenges")
    public String addChallenge(@RequestBody Challenge challenge) {
        Boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if (isChallengeAdded)
            return "Challenge is added Successfully";
        else
            return "Challenge is non added";
    }

    @GetMapping("/challenge/{month}")
    public Challenge getChallenge(@PathVariable String month) {
        Challenge challenge = challengeService.getChallenge(month);
        if (challenge != null) {
            return challenge;
        }
        return null;
    }


}
