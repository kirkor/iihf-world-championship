package pl.com.bernas.sport.game;

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
        this.state = GameState.IN_PROGRESS;
    }

    public GameState getState() {
        return state;
    }

    public void finish() throws GameStateException {
        if (this.state != GameState.IN_PROGRESS) {
            throw new GameStateException("Only games in progress can be finished.");
        }
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
