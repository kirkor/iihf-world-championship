package pl.com.bernas.sport.game;

public class Scoreboard {
    Game createGame(String homeTeam, String awayTeam) {
        return new Game(homeTeam, awayTeam);
    }
}
