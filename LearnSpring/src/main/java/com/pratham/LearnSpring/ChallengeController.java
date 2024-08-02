package com.pratham.LearnSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("challenges")
public class ChallengeController {

    private ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping()
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return new ResponseEntity<>(challengeService.getAllChallenge(), HttpStatus.OK);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable("month") String month) {
        Challenge challenge = challengeService.getChallenge(month);
        if (challenge != null) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        Boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if (isChallengeAdded)
            return new ResponseEntity<>("Challenge is added Successfully", HttpStatus.OK);
        else {
            return new ResponseEntity<>("Challenge is non added", HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable("id") Long id, @RequestBody Challenge updateChallenge) {
        try {
            boolean isChallengeUpdated = challengeService.updateChallenge(id, updateChallenge);
            if (isChallengeUpdated) {
                return new ResponseEntity<>("Challenge is updated Successfully", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Challenge is not updated", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Challenge is not updated", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable("id") Long id) {
        try {
            challengeService.deleteChallenge(id);
            return new ResponseEntity<>("Challenge is deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Challenge is not deleted", HttpStatus.NOT_FOUND);
        }

    }


}
