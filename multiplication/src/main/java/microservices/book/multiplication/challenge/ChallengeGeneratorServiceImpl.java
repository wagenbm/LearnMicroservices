package microservices.book.multiplication.challenge;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.random.RandomGenerator;

@Service
public class ChallengeGeneratorServiceImpl implements ChallengeGeneratorService {

    private final static int MINIMUM_FACTOR = 11;
    private final static int MAXIMUM_FACTOR = 100;

    private final RandomGenerator randomGenerator;

    ChallengeGeneratorServiceImpl() {
        this.randomGenerator = new Random();
    }

    protected ChallengeGeneratorServiceImpl(final RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Challenge randomChallenge() {
        return null;
    }
}