package pl.com.bernas.sport.game;


import static pl.com.bernas.sport.game.GameState.IN_PROGRESS;

public final class Game {

    public final String homeTeam;
    public final String awayTeam;

    public final Score score = new Score();
    private GameState state;

    Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.state = GameState.SCHEDULED;
    }

    @Override
    public String toString() {
        return this.homeTeam + " " + this.score.homeTeamScore + " - " + this.awayTeam + " " + this.score.awayTeamScore;
    }

    public void start() {
        this.state = IN_PROGRESS;
    }

    public GameState getState() {
        return state;
    }

    public void finish() {
        this.state = GameState.FINISHED;
    }

    public class Score {
        private final int homeTeamScore;
        private final int awayTeamScore;

        private Score() {
            this.homeTeamScore = 0;
            this.awayTeamScore = 0;
        }

        @Override
        public String toString() {
            return homeTeamScore + "-" + awayTeamScore;
        }
    }
}
