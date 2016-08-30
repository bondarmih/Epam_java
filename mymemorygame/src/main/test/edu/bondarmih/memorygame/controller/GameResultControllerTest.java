package edu.bondarmih.memorygame.controller;

import edu.bondarmih.memorygame.config.AppConfig;
import edu.bondarmih.memorygame.entity.GameResult;
import edu.bondarmih.memorygame.service.GameResultService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondarm on 26.08.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class GameResultControllerTest extends TestCase {

    private GameResult testaddedResult;
    private List<GameResult> testTopResults;
    private GameResult testBestResult;

    private MockMvc mockMvc;

    @Autowired
    private GameResultService testGameResultService;

    public void setUp() throws Exception {
        testaddedResult = new GameResult();
        testaddedResult.setId(0);
        testaddedResult.setResult(42);

        GameResult testTopResults1 = new GameResult();
        testTopResults1.setId(1);
        testTopResults1.setResult(21);

        GameResult testTopresults2 = new GameResult();
        testTopresults2.setId(2);
        testTopresults2.setResult(10);

        testTopResults = new ArrayList<>();
        testTopResults.add(testTopResults1);
        testTopResults.add(testTopresults2);

        testBestResult = new GameResult();
        testBestResult.setId(3);
        testBestResult.setResult(5);

    }


    public void testAddResult() throws Exception {

    }

    @Test
    public void testTopResults() throws Exception {

    }

    public void testBestUserResult() throws Exception {

    }

}