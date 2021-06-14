package geb.testcontainers

import geb.spock.GebSpec
import groovy.transform.SelfType
import org.junit.After
import org.junit.Before

@SelfType(GebSpec)
trait TestcontainersGeb {
    @Before
    def before() {
        if (driver instanceof TestcontainersWebDriver) {
            def webDriver = driver as TestcontainersWebDriver
            webDriver.addExposedPort(serverPort)
            webDriver.startTestcontainer()
            baseUrl = "http://host.testcontainers.internal:$serverPort"
        }
    }

    @After
    def after() {
        def testcontainersWebDriver = driver as TestcontainersWebDriver
        testcontainersWebDriver.afterTest(
                specificationContext.currentIteration.name, specificationContext.currentSpec.name
        )
    }

}
