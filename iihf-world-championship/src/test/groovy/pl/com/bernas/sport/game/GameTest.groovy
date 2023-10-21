package pl.com.bernas.sport.game

import spock.lang.Specification

class GameTest extends Specification {
    def 'should be able to create game with two teams'() {
        given:
            String visitors = 'Canada'
            String home = 'USA'
        when:
            Game hockeyGame = new Game(home, visitors)
        then:
            hockeyGame.homeTeam == home
            hockeyGame.visitorsTeam == visitors

    }
}
