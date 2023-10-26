package pl.com.bernas.sport.game

import spock.lang.Specification

class GameTest extends Specification {
    def "should be able to create game with two teams"() {
        given:
            String awayTeam = 'Canada'
            String homeTeam = 'USA'
        when:
            Game hockeyGame = new Game(homeTeam, awayTeam)
        then:
            hockeyGame.homeTeam == homeTeam
            hockeyGame.awayTeam == awayTeam
    }

    def "each game should start with 0-0 score"() {
        given:
            String awayTeam = 'Finland'
            String homeTeam = 'Sweden'
        when:
            Game hockeyGame = new Game(homeTeam, awayTeam)
        then:
            hockeyGame.score.toString() == "0-0"
    }

    def "each game should be showed in format 'home team' 'score' - 'away team' 'score'"() {
        expect:
            Game hockeyGame = new Game(homeTeam, awayTeam)
            hockeyGame.toString() == summary

        where:
            homeTeam   | awayTeam  || summary
            'Canada'   | 'USA'     || "${homeTeam} 0 - ${awayTeam} 0"
            'Slovakia' | 'Germany' || "${homeTeam} 0 - ${awayTeam} 0"
            'Czechia'  | 'Finland' || "${homeTeam} 0 - ${awayTeam} 0"
    }

    def 'can start a game'() {
        given:
            String awayTeam = 'Germany'
            String homeTeam = 'Sweden'
        when:
            Game hockeyGame = new Game(homeTeam, awayTeam)
            hockeyGame.start()
        then:
            hockeyGame.status == GameStatus.IN_PROGRESS
    }
}
