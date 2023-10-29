package pl.com.bernas.sport.scoreboard


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

    def 'two games with same combination of teams can not be added into scoreboard'() {
        when:
            scoreboard.createGame('Norway', 'Finland')
            scoreboard.createGame('Finland', 'Norway')
        then:
            thrown(ScoreboardException)
    }

    def 'game from scoreboard can not be removed directly from game list'() {
        when:
            scoreboard.createGame('Poland', 'Latvia')
            scoreboard.createGame('France', 'Kazakhstan')
            scoreboard.games().removeFirst()
        then:
            thrown(UnsupportedOperationException)
    }
}
