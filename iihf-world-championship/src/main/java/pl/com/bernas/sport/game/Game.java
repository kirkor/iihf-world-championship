package pl.com.bernas.sport.game;


public final class Game {

    public final String homeTeam;
    public final String awayTeam;

    public final Score score = new Score();

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
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
