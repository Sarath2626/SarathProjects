package com.sarathproj.Helper;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * @author Sarath Kumar
 * */
public class Base {

    public static WebDriver driver;

    @BeforeMethod
    public static void browserLaunch() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public static void tearDown() {
        driver.quit();
    }

    public static void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static void takeScreenshot(WebElement element) {
        String callingMethodName = new Throwable().getStackTrace()[1].getMethodName();
        String screenshotName = callingMethodName + ".png";
        highlightElement(element);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("./snap/" + screenshotName);
        try {
            FileHandler.copy(src, dest);
            System.out.println("Screenshot taken: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
