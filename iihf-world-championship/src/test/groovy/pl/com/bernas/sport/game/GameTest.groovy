package pl.com.bernas.sport.game

import spock.lang.Specification
import spock.lang.Subject

class GameTest extends Specification {

    private def home = 'Slovakia'
    private def away = 'Poland'

    @Subject
    private Game hockeyGame

    def setup() {
        this.hockeyGame = new Game(home, away)
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
            hockeyGame.start()
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

    def "finished games can't be started"() {
        when:
            hockeyGame.start()
            hockeyGame.finish()
            hockeyGame.start()
        then:
            thrown(GameStateException)
    }

    def "update away team score"() {
        when:
            hockeyGame.start()
            def score = hockeyGame.updateAwayTeamScore(10)
        then:
            hockeyGame.toString() == "${home} 0 - ${away} 10"
            score.toString() == '0-10'
    }

    def "away team score can be updated only if game is in progress"() {
        when:
            hockeyGame.updateAwayTeamScore(10)
        then:
            thrown(GameStateException)
    }

    def "update home team score"() {
        when:
            hockeyGame.start()
            def score = hockeyGame.updateHomeTeamScore(10)
        then:
            hockeyGame.toString() == "${home} 10 - ${away} 0"
            score.toString() == '10-0'
    }

    def "away home score can be updated only if game is in progress"() {
        when:
            hockeyGame.updateHomeTeamScore(10)
        then:
            thrown(GameStateException)
    }

    def "game can be updated with absolute scores for both teams"() {
        when:
            hockeyGame.start()
            def awayTeamScore = 0
            def homeTeamScore = 1
            def score = hockeyGame.updateScore(homeTeamScore, awayTeamScore)
        then:
            score.toString() == '1-0'
            hockeyGame.score.toString() == '1-0'
    }

    def "game can not be updated with negative integers - home team"() {
        when:
            hockeyGame.start()
            hockeyGame.updateScore(-1, 0)
        then:
            thrown(GameStateException)
    }

    def "game can not be updated with negative integers - away team"() {
        when:
            hockeyGame.start()
            hockeyGame.updateScore(0, -1)
        then:
            thrown(GameStateException)
    }
}
