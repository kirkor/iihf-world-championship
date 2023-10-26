package pl.com.bernas.sport.game

import spock.lang.Specification
import spock.lang.Subject

class GameTest extends Specification {

    @Subject
    private Game hockeyGame

    def setup() {
        this.hockeyGame = new Game('Slovakia', 'Poland')
    }

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

    def "can start a game"() {
        given:
            String awayTeam = 'Germany'
            String homeTeam = 'Sweden'
        when:
            Game hockeyGame = new Game(homeTeam, awayTeam)
            hockeyGame.start()
        then:
            hockeyGame.state == GameState.IN_PROGRESS
    }

    def "game is in schedule status by default"() {
        expect:
            hockeyGame.state == GameState.SCHEDULED
    }

    def "game can be finished"() {
        when:
            hockeyGame.finish()
        then:
            hockeyGame.state == GameState.FINISHED
    }

    def "only started games can be finished"() {
        when:
            hockeyGame.finish()
        then:
            thrown(GameStateException)
    }
}
