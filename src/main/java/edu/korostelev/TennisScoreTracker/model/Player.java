package edu.korostelev.TennisScoreTracker.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "tennis-scoreboard", name="players")
public class Player {
    @Id
    @GeneratedValue
    private Integer id;

    @Column (
            unique = true,
            nullable = false,
            length = 50
    )
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
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
}
