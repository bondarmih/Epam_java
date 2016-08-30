package edu.bondarmih.memorygame.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonSerializable;
import edu.bondarmih.memorygame.entity.GameResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * Created by bondarm on 22.08.16.
 */
public class GameResultDTO  implements Serializable {
    @JsonView(View.Summary.class)
    private Integer id;
    @JsonView(View.Summary.class)
    private Integer result;
    @JsonView(View.Summary.class)
    private String user;

    public GameResultDTO(GameResult gameResult) {

        this.id = gameResult.getId();

        this.result = gameResult.getResult();

        this.user = gameResult.getUser().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
