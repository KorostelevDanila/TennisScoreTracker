package edu.korostelev.TennisScoreTracker.model;

import jakarta.persistence.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
