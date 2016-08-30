package edu.bondarmih.memorygame.controller;

import edu.bondarmih.memorygame.dto.GameResultDTO;
import edu.bondarmih.memorygame.entity.GameResult;
import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.service.GameResultService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 20.08.16.
 */

@Controller
@RequestMapping("/result")
public class GameResultController {

    private Logger logger = Logger.getLogger(GameResultController.class);

    private final GameResultService gameResultService;

    @Autowired
    public GameResultController(GameResultService gameResultService) {
        this.gameResultService = gameResultService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Integer addResult(@RequestParam Integer gameResult) {
        GameResult result = new GameResult();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        result.setUser(user);
        result.setResult(gameResult);
        gameResultService.save(result);
        logger.info("Result added: user " + result.getUser().getName() + ", result " + result.getResult());
        Integer bestResult = gameResultService.getUserBestResult(user);
        return bestResult;
    }

    @RequestMapping(value = "/gettopresults", method = RequestMethod.GET)
    public @ResponseBody List<GameResultDTO> topResults() {
        List<GameResultDTO> resultList = gameResultService.getTopResults();
        logger.debug("Top results requested");
        return resultList;
    }

    @RequestMapping(value = "/getbestresult", method = RequestMethod.GET)
    public @ResponseBody Integer bestUserResult() {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return gameResultService.getUserBestResult(user);
    }
}

