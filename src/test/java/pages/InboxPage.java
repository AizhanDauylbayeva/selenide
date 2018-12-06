package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class InboxPage {

    @FindBy(how = How.ID, using = "PH_user-email")
    private SelenideElement userEmailIdentificator;

    @FindBy(xpath = "//*[@id='b-toolbar__left']//span")
    private WebElement createNewMailButton;

    public void assertUserSignedIn() {
        userEmailIdentificator.shouldBe(Condition.visible);
    }

    public InboxPage openWriteNewMail() {
        createNewMailButton.click();
        return this;
    }

    public CloudPage openCloudPage() {
        $(byTitle("Облако")).click();
        switchTo().window(1);
        return new CloudPage();
    }
}
