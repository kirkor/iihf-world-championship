package pl.com.bernas.sport.integration


import pl.com.bernas.sport.game.Scoreboard
import spock.lang.Specification

class IIHF24IntegrationSpec extends Specification {
    def 'games can be created only via Scoreboard'() {
        given:
            Scoreboard scoreboard = new Scoreboard()
        when:
            scoreboard.createGame('A', 'B')
        then:
            scoreboard.games().size() == 1
    }
}
