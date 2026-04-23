package org.test.drivers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.test.utilities.PropertyHandler;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DriverFactory {
    public static WebDriver driver;

    public static void setDriver()
    {
        switch(PropertyHandler.readConfig("webDriver"))
        {
            case "chrome":  WebDriverManager.chromedriver().setup();
                            driver =new ChromeDriver();
                            break;
            case "firefox": WebDriverManager.firefoxdriver().setup();
                            driver = new FirefoxDriver();
                            break;
            default:        driver=null;

        }
        driver.manage().window().maximize();
    }
    public static WebDriver getDriver()
    {
        return driver;
    }

    public static void closeDriver()
    {
        driver.quit();
    }

    //Mobile Driver
    public static AndroidDriver androidDriver ;
    public static void setAndroidDriver() throws IOException {

        File file = new File(PropertyHandler.readData("SelendroidApp"));
        MutableCapabilities cap = new MutableCapabilities();
        cap.setCapability("deviceName", "device");
        cap.setCapability("udid", PropertyHandler.readData("deviceName"));
        cap.setCapability("app", file.getAbsolutePath());
        cap.setCapability("automationName", "UiAutomator2");

       	androidDriver = new AndroidDriver(new URL(PropertyHandler.readConfig("appiumServerURL")), cap);
       // androidDriver = new AndroidDriver(cap);

    }
    public static AndroidDriver getAndroidDriver()
    {
        return androidDriver;
    }
    public static void closeAndroidDriver()
    {
        androidDriver.quit();
    }
}
