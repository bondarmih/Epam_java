package edu.bondarmih.memorygame.service;

import edu.bondarmih.memorygame.dto.GameResultDTO;
import edu.bondarmih.memorygame.entity.GameResult;
import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.repository.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 09.08.16.
 */
@Service
public class GameResultService {

    private final GameResultRepository gameResultRepository;

    @Autowired
    public GameResultService(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }


    public List<GameResultDTO> getTopResults() {

        List<GameResultDTO> result = gameResultRepository.findTop10ByOrderByResultAsc()
                .stream()
                .map(GameResultDTO::new)
                .collect(Collectors.toList());
        return result;
    }

    public void save(GameResult result) {
        gameResultRepository.save(result);
    }

    public Integer getUserBestResult(User user) {
        GameResult gameResult = gameResultRepository.findTop1ByUserOrderByResultAsc(user);
        if (gameResult == null) {
            return 0;
        }
        return gameResult.getResult();
    }


    //    TODO findTopResultsWithUsers, save, findUnviewedRecord
}
