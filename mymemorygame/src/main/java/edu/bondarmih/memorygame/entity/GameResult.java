package edu.bondarmih.memorygame.entity;

import javax.persistence.*;

/**
 * Created by bondarm on 05.08.16.
 */

@Entity
@Table(name = "GameResult")
public class GameResult {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer result;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
