package lotusFlare.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lotusFlare.utilities.ConfigPropertiesReader;
import lotusFlare.utilities.DBUtils;
import lotusFlare.utilities.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    /*
        Method for creating screenshot after failed scenario
     */
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){

            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        //   Driver.closeDriver();

    }

    //terminating driver session after scenario is completed
    @After(order = 2)
    public void closeDriver () {

        Driver.closeDriver();

    }

    /*

    Creating Database connection only for test cases which have @db annotation
    In case we need to verify data from database

     */

    @Before("@db")
    public void setUpDB(){

        String dbUrl = ConfigPropertiesReader.getProperty("dbUrl");
        String dbUsername = ConfigPropertiesReader.getProperty("dbUsername");
        String dbPassword = ConfigPropertiesReader.getProperty("dbPassword");
        System.out.println("Connecting to database...");
        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }


    /*

    Closing connection to the database, only for test case which have @db annotation
    After each Scenario connection is closed

     */
    @After("@db")
    public void tearDownDB(){
        System.out.println("close database connection...");
        DBUtils.destroy();
    }
}
