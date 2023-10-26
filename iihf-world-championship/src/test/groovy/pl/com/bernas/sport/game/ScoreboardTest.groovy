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
}
