package edu.korostelev.TennisScoreTracker.service;

import edu.korostelev.TennisScoreTracker.model.Player;
import edu.korostelev.TennisScoreTracker.repository.PlayersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayersService {
    private final PlayersRepository playersRepository;

    public PlayersService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    public Optional<Player> savePlayer(String name) {
        return Optional.of(playersRepository.save(new Player(name)));
    }

    public Optional<Player> findByName(String name) {
        return playersRepository.findByName(name);
    }

    public Optional<Player> findById(Integer id) {
        return playersRepository.findById(id);
    }
}
