package geb.testcontainers

import geb.spock.GebSpec
import groovy.transform.SelfType
import org.junit.After
import org.junit.Before

/**
 * Adds before() and after() methods to set up and tear down a TestcontainerWebDriver
 */
@SelfType(GebSpec)
trait TestcontainersGeb {

    /**
     * Before executing a GebSpec, if the driver is TestcontainersWebDriver,
     * set the server port and start the container. Called automatically.
     *
     * `driver`, `serverPort` and `baseUrl` are injected by @IntegrationTest from Grails
     */
    @Before
    void before() {
        if (driver instanceof TestcontainersWebDriver) {
            driver.with {
                addExposedPort(serverPort)
                startTestcontainer()
            }
            baseUrl = "http://host.testcontainers.internal:$serverPort"
        }
    }

    /**
     * After executing a test method, set the name to the driver. Used to generate the filename, if set up.
     * Called automatically
     */
    @After
    void after() {
        if (driver instanceof TestcontainersWebDriver) {
            driver.with {
                afterTest(specificationContext.currentIteration.name, specificationContext.currentSpec.name)
            }
        }
    }
}
