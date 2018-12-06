package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.switchTo;

public class CreateNewMailPage {

    @FindBy(xpath = "//textarea[@data-original-name='To']")
    private WebElement addresseeTextArea;

    @FindBy(xpath = "//input[@class='b-input' ]")
    private WebElement subjectTextArea;

    @FindBy(css = "#tinymce")
    private WebElement bodyTextArea;

    @FindBy(xpath = "//div[@data-name='saveDraft']")
    private WebElement saveDraftButton;

    @FindBy(xpath = "//a[@class='toolbar__message_info__link']")
    private WebElement savedIdentificator;

    @FindBy(xpath = "//div[@class='b-toolbar__message']/a")
    private WebElement draftsFolderButton;

    @FindBy(xpath = ".//div[@data-name='send']/span")
    private WebElement sendButton;

    public boolean isMailSaved() {
        return savedIdentificator.isDisplayed();
    }

    public void fillAddressee(String addr) {
        addresseeTextArea.sendKeys(addr);
    }

    public void fillSubject(String subj) {
        subjectTextArea.sendKeys(subj);
    }

    public void fillBody(String content) {
        switchTo().frame(0);
        bodyTextArea.sendKeys(content);
        switchTo().defaultContent();
    }

    public void saveDraft() {
        saveDraftButton.click();
    }

    public void openDraftsFolder() {
        draftsFolderButton.click();
    }

    public void sendMail() {
        sendButton.click();
    }

}
