package microservices.book.multiplication.challenge;

//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import microservices.book.multiplication.serviceclients.GamificationServiceClient;
import microservices.book.multiplication.user.User;
import microservices.book.multiplication.user.UserRepository;
//import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {
    
    private final UserRepository userRepository;
    private final ChallengeAttemptRepository attemptRepository;
    private final ChallengeEventPub challengeEventPub;

    @Transactional
    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {

        // Check if the user already exists for that alias, otherwise create it
        User user = userRepository.findByAlias(attemptDTO.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}",
                            attemptDTO.getUserAlias());
                    return userRepository.save(
                            new User(attemptDTO.getUserAlias())
                    );
                });

        // Check, if the attempt is correct.
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        // Builds the domain object. Null id sinct it'll be generated by the DB.
        ChallengeAttempt checkedAttempt = new ChallengeAttempt(null, 
                            user,
                            attemptDTO.getFactorA(),
                            attemptDTO.getFactorB(),
                            attemptDTO.getGuess(),
                            isCorrect
        );

        // Stores the attempt.
        ChallengeAttempt storedAttempt = attemptRepository.save(checkedAttempt);

        // Publishes an event to notify potentially interested subscribers
        challengeEventPub.challengeSolved(storedAttempt);

        return storedAttempt;
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(final String userAlias) {
        return attemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
