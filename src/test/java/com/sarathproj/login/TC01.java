package com.sarathproj.login;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sarathproj.Helper.Base;
import com.sarathproj.constant.constant;
import pojo.pojoFitepeo;
/**
 * @author Sarath Kumar
 * */
public class TC01 extends Base {

    @Test(description = "FitPeo Task's")
    public static void tc01() throws InterruptedException, AWTException {

        driver.get(constant.url);
        Assert.assertEquals(driver.getCurrentUrl(), constant.url);
        System.out.println("1. Navigated to the FitPeo Homepage");

        pojoFitepeo pojo = new pojoFitepeo();
        pojo.headerRevenueBtn.click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.fitpeo.com/revenue-calculator");
        System.out.println("2. Navigated to the Revenue calculator page");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,350)");
        Assert.assertTrue(pojo.revenueslider.isDisplayed(), "Slider is not displayed");
        System.out.println("3. Scrolled down to the slider section");

        adjustSlider(pojo);
        verifyAndUpdateSliderValue(pojo, js);
        selectCPTCodes(pojo, js);
        validateTotalReimbursement(pojo, js);
    }

    private static void adjustSlider(pojoFitepeo pojo) throws AWTException {
        Actions actions = new Actions(driver);
        Robot robot = new Robot();
        actions.dragAndDropBy(pojo.silderRail, -27, 0).perform();
        for (int i = 0; i < 4; i++) {
            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_UP);
        }
        Assert.assertTrue(pojo.expectedRevBallValue820.isDisplayed(), "Expected value 820 is not displayed");
        System.out.println("4. Adjusted the slider");
    }

    private static void verifyAndUpdateSliderValue(pojoFitepeo pojo, JavascriptExecutor js) throws AWTException {
        pojo.eletoclickonInputfield.click();
        Robot robot = new Robot();
        String initValueSlider = pojo.expectedRevBallValue.getAttribute("value");

        for (int i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        }
        robot.keyPress(KeyEvent.VK_5);
        robot.keyRelease(KeyEvent.VK_5);
        robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
        robot.keyPress(KeyEvent.VK_0);
        robot.keyRelease(KeyEvent.VK_0);

        String aftValueSlider = pojo.expectedRevBallValue.getAttribute("value");
        Assert.assertNotEquals(initValueSlider, aftValueSlider, "Slider value did not update");

        Assert.assertEquals(aftValueSlider.trim(), "560", "Slider value is not 560");
        System.out.println("5. Updated the text field and validated slider value");
    }

    private static void selectCPTCodes(pojoFitepeo pojo, JavascriptExecutor js) throws InterruptedException {
        js.executeScript("window.scroll(0,750)");
        pojo.cPT57.click();
        pojo.cPT63.click();
        pojo.cPT1919.click();
        js.executeScript("window.scroll(0,780)");
        pojo.cPT15.click();
        Assert.assertTrue(pojo.cPT57.isSelected() && pojo.cPT63.isSelected() && pojo.cPT1919.isSelected() && pojo.cPT15.isSelected(), "CPT codes not selected correctly");
        System.out.println("6. Selected CPT codes");
    }

    private static void validateTotalReimbursement(pojoFitepeo pojo, JavascriptExecutor js) throws InterruptedException, AWTException {
        js.executeScript("window.scroll(0,350)");
        pojo.eletoclickonInputfield.click();
        Robot robot = new Robot();
        for (int i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        }
        robot.keyPress(KeyEvent.VK_8);
        robot.keyRelease(KeyEvent.VK_8);
        robot.keyPress(KeyEvent.VK_2);
        robot.keyRelease(KeyEvent.VK_2);
        robot.keyPress(KeyEvent.VK_0);
        robot.keyRelease(KeyEvent.VK_0);

        Assert.assertEquals(pojo.totRecReim.getText(), "$110700", "Total recurring reimbursement is not correct");
        System.out.println("7. Validated total recurring reimbursement");

        js.executeScript("window.scroll(0,780)");
        Thread.sleep(3000);
        Assert.assertEquals(pojo.headerTotRecReim.getText(), "$110700", "Header total recurring reimbursement is not correct");
        System.out.println("8. Verified that the header is displaying");
    }
}
