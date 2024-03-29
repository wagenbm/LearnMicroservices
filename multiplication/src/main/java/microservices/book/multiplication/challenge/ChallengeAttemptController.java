package microservices.book.multiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    
    private final ChallengeService challengeService;
    @PostMapping
    ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO
    challengeAttemptDTO) {

        log.info("Received new attempt from {}", challengeAttemptDTO.getUserAlias());
        return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDTO));

    }

    @GetMapping
    ResponseEntity<List<ChallengeAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(
                challengeService.getStatsForUser(alias)
        );
    }

}
