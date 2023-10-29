package pl.com.bernas.sport.scoreboard;

import pl.com.bernas.sport.game.Game;
import pl.com.bernas.sport.game.GameImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This is an aggregator for Games. Each Game on the scoreboard will be automatically removed after end time.
 * Summary of games is ordered  by their total score and time of start.
 */
public final class Scoreboard {

    private final List<ScoreboardGame> games = new ArrayList<>();

    public Game createGame(String homeTeam, String awayTeam) throws ScoreboardException {
        Game game = new GameImpl(homeTeam, awayTeam);
        ScoreboardGame decoratedGame = new ScoreboardGame(game, this);

        if (this.games.contains(decoratedGame)) {
            throw new ScoreboardException("Scoreboard can't have two games with same teams");
        }

        this.games.add(decoratedGame);

        return decoratedGame;
    }

    public List<Game> games() {
        return List.copyOf(this.games);
    }

    void removeGame(Game game) {
        this.games.remove(game);
    }

    @Override
    public String toString() {
        return this.games.stream().sorted(Collections.reverseOrder()).map(Objects::toString).collect(Collectors.joining("\n"));
    }
}
