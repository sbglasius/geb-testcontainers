package dk.glasius.geb.testcontainers

import org.openqa.selenium.remote.RemoteWebDriver
import org.testcontainers.Testcontainers
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.lifecycle.TestDescription

class TestcontainersWebDriver {

    private final BrowserWebDriverContainer container

    TestcontainersWebDriver(BrowserWebDriverContainer container) {
        this.container = container
    }

    @Delegate
    RemoteWebDriver getDriver() {
        container.webDriver
    }

    void addExposedPort(int port) {
        Testcontainers.exposeHostPorts(port)
    }

    void startTestcontainer() {
        container.start()
    }

    void afterTest(String className, String testName) {
        container.afterTest(new SimpleTestDescription(className, testName), Optional.empty())
    }

    @Override
    void quit() {
        driver.quit()
        container.stop()
    }

    private static class SimpleTestDescription implements TestDescription {

        private final String className
        private final String testName

        SimpleTestDescription(String className, String testName) {
            this.className = className
            this.testName = testName
        }

        @Override
        String getTestId() {
            throw new UnsupportedOperationException()
        }

        @Override
        String getFilesystemFriendlyName() {
            "${className}-${testName}".replaceAll(/\//, '_')
        }
    }

}
