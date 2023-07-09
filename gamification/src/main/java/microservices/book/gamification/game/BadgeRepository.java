package microservices.book.gamification.game;

import microservices.book.gamification.game.domain.BadgeCard;
//import microservices.book.gamification.game.domain.BadgeType;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BadgeRepository extends CrudRepository<BadgeCard, Long> {
    
    /**
     * Retrives all BadgeCards for a given user.
     * 
     * @parm userId the id of the user to look for BadgeCards
     * @return the list of BadgeCards, orderd by the most recent first
     */

    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(Long userId);
}
