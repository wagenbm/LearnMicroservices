package microservices.book.gamification.game.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ScoreCard {

    // The default score assigned to this card, if not specified.
    public static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue
    private Long cardId;
    private Long userId;
    private Long attemptId;
    @EqualsAndHashCode.Exclude
    private long scoreTimestamp;
    private int score;

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
   }
    

}
