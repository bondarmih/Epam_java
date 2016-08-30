package edu.bondarmih.memorygame.dto;

import com.fasterxml.jackson.annotation.JsonView;
import edu.bondarmih.memorygame.entity.GameResult;
import edu.bondarmih.memorygame.entity.Role;
import edu.bondarmih.memorygame.entity.User;
import edu.bondarmih.memorygame.service.GameResultService;
import edu.bondarmih.memorygame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 22.08.16.
 */

public class UserDTO implements Serializable{

    @JsonView(View.Summary.class)
    private Integer id;
    @JsonView(View.Summary.class)
    private String name;
    @JsonView(View.Summary.class)
    private List<String> roles;
    @JsonView(View.Summary.class)
    private List<Integer> results;
    @JsonView(View.Summary.class)
    private Integer bestResult;
    @JsonView(View.Summary.class)
    private Integer gameCount;



    public UserDTO(User user) {

        this.id = user.getId();

        this.name = user.getName();

        this.roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        this.results = user.getGameResults()
                .stream()
                .map(GameResult::getResult)
                .collect(Collectors.toList());

        this.bestResult = null;

        this.gameCount = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Integer> getResults() {
        return results;
    }

    public void setResults(List<Integer> results) {
        this.results = results;
    }

    public Integer getBestResult() {
        return bestResult;
    }

    public void setBestResult(Integer bestResult) {
        this.bestResult = bestResult;
    }

    public Integer getGameCount() {
        return gameCount;
    }

    public void setGameCount(Integer gameCount) {
        this.gameCount = gameCount;
    }

}
