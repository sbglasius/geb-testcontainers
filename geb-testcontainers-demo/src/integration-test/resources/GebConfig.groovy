import com.github.dockerjava.api.model.HostConfig
import geb.testcontainers.TestcontainersWebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.testcontainers.containers.BrowserWebDriverContainer
import static org.openqa.selenium.remote.DesiredCapabilities.chrome
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL
import static org.testcontainers.shaded.org.apache.commons.io.FileUtils.ONE_GB

reportsDir = "build/reports/geb"

environments {

    // run via “./gradlew -Dgeb.env=chrome iT”
    chrome {
        driver = { new ChromeDriver() }
    }

    // run via “./gradlew -Dgeb.env=chromeHeadless iT”
    chromeHeadless {
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.addArguments('headless')
            new ChromeDriver(o)
        }
    }

    // run via “./gradlew -Dgeb.env=firefox iT”
    firefox {
        driver = { new FirefoxDriver() }
    }

    testcontainer {
        def videoDir = new File(reportsDir, "video")
        videoDir.mkdirs()
        def container = new BrowserWebDriverContainer()
                .withCapabilities(chrome())
                .withRecordingMode(RECORD_ALL, videoDir)
                .withSharedMemorySize(2 * ONE_GB) as BrowserWebDriverContainer

        driver = {
            new TestcontainersWebDriver(container)
        }
    }
}
