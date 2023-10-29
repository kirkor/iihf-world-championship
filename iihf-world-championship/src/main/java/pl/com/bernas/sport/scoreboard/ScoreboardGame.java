package pl.com.bernas.sport.scoreboard;

import pl.com.bernas.sport.game.Game;
import pl.com.bernas.sport.game.GameState;
import pl.com.bernas.sport.game.GameStateException;

import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

final class ScoreboardGame implements Game, Comparable<ScoreboardGame> {

    private final Game game;
    private final Scoreboard scoreboard;
    private String gameStartTimeInNano = "0";

    ScoreboardGame(Game game, Scoreboard scoreboard) {
        this.game = game;
        this.scoreboard = scoreboard;
    }

    @Override
    public String getHomeTeam() {
        return this.game.getHomeTeam();
    }

    @Override
    public String getAwayTeam() {
        return this.game.getAwayTeam();
    }

    @Override
    public Game start() throws GameStateException {
        game.start();
        this.gameStartTimeInNano = "" + Instant.now().getEpochSecond() + Instant.now().getNano();
        return this;
    }

    @Override
    public GameState getState() {
        return game.getState();
    }

    @Override
    public void finish() throws GameStateException {
        this.game.finish();
        this.scoreboard.removeGame(this);
    }


    @Override
    public Game.Score updateScore(int homeTeamScore, int awayTeamScore) throws GameStateException {
        return this.game.updateScore(homeTeamScore, awayTeamScore);
    }

    @Override
    public Score getScore() {
        return this.game.getScore();
    }

    @Override
    public String toString() {
        return this.game.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return (Objects.equals(getHomeTeam(), game.getHomeTeam()) && Objects.equals(getAwayTeam(), game.getAwayTeam()))
                || (Objects.equals(getHomeTeam(), game.getAwayTeam()) && Objects.equals(getAwayTeam(), game.getHomeTeam()));
    }

    @Override
    public int hashCode() {
        String[] teams = {getHomeTeam(), getAwayTeam()};
        Arrays.sort(teams);

        return Objects.hash(Arrays.stream(teams).toArray());
    }

    private String getGameStartTimeInNano() {
        return this.gameStartTimeInNano;
    }

    @Override
    public int compareTo(ScoreboardGame o) {
        return Comparator.comparing(ScoreboardGame::getScore, Comparator.comparingInt(Score::totalScore))
                .thenComparing(ScoreboardGame::getGameStartTimeInNano).compare(this, o);
    }
}
