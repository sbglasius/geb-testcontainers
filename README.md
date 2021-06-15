# Testcontainer Webdriver to be used with GebSpec

Testcontainers are great! They are easy to setup and easy to use, but I did struggle a bit with the Selenium test-container. 

Inspired by this github project: https://github.com/geb/geb-testcontainers-videos-per-test I created a small library with the suggested Webdriver, and added the needed code to use it with GebSpec

## Basic configuration

In your `build.gradle` make depencencies to

```groovy
    testCompile "org.testcontainers:spock:1.15.3"
    testCompile "org.testcontainers:selenium:1.15.3"
    testCompile "dk.glasius:geb-testcontainers:1.0.1"
```

In your `test/resources` or `integration-test/resources` make a minimum `GebConfig.groovy` like this:

```groovy
import dk.glasius.geb.testcontainers.TestcontainersWebDriver
import org.openqa.selenium.chrome.ChromeDriver

def container = new BrowserWebDriverContainer()
        .withCapabilities(chrome())

driver = {
    new TestcontainersWebDriver(container)
}

```

In contrast to the `geb-testcontainers-videos-per-test` this 

1. Does not start the container right away
1. Does not do video recordings. The example from `geb-testcontainers-videos-per-test` can be used, but do not start the container in `GebConfig.groovy` 

## Usage

For specifications extending a `GebSpec` you should implement the `TestcontainersGeb` trait. This will set up the correct reverse port forwarding for the test-container, allowing you to test your running application. 

See `geb-testcontainers-demo` in this project.

