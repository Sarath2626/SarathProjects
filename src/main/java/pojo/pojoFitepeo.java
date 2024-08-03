package pojo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sarathproj.Helper.Base;
/**
 * @author Sarath Kumar
 * */
public class pojoFitepeo extends Base {

    public pojoFitepeo() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='Revenue Calculator']")
    public WebElement headerRevenueBtn;

    @FindBy(xpath = "//h5[text()='Total Gross Revenue Per Year']")
    public WebElement revenuecard;

    @FindBy(xpath = "//span[contains(@class, 'MuiSlider-root')]")
    public WebElement revenueslider;

    @FindBy(xpath = "//div[contains(@class, 'MuiInputBase-root')]//input[@type='number']")
    public WebElement revenuesliderinputbox;

    @FindBy(xpath = "//span[contains(@class, 'MuiSlider-thumb')]//input[@type ='range']")
    public WebElement revenuesliderinputBall;

    @FindBy(xpath = "//div[contains(@class, 'MuiInputBase-root')]//input[@value='820']")
    public WebElement expectedRevBallValue820;

    @FindBy(xpath = "//span[contains(@class, 'MuiSlider-rail')]")
    public WebElement silderRail;

    @FindBy(xpath = "//span[contains(@class, 'MuiSlider-thumb')]//input")
    public WebElement expectedRevBallValue;

    @FindBy(xpath = "//div[contains(@class, 'MuiFormControl-root')]//input")
    public WebElement eletoclickonInputfield;

    @FindBy(xpath = "//span[normalize-space(text())='57']//preceding-sibling::span//input")
    public WebElement cPT57;

    @FindBy(xpath = "//span[normalize-space(text())='19.19']//preceding-sibling::span//input")
    public WebElement cPT1919;

    @FindBy(xpath = "//span[normalize-space(text())='63']//preceding-sibling::span//input")
    public WebElement cPT63;

    @FindBy(xpath = "//span[normalize-space(text())='15']//preceding-sibling::span//input")
    public WebElement cPT15;

    @FindBy(xpath = "//*[text()='110700']//parent::p[contains(@class, 'MuiTypography-body1')]")
    public WebElement totRecReim;

    @FindBy(xpath = "//*[text()='110700']//parent::p[contains(@class, 'MuiTypography-body1')]")
    public WebElement headerTotRecReim;
}
