package geb.testcontainers

import dk.glasius.geb.testcontainers.TestcontainersGeb
import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

@Integration
class DemoGebSpec extends GebSpec implements TestcontainersGeb {

    void "get demo index"() {
        when:
        go '/demo'

        then:
        $('h1').text() == 'Welcome to Grails on Testcontainers'
    }

}
