package lotusFlare.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

    //creating private constructor to close access to the object from outside the class
    private Driver () {

    }

    private static WebDriver driver;

    //method which will return same instance of the driver when we call it
    public static WebDriver getDriver () {

        /*
            If browser type is passed from the terminal, use that one
            Otherwise, use the browser type which is specified in configuration.properties file

        */

        if (driver == null) {

            String browser = "";

            if (System.getProperty("BROWSER") == null) {

                browser = ConfigPropertiesReader.getProperty("browser");

            } else {

                browser = System.getProperty("BROWSER");

            }

            System.out.println(browser);


            switch (browser) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                case "chrome-headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;
                case "firefox-headless":
                    FirefoxOptions options1 = new FirefoxOptions();
                    options1.addArguments("-headless");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(options1);
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                    break;

                default:
                    throw new RuntimeException("Invalid browser type");

            }

        }

        return driver;

    }

    //terminating driver session. Method used in Hooks class with @After annotation.
    public static void closeDriver () {

        if (driver != null){

            driver.quit();
            driver = null;
        }

    }


}
