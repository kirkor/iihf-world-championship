package pl.com.bernas.sport.game;

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
}
