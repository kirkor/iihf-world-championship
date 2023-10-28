package pl.com.bernas.sport.game

import spock.lang.Specification

class ScoreboardTest extends Specification {
    def 'should be able to create a game'() {
        given:
            Scoreboard scoreboard = new Scoreboard()
        when:
            def game = scoreboard.createGame('USA', 'Canada')
        then:
            game.homeTeam == 'USA'
    }

    def 'should return list of all games'() {
        given:
            Scoreboard scoreboard = new Scoreboard()
        when:
            scoreboard.createGame('Poland', 'Latvia')
            scoreboard.createGame('France', 'Kazakhstan')
        then:
            scoreboard.games().size() == 2
    }

    def 'finished games should be removed from the scoreboard'() {
        given:
            Scoreboard scoreboard = new Scoreboard()
        when:
            scoreboard.createGame('Sweden', 'USA').start().finish()
        then:
            scoreboard.games().size() == 0
    }
}
