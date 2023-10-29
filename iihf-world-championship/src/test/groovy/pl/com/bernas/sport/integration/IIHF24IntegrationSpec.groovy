package pl.com.bernas.sport.integration

import pl.com.bernas.sport.scoreboard.Scoreboard
import spock.lang.Specification
import spock.lang.Subject

class IIHF24IntegrationSpec extends Specification {
    @Subject
    private Scoreboard scoreboard = new Scoreboard()

    def 'games can be created only via Scoreboard'() {
        when:
            scoreboard.createGame('A', 'B')
        then:
            scoreboard.games().size() == 1
    }

    def 'finished games should be removed from the scoreboard'() {
        when:
            scoreboard.createGame('Sweden', 'USA').start().finish()
        then:
            this.scoreboard.games().size() == 0
    }

    def 'summary for IIHF24 should be ordered by their total score and time of start'() {
        when:
            scoreboard.createGame('Switzerland', 'Norway').start().updateScore(0, 5)
            scoreboard.createGame('Slovakia', 'Germany').start().updateScore(10, 2)
            scoreboard.createGame('Czechia', 'Finland').start().updateScore(2, 2)
            scoreboard.createGame('Sweden', 'USA').start().updateScore(6, 6)
            scoreboard.createGame('Great Britain', 'Canada').start().updateScore(3, 1)
        then:
            scoreboard.toString() == "Sweden 6 - USA 6" +
                    "\nSlovakia 10 - Germany 2" +
                    "\nSwitzerland 0 - Norway 5" +
                    "\nGreat Britain 3 - Canada 1" +
                    "\nCzechia 2 - Finland 2"
    }
}
