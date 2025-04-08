package edu.korostelev.TennisScoreTracker.models;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
}
