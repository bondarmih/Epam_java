package edu.bondarmih.memorygame.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bondarm on 05.08.16.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 4, message = "Name must be at least 4 characters")
    @Column(unique = true)
    private String name;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @ManyToMany
    @JoinTable
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<GameResult> gameResults;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }

    public void setGameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public String toString() {
        String s = "Name: " + getName()
                + ", Password: " + getPassword()
                + ", Roles: " + Arrays.toString(this.getRoles().toArray());
        return s;
    }
}
