package pl.com.bernas.sport.game;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final List<Game> games = new ArrayList<>();

    public Game createGame(String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        games.add(game);
        return game;
    }

    public List<Game> games() {
        return games;
    }
}
