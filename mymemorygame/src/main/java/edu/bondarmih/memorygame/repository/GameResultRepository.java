package edu.bondarmih.memorygame.repository;

import edu.bondarmih.memorygame.entity.GameResult;
import edu.bondarmih.memorygame.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by bondarm on 05.08.16.
 */

public interface GameResultRepository extends JpaRepository<GameResult, Long> {
    List<GameResult> findByUserOrderByResultDesc(User user);
    List<GameResult> findTop10ByOrderByResultAsc();
    List<GameResult> findAllByOrderByResultAsc();
    GameResult findTop1ByUserOrderByResultAsc(User user);

    @Query("SELECT count (r.id) from GameResult r where r.user = ?1")
    Integer getUserGameCount(User user);
}
