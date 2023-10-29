package pl.com.bernas.sport.game;

/**
 * Each Game is created with state {@see GameState.SCHEDULED}
 */
public interface Game {
    String getHomeTeam();

    String getAwayTeam();

    /**
     * Show game in format [Home team name] [home team score] - [Away team name] [away team score], eg.
     * Uruguay 6 - Italy 6
     *
     * @return formatted string
     */
    @Override
    String toString();

    /**
     * Only scheduled game can be started, otherwise exception will be thrown.
     * After start() Game will be in {@see GameState.IN_PROGRESS} state.
     *
     * @throws GameStateException when unallowed state transition is detected
     */
    Game start() throws GameStateException;

    GameState getState();

    /**
     * Only games {@see GameState.IN_PROGRESS} can be finished
     *
     * @throws GameStateException when unallowed state transition is detected
     */
    void finish() throws GameStateException;

    Score updateScore(int homeTeamScore, int awayTeamScore) throws GameStateException;

    Score getScore();

    interface Score {
        Score updateScore(int homeTeamScore, int awayTeamScore) throws GameStateException;

        /**
         * @return sum of scores
         */
        int totalScore();

        /**
         * @return score in format [home team score] - [away team score]
         */
        String toString();
    }
}
