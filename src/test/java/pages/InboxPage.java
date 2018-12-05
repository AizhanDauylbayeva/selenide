package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;

public class InboxPage {

    @FindBy(how = How.ID, using = "PH_user-email")
    private SelenideElement userEmailIdentificator;

    @FindBy(xpath = "//*[@id='b-toolbar__left']//span")
    private SelenideElement createNewMailButton;

    @FindBy(xpath = "//span[@class='js-text-inner pm-toolbar__button__text__inner' and contains(string(), 'Облако')]")
    private SelenideElement cloudButton;

    public void assertUserSignedIn() {
        waitForElementVisible(userEmailIdentificator);
        userEmailIdentificator.shouldBe(Condition.visible);
    }

    public InboxPage openWriteNewMail() {
        createNewMailButton.click();
        return this;
    }

    public CloudPage openCloudPage() {
        cloudButton.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new CloudPage();
    }
}
