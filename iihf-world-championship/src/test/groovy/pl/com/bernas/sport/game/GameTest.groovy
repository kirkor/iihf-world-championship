package pl.com.bernas.sport.game

import spock.lang.Specification

class GameTest extends Specification {
    def 'should be able to create game with two teams'() {
        given:
            String awayTeam = 'Canada'
            String homeTeam = 'USA'
        when:
            Game hockeyGame = new Game(homeTeam, awayTeam)
        then:
            hockeyGame.homeTeam == homeTeam
            hockeyGame.awayTeam == awayTeam

    }
}
