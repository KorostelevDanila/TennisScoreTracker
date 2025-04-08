package edu.korostelev.TennisScoreTracker.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "tennis-scoreboard", name = "completed-matches")
public class Match {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Player winner;
}
