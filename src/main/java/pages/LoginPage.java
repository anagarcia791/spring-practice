package pages;

import annotations.Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@Page
public class LoginPage extends BasePage {
    @AndroidFindBy(id = "inputEditText")
    private WebElement passwordTextBox;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Menú')] | //android.widget.TextView[contains(@text,'Bienvenido')]")
    private WebElement menuTitle;

    public LoginPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(getAppiumFieldDecorator(), this);
    }

    public boolean isPasswordDisplayed() {
        return passwordTextBox.isDisplayed();
    }

    public boolean tapNotifications() {
        return menuTitle.isDisplayed();
    }
}
