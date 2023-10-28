package pl.com.bernas.sport.game;

import java.util.Arrays;
import java.util.Objects;

final class ScoreboardGame implements Game {

    private final Game game;
    private final Scoreboard scoreboard;

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
    public GameImpl.Score updateAwayTeamScore(int score) throws GameStateException {
        return this.game.updateAwayTeamScore(score);
    }

    @Override
    public GameImpl.Score updateHomeTeamScore(int score) throws GameStateException {
        return this.game.updateHomeTeamScore(score);
    }

    @Override
    public GameImpl.Score updateScore(int homeTeamScore, int awayTeamScore) throws GameStateException {
        return this.game.updateScore(homeTeamScore, awayTeamScore);
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
}
