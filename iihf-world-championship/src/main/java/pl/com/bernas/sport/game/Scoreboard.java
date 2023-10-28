package pl.com.bernas.sport.game;

import java.util.ArrayList;
import java.util.List;

public final class Scoreboard {

    private final List<Game> games = new ArrayList<>();

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
        return this.games;
    }

    void removeGame(Game game) {
        this.games.remove(game);
    }
}
