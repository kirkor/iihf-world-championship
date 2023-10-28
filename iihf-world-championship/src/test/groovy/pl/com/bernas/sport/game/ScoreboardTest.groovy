package pl.com.bernas.sport.game

import spock.lang.Specification
import spock.lang.Subject

class ScoreboardTest extends Specification {
    @Subject
    private Scoreboard scoreboard = new Scoreboard()

    def 'should be able to create a game'() {
        when:
            def game = scoreboard.createGame('USA', 'Canada')
        then:
            game.homeTeam == 'USA'
    }

    def 'should return list of all games'() {
        when:
            scoreboard.createGame('Poland', 'Latvia')
            scoreboard.createGame('France', 'Kazakhstan')
        then:
            scoreboard.games().size() == 2
    }

    def 'finished games should be removed from the scoreboard'() {
        when:
            scoreboard.createGame('Sweden', 'USA').start().finish()
        then:
            this.scoreboard.games().size() == 0
    }

    def 'two games with same combination of teams can not be added into scoreboard'() {
        when:
            scoreboard.createGame('Norway', 'Finland')
            scoreboard.createGame('Norway', 'Finland')
        then:
            thrown(ScoreboardException)
    }
}
